package xgf.ejercicios;

import java.util.Scanner;

public class Ejercicio05 {

	public static void ejecutar(Scanner sc) {
		
		boolean numerosIguales = false;
		
		do {
			
			System.out.print("Primer número a comparar: ");
			int numero1 = sc.nextInt();
			
			System.out.println();
			
			System.out.print("Segundo número a comparar: ");
			int numero2 = sc.nextInt();
			
			if (numero1 == numero2) {
				
				numerosIguales = true;
				
			}else {
				
				System.out.println(numero1 + " y " + numero2 + " no son iguales, se repite");
			}
			
		} while (numerosIguales == false);
	}
}
