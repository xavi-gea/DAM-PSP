package xgf.main;

import javax.swing.JFrame;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

/**
 * Creates the visual interface and runs the simulations.
 * @author Xavi
 * @version 1.0
 */
public class Simulador extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JSpinner spinnerPrimary;
	private JSpinner spinnerSecondary;
	private JSpinner spinnerTertiary;
	private JSpinner spinnerQuaternary;
	
	private JTextArea textTimeSpent;
	
	private String[] proteinStructures;
	static int currentSimulation = 0;
	
	List<Process> simulationProcesses = new ArrayList<>();
	List<Thread> simulationThreads = new ArrayList<>();
	
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
	 * Creates the visual frame and starts the multiprocess and threading.
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
			
			/**
			 * Starts the multiprocess and threading when btnSimulate is pressed.
			 */
			public void actionPerformed(ActionEvent e) {
				
				textTimeSpent.setText("");
				
				proteinStructures = new String[] {
						getSpinnerPrimary().getValue().toString(),
						getSpinnerSecondary().getValue().toString(),
						getSpinnerTertiary().getValue().toString(),
						getSpinnerQuaternary().getValue().toString()
				};
				
				try {
					
					startMP();
					startMT();
					
				} catch (IOException | InterruptedException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		
		btnSimulate.setBounds(218, 62, 296, 76);
		getContentPane().add(btnSimulate);
		
		textTimeSpent = new JTextArea();
		textTimeSpent.setBounds(76, 149, 607, 208);
		getContentPane().add(textTimeSpent);
	}

	/**
	 * Loops through proteinStructures and runs multiple processes with SimulacionMP.simulateMP. 
	 * When it's done, it ensures that all processes have finished and 
	 * appends the total time to textTimeSpent.
	 * @throws IOException when a process fails to be started.
	 * @throws InterruptedException when the current thread is interrupted by another thread 
	 * while it is waiting.
	 */
	private void startMP() throws IOException, InterruptedException {
		
		long startMPnanoseconds = System.nanoTime();
		
		int structureType = 1;
		
		for (String iterations : proteinStructures) {
			
			for (int i = 0; i < Integer.parseInt(iterations); i++) {
				
				currentSimulation++;
				
				simulationProcesses.add(SimulacionMP.simulateMP(Integer.toString(structureType), currentSimulation, LocalDateTime.now()));
			}
			
			structureType++;
		}
		
		for (Process process : simulationProcesses) {

			process.waitFor();
		}
		
		long endMPnanoseconds = System.nanoTime();
		
		long totalDurationMP = endMPnanoseconds - startMPnanoseconds;
		
		textTimeSpent.append("Se ha tardado " + String.valueOf(totalDurationMP / 1000000000) + " segundos y " + String.valueOf(totalDurationMP / 1000000) + " centésimas en simular con multiproceso" + "\n");
		
		simulationProcesses.clear();
	}

	/**
	 * Loops through proteinStructures and runs multiple threads with thread.start(). 
	 * When it's done, it ensures that all threads have finished and 
	 * appends the total time to textTimeSpent.
	 * @throws InterruptedException when any thread has interrupted the current thread 
	 * while it is waiting.
	 */
	private void startMT() throws InterruptedException {
		
		long startMTnanoseconds = System.nanoTime();
		
		int structureType = 1;
		
		for (String iterations : proteinStructures) {
			
			for (int i = 0; i < Integer.parseInt(iterations); i++) {
				
				currentSimulation++;
				
				Thread thread = new Thread(new SimulacionMT(Integer.toString(structureType), currentSimulation));
				
				thread.start();
				
				simulationThreads.add(thread);
			}
			
			structureType++;
		}
		
		for (Thread thread : simulationThreads) {

			thread.join();
		}
		
		long endMTnanoseconds = System.nanoTime();
		
		long totalDurationMT = endMTnanoseconds - startMTnanoseconds;
		
		textTimeSpent.append("Se ha tardado " + String.valueOf(totalDurationMT / 1000000000) + " segundos y " + String.valueOf(totalDurationMT / 1000000) + " centésimas en simular con multihilo");
		
		simulationThreads.clear();
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
