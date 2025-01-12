package xgf.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
				
				objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			// send channel list to client
			printWriter.println(getChannelList());
			
			System.err.println("SERVIDOR >>> Esperando selección de canal y usuario...");
			
			try {
				
				getChannel();
				getUser();
				
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
			
			Channel.getServerChannel(chosenChannel).getUsers().add(Thread.currentThread());
			
			System.err.println("SERVIDOR >>> Usuario " + chosenUser + " ha seleccionado canal " + chosenChannel);
			
//			System.err.println("Channel id to print users: " + Channel.getServerChannel(chosenChannel).getId());
//			System.err.println("Users in channel: " + Channel.getServerChannel(chosenChannel).getUsers());
			
			try {
				
				getinput();
				
			} catch (IOException e) {
				
				System.err.println("SERVIDOR >>> Usuario " + chosenUser + " disconnected");
				
				try {
					
					clientSocket.close();
					
				} catch (IOException e1) {

					e1.printStackTrace();
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
		
		do {
				
			nextInput = bufferedReader.readLine();
			
			// handle different inputs
			// if Server.isCommand
			
			System.err.println("nextInput desde client: " + nextInput);
			
		} while (!nextInput.toLowerCase().equals("exit") && !clientSocket.isClosed());
		
	}

	private String getChannelList() {
		
		return getTimestamp() + "Canales disponibles: " + Server.channelNames.toString();
	}
	
	private String getTimestamp() {
		
		return (LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ": ");
	}

	
}
