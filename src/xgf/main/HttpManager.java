package xgf.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HttpManager implements HttpHandler{

	@Override
	public void handle(HttpExchange exchange) throws IOException {

		String requestMethod = exchange.getRequestMethod();
		
		if (requestMethod.equals("GET")) {
			
			handleGetResponse(exchange, handleGetRequest(exchange));
			
		}else if (requestMethod.equals("POST")) {
			
			handlePostResponse(exchange, handlePostRequest(exchange));
		}
	}

	private String handleGetRequest(HttpExchange exchange) {
		
		return exchange.getRequestURI().toString().split("\\?")[1].split("=")[1];
		
		// split[1]: whatever=something
		// split[1]: something
	}

	private void handleGetResponse(HttpExchange exchange, String requestParam) throws IOException {
		
		OutputStream outputStream = exchange.getResponseBody();
		
		String htmlResponse = "<html><body>Hello " + requestParam + "</body></html>";
		
		exchange.sendResponseHeaders(200, htmlResponse.length());
		
		outputStream.write(htmlResponse.getBytes());
		
		outputStream.flush();
		outputStream.close();
	}

	private String handlePostRequest(HttpExchange exchange) throws IOException {
		
		InputStream inputStream = exchange.getRequestBody();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		
		String postString = "";
		
		while (reader.ready()) {
			
			postString += reader.readLine() + "\n";
		}
		
		return postString;
	}

	private void handlePostResponse(HttpExchange exchange, String requestParam) throws IOException {
		
		OutputStream outputStream = exchange.getResponseBody();
		
		String htmlResponse = "Post request response";
		
		exchange.sendResponseHeaders(201, htmlResponse.length());
		
		outputStream.write(htmlResponse.getBytes());
		
		outputStream.flush();
		outputStream.close();
	}
}
