
package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JTextField;

import org.math.plot.Plot2DPanel;

import main.Main;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTable;

public class MainWindow {

	private JFrame frmPractica;
	private JTextField textField_poblacion;
	private JTextField textField_generaciones;
	private JTextField textField_cruce;
	private JTextField textField_mutacion;
	private Plot2DPanel plot;
	private JLabel label_RESULT;
	private JTextField textField_elitismo;
	private Main main;
	private JTextField datos_entrad;
	private JTable table;
	private JTable table_1;

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
		frmPractica.setTitle("Practica 2");
		frmPractica.setBounds(100, 100, 1278, 631);
		frmPractica.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPractica.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Poblacion");
		lblNewLabel_1.setBounds(22, 22, 108, 31);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmPractica.getContentPane().add(lblNewLabel_1);
		
		textField_poblacion = new JTextField();
		textField_poblacion.setText("100");
		textField_poblacion.setBounds(22, 63, 96, 19);
		textField_poblacion.setColumns(10);
		frmPractica.getContentPane().add(textField_poblacion);
		
		JLabel lblNewLabel_2 = new JLabel("N Generaciones");
		lblNewLabel_2.setBounds(22, 92, 108, 31);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmPractica.getContentPane().add(lblNewLabel_2);
		
		textField_generaciones = new JTextField();
		textField_generaciones.setText("100");
		textField_generaciones.setBounds(22, 133, 96, 19);
		textField_generaciones.setColumns(10);
		frmPractica.getContentPane().add(textField_generaciones);
		
		JLabel lblNewLabel_3 = new JLabel("% Cruce");
		lblNewLabel_3.setBounds(22, 162, 69, 31);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmPractica.getContentPane().add(lblNewLabel_3);
		
		textField_cruce = new JTextField();
		textField_cruce.setText("60");
		textField_cruce.setBounds(22, 206, 96, 19);
		textField_cruce.setColumns(10);
		frmPractica.getContentPane().add(textField_cruce);
		
		JLabel lblNewLabel_4 = new JLabel("% Mutacion");
		lblNewLabel_4.setBounds(22, 231, 108, 31);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmPractica.getContentPane().add(lblNewLabel_4);
		
		textField_mutacion = new JTextField();
		textField_mutacion.setText("5");
		textField_mutacion.setBounds(22, 272, 96, 19);
		textField_mutacion.setColumns(10);
		frmPractica.getContentPane().add(textField_mutacion);
		
    	plot = new Plot2DPanel();
 		plot.setBounds(158, 10, 562, 418);
 		frmPractica.getContentPane().add(plot);
		
		label_RESULT = new JLabel("Results:");
		label_RESULT.setVerticalAlignment(SwingConstants.TOP);
		label_RESULT.setBounds(742, 151, 69, 21);
		label_RESULT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmPractica.getContentPane().add(label_RESULT);
		
		JLabel lblNewLabel_5_1 = new JLabel("% Elitismo");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5_1.setBounds(22, 490, 96, 31);
		frmPractica.getContentPane().add(lblNewLabel_5_1);
		
		textField_elitismo = new JTextField();
		textField_elitismo.setText("2");
		textField_elitismo.setColumns(10);
		textField_elitismo.setBounds(22, 531, 96, 19);
		frmPractica.getContentPane().add(textField_elitismo);
		
		JComboBox comboBox_metodo = new JComboBox();
		comboBox_metodo.setModel(new DefaultComboBoxModel(new String[] {"Ruleta", "Torneo Det", "Torneo Prob", "Estocastico", "Truncamiento", "Restos"}));
		comboBox_metodo.setBounds(22, 387, 96, 21);
		frmPractica.getContentPane().add(comboBox_metodo);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("Metodo de Seleccion");
		lblNewLabel_5_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5_1_1.setBounds(22, 347, 138, 31);
		frmPractica.getContentPane().add(lblNewLabel_5_1_1);
		
		JButton btnNewButton = new JButton("EXEC");
		btnNewButton.setBounds(158, 465, 108, 41);
		frmPractica.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_5_2 = new JLabel("Tipo Cruce");
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5_2.setBounds(22, 418, 126, 31);
		frmPractica.getContentPane().add(lblNewLabel_5_2);
		
		JComboBox comboBox_cruce = new JComboBox();
		comboBox_cruce.setModel(new DefaultComboBoxModel(new String[] {"Monopunto", "Uniforme", "Arimetrico", "BLX"}));
		comboBox_cruce.setBounds(22, 459, 96, 21);
		frmPractica.getContentPane().add(comboBox_cruce);
		
