package xgf.ejercicios;

import java.util.Scanner;

public class Ejercicio21 {

	public static void ejecutar(Scanner sc) {

		String[] personas = new String[5];

		System.out.println("Introduce el nombre de " + personas.length + " personas");

		for (int i = 0; i < personas.length; i++) {

			System.out.print("Persona " + (i + 1) + ": ");
			personas[i] = sc.next();
		}
	}
}
