package xgf.main;

public class SimulacionMP {

	public static void main(String[] args) {
		
		for (int i = 0; i < args.length; i++) {
			
			System.out.println(simulation(returnNumber(args[i])));
		}
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
}
