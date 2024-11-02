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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	 * Create the frame.
	 */
	public Simulador() {
		
		setBounds(100, 100, 772, 407);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(76, 11, 607, 40);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 5, 127, 30);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Primarias");
		lblNewLabel.setBounds(10, 8, 43, 14);
		panel_1.add(lblNewLabel);
		
		spinnerPrimary = new JSpinner();
		spinnerPrimary.setBounds(63, 5, 57, 20);
		panel_1.add(spinnerPrimary);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(147, 5, 152, 30);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Secundarias");
		lblNewLabel_2.setBounds(10, 8, 58, 14);
		panel_2.add(lblNewLabel_2);
		
		spinnerSecondary = new JSpinner();
		spinnerSecondary.setBounds(78, 5, 58, 20);
		panel_2.add(spinnerSecondary);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(309, 5, 127, 30);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Terciarias");
		lblNewLabel_3.setBounds(5, 8, 46, 14);
		panel_3.add(lblNewLabel_3);
		
		spinnerTertiary = new JSpinner();
		spinnerTertiary.setBounds(56, 5, 61, 20);
		panel_3.add(spinnerTertiary);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(446, 5, 151, 30);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Cuaternarias");
		lblNewLabel_4.setBounds(5, 8, 65, 14);
		panel_4.add(lblNewLabel_4);
		
		spinnerQuaternary = new JSpinner();
		spinnerQuaternary.setBounds(80, 5, 61, 20);
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
				
				textTimeSpent.append("Se ha tardado " + String.valueOf(totalDurationMP / 1000000000) + " segundos y " + String.valueOf(totalDurationMP / 1000000) + " centésimas en simular con multiproceso" + "\n");
				
				// simulateMT
				
				structureType = 1;
				currentStructure = 0;
				
				for (String iterations : proteinStructures) {
					
					for (int i = 0; i < Integer.parseInt(iterations); i++) {
						
						currentStructure++;
						simulateMT(Integer.toString(structureType));
					}
					
					structureType++;
				}
				
				textTimeSpent.append("Se ha tardado " + String.valueOf(totalDurationMT / 1000000000) + " segundos y " + String.valueOf(totalDurationMT / 1000000) + " centésimas en simular con multihilo" + "\n");
			}
		});
		
		btnSimulate.setBounds(218, 62, 296, 76);
		getContentPane().add(btnSimulate);
		
		textTimeSpent = new JTextArea();
		textTimeSpent.setBounds(76, 149, 607, 208);
		getContentPane().add(textTimeSpent);
	}

	private static void simulateMP(String proteinStructureType) {
		
		long startNanoseconds = System.nanoTime();
		
		LocalDateTime startDateTime = LocalDateTime.now();
		
		String startDateTimeFormatted = getDateTimeFormatted(startDateTime, "yyyyMMdd_HHmmss_SS");
		
		String simulationResult = "";
		
		File directorioResultado = new File("...");
		
		File resultFile = new File(getSimulationPathName("MP", proteinStructureType, startDateTimeFormatted));
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
		
		String endDateTimeFormatted = getDateTimeFormatted(endDateTime, "yyyyMMdd_HHmmss_SS");
		
		long totalDuration = endNanoseconds - startNanoseconds;
		totalDurationMP += totalDuration;
		
		String totalDurationSecondsMiliseconds = (totalDuration / 1000000000) + "_" + (totalDuration / 1000000);
		
		createSimulationFile(startDateTimeFormatted, simulationResult, resultFile, endDateTimeFormatted, totalDurationSecondsMiliseconds);
	}

	protected void simulateMT(String proteinStructureType) {
		
		SimulacionMT sMT = new SimulacionMT(proteinStructureType);
		
		Thread thread = new Thread(sMT);
		
		thread.start();
		long startNanoseconds = System.nanoTime();
		LocalDateTime startDateTime = LocalDateTime.now();
		
		String startDateTimeFormatted = getDateTimeFormatted(startDateTime, "yyyyMMdd_HHmmss_SS");
		
		File resultFile = new File(getSimulationPathName("MT", proteinStructureType, startDateTimeFormatted));
		
		try {
			
			thread.join();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		long endNanoseconds = System.nanoTime();
		LocalDateTime endDateTime = LocalDateTime.now();
		
		String endDateTimeFormatted = getDateTimeFormatted(endDateTime, "yyyyMMdd_HHmmss_SS");
		
		long totalDuration = endNanoseconds - startNanoseconds;
		totalDurationMT += totalDuration;
		
		String totalDurationSecondsMiliseconds = (totalDuration / 1000000000) + "_" + (totalDuration / 1000000);
		
		String simulationResult = String.valueOf(sMT.getSimulationResult());
		System.out.println("Resultado fuera de clase: " + simulationResult);
		
		createSimulationFile(startDateTimeFormatted, simulationResult, resultFile, endDateTimeFormatted, totalDurationSecondsMiliseconds);
	}
	
	private static void createSimulationFile(String startDateTimeFormatted, String simulationResult, File resultFile, String endDateTimeFormatted, String totalDurationSecondsMiliseconds) {
		
		List<String> contentToWrite = new ArrayList<String>();
		contentToWrite.add(startDateTimeFormatted);
		contentToWrite.add("");
		contentToWrite.add(endDateTimeFormatted);
		contentToWrite.add("");
		contentToWrite.add(totalDurationSecondsMiliseconds);
		contentToWrite.add("");
		contentToWrite.add(simulationResult);
		
		try {
			
			Files.write(resultFile.toPath(), contentToWrite, StandardCharsets.UTF_8);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	private static String getSimulationPathName(String simulationType, String proteinStructureType, String dateTime) {
		
		String pathName = "";
		pathName += "PROT_";
		pathName += simulationType + "_";
		pathName += proteinStructureType + "_";
		pathName += "n" + Integer.toString(currentStructure) + "_";
		pathName += dateTime;
		pathName += ".sim";

		return pathName;
	}

	private static String getDateTimeFormatted(LocalDateTime dateToFormat, String format) {
		
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
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
