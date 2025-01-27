package xgf.main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.sun.net.httpserver.HttpServer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		int port = 5000;
		
		InetSocketAddress socketAddress = new InetSocketAddress("localhost", port);
		
		HttpServer server = HttpServer.create(socketAddress, 0);
		
		server.createContext("/test", new HttpManager());
		
		// localhost:5000/test?whatever=something
		
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
		server.setExecutor(executor);
		
		server.start();
		
		System.out.println("Server listening on port " + port);
	}

}