		JLabel lblNewLabel_6 = new JLabel("FILE NAMES:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_6.setBounds(740, 22, 117, 50);
		frmPractica.getContentPane().add(lblNewLabel_6);
		
		datos_entrad = new JTextField();
		datos_entrad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		datos_entrad.setText("TEL1.txt vuelos1.txt ");
		datos_entrad.setBounds(730, 72, 225, 31);
		frmPractica.getContentPane().add(datos_entrad);
		datos_entrad.setColumns(10);
		

		
		JComboBox comboBox_metodo_1 = new JComboBox();
		comboBox_metodo_1.setModel(new DefaultComboBoxModel(new String[] {"Inserción", "Intercambio", "Inversión", "Heurística", "Método propio"}));
		comboBox_metodo_1.setBounds(22, 330, 96, 21);
		frmPractica.getContentPane().add(comboBox_metodo_1);
		
		JLabel lblNewLabel_5_1_1_1 = new JLabel("Tipo Mutación");
		lblNewLabel_5_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5_1_1_1.setBounds(22, 290, 138, 31);
		frmPractica.getContentPane().add(lblNewLabel_5_1_1_1);
		
		table_1 = new JTable();
		table_1.setBounds(882, 368, 1, 1);
		frmPractica.getContentPane().add(table_1);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(752, 182, 395, 21);
		frmPractica.getContentPane().add(lblNewLabel);
		
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	
            	//DELETE OTHER LINES
            	plot.removeAllPlots();
            	
            	//datos de entrada
            	
            	//VUELOS PRIMERO LUEGO AVIONES
            	
            	Map mapaAvionesinit = new TreeMap<Integer, String[]>();
            	Map mapaTELinit = new TreeMap<Integer, List<Integer>>();
            	
            	String filenames = datos_entrad.getText();
                String[] fileNamesArray = filenames.split(" ");

                if (fileNamesArray.length != 2) {
                    //solo puede haber 2 ficheros
                	JOptionPane.showMessageDialog(null, "Solo dos ficheros separados por un espacio, primero TEL, luego vuelos");
                    return;
                }

                String telFileName = fileNamesArray[0];
                String sepFileName = fileNamesArray[1];

            
                try (BufferedReader br = new BufferedReader(new FileReader(telFileName))) {
                	String line;
                    int columnCount = -1;
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split("\\s+");
                        if (columnCount == -1) {
                            columnCount = parts.length;
                        }
                        if (parts.length == columnCount) {
                            for (int i = 0; i < parts.length; i++) {
                                List<Integer> values = (List<Integer>) mapaTELinit.getOrDefault(i + 1, new ArrayList<>());
                                values.add(Integer.parseInt(parts[i]));
                                mapaTELinit.put(i + 1, values);
                            }
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                try (BufferedReader br = new BufferedReader(new FileReader(sepFileName))) {
                    String line;
                    int cont = 1;
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split("\\s+");
                        if (parts.length == 2) {
                        	mapaAvionesinit.put(cont++, parts);
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            	
            	
                Main.mapaAviones = mapaAvionesinit;
                Main.mapaTEL = mapaTELinit;
            	            	
            	Main.poblacion = Integer.parseInt(textField_poblacion.getText());
            	Main.generaciones = Integer.parseInt(textField_generaciones.getText());
            	Main.mutar = (double)Integer.parseInt(textField_mutacion.getText())/100;
            	Main.cruce = (double)Integer.parseInt(textField_cruce.getText())/100;
            	
            	Main.tipoCruce = comboBox_cruce.getSelectedIndex(); //0 monopunto, 1 uniforme, 2 arimetrico, 3 BLX
              	Main.elitismo = (double)Integer.parseInt(textField_elitismo.getText())/100;
              	
            	
            	switch ((String) comboBox_metodo.getSelectedItem()) {
            		case "Ruleta":
            			Main.seleccion = 1;
            			break;
            		case "Torneo Det":
            			Main.seleccion = 2;
            			break;
            		case "Torneo Prob":
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
	            	 
	
            		main.set_funcion();
	         		
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
	         		
	         		
	         			         		
	         		Object[][] data = {//para testeo mientras
	                        {"Nombre", "ID", "okey", "Nombre", "ID", "okey", "Nombre", "ID", "okey"},
	                        {2, "AUX12", "okey", 2, "AUX12", "okey", 2, "AUX12", "okey"},
	                        {2, "AUX12", "okey", 2, "AUX12", "okey", 2, "AUX12", "okey",}
	                };

	                String[] columnNames = {"Nombre", "ID", "okey", "Nombre", "ID", "okey", "Nombre", "ID", "okey"};
	         		
	         		DefaultTableModel model = new DefaultTableModel(data, columnNames);
	         		
	        		table = new JTable(model);
	        		table.setBounds(740, 208, 471, 277);
	        		frmPractica.getContentPane().add(table);
	        		
	        		lblNewLabel.setText("Pista 1                                     Pista 2                                      Pista 3");
	        		
	        		frmPractica.setVisible(false);
	        		frmPractica.setVisible(true);
	         		/*StringBuilder sb = new StringBuilder();
	         		
	         		double[] t;
	         		
	         		
	         		for (int i = 0; i < main.getCont() + 1; ++i) {
	         			t = main.getXfinal();
	         			for (int j = 0; j < t.length; ++j) {
	         				if (j == t.length - 1) sb.append(" X" + j + "[" + t[j] + "]");
	         				else sb.append(" X" + j + "[" + t[j] + "]" + ", ");
	         				sb.append("\n");
	         			}
	         		}*/
	         		
	         		
            	}
            
        };

        // Add the ActionListener to the button
        btnNewButton.addActionListener(listener);
		
		
		this.frmPractica.setVisible(true);
	}
}

