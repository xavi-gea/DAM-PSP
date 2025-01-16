package xgf.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

//import javax.swing.JOptionPane;
//import javax.swing.SwingUtilities;
import javax.swing.*;

/**
 * @author Xavi
 */
public class Client {
	
	private static InputStream inputStream;
	private static InputStreamReader inputStreamReader;
	private static ObjectInputStream objectInputStream;
	
	private static OutputStream outputStream;
	private static BufferedReader bufferedReader;
	private static PrintWriter printWriter;
	
	private static String chosenChannel;
	private static String chosenUser;
	
	private static Scanner scanner = new Scanner(System.in);

	/**
	 * Main loop of the client. Asks for data related to the channel, user name and messages to send to the server
	 * @param args
	 */
	public static void main(String[] args) {
		
		Socket socket = new Socket();
		
		try {
			
			System.out.print("IP: ");
			String hostname = scanner.nextLine();
			
			System.out.print("Puerto: ");
			String port = scanner.nextLine();
			
			InetSocketAddress address = new InetSocketAddress(hostname, Integer.parseInt(port));
			
			socket.connect(address);
			
			inputStream = socket.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			objectInputStream = new ObjectInputStream(inputStream);
			
			outputStream = socket.getOutputStream();
			printWriter = new PrintWriter(outputStream, true);
			
			System.out.println(bufferedReader.readLine());
			
			askForChannel();
			askForUser();
			
		} catch (Exception e) {
			
			System.err.println("No ha podido conectarse y/o configurar la conexión con el servidor");
			System.err.println("Por favor, comprueba que la IP y puerto son correctos");
			System.exit(0);
		}
		
		try {
			
			ClientAux clientAux = new ClientAux(socket,bufferedReader,objectInputStream);
			
			Thread thread = new Thread(clientAux);
			
			thread.start();
				
			askForInput();
			
		} catch (Exception e) {
			
			System.err.println("Se ha perdido la conexión con el servidor");
			
		}finally {
			
			try {
				
				socket.close();
				
			} catch (IOException e) {
				
				return;
			}
			
			scanner.close();
			
			System.exit(0);
		}
	}
	
	/**
	 * Constantly asks for a channel until the server confirms that it is valid
	 * @throws IOException If the communication with the server returns an error
	 */
	private static void askForChannel() throws IOException {
		
		chosenChannel = "";
		
		Boolean channelExists = false;
		
		do {
			
			System.out.print("Selecciona un canal de entre los disponibles: ");
			chosenChannel = scanner.nextLine();
			
			printWriter.println(chosenChannel);
			
			channelExists = objectInputStream.readBoolean();
			
			if (!channelExists) {
				
				System.out.println("Ese canal no existe.");
			}
			
		} while (!channelExists);
	}

	/**
	 * Constantly asks for a user name until the server confirms that it is valid
	 * @throws IOException If the communication with the server returns an error
	 */
	private static void askForUser() throws IOException {
		
		chosenUser = "";
		
		Boolean userAlreadyExists = true;
		
		do {
			
			System.out.print("Introduce un nombre de usuario: ");
			chosenUser = scanner.nextLine();
			
			if (chosenUser.contains(" ")) {
				
				System.out.println("El nombre de usuario no puede contener espacios.");
				
			}else {
				
				printWriter.println(chosenUser);
				
				userAlreadyExists = objectInputStream.readBoolean();
				
				if(userAlreadyExists) {
					
					System.out.println("Ese usuario ya existe en el canal.");
				}
			}
			
		} while (userAlreadyExists);
	}	
	
	/**
	 * Constantly asks for user input unless you send "exit"
	 */
	private static void askForInput() {
		
		String nextInput = "";
		System.out.println("Pulsa ENTER para enviar mensajes...");
		
		do {
			
			if (scanner.nextLine().isEmpty()) {
				
				JDialog inputDialog = new JDialog();
				inputDialog.setAlwaysOnTop(true);
				
				nextInput = JOptionPane.showInputDialog(inputDialog,"Introduce 'exit' para cerrar la conexión");			
				
				if (nextInput != null) {
					
					printWriter.println(nextInput);
				}				
			}
			
		} while (!nextInput.toLowerCase().equals("exit"));
	}
}
