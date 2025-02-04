package estufa;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.sun.net.httpserver.HttpServer;

public class Servidor {

	public static void main(String[] args) {

		int port = 7777;
		
		InetSocketAddress socketAddress = new InetSocketAddress("localhost", port);
		
		HttpServer server;
		
		try {
			
			server = HttpServer.create(socketAddress, 0);
			
			server.createContext("/estufa", new GestorHTTP());
			
			ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
			server.setExecutor(executor);
			
			server.start();
			
			System.out.println("Server listening on port " + port);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
