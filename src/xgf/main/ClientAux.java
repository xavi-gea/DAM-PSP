package xgf.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @author Xavi
 */
public class ClientAux implements Runnable {
	
	private BufferedReader bufferedReader;
	private ObjectInputStream objectInputStream;
	
	private Socket socket;
	
	private Boolean isMessageFromSelf;
	private String messageToShow;

	public ClientAux(Socket socket, BufferedReader bufferedReader, ObjectInputStream objectInputStream2) throws IOException {
		
		this.socket = socket;
		this.bufferedReader = bufferedReader;
		this.objectInputStream = objectInputStream2;
	}

	/**
	 * Listen for inputs provided by the server. Either from another user or this client user
	 */
	@Override
	public void run() {
		
		try {
			
			while (socket.isConnected()) {
				
				isMessageFromSelf = objectInputStream.readBoolean();
				messageToShow = bufferedReader.readLine();
				
				if (isMessageFromSelf) {
					
					System.out.println(messageToShow);
					
				}else {
					
					System.err.println(messageToShow);
				}
			}
			
		} catch (Exception e) {
			
			return;
			
		}finally {
			
			System.exit(0);
		}
	}
}
