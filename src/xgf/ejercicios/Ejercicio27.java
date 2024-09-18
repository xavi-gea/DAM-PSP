package xgf.ejercicios;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio27 {

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
		
		String letra = calcularLetraDNI(numeroDNI);
		
		if (letra != "") {
			
			System.out.println("El DNI con letra es: " + numeroDNI + letra);
		}
	}

	/**
	 * Devuelve la letra a la que corresponde el numeroDNI pasado
	 * @param numeroDNI DNI sin letra
	 * @return String Letra correspondiente
	 */
	private static String calcularLetraDNI(int numeroDNI) {
		
		int resto = numeroDNI % 23;
		
		switch (resto) {
		
			case 0: return "T";
			case 1: return  "R";
			case 2: return  "W";
			case 3: return  "A";
			case 4: return  "G";	
			case 5: return  "M";
			case 6: return  "Y";
			case 7: return  "F";
			case 8: return  "P";
			case 9: return  "D";
			case 10: return  "X";
			case 11: return  "B";
			case 12: return  "N";
			case 13: return  "J";
			case 14: return  "Z";
			case 15: return  "S";
			case 16: return  "Q";
			case 17: return  "V";
			case 18: return  "H";
			case 19: return  "L";
			case 20: return  "C";
			case 21: return  "K";
			case 22: return  "E";
			
			default:

				System.out.println("El formato del DNI sin letra " + numeroDNI + " no es correcto");
				return "";
		}
	}
}
