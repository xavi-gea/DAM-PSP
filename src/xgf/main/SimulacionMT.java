package xgf.main;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Runs the simulation related to threading.
 * @author Xavi
 * @version 1.0
 */
public class SimulacionMT implements Runnable {
	
	private String proteinStructureType;
	private int currentSimulation;
	private double simulationResult;

	/**
	 * Constructor that sets local variables that will be used by a method of this class.
	 * @param proteinStructureType type of structure that will be simulated.
	 * @param currentSimulation current simulation number from the total.
	 */
	public SimulacionMT(String proteinStructureType, int currentSimulation) {
		
		this.proteinStructureType = proteinStructureType;
		this.currentSimulation = currentSimulation;
	}
	
	/**
	 * Executes when an instantiated thread is started. 
	 * It runs a simulation and generates a file with the results.
	 */
	@Override
	public void run() {
		
		long startNanoseconds = System.nanoTime();
		LocalDateTime startDateTime = LocalDateTime.now();
		
		simulationResult = simulation(Comunes.returnNumber(proteinStructureType));
		
		String startDateTimeFormatted = Comunes.getDateTimeFormatted(startDateTime, "yyyyMMdd_HHmmss_SS");
		
		File resultFile = new File(Comunes.getSimulationPathName("MT", proteinStructureType, currentSimulation, startDateTimeFormatted));
		
		long endNanoseconds = System.nanoTime();
		LocalDateTime endDateTime = LocalDateTime.now();
		
		String endDateTimeFormatted = Comunes.getDateTimeFormatted(endDateTime, "yyyyMMdd_HHmmss_SS");
		
		long totalDuration = endNanoseconds - startNanoseconds;
		
		String totalDurationSecondsMilliseconds = (totalDuration / 1000000000) + "_" + (totalDuration / 1000000);
		
		createSimulationFile(startDateTimeFormatted, String.valueOf(simulationResult), resultFile, endDateTimeFormatted, totalDurationSecondsMilliseconds);
	}
	
	/**
	 * Generates a file with the name and content specified in the parameters.
	 * @param startDateTimeFormatted date and time when the simulation has started.
	 * @param simulationResult result of the simulation.
	 * @param resultFile name and location of the file to generate.
	 * @param endDateTimeFormatted date and time when the simulation has finished.
	 * @param totalDurationSecondsMilliseconds duration of the simulation in seconds and milliseconds.
	 */
	static void createSimulationFile(String startDateTimeFormatted, String simulationResult, File resultFile, String endDateTimeFormatted, String totalDurationSecondsMilliseconds) {
		
		List<String> contentToWrite = new ArrayList<String>();
		contentToWrite.add(startDateTimeFormatted);
		contentToWrite.add("");
		contentToWrite.add(endDateTimeFormatted);
		contentToWrite.add("");
		contentToWrite.add(totalDurationSecondsMilliseconds);
		contentToWrite.add("");
		contentToWrite.add(simulationResult);
		
		try {
			
			Files.write(resultFile.toPath(), contentToWrite, StandardCharsets.UTF_8);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
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
