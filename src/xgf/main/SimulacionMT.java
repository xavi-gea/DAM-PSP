package xgf.main;

public class SimulacionMT implements Runnable {
	
	private String simulationType = "1";
	private double simulationResult;

	public SimulacionMT(String proteinStructureType) {
		
		this.simulationType = proteinStructureType;
	}
	
	@Override
	public void run() {
		
		this.simulationResult = simulation(returnNumber(simulationType));
		System.out.println("Resultado dentro de clase: " + this.simulationResult);
	}
	
	private static int returnNumber(String number) {
		
		try {
			
			return Integer.parseInt(number);
			
		} catch (NumberFormatException e) {
			
			return 0;
		}
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

	public double getSimulationResult() {
		return simulationResult;
	}
}
