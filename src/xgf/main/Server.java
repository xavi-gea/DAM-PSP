package xgf.main;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Xavi
 */
public class Server {
	
	final static List<String> channelNames = getChannelNames();
	
	public static List<Channel> channelList = getChannelList();
	
	final static List<String> commandList = Arrays.asList(
			"whois",
			"channels",
			"exit",
			"@canal"
	);

	/**
	 * Main loop that checks for new clients. Instantiates a new ServerAux class if connection is successful
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		System.err.println("SERVIDOR >>> Arrancando...");
		
		List<Thread> threadList = new ArrayList<Thread>();
		
		ServerSocket serverSocket = null;
		
		try {
			
			serverSocket = new ServerSocket(6000);
			
		} catch (IOException e) {
			
			System.out.println("SERVIDOR >>> Error al arrancar");
			System.exit(0);
		}
		
		while (true) {
		
			System.err.println("SERVIDOR >>> Escuchando...");
			
			Socket clientSocket;
			
			try {
				
				clientSocket = serverSocket.accept();
				
				System.err.println("SERVIDOR >>> Conexión recibida, creando hilo...");
				
				ServerAux serverAux = new ServerAux(clientSocket);
				
				Thread thread = new Thread(serverAux);
				
				threadList.add(thread);
				
				thread.start();
				
			} catch (IOException e) {

				System.err.println("SERVIDOR >>> No ha podido conectarse al cliente...");
			}
		}
	}

	/**
	 * Populates the list serverChannelNames with chanel names
	 * @return List with the available channels
	 */
	private static List<Channel> getChannelList() {
		
		List<Channel> serverChannelNames = new ArrayList<Channel>();
		
		for (String channelName : channelNames) {
			
			String[] channelNameSplit = channelName.split("-");
			
			serverChannelNames.add(new Channel(channelNameSplit[0]));
		}
		
		return serverChannelNames;
	}

	/**
	 * Reads the channels contained inside the file  channels.txt
	 * @return List of read channel names
	 */
	private static List<String> getChannelNames() {
		
		List<String> channelList = null;
		
		try {
			
			channelList = Files.readAllLines(Paths.get("." + File.separator + "channels.txt"));
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return channelList;
	}
	
	/**
	 * Returns true if the provided input is a valid command
	 * @param nextInput Input to check if it is a command
	 * @return If the provided message is identified as a valid command
	 */
	public static boolean isCommand(String nextInput) {
		
		for (String command : commandList) {
			
			if (nextInput.startsWith(command)) {
				
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Obtains the current date and time in hours and minutes returning it
	 * @return String with current date and time
	 */
	public static String getTimestamp() {
		
		return (LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ": ");
	}

}
