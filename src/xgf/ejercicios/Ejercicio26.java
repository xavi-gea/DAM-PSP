package xgf.ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio26 {

	public static void ejecutar(Scanner sc) {
		
		List<Integer> suspensos = new ArrayList<Integer>();
		List<Integer> aprobados = new ArrayList<Integer>();
		List<Integer> notables = new ArrayList<Integer>();
		List<Integer> sobresalientes = new ArrayList<Integer>();
		List<Integer> matriculas = new ArrayList<Integer>();
		
		for (int i = 1; i <= 10; i++) {
			
			int notaSolicitada = 0;
			
			do {
				
				System.out.print("Nota " + i + " (de 0 a 10): ");
				notaSolicitada = sc.nextInt();
				
				if (notaSolicitada >= 0 && notaSolicitada <= 10) {
					
					if(comprobarCalificacion(notaSolicitada,"suspenso")) {
						
						suspensos.add(notaSolicitada);
						continue;
					}
					
					if (comprobarCalificacion(notaSolicitada,"aprobado")) {
						
						aprobados.add(notaSolicitada);
						continue;
					}
					
					if (comprobarCalificacion(notaSolicitada,"notable")) {
						
						notables.add(notaSolicitada);
						continue;
					}
					
					if (comprobarCalificacion(notaSolicitada,"sobresaliente")) {
						
						sobresalientes.add(notaSolicitada);
						continue;
					}
					
					if (comprobarCalificacion(notaSolicitada,"matricula")) {
						
						matriculas.add(notaSolicitada);
						continue;
					}
				}
				
			} while (notaSolicitada < 0 || notaSolicitada > 10);
		}
		
		System.out.println(suspensos.size() + " suspensos");
		System.out.println(aprobados.size() + " aprobados");
		System.out.println(notables.size() + " notables");
		System.out.println(sobresalientes.size() + " sobresalientes");
		System.out.println(matriculas.size() + " matriculas");		
	}

	private static boolean comprobarCalificacion(int notaSolicitada, String calificacion) {

		if (calificacion.equals("suspenso") && notaSolicitada < 5) {
			
			return true;
		}
		
		if (calificacion.equals("aprobado") && notaSolicitada < 7) {
			
			return true;
		}
		
		if (calificacion.equals("notable") && notaSolicitada < 9) {
			
			return true;
		}
		
		if (calificacion.equals("sobresaliente") && notaSolicitada < 10) {
			
			return true;
		}
		
		if (calificacion.equals("matricula") && notaSolicitada == 10) {
			
			return true;
		}
		
		return false;
	}
}
