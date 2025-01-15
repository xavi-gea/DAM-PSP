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

import xgf.main.commands.*;

public class Server {
	
	final static List<String> channelNames = getChannelNames();
	
	public static List<Channel> channelList = getChannelList();
	
	//static Map<String,Command> commandList = getCommandList();
	
	final static List<String> commandList = Arrays.asList(
			"whois",
			"channels",
			"exit"
	);
	
	
	//final static List<String> channelListNumbers = getChannelListNumbers();

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		System.err.println("SERVIDOR >>> Arrancando...");
		
		List<Thread> threadList = new ArrayList<Thread>();
		
		ServerSocket serverSocket = null;
		
		try {
			
			serverSocket = new ServerSocket(6000);
			
		} catch (IOException e) {
			
			System.out.println("SERVIDOR >>> Error al arrancar");
			return;
		}
		
		while (true) {
		
			System.err.println("SERVIDOR >>> Escuchando...");
			
			Socket clientSocket = serverSocket.accept();
			
			System.err.println("SERVIDOR >>> Conexión recibida, creando hilo...");
			
			ServerAux serverAux = new ServerAux(clientSocket, threadList);
			
			Thread thread = new Thread(serverAux);
			
			threadList.add(thread);
			
			thread.start();
		}
	}

	private static List<Channel> getChannelList() {
		
		List<Channel> serverChannelNames = new ArrayList<Channel>();
		
		for (String channelName : channelNames) {
			
			String[] channelNameSplit = channelName.split("-");
			
			serverChannelNames.add(new Channel(channelNameSplit[0],channelNameSplit[1]));
		}
		
		return serverChannelNames;
	}

	private static List<String> getChannelNames() {
		
		List<String> channelList = null;
		
		try {
			
			channelList = Files.readAllLines(Paths.get("." + File.separator + "channels.txt"));
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return channelList;
	}
	
//	private static Map<String,Command> getCommandList() {
//		
//		Map<String, Command> commandList = new HashMap<String, Command>();
//		commandList.put("whois", new WhoIs());
//		
//		return commandList;
//	}

	private static List<String> getChannelListNumbers() {
		
		List<String> channelNumberList = new ArrayList<String>();;
		
		for (String channel : channelNames) {
			
			channelNumberList.add(channel.substring(0, 1));
		}
		
		return channelNumberList;
	}

//	public static boolean isCommand(String nextInput) {
//		
//		Command command = commandList.get(nextInput);
//		
//		return command != null ? true : false;
//	}
	
	public static boolean isCommand(String nextInput) {

		return commandList.contains(nextInput) ? true : false;
	}
	
	public static String getTimestamp() {
		
		return (LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ": ");
	}

}
