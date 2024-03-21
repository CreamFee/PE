package view;

import javax.swing.JFrame;
import javax.swing.JTable;


public class ResultWindow {

	private JFrame frameResults;
	private JTable tabla;
	private JTable pistas;
	
	public ResultWindow(JTable tabla, JTable pistas){
		this.tabla = tabla;
		this.pistas = pistas;
		init();
	}
	
	private void init() {
		frameResults = new JFrame();
		frameResults.setTitle("Practica 2");
		frameResults.setBounds(100, 100, this.tabla.getBounds().width, this.tabla.getBounds().height);
		//frameResults.setLocation(0, 0);
		frameResults.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameResults.getContentPane().setLayout(null);
		frameResults.setAlwaysOnTop(true);
		
		frameResults.getContentPane().add(this.tabla);
		frameResults.getContentPane().add(this.pistas);
		
		
		frameResults.setVisible(false);
		frameResults.setVisible(true);
		
	}
	public void eliminar() {
		this.frameResults.dispose();
	}
}
