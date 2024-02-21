package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import org.math.plot.Plot2DPanel;

public class MainWindow {

	private JFrame frmPractica;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private Plot2DPanel plot;
	private JLabel lblNewLabel_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
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
		
		JLabel lblNewLabel = new JLabel("x");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(22, 26, 69, 31);
		frmPractica.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(22, 68, 96, 19);
		frmPractica.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("x");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(22, 97, 69, 31);
		frmPractica.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(22, 139, 96, 19);
		frmPractica.getContentPane().add(textField_1);
		
		JLabel lblNewLabel_2 = new JLabel("x");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(22, 168, 69, 31);
		frmPractica.getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(22, 210, 96, 19);
		frmPractica.getContentPane().add(textField_2);
		
		JLabel lblNewLabel_3 = new JLabel("x");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(22, 239, 69, 31);
		frmPractica.getContentPane().add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(22, 281, 96, 19);
		frmPractica.getContentPane().add(textField_3);
		
		JLabel lblNewLabel_4 = new JLabel("x");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(22, 310, 69, 31);
		frmPractica.getContentPane().add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(22, 352, 96, 19);
		frmPractica.getContentPane().add(textField_4);
		
		JLabel lblNewLabel_5 = new JLabel("x");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(22, 381, 69, 31);
		frmPractica.getContentPane().add(lblNewLabel_5);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(22, 423, 96, 19);
		frmPractica.getContentPane().add(textField_5);
		
		plot = new Plot2DPanel();
		plot.setBounds(158, 10, 562, 418);
		frmPractica.getContentPane().add(plot);
		
		lblNewLabel_6 = new JLabel("Results:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(158, 439, 562, 29);
		frmPractica.getContentPane().add(lblNewLabel_6);
	}
}
