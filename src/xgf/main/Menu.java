package xgf.main;

import java.util.Scanner;
import xgf.ejercicios.*;

/**
 * Se encarga de contener el método que muestra el menú
 */
public class Menu {
	
	/**
	 * Se encarga demostrar el menú
	 * @return void Muestra los ejercicios hechos y ejecuta las distintas clases dependiendo de la opción elegida
	 */
	public static void mostrarMenu () {
		
		boolean salir = false;
		
		String[] opciones = {
				"Salir del programa",
				"A partir de dos números (introducidos en el código) muestre su suma por pantalla.",
				"Leer un nombre desde teclado y mostrar por pantalla un mensaje tipo 'Hola xxxxx'.",
				"Leer dos números desde teclado y mostrar la suma por pantalla",
				"Leer dos números desde teclado y mostrar el menor y el mayor en pantalla. Si son iguales deberá mostrar un mensaje indicándolo.",
				"Leer dos números desde teclado en un bucle repetitivo. El bucle deberá finalizar cuando los números leídos sean iguales.",
				"Leer cinco números desde teclado y muestra la suma por pantalla.",
				"Resolver el ejercicio anterior empleando otra estructura de bucle.",
				"Leer por teclado un número del 1 al 12 e indicar por pantalla a qué mes del año en texto corresponde.",
				"Resolver el ejercicio anterior empleando otra estructura condicional.",
				"Modificación del ejercicio anterior que implementa un control de la entrada por teclado (admite sólo números del 1 al 12).",
				"Programa que dado un DNI (sin letra) por teclado devuelve el DNI con la letra correcta.",
				"Programa que dadas 10 notas introducidas por teclado (valores de 0 a 10), las agrupa en suspensos, aprobados, notables, sobresalientes y matrícula, y muestra por pantalla cuantas notas hay en cada grupo.",
				"Programa que lee un valor de grados centígrados desde teclado y lo muestra en grados Fahrenheit con una precisión de 1 decimal.",
				"Programa que lee el valor del radio de una circunferencia y devuelve por pantalla el diámetro y el área con una precisión de 3 decimales.",
				"Amplia el programa anterior devolviendo el volumen de una esfera definida por el mismo radio.",
				"Dada la fecha de nacimiento de una persona devuelva su número de la suerte.",
				"Controlar los requisitos de una contraseña introducida por teclado (mínimo 5 caracteres, 1 número y 1 letra mayúscula).",
				"Amplía el programa anterior haciendo que el usuario repita la contraseña y comprobando que lo ha hecho correctamente.",
				"Mostrar por pantalla los caracteres ASCII (código y carácter).",
				"Amplía el programa anterior incluyendo también los caracteres de ASCII extendido.",
				"Almacenar en una estructura el nombre de 5 personas que se introducen por teclado de uno en uno.",
				"Modifica el programa anterior para almacenar en una estructura el nombre de un número cualquiera de personas, introducidos por teclado de uno en uno. La condición de finalización es introducir un 0 por teclado.",
				"Amplía el programa anterior para que, una vez tenga almacenados los nombres en una variable, los muestre por pantalla de uno en uno de la siguiente forma: primera línea: “1. Primer nombre”, segunda línea: “2. Segundo nombre”,…",
				"Toma como parámetro de entrada (en la llamada al programa) un número entre 1 y 10, obtenga de forma aleatorio un número (también entre 1 y 10), compare ambos números, los muestre por pantalla y de un premio (a elegir) si coinciden.",
				"Amplía el programa anterior para que permita apostar 3 números a la vez.",
				"Modificación sobre el programa de las notas (ejercicio 12). Implementa la funcionalidad de determinar la calificación (suspenso, aprobado,…) en un método aparte (fuera del método “main”)",
				"De la misma forma que en el ejercicio anterior, Implementa en un método adicional el cálculo de la letra del DNI (ejercicio 11) para que sea llamado desde “main”.",
				"Generar 5 objetos de clase “Vehículo”. Cada vehículo tendrá tres atributos: tipo (coche, motocicleta,…), marca y modelo. Una vez creados, el programa los mostrará por pantalla.",
				"Admitir una lista de longitud indeterminada de vehículos.",
				"Genera archivos ejecutables (.JAR) de algunos ejercicios y ejecútalos por línea de comandos.",
				"Utiliza una clase que tenga un método “sayHello” que saque por consola “Hola Mundo”. La clase puede tener el nombre que consideres.",
				"Array de elementos que contenga el nombre de 6 compañeros de clase y haz que se escriban por la consola en líneas consecutivas. Ahora haz lo mismo, pero empleando un objeto de tipo lista.",
				"Sumar números pares hasta el número que acepta como parámetro y devuelva el resultado de la suma.",
				"Calcular el factorial del número 15, en menos de 5 instrucciones.",
				"Aceptar un array o una lista de elementos y devuelva el mayor de ellos.",
				"Pedir 5 números enteros (sin validación de momento), mostrarlos por consola en orden inverso y devolver la suma de todos los números proporcionados.",
				"Pedir por teclado el nombre y los años de experiencia como desarrollador de software y mostrar el nivel y el salario en base al siguiente criterio:",
				"Pedir por teclado 2 números como extremos de un intervalo. Luego imprimir por pantalla todos los números entre ese intervalo, indicando junto al número si es un número primo o no. Al terminar de mostrar los números por pantalla, mostrar un mensaje indicando el tiempo consumido en la ejecución del método."
		};
		
		Scanner sc = new Scanner(System.in);
		
		do {
			
			System.out.println("-------------------------------");
			
			for (int i = 0; i < opciones.length; i++) {
				
				System.out.println(i + ". " + opciones[i]);
			}
			
			System.out.println("-------------------------------");
			
			System.out.print("Elije una de las opciones anteriores: ");
			int opcion = sc.nextInt();
			
			switch (opcion) {
			
				case 0: {
					
					salir = true;
					break;
				}
				
				case 1: {
					
					Ejercicio01.ejecutar();
					break;
				}
				
				case 2: {
					
					Ejercicio02.ejecutar(sc);
					break;
				}
				
				case 3: {
					
					Ejercicio03.ejecutar(sc);
					break;
				}
				
				case 4: {
					
					Ejercicio04.ejecutar(sc);
					break;
				}
				
				case 5: {
					
					Ejercicio05.ejecutar(sc);
					break;
				}
				
				case 6: {
					
					Ejercicio06.ejecutar(sc);
					break;
				}
				
				case 7: {
					
					Ejercicio07.ejecutar(sc);
					break;
				}
				
				case 8: {
					
					Ejercicio08.ejecutar(sc);
					break;
				}
				
				case 9: {
					
					Ejercicio09.ejecutar(sc);
					break;
				}
				
				case 10: {
					
					Ejercicio10.ejecutar(sc);
					break;
				}
				
				case 11: {
					
					Ejercicio11.ejecutar(sc);
					break;
				}
				
				case 12: {
					
					Ejercicio12.ejecutar(sc);
					break;
				}
				
				case 13: {
					
					Ejercicio13.ejecutar(sc);
					break;
				}
				
				case 14: {
					
					Ejercicio14.ejecutar(sc);
					break;
				}
				
				case 15: {
					
					Ejercicio15.ejecutar(sc);
					break;
				}
				
				case 16: {
					
					Ejercicio16.ejecutar(sc);
					break;
				}
				
				case 17: {
					
					Ejercicio17.ejecutar(sc);
					break;
				}
				
				case 18: {
					
					Ejercicio18.ejecutar(sc);
					break;
				}
				
				case 19: {
					
					Ejercicio19.ejecutar();
					break;
				}
				
				case 20: {
					
					Ejercicio20.ejecutar();
					break;
				}
				
				case 21: {
					
					Ejercicio21.ejecutar(sc);
					break;
				}
				
				case 22: {
					
					Ejercicio22.ejecutar(sc);
					break;
				}
				
				case 23: {
					
					Ejercicio23.ejecutar(sc);
					break;
				}
				
				case 24: {
					
					Ejercicio24.ejecutar(sc);
					break;
				}
				
				case 25: {
					
					Ejercicio25.ejecutar(sc);
					break;
				}
				
				case 26: {
					
					Ejercicio26.ejecutar(sc);
					break;
				}
				
				case 27: {
					
					Ejercicio27.ejecutar(sc);
					break;
				}
				
				case 28: {
					
					Ejercicio28.ejecutar(sc);
					break;
				}
				
				case 29: {
					
					Ejercicio29.ejecutar(sc);
					break;
				}
				
				case 30: {
					
					Ejercicio30.ejecutar();
					break;
				}
				
				case 31: {
					
					Ejercicio31.ejecutar();
					break;
				}
				
				case 32: {
					
					Ejercicio32.ejecutar(sc);
					break;
				}
				
				case 33: {
					
					Ejercicio33.ejecutar(sc);
					break;
				}
				
				case 34: {
					
					Ejercicio34.ejecutar(sc);
					break;
				}
				
				case 35: {
					
					Ejercicio35.ejecutar(sc);
					break;
				}
				
				case 36: {
					
					Ejercicio36.ejecutar(sc);
					break;
				}
				
				case 37: {
					
					Ejercicio37.ejecutar(sc);
					break;
				}
				
				case 38: {
					
					Ejercicio38.ejecutar(sc);
					break;
				}
				
				default:
					
					salir = true;
			}
			
		} while (salir == false);
		
		sc.close();
		System.out.print("Se ha salido del programa");
	}
}
