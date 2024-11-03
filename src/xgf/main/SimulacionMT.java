package xgf.main;

import java.io.File;
import java.time.LocalDateTime;

public class SimulacionMT implements Runnable {
	
	private String proteinStructureType;
	private int currentStructure;
	
	private double simulationResult;

	public SimulacionMT(String proteinStructureType, int currentStructure) {
		
		this.proteinStructureType = proteinStructureType;
		this.currentStructure = currentStructure;
	}
	
	@Override
	public void run() {
		
		long startNanoseconds = System.nanoTime();
		LocalDateTime startDateTime = LocalDateTime.now();
		
		simulationResult = simulation(Comunes.returnNumber(proteinStructureType));
		System.out.println(simulationResult);
		
		String startDateTimeFormatted = Comunes.getDateTimeFormatted(startDateTime, "yyyyMMdd_HHmmss_SS");
		
		File resultFile = new File(Comunes.getSimulationPathName("MT", proteinStructureType, currentStructure, startDateTimeFormatted));
		
		long endNanoseconds = System.nanoTime();
		LocalDateTime endDateTime = LocalDateTime.now();
		
		String endDateTimeFormatted = Comunes.getDateTimeFormatted(endDateTime, "yyyyMMdd_HHmmss_SS");
		
		long totalDuration = endNanoseconds - startNanoseconds;
		
		Simulador.setTotalDurationMT(Simulador.getTotalDurationMT() + totalDuration);
		
		String totalDurationSecondsMiliseconds = (totalDuration / 1000000000) + "_" + (totalDuration / 1000000);
		
		Comunes.createSimulationFile(startDateTimeFormatted, String.valueOf(simulationResult), resultFile, endDateTimeFormatted, totalDurationSecondsMiliseconds);
	}
	
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
