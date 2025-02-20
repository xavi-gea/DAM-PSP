package pelis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pelis.model.Pelis;

@RestController
public class Controller {
	
	Pelis pelis = new Pelis();

	@GetMapping("/APIpelis/t")
	public ResponseEntity<String> getFilm(@RequestParam(value = "id") String filmID) {
		
		String result = "";
		
		if (filmID.equals("all")) {
			
			result = pelis.getAllPelis();
			
		}else {
			
			result = pelis.getPeli(filmID);
		}
		
		if (result != null) {
			
			return ResponseEntity.status(HttpStatus.OK).body(result);
			
		}else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
		}
	}
	
	@PostMapping("/APIpelis/nuevaResenya")
	public ResponseEntity<Object> newReview(@RequestBody String filmReviewBody){
		
		boolean reviewWritten = false;
		
		reviewWritten = pelis.postFilmReview(filmReviewBody);
		
		if (reviewWritten) {
			
			return ResponseEntity.status(HttpStatus.OK).build();
			
		}else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PostMapping("/APIpelis/nuevaPeli")
	public ResponseEntity<Object> newPeli(@RequestBody String filmBody){
		
		boolean filmWritten = false;
		
		filmWritten = pelis.postFilm(filmBody);
		
		if (filmWritten) {
			
			return ResponseEntity.status(HttpStatus.OK).build();
			
		}else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
//	@PutMapping("/APIpelis/modifPeli")
//	public void updateUser(@RequestParam(value = "id") String filmID, @RequestBody String filmBody) {
//		// inside this method we have to update the film record
//	}
//	
//	@DeleteMapping("/APIpelis/{filmID}")
//    public ResponseEntity<Object> deletePeli(@PathVariable("filmID") String filmID) {
//
//		boolean filmDeleted = false;
//		
//		filmDeleted = pelis.removeFilm(filmID);
//		
//		if (filmDeleted) {
//			
//			return ResponseEntity.status(HttpStatus.OK).build();
//			
//		}else {
//			
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//		}
//    }
	
	// for best practices, it should be with PathVariable above
	@DeleteMapping("/APIpelis/deletPeli")
	public ResponseEntity<Object> deleteUser(@RequestParam(value = "id") String filmID) {

		boolean filmDeleted = false;
		
		filmDeleted = pelis.removeFilm(filmID);
		
		if (filmDeleted) {
			
			return ResponseEntity.status(HttpStatus.OK).build();
			
		}else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
