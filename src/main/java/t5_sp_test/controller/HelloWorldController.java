package t5_sp_test.controller;

//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@RequestMapping("/hello")
	String home() {
		
		return "Hello World!";
	}
	
	@RequestMapping("/bye")
	String bye() {
		
		return "Bye!";
	}
	
	@GetMapping("/greeting")
	String greeting(@RequestParam(value = "name") String strName) {

		return "Hello " + strName + "!";
	}
	
//	@GetMapping("/greeting")
//	public ResponseEntity<String> greeting(@RequestParam(value="name") String strName){
//		
//		String response = "Hello " + strName;
//		
//		return ResponseEntity.status(HttpStatus.OK).body(response);
//	}
}
