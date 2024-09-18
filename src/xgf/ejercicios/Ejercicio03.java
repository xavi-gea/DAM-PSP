package xgf.ejercicios;
import java.util.Scanner;

public class Ejercicio03 {

	public static void ejecutar(Scanner sc) {
		
		System.out.print("Primer número a sumar: ");
		int numero1 = sc.nextInt();
		
		System.out.println();
		
		System.out.print("Segundo número a sumar: ");
		int numero2 = sc.nextInt();
		
		System.out.println("El resultado de la suma: " + (numero1 + numero2));
	}
}