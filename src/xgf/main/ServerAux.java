package xgf.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			// send channel list to client
			printWriter.println(getChannelList());
			
			System.err.println("SERVIDOR >>> Esperando selección de canal y usuario...");
			
			getChannel();
			
			getUser();
			
//			 if the user does not exist in the channel, change the name of this thread with the channel number 
//			 and user name?
//			 an independent List for each one of the channels?
		
			Thread.currentThread().setName(chosenUser);
			
			Channel.getServerChannel(chosenChannel).getUsers().add(Thread.currentThread());
			
			System.err.println("SERVIDOR >>> Usuario " + chosenUser + " ha seleccionado canal " + chosenChannel);
			
//			System.err.println("Channel id to print users: " + Channel.getServerChannel(chosenChannel).getId());
//			System.err.println("Users in channel: " + Channel.getServerChannel(chosenChannel).getUsers());
			
			getinput();
	}

	private void getUser() {
		
		chosenUser = "";
		Boolean chosenUserExists = false;
		
		do {
			
			try {
				
				chosenUser = bufferedReader.readLine();
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			chosenUserExists = Channel.userExistsInChannel(chosenUser, chosenChannel);
			
			if (chosenUserExists) {
				
				printWriter.println("true");
				
			}else {
				
				printWriter.println("false");
			}
			
		} while (chosenUserExists);
	}

	private void getChannel() {
		
		chosenChannel = "";
		
		Boolean chosenChannelExists = false;
		
		do {
			
			try {
				
				chosenChannel = bufferedReader.readLine();
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			chosenChannelExists = Channel.channelExists(chosenChannel);
			
			if (chosenChannelExists) {
				
				printWriter.println("true");
				
			}else {
				
				printWriter.println("false");
			}
			
		} while (!chosenChannelExists);
	}

	private void getinput() {
		
		String nextInput = "";
		
		do {
			
			try {
				
				nextInput = bufferedReader.readLine();
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			System.err.println("nextInput: " + nextInput);
			
		} while (!nextInput.toLowerCase().equals("exit"));
		
	}

	private String getChannelList() {
		
		return getTimestamp() + "Canales disponibles: " + Server.channelNames.toString();
	}
	
	private String getTimestamp() {
		
		return (LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ": ");
	}

	
}
