package logic;

public class DoubleData {
	private double[] datos;
	private int tam;
	
	//Constructores
	public DoubleData() {
		this.datos = new double[1];
	}
	public DoubleData(int tam) {
		this.tam = tam;
		this.datos = new double[tam];
	}
	public DoubleData (DoubleData a) {
		this.tam = a.tam;
		this.datos = new double [this.tam];
		for(int i = 0; i < this.tam; i++) {
			this.datos[i] = a.datos[i];
		}
		
	}
	
	//Gets y sets de los datos
	public double[] getDatos() {
		return this.datos;
	}
	public double getDatoI(int i) {
		return this.datos[i];
	}
	public void setDatoI(double dato, int i) {
		this.datos[i] = dato;
	}
	public void setDatos(double[] datos) {
		this.datos = datos;
	}
	
}
