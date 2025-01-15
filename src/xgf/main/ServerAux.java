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
			
			return;
		}
		
		printWriter.println(Channel.getServerChannelList());
		
		System.err.println("SERVIDOR >>> Esperando selección de canal y usuario...");
		
		try {
			
			getChannel();
			getUser();
			objectOutputStream.flush();
			
		} catch (IOException e) {
			
			System.err.println("SERVIDOR >>> El usuario nuevo se ha desconectado");
			
			try {
				
				clientSocket.close();
				
			} catch (IOException e1) {

				return;
			}	
		}
		
		try {
			
			Thread.currentThread().setName(chosenUser.toLowerCase());
			
			clientUser = new User(chosenUser, clientSocket, objectOutputStream);
			
			Channel.getServerChannel(chosenChannel).getUsers().add(clientUser);
			
			System.err.println("SERVIDOR >>> Usuario " + chosenUser + " ha seleccionado canal " + chosenChannel);
			
			getinput();
			
		} catch (IOException e) {
			
			System.err.println("SERVIDOR >>> Usuario " + chosenUser + " se ha desconectado del canal " + chosenChannel);
			
		} finally {
		
			Channel.getServerChannel(chosenChannel).getUsers().remove(clientUser);
			
			try {
				
				clientSocket.close();
				
			} catch (IOException e) {
				
				return;
			}
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
			
			System.err.println("SERVIDOR >>> " + chosenUser + " (canal " + chosenChannel + ") >>> " + nextInput);
			
			objectOutputStream.writeBoolean(true);
			objectOutputStream.flush();
			
			printWriter.println(Server.getTimestamp() + nextInput);
			
			if (Server.isCommand(nextInput)) {
				
				invokeCommand(nextInput);
				
			}else {
				
				writeToChannelUsers(nextInput, Channel.getServerChannel(chosenChannel));
			}
		}
	}

	private void writeToChannelUsers(String message, Channel channel) throws IOException {
		
		for (User user : channel.getUsers()) {
				
			if (!user.getName().equals(chosenUser)) {
				
				OutputStream userOutputStream = user.getSocket().getOutputStream();
				PrintWriter userPrintWriter = new PrintWriter(userOutputStream,true);
				ObjectOutputStream userObjectOutputStream = user.getObjectOutputStream();
				
				userObjectOutputStream.writeBoolean(false);
				userObjectOutputStream.flush();
				
				if (channel.getId().equals(chosenChannel)) {
					
					userPrintWriter.println(Server.getTimestamp() + chosenUser + " >>> " + message);
					
				}else {
					
					// string format?
					
//					String formatString = "%s(canal%d, %d) >>> %d";
//					
//					userPrintWriter.println(String.format(formatString, Server.getTimestamp(), channel.getId(), chosenUser,message));
					
					userPrintWriter.println(Server.getTimestamp() + "(canal" + channel.getId() + ", " + chosenUser + ") >>> " + message);
				}
			}
		}
	}

	private void invokeCommand(String nextInput) throws IOException {
		
		if (nextInput.startsWith("whois")) {
			
			objectOutputStream.writeBoolean(false);
			objectOutputStream.flush();
			
			printWriter.println(Channel.getChannelUsersToString(chosenChannel));
			
		}else if(nextInput.startsWith("channels")) {
			
			objectOutputStream.writeBoolean(false);
			objectOutputStream.flush();
			
			printWriter.println(Channel.getServerChannelList());
			
		}else if(nextInput.startsWith("exit")) {
			
			return;
		
		}else if(nextInput.startsWith("@canal") && nextInput.length() > 8) {
			
			String targetChannel = Character.toString(nextInput.charAt(6));
			
			if (Channel.channelExists(targetChannel)) {
				
				String message = nextInput.substring(8);
				
				writeToChannelUsers(message, Channel.getServerChannel(targetChannel));
			}
		}
	}
}
