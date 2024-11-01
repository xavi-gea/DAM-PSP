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
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class Simulador extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JSpinner spinnerPrimary;
	private JSpinner spinnerSecondary;
	private JSpinner spinnerTertiary;
	private JSpinner spinnerQuaternary;
	
	String[] proteinStructures;
	
	
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
				
				proteinStructures = new String[] {
						getSpinnerPrimary().getValue().toString(),
						getSpinnerSecondary().getValue().toString(),
						getSpinnerTertiary().getValue().toString(),
						getSpinnerQuaternary().getValue().toString()
				};
				
				int structureType = 1;
				
				for (String iterations : proteinStructures) {
					
					for (int i = 0; i < Integer.parseInt(iterations); i++) {
						
						System.out.println("estructura de proteinas a simular: " + structureType);
						
						SimularMP(Integer.toString(structureType));
						
						//SimulacionMP.main(new String[] {structureType});
					}
					
					structureType++;
				}
			}
		});
		btnSimulate.setBounds(218, 62, 296, 76);
		getContentPane().add(btnSimulate);
		
		JTextArea textTimeSpent = new JTextArea();
		textTimeSpent.setBounds(76, 149, 607, 208);
		getContentPane().add(textTimeSpent);
	}
	
	private static void SimularMP(String proteinStructure) {
		
//		File directorioSumador = new File("...");
//		//File fichResultado = new File(fichResultados);
		String javaHome = System.getProperty("java.home");
		String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
		String classpath = System.getProperty("java.class.path");
		String className = "xgf.main.SimulacionMP";
		List<String> command = new ArrayList<>();
		command.add(javaBin);
		command.add("-cp");
		command.add(classpath);
		command.add(className);
		command.add(proteinStructure);
		
		ProcessBuilder builder = new ProcessBuilder(command);
		builder.inheritIO();
		
//		builder.directory(directorioSumador);
//		builder.redirectOutput(fichResultado);
		
		try {
			
			//Process p = builder.start();
			
			builder.start();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
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
