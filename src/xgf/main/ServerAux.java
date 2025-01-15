package xgf.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ServerAux implements Runnable {

	private Socket clientSocket;
	private List<Thread> threadList;
	
	private InputStream inputStream;
	private InputStreamReader inputStreamReader;
	
	private OutputStream outputStream;
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;
	private ObjectOutputStream objectOutputStream;
	
	private String chosenChannel;
	private String chosenUser;
	
	private User clientUser;

	public ServerAux(Socket clientSocket, List<Thread> threadList) {
		
		this.clientSocket = clientSocket;
		this.threadList = threadList;
	}

	@Override
	public void run() {
		
			try {
				
				inputStream = clientSocket.getInputStream();
				inputStreamReader = new InputStreamReader(inputStream);
				bufferedReader = new BufferedReader(inputStreamReader);
				
				outputStream = clientSocket.getOutputStream();
				printWriter = new PrintWriter(outputStream, true);
				objectOutputStream = new ObjectOutputStream(outputStream);
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			// send channel list to client
			printWriter.println(Channel.getServerChannelList());
			
			System.err.println("SERVIDOR >>> Esperando selección de canal y usuario...");
			
			try {
				
				getChannel();
				getUser();
				objectOutputStream.flush();
				//objectOutputStream.reset();
				
			} catch (IOException e) {
				
				System.err.println("SERVIDOR >>> " + chosenUser + " disconnected");
				
				try {
					
					clientSocket.close();
					
				} catch (IOException e1) {

					e1.printStackTrace();
				}	
			}			
			
//			 if the user does not exist in the channel, change the name of this thread with the channel number 
//			 and user name?
//			 an independent List for each one of the channels?
		
			Thread.currentThread().setName(chosenUser.toLowerCase());
			
			// add new user
			
			clientUser = new User(chosenUser, clientSocket, objectOutputStream);
			
			//Channel.getServerChannel(chosenChannel).getUsers().add(Thread.currentThread());
			Channel.getServerChannel(chosenChannel).getUsers().add(clientUser);
			
			System.err.println("SERVIDOR >>> Usuario " + chosenUser + " ha seleccionado canal " + chosenChannel);
			
//			System.err.println("Channel id to print users: " + Channel.getServerChannel(chosenChannel).getId());
//			System.err.println("Users in channel: " + Channel.getServerChannel(chosenChannel).getUsers());
			
			try {
				
				getinput();
				
				Channel.getServerChannel(chosenChannel).getUsers().remove(clientUser);
				
				clientSocket.close();
				
			} catch (IOException e) {
				
				System.err.println("SERVIDOR >>> Usuario " + chosenUser + " se ha desconectado del canal " + chosenChannel);
			}
	}

	private void getChannel() throws IOException {
		
		chosenChannel = "";
		
		Boolean chosenChannelExists = false;
		
		do {
				
			chosenChannel = bufferedReader.readLine();
			
			chosenChannelExists = Channel.channelExists(chosenChannel);
			
			objectOutputStream.writeBoolean(chosenChannelExists);
			objectOutputStream.flush();
			
		} while (!chosenChannelExists);
	}

	private void getUser() throws IOException {
		
		chosenUser = "";
		Boolean chosenUserExists = false;
		
		do {
				
			chosenUser = bufferedReader.readLine();
			
			chosenUserExists = Channel.userExistsInChannel(chosenUser, chosenChannel);
			
			objectOutputStream.writeBoolean(chosenUserExists);
			objectOutputStream.flush();
			
		} while (chosenUserExists);
	}

	private void getinput() throws IOException {
		
		String nextInput = "";
		
		while (!nextInput.toLowerCase().equals("exit") && !clientSocket.isClosed()) {
			
			nextInput = bufferedReader.readLine();
			
			// handle different inputs
			// if Server.isCommand
			
			//System.err.println("nextInput desde client: " + nextInput);
			
			// to each user in channel?, write:
			// declare local print writers grabbing thread socket??
			
			System.err.println("SERVIDOR >>> " + chosenUser + " (canal " + chosenChannel + ") >>> " + nextInput);
			
			System.out.println("clientsocket closed: " + clientSocket.isClosed());
			
			//objectOutputStream = new ObjectOutputStream(outputStream);
			
			objectOutputStream.writeBoolean(true);
			objectOutputStream.flush();
			
			printWriter.println(Server.getTimestamp() + nextInput);
			
			//objectOutputStream.reset();
			
			if (Server.isCommand(nextInput)) {
				
				invokeCommand(nextInput);
				
			}else {
				
				Channel thisChannel = Channel.getServerChannel(chosenChannel);
				
				for (User user : thisChannel.getUsers()) {
						
					if (!user.getName().equals(chosenUser)) {
						
						OutputStream userOutputStream = user.getSocket().getOutputStream();
						PrintWriter userPrintWriter = new PrintWriter(userOutputStream,true);
						ObjectOutputStream userObjectOutputStream = user.getObjectOutputStream();
						// ^ I shouldn't be doing a new ObjectOutputStream?
						// do I place it inside the user properties so I can directly grab it?
						
						userObjectOutputStream.writeBoolean(false);
						userObjectOutputStream.flush();
						//userObjectOutputStream.reset();
						
						userPrintWriter.println(Server.getTimestamp() + chosenUser + " >>> " + nextInput);
					}
					
					// how do I get sockets?
					// do I pass the socket of the client to the server?
					// clientAux listens for messages from serverAux 
					// inside Channel, make users be a list of Users?
					// and each of those users contain it's name and socket? (of the server)
				}
			}
		}
		
		// client should close itself?
		
	}

	private void invokeCommand(String nextInput) throws IOException {
		
		if (nextInput.equals("whois")) {
			
			objectOutputStream.writeBoolean(false);
			objectOutputStream.flush();
			
			printWriter.println(Channel.getChannelUsersToString(chosenChannel));
			
		}else if(nextInput.equals("channels")) {
			
			objectOutputStream.writeBoolean(false);
			objectOutputStream.flush();
			
			printWriter.println(Channel.getServerChannelList());
			
		}else if(nextInput.equals("exit")) {
			
			return;
		}
		
	}

//	private String getServerChannelList() {
//		
//		return getTimestamp() + "Canales disponibles: " + Server.channelNames.toString();
//	}
//	
//	private String getTimestamp() {
//		
//		return (LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ": ");
//	}

	public synchronized Socket getSocket() {
		
		return clientSocket;
	}
}
