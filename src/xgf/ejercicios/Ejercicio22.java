package xgf.ejercicios;

import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio22 {

	public static void ejecutar(Scanner sc) {

		ArrayList<String> personas = new ArrayList<String>();

		String persona = "";

		do {

			System.out.print("Introduce el nombre de la siguiente persona (0 para finalizar): ");

			try {

				persona = sc.next();
				
				if (!persona.equals("0")) {
					
					personas.add(persona);
				}

			} catch (Exception e) {

				System.out.println("El nombre introducido no es correcto: " + e.getMessage());
			}

		} while (!persona.equals("0"));
	}
}
