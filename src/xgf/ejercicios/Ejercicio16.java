package xgf.ejercicios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Ejercicio16 {

	public static void ejecutar(Scanner sc) {
		
		boolean fechaCorrecta = true;
		
		String fecha = "";
		
		do {
			
			System.out.print("Fecha de nacimiento (formato DD/MM/YYYY): ");
			fecha = sc.next();
			
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/uuuu");
			
			try {
				
				LocalDate.parse(fecha,formato);
				fechaCorrecta = true;
				
			} catch (DateTimeParseException dtpe) {
				
				System.out.println("Fecha de nacimiento incorrecta");
				fechaCorrecta = false;
			}
			
		} while (fechaCorrecta == false);
		
		if (fecha != "") {
			
			String[] numeros = fecha.split("/");
			
			System.out.println("Tu número de la suerte es: " + (Integer.parseInt(numeros[0]) + Integer.parseInt(numeros[1]) + Integer.parseInt(numeros[2])));
		}
	}
}
