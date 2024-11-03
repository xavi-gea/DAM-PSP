package xgf.main;

import javax.swing.JFrame;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class Simulador extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JSpinner spinnerPrimary;
	private JSpinner spinnerSecondary;
	private JSpinner spinnerTertiary;
	private JSpinner spinnerQuaternary;
	
	private JTextArea textTimeSpent;
	
	private String[] proteinStructures;
	private static int currentStructure = 0;
	
	private static long totalDurationMP = 0;
	private static long totalDurationMT = 0;
	
	List<Thread> simulationThreads = new ArrayList<>();
	private static List<File> simulationFilenames = new ArrayList<File>();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Simulador frame = new Simulador();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the visual frame and 
	 */
	public Simulador() {
		
		setBounds(100, 100, 772, 407);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 736, 40);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 5, 151, 30);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Primarias");
		lblNewLabel.setBounds(10, 8, 68, 14);
		panel_1.add(lblNewLabel);
		
		spinnerPrimary = new JSpinner();
		spinnerPrimary.setBounds(88, 5, 57, 20);
		panel_1.add(spinnerPrimary);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(190, 5, 170, 30);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Secundarias");
		lblNewLabel_2.setBounds(10, 8, 75, 14);
		panel_2.add(lblNewLabel_2);
		
		spinnerSecondary = new JSpinner();
		spinnerSecondary.setBounds(95, 5, 58, 20);
		panel_2.add(spinnerSecondary);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(395, 5, 151, 30);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Terciarias");
		lblNewLabel_3.setBounds(5, 8, 61, 14);
		panel_3.add(lblNewLabel_3);
		
		spinnerTertiary = new JSpinner();
		spinnerTertiary.setBounds(76, 5, 61, 20);
		panel_3.add(spinnerTertiary);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(556, 5, 170, 30);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Cuaternarias");
		lblNewLabel_4.setBounds(5, 8, 84, 14);
		panel_4.add(lblNewLabel_4);
		
		spinnerQuaternary = new JSpinner();
		spinnerQuaternary.setBounds(99, 5, 61, 20);
		panel_4.add(spinnerQuaternary);
		
		JButton btnSimulate = new JButton("Simular");
		btnSimulate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textTimeSpent.setText("");
				
				proteinStructures = new String[] {
						getSpinnerPrimary().getValue().toString(),
						getSpinnerSecondary().getValue().toString(),
						getSpinnerTertiary().getValue().toString(),
						getSpinnerQuaternary().getValue().toString()
				};
				
				int structureType = 1;
				
				for (String iterations : proteinStructures) {
					
					for (int i = 0; i < Integer.parseInt(iterations); i++) {
						
						currentStructure++;
						simulateMP(Integer.toString(structureType));
					}
					
					structureType++;
				}
				
				while (!allfilesExist(getSimulationFilenames())) {
					
					// pass
				}
				
				simulationFilenames.clear();
				
				textTimeSpent.append("Se ha tardado " + String.valueOf(totalDurationMP / 1000000000) + " segundos y " + String.valueOf(totalDurationMP / 1000000) + " centésimas en simular con multiproceso" + "\n");
				
				// simulateMT
				
				structureType = 1;
				
				for (String iterations : proteinStructures) {
					
					for (int i = 0; i < Integer.parseInt(iterations); i++) {
						
						currentStructure++;
						
						Thread thread = new Thread(new SimulacionMT(Integer.toString(structureType), currentStructure));
						
						thread.start();
						
						simulationThreads.add(thread);
					}
					
					structureType++;
				}
				
				for (Thread thread : simulationThreads) {
					
					try {
						
						thread.join();
						
					} catch (InterruptedException exception) {
						
						exception.printStackTrace();
					}
				}
				
				simulationFilenames.clear();
				
				textTimeSpent.append("Se ha tardado " + String.valueOf(totalDurationMT / 1000000000) + " segundos y " + String.valueOf(totalDurationMT / 1000000) + " centésimas en simular con multihilo" + "\n");
			}
		});
		
		btnSimulate.setBounds(218, 62, 296, 76);
		getContentPane().add(btnSimulate);
		
		textTimeSpent = new JTextArea();
		textTimeSpent.setBounds(76, 149, 607, 208);
		getContentPane().add(textTimeSpent);
	}

	private boolean allfilesExist(List<File> files) {
		
		for (File file : files) {
			
			if (!file.exists()) {
				
				return false;
			}
		}
		
		return true;
	}

	private static void simulateMP(String proteinStructureType) {
		
		long startNanoseconds = System.nanoTime();
		LocalDateTime startDateTime = LocalDateTime.now();
		
		String startDateTimeFormatted = Comunes.getDateTimeFormatted(startDateTime, "yyyyMMdd_HHmmss_SS");
		
		String simulationResult = "";
		
		File directorioResultado = new File("...");
		
		File resultFile = new File(Comunes.getSimulationPathName("MP", proteinStructureType, currentStructure, startDateTimeFormatted));
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
		
		ProcessBuilder builder = new ProcessBuilder(command);
		
		//builder.inheritIO();
		builder.directory(directorioResultado);
		//builder.redirectOutput(resultFile);
		
		try {
			
			Process p = builder.start();
			simulationResult = new String(p.getInputStream().readAllBytes());
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		long endNanoseconds = System.nanoTime();
		LocalDateTime endDateTime = LocalDateTime.now();
		
		String endDateTimeFormatted = Comunes.getDateTimeFormatted(endDateTime, "yyyyMMdd_HHmmss_SS");
		
		long totalDuration = endNanoseconds - startNanoseconds;
		totalDurationMP += totalDuration;
		
		String totalDurationSecondsMiliseconds = (totalDuration / 1000000000) + "_" + (totalDuration / 1000000);
		
		Comunes.createSimulationFile(startDateTimeFormatted, simulationResult, resultFile, endDateTimeFormatted, totalDurationSecondsMiliseconds);
	}
	
	public static List<File> getSimulationFilenames() {
		return simulationFilenames;
	}
	
	public static long getTotalDurationMT() {
		return totalDurationMT;
	}

	public static void setTotalDurationMT(long totalDurationMT) {
		Simulador.totalDurationMT = totalDurationMT;
	}
	
	public static int getCurrentStructure() {
		return currentStructure;
	}

	public JSpinner getSpinnerPrimary() {
		return spinnerPrimary;
	}

	public JSpinner getSpinnerSecondary() {
		return spinnerSecondary;
	}

	public JSpinner getSpinnerTertiary() {
		return spinnerTertiary;
	}

	public JSpinner getSpinnerQuaternary() {
		return spinnerQuaternary;
	}
}
