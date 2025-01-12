package xgf.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientAux implements Runnable {
	
	private static InputStream inputStream;
	private static InputStreamReader inputStreamReader;
	private static BufferedReader bufferedReader;
	
	private Socket socket;

	public ClientAux(Socket socket) {
		
		this.socket = socket;
	}

	@Override
	public void run() {
		
		try {
			
			inputStream = socket.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			
			while (true) {
				
				System.out.println(bufferedReader.readLine());
			}
			
		} catch (Exception e) {
			
			//System.out.println("Can't receive server messages");
			return;
		}
	}
}
