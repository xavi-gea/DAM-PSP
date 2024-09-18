package xgf.ejercicios;

import java.util.Scanner;

public class Ejercicio13 {

	public static void ejecutar(Scanner sc) {
		
		System.out.print("Grados centígrados: ");
		
		float gradosC = sc.nextFloat();
		double gradosF = (gradosC * 1.8) + 32;
		
		System.out.println("Grados fahrenheit equivalentes: " + String.format("%.1f",gradosF));
	}
}
