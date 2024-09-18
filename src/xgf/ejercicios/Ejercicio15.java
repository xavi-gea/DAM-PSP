package xgf.ejercicios;

import java.util.Scanner;

public class Ejercicio15 {

	public static void ejecutar(Scanner sc) {
		
		System.out.print("Radio de una circunferencia: ");
		float radio = sc.nextFloat();
		
		double diametro = radio * 2;
		double area = Math.PI * Math.pow(radio, 2);
		double volumen = (4.0/3) * Math.PI * Math.pow(radio, 3);
		
		System.out.println("Diámetro en cm: " + String.format("%.3f",diametro));
		System.out.println("Área: " + String.format("%.3f",area));
		System.out.println("Volumen: " + String.format("%.3f",volumen));
	}
}
