package xgf.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

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
			
			e.printStackTrace();
			return;
		}
		
		//System.exit(0);
	}
}
