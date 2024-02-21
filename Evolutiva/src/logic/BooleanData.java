package logic;

public class BooleanData { 
	//Clase creada para almacenar los datos binarios 
	//de los genes de los cromosomas
	
	//Atributos
	private boolean[] datos;
	private int tam;
	
	//Constructores
	public BooleanData() {
		this.datos = new boolean[1];
	}
	public BooleanData(int tam) {
		this.tam = tam;
		this.datos = new boolean[tam];
	}
	public BooleanData (BooleanData a) {
		this.tam = a.tam;
		this.datos = a.datos;
	}
	
	//Gets y sets de los datos
	public boolean[] getDatos() {
		return this.datos;
	}
	public boolean getDatoI(int i) {
		return this.datos[i];
	}
	public void setDatoI(boolean dato, int i) {
		this.datos[i] = dato;
	}
	public void setDatos(boolean[] datos) {
		this.datos = datos;
	}
	
}
