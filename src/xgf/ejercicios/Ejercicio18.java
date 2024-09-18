package xgf.ejercicios;

import java.text.ParseException;
import java.util.Scanner;

public class Ejercicio18 {

	public static void ejecutar(Scanner sc) {

		boolean cumpleRequisitos = true;

		String password = "";

		try {

			System.out.print("Escribe una contraseña: ");
			password = sc.next();

			if (password.length() < 5) {

				throw new ParseException("Su longitud no respeta el mínimo de 5 caracteres", 0);
			}

			if (!password.matches(".*\\d.*")) {

				throw new ParseException("Debe contener como mínimo un cararacter numérico", 0);
			}

			if (!password.matches(".*[A-Z].*")) {

				throw new ParseException("Debe contener como mínimo un cararacter en mayuscula", 0);
			}

		} catch (ParseException e) {

			cumpleRequisitos = false;

			System.out.println("La contraseña no cumple los requisitos");
			System.out.println(e.getMessage());

		} finally {

			if (cumpleRequisitos) {

				System.out.println("La contraseña cumple los requisitos");
				
				System.out.print("Repite la contraseña: ");
				
				if (!password.equals(sc.next())) {
					
					System.out.println("La contraseña repetida no coincide con la primera");
					
				}else {
				
					System.out.println("Ambas contraseñas coinciden");
				}
			}
		}
	}
}
