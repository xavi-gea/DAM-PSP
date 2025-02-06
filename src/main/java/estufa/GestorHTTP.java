package estufa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GestorHTTP implements HttpHandler{
	
	int temperaturaActual = 15;
	int temperaturaTermostato = 15;

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		
		String requestMethod = exchange.getRequestMethod();
		
		if (requestMethod.equals("GET")) {
			
			handleGetResponse(exchange, handleGetRequest(exchange));
			
		}else if (requestMethod.equals("POST")) {
			
			try {
				
				handlePostResponse(exchange, handlePostRequest(exchange));
				
			} catch (InterruptedException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
	private String handleGetRequest(HttpExchange exchange) {
		
		return exchange.getRequestURI().toString().split("\\?")[1];
	}

	private void handleGetResponse(HttpExchange exchange, String requestParam) throws IOException {
		
		if (requestParam.equals("temperaturaActual")) {
			
			OutputStream outputStream = exchange.getResponseBody();
			
			//String htmlResponse = "<html><body>Hello " + requestParam + "</body></html>";
			
			String htmlResponse = "<html>";
			htmlResponse += "<body>";
			
			// WARNING, NO PASAR º
			// utilizar html &ordm
			
			htmlResponse += "<p>";
			htmlResponse += "Temperatura Actual: " + temperaturaActual + "&ordm";
			htmlResponse += "</p>";
			
			htmlResponse += "<p>";
			htmlResponse += "Temperatura Termostato: " + temperaturaTermostato + "&ordm";
			htmlResponse += "</p>";
			
			htmlResponse += "</body>";
			htmlResponse += "</html>";
			
			exchange.sendResponseHeaders(200, htmlResponse.length());
			
			outputStream.write(htmlResponse.getBytes());
			
			outputStream.flush();
			outputStream.close();
		}
	}
	
	private boolean handlePostRequest(HttpExchange exchange) throws IOException, InterruptedException {
		
		InputStream inputStream = exchange.getRequestBody();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		
		while (reader.ready()) {
			
			String[] instruction = reader.readLine().split("=");
			
			if (instruction[0].equals("setTemperatura")) {

				temperaturaTermostato = Integer.parseInt(instruction[1]);
				
				regularTemperatura();
				
				return true;
			}
		}
		
		return false;
	}

	private void handlePostResponse(HttpExchange exchange, boolean requestSuccessful) throws IOException {
		
		OutputStream outputStream = exchange.getResponseBody();
		
		String htmlResponse = "";
		
		if (requestSuccessful) {
			
			exchange.sendResponseHeaders(204, -1);
			
		}else {
			
			htmlResponse = "Error. Temperature not changed";
			
			exchange.sendResponseHeaders(400, htmlResponse.length());
		}
		
		outputStream.write(htmlResponse.getBytes());
		
		outputStream.flush();
		outputStream.close();
	}
	
	private void regularTemperatura() throws InterruptedException {
		
		while (temperaturaActual != temperaturaTermostato) {
			
			Thread.sleep(5000);
			
			if (temperaturaActual < temperaturaTermostato) {
				
				temperaturaActual++;
				
			}else {
				
				temperaturaActual--;
			}
		}
	}
}
