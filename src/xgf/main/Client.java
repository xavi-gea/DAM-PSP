package xgf.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Client {
	
	private static InputStream inputStream;
	private static InputStreamReader inputStreamReader;
	private static OutputStream outputStream;
	private static BufferedReader bufferedReader;
	private static PrintWriter printWriter;
	
	private static String chosenChannel;
	private static String chosenUser;
	
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		
//		System.out.print("IP: ");
//		String hostname = sc.nextLine();
//		
//		System.out.print("Puerto: ");
//		Integer port = sc.nextInt();
//		
//		InetSocketAddress address = new InetSocketAddress(hostname, port);
		
		InetSocketAddress address = new InetSocketAddress("localhost", 6000);
		
		Socket socket = new Socket();
		
		socket.connect(address);
		
		inputStream = socket.getInputStream();
		inputStreamReader = new InputStreamReader(inputStream);
		outputStream = socket.getOutputStream();
		bufferedReader = new BufferedReader(inputStreamReader);
		printWriter = new PrintWriter(outputStream, true);
			
		// read channel list
		System.out.println(bufferedReader.readLine());
		
		askForChannel();
		
		askForUser();
		
		ClientAux clientAux = new ClientAux(socket);
		
		Thread thread = new Thread(clientAux);
		
		thread.start();
			
		askForInput();
		
		socket.close();
		sc.close();
	}
	
	private static void askForChannel() throws IOException {
		
		chosenChannel = "";
		
		Boolean channelExists = false;
		
		do {
			
			System.out.print("Selecciona un canal de entre los disponibles: ");
			chosenChannel = sc.nextLine();
			
			// send channel
			printWriter.println(chosenChannel);
			
			channelExists = bufferedReader.readLine().equals("true") ? true : false;
			
			if (!channelExists) {
				
				System.out.println("Ese canal no existe.");
			}
			
		} while (!channelExists);
	}

	private static void askForUser() throws IOException {
		
		chosenUser = "";
		
		Boolean userAlreadyExists = true;
		
		do {
			
			System.out.print("Introduce un nombre de usuario: ");
			chosenUser = sc.nextLine();
			
			if (chosenUser.contains(" ")) {
				
				System.out.println("El nombre de usuario no puede contener espacios.");
				
			}else {
				
				// send user
				printWriter.println(chosenUser);
				
				userAlreadyExists = bufferedReader.readLine().equals("true") ? true : false;
				
				if(userAlreadyExists) {
					
					System.out.println("Ese usuario ya existe en el canal.");
				}
			}
			
		} while (userAlreadyExists);
	}

	private static void askForInput() {
		
		String nextInput = "";
		
		do {
		
			System.out.println("Pulsa ENTER para enviar mensajes...");
			
			if (sc.hasNextLine()) {
				
				sc.nextLine();
			}
			
			System.out.println("Is EDT: " + SwingUtilities.isEventDispatchThread());
			System.out.println("If false, bad news");
			
			nextInput = JOptionPane.showInputDialog("Introduce 'exit' para cerrar la conexión");
			
			printWriter.println(nextInput);
		
		} while (!nextInput.toLowerCase().equals("exit"));
	}

}
