package xgf.ejercicios;

import java.util.Scanner;

public class Ejercicio06 {

	public static void ejecutar(Scanner sc) {
		
		int sumaTotal = 0;
		
		for (int i = 1; i <= 5; i++) {
			
			System.out.print("Número " + i + " a sumar: ");
			int numeroSolicitado = sc.nextInt();
			
			sumaTotal += numeroSolicitado;
		}
		
		System.out.println("La suma total es: " + sumaTotal);
	}
}
