package xgf.main;

import java.net.Socket;

public class ClientAux implements Runnable {
	
	private Socket socket;

	public ClientAux(Socket socket) {
		
		this.socket = socket;
	}

	@Override
	public void run() {
		
		System.out.println("clientaux in new thread");
		
	}

}
