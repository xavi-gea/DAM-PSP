package xgf.ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio12 {

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
					
					if (notaSolicitada < 5) {
						
						suspensos.add(notaSolicitada);
						continue;
					}
					
					if (notaSolicitada < 7) {
						
						aprobados.add(notaSolicitada);
						continue;
					}
					
					if (notaSolicitada < 9) {
						
						notables.add(notaSolicitada);
						continue;
					}
					
					if (notaSolicitada < 10) {
						
						sobresalientes.add(notaSolicitada);
						continue;
					}
					
					if (notaSolicitada == 10) {
						
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
}
