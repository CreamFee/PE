package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import org.math.plot.Plot2DPanel;

import main.Main;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class MainWindow {

	private JFrame frmPractica;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private Plot2DPanel plot;
	private JLabel lblNewLabel_6;
	private JTextField textField;
	private JTextField textField_6;
	private Main main;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmPractica.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public MainWindow(Main main) {
		this.main = main;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPractica = new JFrame();
		frmPractica.setTitle("Practica 1");
		frmPractica.setBounds(100, 100, 744, 515);
		frmPractica.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPractica.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Funcion");
		lblNewLabel.setBounds(22, 10, 69, 31);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmPractica.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Poblacion");
		lblNewLabel_1.setBounds(22, 71, 108, 31);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmPractica.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(22, 100, 96, 19);
		textField_1.setColumns(10);
		frmPractica.getContentPane().add(textField_1);
		
		JLabel lblNewLabel_2 = new JLabel("N Generaciones");
		lblNewLabel_2.setBounds(22, 123, 108, 31);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmPractica.getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(22, 150, 96, 19);
		textField_2.setColumns(10);
		frmPractica.getContentPane().add(textField_2);
		
		JLabel lblNewLabel_3 = new JLabel("% Cruce");
		lblNewLabel_3.setBounds(22, 179, 69, 31);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmPractica.getContentPane().add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(22, 206, 96, 19);
		textField_3.setColumns(10);
		frmPractica.getContentPane().add(textField_3);
		
		JLabel lblNewLabel_4 = new JLabel("% Mutacion");
		lblNewLabel_4.setBounds(22, 235, 108, 31);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmPractica.getContentPane().add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setBounds(22, 262, 96, 19);
		textField_4.setColumns(10);
		frmPractica.getContentPane().add(textField_4);
		
		JLabel lblNewLabel_5 = new JLabel("Precision");
		lblNewLabel_5.setBounds(22, 291, 96, 31);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmPractica.getContentPane().add(lblNewLabel_5);
		
		textField_5 = new JTextField();
		textField_5.setBounds(22, 320, 96, 19);
		textField_5.setColumns(10);
		frmPractica.getContentPane().add(textField_5);
		
		plot = new Plot2DPanel();
		plot.setBounds(158, 10, 562, 418);
		
		double[] avr = main.getAvr();
		double[] abs = main.getAbsMax();
		double[] max = main.getMax();
		double[] x = new double[100];
		for(int i = 0; i < 100; ++i) {
			x[i] = i;
		}
		
		plot.addLinePlot("Average", Color.GREEN, x, avr);
		plot.addLinePlot("Absoulute Max", Color.BLACK, x, abs);
		plot.addLinePlot("Maximums", Color.RED, x, max);
			
		frmPractica.getContentPane().add(plot);
		
		lblNewLabel_6 = new JLabel("Results:");
		lblNewLabel_6.setBounds(158, 439, 562, 29);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmPractica.getContentPane().add(lblNewLabel_6);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBox.setBounds(22, 40, 96, 21);
		frmPractica.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_5_1 = new JLabel("% Elitismo");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5_1.setBounds(22, 378, 96, 31);
		frmPractica.getContentPane().add(lblNewLabel_5_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(22, 407, 96, 19);
		frmPractica.getContentPane().add(textField);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Metodo de Seleccion", "Metodode Cruce", "Metodo de Mutacion"}));
		comboBox_1.setBounds(22, 363, 96, 21);
		frmPractica.getContentPane().add(comboBox_1);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("Metodo");
		lblNewLabel_5_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5_1_1.setBounds(22, 337, 96, 31);
		frmPractica.getContentPane().add(lblNewLabel_5_1_1);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(22, 459, 96, 19);
		frmPractica.getContentPane().add(textField_6);
		
		JLabel lblNewLabel_5_1_2 = new JLabel("Dimension (4 y 5)");
		lblNewLabel_5_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5_1_2.setBounds(22, 428, 126, 31);
		frmPractica.getContentPane().add(lblNewLabel_5_1_2);
		
		
		this.frmPractica.setVisible(true);
	}
}
