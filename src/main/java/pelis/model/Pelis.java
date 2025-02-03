package pelis.model;

import java.io.File;

import org.json.JSONObject;
//import java.nio.file.Files;

public class Pelis {
	
	static String pelisLocation = "./pelis";

	public String getAllPelis() {
		
		String result = "";
		
		if (isEmptyDirectory()) {
			
			result = new JSONObject().append("títulos", "").toString();
			
		}else {
			
			result = null;
		}
		
		return result;
	}

	private boolean isEmptyDirectory() {
		
		return new File("./pelis").list().length == 0 ? true : false;
	}

	
}
