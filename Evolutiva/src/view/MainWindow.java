
package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import org.math.plot.Plot2DPanel;

import main.Main;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class MainWindow {

	private JFrame frmPractica;
	private JTextField textField_poblacion;
	private JTextField textField_generaciones;
	private JTextField textField_cruce;
	private JTextField textField_mutacion;
	private JTextField textField_precision;
	private Plot2DPanel plot;
	private JLabel label_RESULT;
	private JTextField textField_elitismo;
	private JTextField textField_dimension;
	private Main main;
	private JTextField textField_results;

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
		frmPractica.setBounds(100, 100, 937, 607);
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
		
		textField_poblacion = new JTextField();
		textField_poblacion.setText("100");
		textField_poblacion.setBounds(22, 100, 96, 19);
		textField_poblacion.setColumns(10);
		frmPractica.getContentPane().add(textField_poblacion);
		
		JLabel lblNewLabel_2 = new JLabel("N Generaciones");
		lblNewLabel_2.setBounds(22, 123, 108, 31);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmPractica.getContentPane().add(lblNewLabel_2);
		
		textField_generaciones = new JTextField();
		textField_generaciones.setText("100");
		textField_generaciones.setBounds(22, 150, 96, 19);
		textField_generaciones.setColumns(10);
		frmPractica.getContentPane().add(textField_generaciones);
		
		JLabel lblNewLabel_3 = new JLabel("% Cruce");
		lblNewLabel_3.setBounds(22, 179, 69, 31);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmPractica.getContentPane().add(lblNewLabel_3);
		
		textField_cruce = new JTextField();
		textField_cruce.setText("60");
		textField_cruce.setBounds(22, 206, 96, 19);
		textField_cruce.setColumns(10);
		frmPractica.getContentPane().add(textField_cruce);
		
		JLabel lblNewLabel_4 = new JLabel("% Mutacion");
		lblNewLabel_4.setBounds(22, 235, 108, 31);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmPractica.getContentPane().add(lblNewLabel_4);
		
		textField_mutacion = new JTextField();
		textField_mutacion.setText("5");
		textField_mutacion.setBounds(22, 262, 96, 19);
		textField_mutacion.setColumns(10);
		frmPractica.getContentPane().add(textField_mutacion);
		
		JLabel lblNewLabel_5 = new JLabel("Precision");
		lblNewLabel_5.setBounds(22, 291, 96, 31);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmPractica.getContentPane().add(lblNewLabel_5);
		
		textField_precision = new JTextField();
		textField_precision.setText("0.01");
		textField_precision.setBounds(22, 320, 96, 19);
		textField_precision.setColumns(10);
		frmPractica.getContentPane().add(textField_precision);
		
    	plot = new Plot2DPanel();
 		plot.setBounds(158, 10, 562, 418);
 		frmPractica.getContentPane().add(plot);
		
		label_RESULT = new JLabel("Results:");
		label_RESULT.setVerticalAlignment(SwingConstants.TOP);
		label_RESULT.setBounds(168, 438, 69, 21);
		label_RESULT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmPractica.getContentPane().add(label_RESULT);
		
		JComboBox comboBox_funcion = new JComboBox();
		comboBox_funcion.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBox_funcion.setBounds(22, 40, 96, 21);
		frmPractica.getContentPane().add(comboBox_funcion);
		
		JLabel lblNewLabel_5_1 = new JLabel("% Elitismo");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5_1.setBounds(22, 436, 96, 31);
		frmPractica.getContentPane().add(lblNewLabel_5_1);
		
		textField_elitismo = new JTextField();
		textField_elitismo.setText("2");
		textField_elitismo.setColumns(10);
		textField_elitismo.setBounds(22, 460, 96, 19);
		frmPractica.getContentPane().add(textField_elitismo);
		
		JComboBox comboBox_metodo = new JComboBox();
		comboBox_metodo.setModel(new DefaultComboBoxModel(new String[] {"Ruleta", "Torneo 1", "Torneo 2", "Estocastico", "Truncamiento", "Restos"}));
		comboBox_metodo.setBounds(22, 363, 96, 21);
		frmPractica.getContentPane().add(comboBox_metodo);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("Metodo de Seleccion");
		lblNewLabel_5_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5_1_1.setBounds(22, 337, 138, 31);
		frmPractica.getContentPane().add(lblNewLabel_5_1_1);
		
		textField_dimension = new JTextField();
		textField_dimension.setText("4");
		textField_dimension.setColumns(10);
		textField_dimension.setBounds(22, 514, 96, 19);
		frmPractica.getContentPane().add(textField_dimension);
		
		JLabel lblNewLabel_5_1_2 = new JLabel("Dimension (4 y 5)");
		lblNewLabel_5_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5_1_2.setBounds(22, 473, 126, 31);
		frmPractica.getContentPane().add(lblNewLabel_5_1_2);
		
		JButton btnNewButton = new JButton("EXEC");
		btnNewButton.setBounds(22, 543, 85, 21);
		frmPractica.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_5_2 = new JLabel("Tipo Cruce");
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5_2.setBounds(22, 380, 126, 31);
		frmPractica.getContentPane().add(lblNewLabel_5_2);
		
		JComboBox comboBox_cruce = new JComboBox();
		comboBox_cruce.setModel(new DefaultComboBoxModel(new String[] {"Monopunto", "Uniforme"}));
		comboBox_cruce.setBounds(22, 409, 96, 21);
		frmPractica.getContentPane().add(comboBox_cruce);
		
		textField_results = new JTextField();
		textField_results.setBounds(158, 469, 755, 59);
		frmPractica.getContentPane().add(textField_results);
		textField_results.setColumns(10);
		
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	//DELETE OTHER LINES
            	plot.removeAllPlots();
            	            	
            	Main.poblacion = Integer.parseInt(textField_poblacion.getText());
            	Main.generaciones = Integer.parseInt(textField_generaciones.getText());
            	Main.mutar = (double)Integer.parseInt(textField_mutacion.getText())/100;
            	Main.cruce = (double)Integer.parseInt(textField_cruce.getText())/100;
            	Main.precision = Double.parseDouble(textField_precision.getText());
            	
            	if (comboBox_cruce.getSelectedIndex() == 0) Main.tipoCruce = false; //solo hay dos opciones
            	else Main.tipoCruce = true;
            	
              	Main.elitismo = (double)Integer.parseInt(textField_elitismo.getText())/100;
            	Main.dimension = Integer.parseInt(textField_dimension.getText());
            	
            	switch ((String) comboBox_metodo.getSelectedItem()) {
            		case "Ruleta":
            			Main.seleccion = 1;
            			break;
            		case "Torneo 1":
            			Main.seleccion = 2;
            			break;
            		case "Torneo 2":
            			Main.seleccion = 3;
            			break;
            		case "Estocastico":
            			Main.seleccion = 4;
            			break;
            		case "Truncamiento":
            			Main.seleccion = 5;
            			break;
            		case "Restos":
            			Main.seleccion = 6;
            			break;
            	
            	}
 
            	
            	 switch (Integer.parseInt((String) comboBox_funcion.getSelectedItem())) {
            	 	case 1:
            	 		//llamar funcion 1
            	 		
            	 		main.set_funcion(1);
            	 		break;
            	 	case 2:
            	 		//llamar funcion 2
            	 		
            	 		
            	 		main.set_funcion(2);
            	 		break;
            	 	case 3:
            	 		//llamar funcion 3
            	 		
            	 		
            	 		main.set_funcion(3);
            	 		break;
            	 	case 4:
            	 		//llamar funcion 4
            	 		
            	 		
            	 		main.set_funcion(4);
            	 		break;
            	 	case 5:
            	 		//llamar funcion 5
            	 		
            	 		
            	 		main.set_funcion(5);
            	 		break;
            	 
                 }
            	 

         		
         		double[] avr = main.getAvr();
         		double[] abs = main.getAbsMax();
         		double[] max = main.getMax();
         		double[] x = new double[Main.generaciones];
         		for(int i = 0; i < Main.generaciones; ++i) {
         			x[i] = i;
         		}
         		
         		
         		plot.addLinePlot("Average", Color.GREEN, x, avr);
         		plot.addLinePlot("Absoulute Max", Color.BLACK, x, abs);
         		plot.addLinePlot("Maximums", Color.RED, x, max);
         			
         		frmPractica.getContentPane().add(plot);
         		
         		StringBuilder sb = new StringBuilder();
         		
         		double[] t;
         		
         		
         		for (int i = 0; i < main.getCont() + 1; ++i) {
         			t = main.getXfinal();
         			for (int j = 0; j < t.length; ++j) {
         				if (j == t.length - 1) sb.append(" X" + j + "[" + t[j] + "]");
         				else sb.append(" X" + j + "[" + t[j] + "]" + ", ");
         				sb.append("\n");
         			}
         		}
         		
         		textField_results.setText("Solucion: " + abs[Main.generaciones - 1] + "   " + sb);
            }
        };

        // Add the ActionListener to the button
        btnNewButton.addActionListener(listener);
		
		
		this.frmPractica.setVisible(true);
	}
}

