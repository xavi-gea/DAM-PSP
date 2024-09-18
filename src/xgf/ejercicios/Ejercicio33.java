package xgf.ejercicios;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio33 {

	public static void ejecutar(Scanner sc) {
		
		boolean esNumeroValido = false;
		
		int maximo = 0;
		
		do {
			
			System.out.println("Número hasta el que se deben sumar los pares: ");
			
			try {
				
				maximo = sc.nextInt();
				
				esNumeroValido = true;
				
			} catch (InputMismatchException e) {
				
				System.out.println("El número introducido no es correcto: " + e.getMessage());
				sc.nextLine();
			}
			
		} while (esNumeroValido == false);
		
		System.out.println("Resultado final: " + sumaDePares(maximo));
	}

	/**
	 * Devuelve la suma de los números pares hasta el máximo especificado
	 * @param maximo
	 * @return int Suma de números pares
	 */
	private static int sumaDePares(int maximo) {
		
		int resto = 0;
		int numeroActual = 0;
		int suma = 0;
		
		do {
			
			resto = (numeroActual % 2);
			
			if (resto == 0) {
				
				System.out.println("Se suma " + numeroActual + " a " + suma);
				suma += numeroActual;
			}
			
			numeroActual++;
			
		} while (numeroActual <= maximo);
		
		return suma;
	}
}
