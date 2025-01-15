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
				
				//System.out.println("Inside ClientAux while");
				
				// if message is from another user, system.err?
				// send boolean from server to know if message comes from ServerAux with same socket?
				
				// problem is that from serverAux I've already contacted this method with a objectOutput?
				//"The underlying problem is that you are using a new ObjectOutputStream to write to a stream that you have already used a prior ObjectOutputStream to write to"
				
				isMessageFromSelf = objectInputStream.readBoolean();
				messageToShow = bufferedReader.readLine();
				
				if (isMessageFromSelf) {
					
					System.out.println(messageToShow);
					
				}else {
					
					System.err.println(messageToShow);
				}				
				
				//System.out.println("After ClientAux while");
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			//System.out.println("Can't receive server messages");
			return;
		}
		
		//System.exit(0);
	}
	
//	public synchronized Socket getSocket() {
//		
//		return socket;
//	}
}
