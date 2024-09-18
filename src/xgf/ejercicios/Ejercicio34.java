package xgf.ejercicios;
import java.util.Scanner;

public class Ejercicio34 {

	public static void ejecutar(Scanner sc) {
		
		System.out.print("Número del que calcular el factorial: ");
		int numero = sc.nextInt();
		
		System.out.println("Factorial de " + numero + ": " + calcularFactorial(numero));
	}

	private static long calcularFactorial(int numero) {

		return (numero <= 1) ? numero : numero * calcularFactorial(numero - 1);
	}	
}
