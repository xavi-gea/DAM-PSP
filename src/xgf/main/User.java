package xgf.main;

import java.net.Socket;

public class User {

	private String name;
	private Socket socket;
	//private Thread thread;
	
	public User(String name, Socket socket) {
		super();
		this.name = name;
		this.socket = socket;
	}

	public String getName() {
		return name;
	}

	public Socket getSocket() {
		return socket;
	}
}
