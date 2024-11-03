package xgf.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Contains utility methods used by the rest of the classes.
 * @author Xavi
 * @version 1.0
 */
public class Comunes {

	/**
	 * Returns a provided date in the first parameter with a specified format 
	 * in the second parameter.
	 * @param dateToFormat date to be formatted.
	 * @param format format to use to format the date.
	 * @return formatted dateToFormat with the specified format.
	 */
	static String getDateTimeFormatted(LocalDateTime dateToFormat, String format) {
		
		return dateToFormat.format(DateTimeFormatter.ofPattern(format));
	}

	/**
	 * Returns a pathname that can be used to generate a File.
	 * @param simulationType type of simulation that will be executed.
	 * @param proteinStructureType type of protein that will be simulated.
	 * @param currentSimulation current simulation number from the total.
	 * @param dateTime date and time that denotes the start of the current simulation.
	 * @return pathname pathname that can be used to generate a File.
	 */
	static String getSimulationPathName(String simulationType, String proteinStructureType, int currentSimulation, String dateTime) {
		
		String pathName = "";
		pathName += "PROT_";
		pathName += simulationType + "_";
		pathName += proteinStructureType + "_";
		pathName += "n" + Integer.toString(currentSimulation) + "_";
		pathName += dateTime;
		pathName += ".sim";
	
		return pathName;
	}

	/**
	 * If the string provided can't be parsed to an Integer, it returns 0.
	 * @param number number to parse.
	 * @return the number provided or 0.
	 */
	static int returnNumber(String number) {
		
		try {
			
			return Integer.parseInt(number);
			
		} catch (NumberFormatException e) {
			
			return 0;
		}
	}

}
