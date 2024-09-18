package xgf.ejercicios;
import java.util.Scanner;

public class Ejercicio37 {

	public static void ejecutar(Scanner sc) {
		
		int anyosExperiencia = 0;

		System.out.print("Introduce tu nombre: ");
		sc.next();
		
		System.out.print("Años de experiencia: ");
		anyosExperiencia = sc.nextInt();

		mostrarNivelSalario(anyosExperiencia);
	}

	/**
	 * Utilizando anyosExperiencia devuelve un syso con un String distinto
	 * @param anyosExperiencia
	 */
	private static void mostrarNivelSalario(int anyosExperiencia) {
		
		String respuesta = "Desarrollador Junior L1 – 15000-18000";
		
		if (anyosExperiencia == 2) {
			
			respuesta = "Desarrollador Junior L2 – 18000-22000";
		
		}else if(anyosExperiencia >= 3 && anyosExperiencia <= 5){
			
			respuesta = "Desarrollador Senior L1 – 22000-28000";
			
		}else if(anyosExperiencia >= 5 && anyosExperiencia <= 8) {
			
			respuesta = "Desarrollador Senior L2 – 28000-36000";
		
		}else if(anyosExperiencia > 8) {
			
			respuesta = "Analista / Arquitecto. Salario a convenir en base a rol";
		}
		
		System.out.println(respuesta);
	}
}
