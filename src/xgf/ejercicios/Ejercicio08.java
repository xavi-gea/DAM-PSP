package xgf.ejercicios;

import java.util.Scanner;

public class Ejercicio08 {

	public static void ejecutar(Scanner sc) {
		
		System.out.print("Número del 1 al 12: ");
		int numeroSolicitado = sc.nextInt();
		
		if (numeroSolicitado == 1) {
			
			System.out.println("Enero");
			
		}else if (numeroSolicitado == 2) {
			
			System.out.println("Febrero");
			
		}else if (numeroSolicitado == 3) {
			
			System.out.println("Marzo");
		
		}else if (numeroSolicitado == 4) {
			
			System.out.println("Abril");
			
		}else if (numeroSolicitado == 5) {
			
			System.out.println("Mayo");
			
		}else if (numeroSolicitado == 6) {
			
			System.out.println("Junio");
			
		}else if (numeroSolicitado == 7) {
			
			System.out.println("Julio");
			
		}else if (numeroSolicitado == 8) {
			
			System.out.println("Agosto");
			
		}else if (numeroSolicitado == 9) {
			
			System.out.println("Septiembre");
			
		}else if (numeroSolicitado == 10) {
			
			System.out.println("Octubre");
			
		}else if (numeroSolicitado == 11) {
			
			System.out.println("Noviembre");
			
		}else if (numeroSolicitado == 11) {
			
			System.out.println("Diciembre");	
		
		}else {
			
			System.out.println(numeroSolicitado + " no es del 1 al 12");
		}
	}
}
