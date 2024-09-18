package xgf.ejercicios;
import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio36 {

	public static void ejecutar(Scanner sc) {
		
		int[] numeros = new int[5];

		System.out.println("Introduce " + numeros.length + " números");

		for (int i = 0; i < numeros.length; i++) {

			System.out.print("Número " + (i + 1) + ": ");
			numeros[i] = sc.nextInt();
		}
		
		Arrays.sort(numeros);
		System.out.println("Se invierten.");
		
		int suma = 0;
		
		for (int i = 0; i < numeros.length; i++) {

			suma += numeros[i];
			System.out.println("Número " + (i + 1) + ": " + numeros[i]);
		}
		
		System.out.println("Suma: " + suma);
	}
}
