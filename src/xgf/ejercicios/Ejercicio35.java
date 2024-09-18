package xgf.ejercicios;
import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio35 {

	public static void ejecutar(Scanner sc) {
		
		int[] numeros = new int[5];

		System.out.println("Introduce " + numeros.length + " números");

		for (int i = 0; i < numeros.length; i++) {

			System.out.print("Número " + (i + 1) + ": ");
			numeros[i] = sc.nextInt();
		}
		
		Arrays.sort(numeros);
		
		System.out.println("El mayor número es: " + numeros[4]);
	}
}
