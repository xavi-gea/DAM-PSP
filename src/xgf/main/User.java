package xgf.main;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author Xavi
 */
public class User {

	private String name;
	private Socket socket;
	private ObjectOutputStream objectOutputStream;
	
	public User(String name, Socket socket, ObjectOutputStream objectOutputStream) {
		super();
		this.name = name;
		this.socket = socket;
		this.objectOutputStream = objectOutputStream;
	}

	public String getName() {
		return name;
	}

	public Socket getSocket() {
		return socket;
	}

	public ObjectOutputStream getObjectOutputStream() {
		return objectOutputStream;
	}
}
