package pelis.model;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class Pelis {
	
	private static File pelisLocation = new File("./pelis");

	public String getAllPelis() {
		
		String result = "";
		
		if (isEmptyDirectory()) {
			
			result = new JSONObject().append("titulos", "").toString();
			
		}else {
			
			JSONObject pelisList = new JSONObject();
			JSONObject peli = new JSONObject();
			
			List<String> fileLines = new ArrayList<String>();
			
			for (File file : pelisLocation.listFiles()) {
				
				if (file.canRead()) {
					
					fileLines.clear();
					
					try {
						
						fileLines = Files.readAllLines(file.toPath());
						
						if (fileLines.size() > 0) {
							
							peli.clear();
							peli.put("id", removeExtension(file.getName()));
							peli.put("titulo", fileLines.get(0).replace("Titulo: ", ""));
							
							pelisList.append("titulos", peli);
						}
						
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
			}
			
			result = pelisList.toString();
		}
		
		return result;
	}

	public String getPeli(String filmID) {
		
		List<String> fileLines = new ArrayList<String>();
		JSONObject peli = new JSONObject();
		
		for (File file : pelisLocation.listFiles()) {
			
			if (file.canRead()) {
				
				String fileName = removeExtension(file.getName());
				
				if (fileName.equals(filmID)) {
					
					try {
						
						fileLines = Files.readAllLines(file.toPath());
						
						if (fileLines.size() > 0) {
							
							peli.clear();
							peli.put("id", fileName);
							peli.put("titulo", fileLines.get(0).replace("Titulo: ", ""));
							
							fileLines.remove(0);
							
							peli.append("resenyas", fileLines);
							
							return peli.toString();
						}
						
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
			}
		}
		
		return null;
	}

	private static String removeExtension(String fileName) {
		
		String extensionArchivo = "";
		
		int extensionPosition = fileName.lastIndexOf(".");
		
		if (extensionPosition != -1) {
		
			extensionArchivo = fileName.substring(extensionPosition);
		}
		
		return fileName.replace(extensionArchivo,"");
	}

	private boolean isEmptyDirectory() {
		
		return pelisLocation.list().length == 0 ? true : false;
	}

	public boolean postFilmReview(String jsonContent) {
		
		JSONObject filmJson = new JSONObject(jsonContent);
		
		if (!filmJson.get("id").equals(null) && !filmJson.get("usuario").equals(null) && !filmJson.get("resenya").equals(null)) {
			
			if (!isEmptyDirectory()) {
				
				for (File file : pelisLocation.listFiles()) {
					
					if (file.canRead()) {
						
						String fileName = removeExtension(file.getName());
						
						if (fileName.equals(filmJson.get("id"))) {
							
							try {
								
								String review = "\n" + filmJson.get("usuario") + ": " + filmJson.get("resenya");
								
								Files.writeString(file.toPath(), review, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
								
								return true;
								
							} catch (IOException e) {

								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		
		return false;
	}

	public boolean postFilm(String jsonContent) {
		
		JSONObject filmJson = new JSONObject(jsonContent);
		
		int filmID = 1;
		
		if (!filmJson.get("titulo").equals(null)) {
			
			if (!isEmptyDirectory()) {
				
				filmID = pelisLocation.listFiles().length + 1;
			}
			
			String fileContent = "Título: " + filmJson.get("titulo");
			String fileName = Integer.toString(filmID) + ".txt";
			
			Path fileLocation = new File(pelisLocation + File.separator + fileName).toPath();
			
			try {
				
				Files.writeString(fileLocation, fileContent, StandardCharsets.UTF_8);
				
				return true;
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		return false;
	}

	
}
