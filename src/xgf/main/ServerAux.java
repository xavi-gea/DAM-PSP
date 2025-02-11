package xgf.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Xavi
 */
public class ServerAux implements Runnable {

	private Socket clientSocket;
	
	private InputStream inputStream;
	private InputStreamReader inputStreamReader;
	
	private OutputStream outputStream;
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;
	private ObjectOutputStream objectOutputStream;
	
	private String chosenChannel;
	private String chosenUser;
	
	private User clientUser;

	public ServerAux(Socket clientSocket) {
		
		this.clientSocket = clientSocket;
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
			
			System.err.println("SERVIDOR >>> No ha podido configurarse la conexión con el cliente");
			System.exit(0);
		}
		
		printWriter.println(Channel.getServerChannelList());
		
		System.err.println("SERVIDOR >>> Esperando selección de canal y usuario...");
		
		try {
			
			getChannel();
			getUser();
			objectOutputStream.flush();
			
		} catch (IOException e) {
			
			System.err.println("SERVIDOR >>> Usuario desconectado antes de elegir nombre y canal");
			
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
		
			try {
			
				Channel.getServerChannel(chosenChannel).getUsers().remove(clientUser);
				
				clientSocket.close();
				
			} catch (Exception e) {
				
				return;
			}
		}
	}

	/**
	 * Constantly expects a channel until it confirms that it is valid
	 * Expect the channel with bufferedReader.readLine()
	 * Sends the validation result to the client with objectOutputStream.writeBoolean(chosenChannelExists)
	 * @throws IOException If the communication with the client returns an error
	 */
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

	/**
	 * Constantly expects a user name until it confirms that it is valid
	 * Expect the user with bufferedReader.readLine()
	 * Sends the validation result to the client with objectOutputStream.writeBoolean(chosenUserExists)
	 * @throws IOException If the communication with the client returns an error
	 */
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

	/**
	 * Constantly expects input from the client until it reads "exit" or connection ends
	 * Expect the input with bufferedReader.readLine()
	 * Sends back what the client wrote
	 * Checks if the input contains a valid command and invoke it
	 * If not, send the input to the rest of the clients that are in the same channel
	 * @throws IOException If the communication with the client returns an error
	 */
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
				
				writeToUsersInChannel(nextInput, Channel.getServerChannel(chosenChannel));
			}
		}
	}

	
	/**
	 * Write the provided message to every user in a channel avoiding the user that originally sent it 
	 * @param message message to be sent
	 * @param channel channel to compare
	 * @throws IOException If the communication with the client returns an error
	 */
	private void writeToUsersInChannel(String message, Channel channel) throws IOException {
		
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
					
					userPrintWriter.println(Server.getTimestamp() + "(canal" + chosenChannel + ", " + chosenUser + ") >>> " + message);
				}
			}
		}
	}

	/**
	 * With the provided command, verify which it is and return relevant content
	 * @param nextInput Input to be checked
	 * @throws IOException If the communication with the client returns an error
	 */
	private void invokeCommand(String nextInput) throws IOException {
		
		if (nextInput.startsWith("whois")) {
			
			objectOutputStream.writeBoolean(false);
			objectOutputStream.flush();
			
			printWriter.println(Channel.getChannelUsersToString(chosenChannel));
			
		}else if(nextInput.startsWith("channels")) {
			
			objectOutputStream.writeBoolean(false);
			objectOutputStream.flush();
			
			printWriter.println(Channel.getServerChannelList());
			
		}else if(nextInput.startsWith("@canal") && nextInput.length() > 8) {
			
			String targetChannel = Character.toString(nextInput.charAt(6));
			
			if (Channel.channelExists(targetChannel)) {
				
				String message = nextInput.substring(8);
				
				writeToUsersInChannel(message, Channel.getServerChannel(targetChannel));
			}
		}
	}
}
