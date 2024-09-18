package xgf.ejercicios;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Ejercicio25 {

	public static void ejecutar(Scanner sc) {

		Random generador = new Random();

		int numero1 = pedirNumero(sc, 1);
		int numero2 = pedirNumero(sc, 2);
		int numero3 = pedirNumero(sc, 3);
		
		if (numero1 == generador.nextInt(1, 11) && 
			numero2 == generador.nextInt(1, 11) && 
			numero3 == generador.nextInt(1, 11)) {

			System.out.println("¡Enhorabuena!");
			mostrarPremios(sc);
			
		}else {
			
			System.out.println("Mejor suerte la próxima vez...");
		}
	}

	private static int pedirNumero(Scanner sc, int orden) {

		int numero = 0;
		boolean esNumeroValido = false;

		do {

			System.out.print("Número " + orden + " entre 1 y 10: ");

			try {

				numero = sc.nextInt();

				if (numero < 1 || numero > 10) {

					esNumeroValido = false;

				} else {

					esNumeroValido = true;
				}

			} catch (InputMismatchException e) {

				System.out.println("El número introducido no es correcto: " + e.getMessage());
				sc.nextLine();

				esNumeroValido = false;
			}

		} while (esNumeroValido == false);

		return numero;
	}

	private static void mostrarPremios(Scanner sc) {

		int premioElegido = 0;
		boolean esPremioValido = true;

		do {

			System.out.println("1. Peluche");
			System.out.println("2. Colgante");
			System.out.println("3. Figura");
			System.out.print("Elige un premio de arriba escribiendo del 1 al 3: ");

			try {

				premioElegido = sc.nextInt();

				switch (premioElegido) {

				case 1: {
					System.out.println("Has obtenido un peluche");
					esPremioValido = true;
					break;
				}
				case 2: {
					System.out.println("Has obtenido un colgante");
					esPremioValido = true;
					break;
				}
				case 3: {
					System.out.println("Has obtenido una figura");
					esPremioValido = true;
					break;
				}
				default:

					esPremioValido = false;
					throw new InputMismatchException("debe ser entre 1 y 3");
				}

			} catch (InputMismatchException e) {

				System.out.println("El número introducido no es correcto: " + e.getMessage());
				sc.nextLine();

				esPremioValido = false;
			}

		} while (esPremioValido == false);

	}
}
