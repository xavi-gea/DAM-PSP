package xgf.ejercicios;

import java.util.Scanner;

public class Ejercicio04 {

	public static void ejecutar (Scanner sc) {
		
		System.out.print("Primer número a comparar: ");
		int numero1 = sc.nextInt();
		
		System.out.println();
		
		System.out.print("Segundo número a comparar: ");
		int numero2 = sc.nextInt();
		
		if (numero1 > numero2) {
			
			System.out.println(numero1 + " es mayor que " + numero2);
			
		}else if (numero2 > numero1) {
			
			System.out.println(numero2 + " es mayor que " + numero1);
		
		}else if (numero1 == numero2) {
			
			System.out.println(numero1 + " y " + numero2 + " son iguales");
		}
	}
}
