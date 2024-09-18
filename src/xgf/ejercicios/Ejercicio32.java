package xgf.ejercicios;

import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio32 {

	public static void ejecutar(Scanner sc) {
		
		// Array

		String[] arrayPersonas = new String[6];

		System.out.println("Introduce el nombre de " + arrayPersonas.length + " compañeros (array)");

		for (int i = 0; i < arrayPersonas.length; i++) {

			System.out.print("Compañero " + (i + 1) + ": ");
			arrayPersonas[i] = sc.next();
		}
		
		// List
		
		ArrayList<String> listaPersonas = new ArrayList<String>();

		System.out.println("Introduce el nombre de 6 compañeros (lista)");

		for (int i = 0; i < 5; i++) {

			System.out.print("Compañero " + (i + 1) + ": ");
			listaPersonas.add(sc.next());
		}
	}
}
