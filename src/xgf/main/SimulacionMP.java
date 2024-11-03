package xgf.main;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Runs the simulation related to multiprocess.
 * @author Xavi
 * @version 1.0
 */
public class SimulacionMP {

	/**
	 * Executes when a instantiated ProcessBuilder is started.
	 * It runs a simulation and returns its result.
	 * @param args type of structure and dateTime formatted to yyyyMMdd_HHmmss_SS.
	 */
	public static void main(String[] args) {
		
		long startNanoseconds = System.nanoTime();
			
		System.out.println(args[1]);
		
		System.out.println();
		
		String simulationResult = Double.toString(simulation(Comunes.returnNumber(args[0])));
		
		long endNanoseconds = System.nanoTime();
		LocalDateTime endDateTime = LocalDateTime.now();
		
		String endDateTimeFormatted = Comunes.getDateTimeFormatted(endDateTime, "yyyyMMdd_HHmmss_SS");
		
		System.out.println(endDateTimeFormatted);
		
		System.out.println();
		
		long totalDuration = endNanoseconds - startNanoseconds;
		
		String totalDurationSecondsMiliseconds = (totalDuration / 1000000000) + "_" + (totalDuration / 1000000);
		
		System.out.println(totalDurationSecondsMiliseconds);
		
		System.out.println();
		
		System.out.println(simulationResult);
	}
	
	/**
	 * Returns content from main and saves it into a file.
	 * @param proteinStructureType type of protein that will be simulated.
	 * @param currentSimulation current simulation number from the total.
	 * @param startDateTime date and time when the simulation has started.
	 * @return output output from main in the form of System.out.println.
	 * @throws IOException when a process fails to be started.
	 */
	static Process simulateMP(String proteinStructureType, int currentSimulation, LocalDateTime startDateTime) throws IOException {
		
		String startDateTimeFormatted = Comunes.getDateTimeFormatted(startDateTime, "yyyyMMdd_HHmmss_SS");
		
		File directorioResultado = new File("...");
		
		File resultFile = new File(Comunes.getSimulationPathName("MP", proteinStructureType, currentSimulation, startDateTimeFormatted));
		String javaHome = System.getProperty("java.home");
		String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
		String classpath = System.getProperty("java.class.path");
		String className = "xgf.main.SimulacionMP";
		
		List<String> command = new ArrayList<>();
		command.add(javaBin);
		command.add("-cp");
		command.add(classpath);
		command.add(className);
		command.add(proteinStructureType);
		command.add(startDateTimeFormatted);
		
		ProcessBuilder builder = new ProcessBuilder(command);
		
		builder.directory(directorioResultado);
		builder.inheritIO();
		builder.redirectOutput(resultFile);
		
		return builder.start();
	}

	/**
	 * Simulation to run with the specified type of structure.
	 * @param type type of structure to simulate.
	 * @return result of the simulation.
	 */
	public static double simulation(int type) {
		double calc = 0.0;
		double simulationTime = Math.pow(5, type);
		double startTime = System.currentTimeMillis();
		double endTime = startTime + simulationTime;
		while (System.currentTimeMillis() < endTime) {
			calc = Math.sin(Math.pow(Math.random(), 2));
		}
		return calc;
	}
}
