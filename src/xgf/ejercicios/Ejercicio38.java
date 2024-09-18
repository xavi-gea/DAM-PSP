package xgf.ejercicios;
import java.util.Scanner;

public class Ejercicio38 {

	public static void ejecutar(Scanner sc) {
		
		int numero1;
		int numero2;
		
		System.out.print("Número para comenzar intervalo: ");
		numero1 = sc.nextInt();
		
		System.out.print("Número para terminar intervalo: ");
		numero2 = sc.nextInt();
		
		long tiempoAlEmpezar = System.currentTimeMillis();
		
		int cursor = numero1;
		
		do {
			
			cursor++;
			System.out.println(cursor + " " + esPrimo(cursor));
			
		} while (cursor < numero2);
		
		long tiempoAlTerminar = System.currentTimeMillis();
		
		System.out.println("Para calcular los números primos se han tardado " + (tiempoAlTerminar - tiempoAlEmpezar) + " milisegundos");
	}

	/**
	 * Devuelve un string indicando si el número proporcionado es primo.
	 * @param numero
	 * @return String Devuelve "es primo" o "no es primo"
	 */
	private static String esPrimo(int numero) {

		if (numero < 2 || numero == 4) {
			
			return "es primo";
		}
		
		for (int i = 2; i < numero / 2; i++) {
			
			if (numero % i == 0) {
				
				return "no es primo";
			}
		}
		
		return "es primo";
	}
}
