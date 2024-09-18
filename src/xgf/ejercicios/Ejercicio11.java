package xgf.ejercicios;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio11 {

	public static void ejecutar(Scanner sc) {
		
		int numeroDNI = 0;
		boolean esNumeroDNIValido = false;
		
		do {
			
			System.out.print("DNI sin letra: ");
			
			try {
				
				numeroDNI = sc.nextInt();
				
				if (Integer.toString(numeroDNI).length() != 8) {
					
					throw new InputMismatchException("el tamaño debe ser de 8 digitos");
					
				}else {
					
					esNumeroDNIValido = true;
				}
				
			} catch (InputMismatchException e) {
				
				System.out.println("El número introducido no es correcto: " + e.getMessage());
				sc.nextLine();
			}
			
		} while (esNumeroDNIValido == false);
		
		int resto = numeroDNI % 23;
		String letra = "";
		
		switch (resto) {
		
			case 0: letra = "T"; break;
			case 1: letra = "R"; break;
			case 2: letra = "W"; break;
			case 3: letra = "A"; break;
			case 4: letra = "G"; break;	
			case 5: letra = "M"; break;
			case 6: letra = "Y"; break;
			case 7: letra = "F"; break;
			case 8: letra = "P"; break;	
			case 9: letra = "D"; break;
			case 10: letra = "X"; break;
			case 11: letra = "B"; break;
			case 12: letra = "N"; break;
			case 13: letra = "J"; break;
			case 14: letra = "Z"; break;
			case 15: letra = "S"; break;
			case 16: letra = "Q"; break;
			case 17: letra = "V"; break;
			case 18: letra = "H"; break;
			case 19: letra = "L"; break;
			case 20: letra = "C"; break;
			case 21: letra = "K"; break;
			case 22: letra = "E"; break;
			
			default:

				System.out.println("El formato del DNI sin letra " + numeroDNI + " no es correcto");
			}
		
		if (letra != "") {
			
			System.out.println("El DNI con letra es: " + numeroDNI + letra);
		}
	}
}
