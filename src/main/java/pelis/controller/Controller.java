package pelis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pelis.model.Pelis;

@RestController
public class Controller {
	
	Pelis pelis = new Pelis();

	@GetMapping("/APIpelis/t")
	String getFilm(@RequestParam(value = "id") String filmID) {
		
		return pelis.getAllPelis();
		
//		if (filmID.equals("all")) {
//			
//			return pelis.getAllPelis();
//			
//		}else {
//			
//			return pelis.getPeli(filmID);
//		}
	}
}
