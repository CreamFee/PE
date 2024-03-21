
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

import logic.Practica2;
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
	private ResultWindow resultados;
	private JLabel printta;
	private JLabel fitness;
	/**
	 * 
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
	 * 
	 */
	public MainWindow(Main main) {
		this.main = main;
		initialize();
	}

	/**
	 * Initialize
	 */
	private void initialize() {
		frmPractica = new JFrame();
		frmPractica.setTitle("Practica 2");
		frmPractica.setBounds(100, 100, 1000, 600);
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
		/*
		label_RESULT = new JLabel("Results:");
		label_RESULT.setVerticalAlignment(SwingConstants.TOP);
		label_RESULT.setBounds(742, 151, 69, 21);
		label_RESULT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmPractica.getContentPane().add(label_RESULT);
		*/
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
		btnNewButton.setBounds(158, 500, 100, 40);
		frmPractica.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_5_2 = new JLabel("Tipo Cruce");
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5_2.setBounds(22, 418, 126, 31);
		frmPractica.getContentPane().add(lblNewLabel_5_2);
		
		JComboBox comboBox_cruce = new JComboBox();
		comboBox_cruce.setModel(new DefaultComboBoxModel(new String[] {"PMX", "OX", "OXPP", "CX", "CO","Metodo Propio"}));
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
		comboBox_metodo_1.setModel(new DefaultComboBoxModel(new String[] {"Insercion", "Intercambio", "Inversion", "Heuristica", "Metodo propio"}));
		comboBox_metodo_1.setBounds(22, 330, 96, 21);
		frmPractica.getContentPane().add(comboBox_metodo_1);
		
		JLabel lblNewLabel_5_1_1_1 = new JLabel("Tipo Mutacion");
		lblNewLabel_5_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5_1_1_1.setBounds(22, 290, 138, 31);
		frmPractica.getContentPane().add(lblNewLabel_5_1_1_1);
		
		table_1 = new JTable();
		table_1.setBounds(882, 368, 1, 1);
		frmPractica.getContentPane().add(table_1);
		
		table = new JTable();
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(752, 182, 500, 21);
		frmPractica.getContentPane().add(lblNewLabel);
		
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	//DELETE OTHER LINES
            	plot.removeAllPlots();
            	
            	//datos de entrada
            	
            	//VUELOS PRIMERO LUEGO AVIONES
            	
            	Map<Integer, String[]> mapaAvionesinit = new TreeMap<Integer, String[]>();
            	Map<Integer, List<Integer>> mapaTELinit = new TreeMap<Integer, List<Integer>>();
            	
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
            	
            	Main.tipoCruce = comboBox_cruce.getSelectedIndex();//0 PMX, 1 OX, 2 OXPP, 3 CX, 4 CO, 5 METODO PROPIO
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
         		int a, b;
         		a = mapaTELinit.get(1).size();
         		b = mapaTELinit.size();
         		String [][] data1 = new String[(3 * a) + 1][b];
         		String[] columnNames = new String[3 * a];
         		
         		for(int i = 0; i < 3 * a; i+=3) {
         			columnNames[i] = "Vuelo";
         			columnNames[i + 1] = "Nombre";
         			columnNames[i + 2] = "TLA";
         		}
                data1[0] = columnNames;
         		
         		for (int jj = 0; jj < a; ++jj) {
	         		for(int i = 1; i < Practica2.vueloListSol[jj].length; ++i) {
	         			if (Practica2.vueloListSol[jj][i] != 0) {
	         				data1[i][0 + 3 * jj] = String.valueOf(Practica2.vueloListSol[jj][i]);
		         			data1[i][1 + 3 * jj] = mapaAvionesinit.get(Practica2.vueloListSol[jj][i])[0];
		         			data1[i][2 + 3 * jj] = String.valueOf(Practica2.TLALISTsol[jj][i]);
	         			}
	         		}
         		}
         		JTable pistas;
        		if (a == 5) {
        			String titulo5[] = {"Pista 1", "Pista 2", "Pista 3", "Pista 4", "Pista 5"};

             		String [][] data0 = {titulo5};
        			DefaultTableModel model5 = new DefaultTableModel(data0, titulo5);
        			pistas = new JTable(model5);
        		}	
        		else {
        			String titulo3[] = {"Pista 1", "Pista 2", "Pista 3"};
        			String [][] data0 = {titulo3};
        			DefaultTableModel model3 = new DefaultTableModel(data0, titulo3);
        			pistas = new JTable(model3);
        		}
    			pistas.setBounds(0, 0, 200 * a, 16);
        		DefaultTableModel model = new DefaultTableModel(data1, columnNames);
        		table = new JTable(model);
        		table.setBounds(0, 16, 200 * a, 300);
        		
        		frmPractica.setVisible(false);
        		frmPractica.setVisible(true);
        		if(resultados != null)
        			resultados.eliminar();
        		resultados = new ResultWindow(table, pistas);
        		double[] resultado = main.getXfinal();
        		String printa = "Resultado: [";
        		for (int i = 0; i < resultado.length; i++) {
        			if(i != resultado.length - 1)
        				printa += (int)resultado[i] + ", ";
        			else
        				printa += (int)resultado[i] + "]";
        		}
        		if(printta != null)
        			frmPractica.remove(printta);
        		printta = new JLabel(printa);
        		printta.setBounds(158, 430, printa.length() * 10, 40);
        		printta.setFont(new Font("Tahoma", Font.PLAIN, 15));
        		frmPractica.getContentPane().add(printta);
        		
        		if(fitness != null)
        			frmPractica.remove(fitness);
        		fitness = new JLabel("Fitness: " + main.getFitnessMax());
        		fitness.setFont(new Font("Tahoma", Font.PLAIN, 15));
        		fitness.setBounds(158, 450, 100, 40);
        		frmPractica.getContentPane().add(fitness);
        	}
        };

        btnNewButton.addActionListener(listener);
		
		
		this.frmPractica.setVisible(true);
	}
}

