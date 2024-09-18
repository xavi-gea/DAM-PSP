package xgf.ejercicios;

import java.util.ArrayList;
import java.util.Scanner;

import xgf.clases.Vehiculo;

public class Ejercicio28 {

	public static void ejecutar(Scanner sc) {
		
		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		String tipo = "";
		String marca = "";
		String modelo = "";
		
		for (int i = 0; i < 5; i++) {
			
			System.out.println("Datos del vehículo " + (i + 1));
			
			System.out.print("Tipo: ");
			tipo = sc.next();
			
			System.out.print("Marca: ");
			marca = sc.next();
			
			System.out.print("Modelo: ");
			modelo = sc.next();
			
			vehiculos.add(new Vehiculo(tipo, marca, modelo));
		}
		
		for (int i = 0; i < vehiculos.size(); i++) {
			
			System.out.println("------------------");
			System.out.println("Vehículo " + (i + 1) + ":");
			System.out.println("Tipo: " + vehiculos.get(i).getTipo());
			System.out.println("Marca: " + vehiculos.get(i).getMarca());
			System.out.println("Modelo: " + vehiculos.get(i).getModelo());
		}
	}
}
