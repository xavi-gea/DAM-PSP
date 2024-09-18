package xgf.ejercicios;

import java.util.Scanner;

public class Ejercicio07 {

	public static void ejecutar(Scanner sc) {
			
		int sumaTotal = 0;
		
		int contador = 1;
		
		while (contador <= 5) {
			
			System.out.print("Número " + contador + " a sumar: ");
			int numeroSolicitado = sc.nextInt();
			
			sumaTotal += numeroSolicitado;
			
			contador++;
		}
		
		System.out.println("La suma total es: " + sumaTotal);
	}
}
