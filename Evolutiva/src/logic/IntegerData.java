package logic;

public class IntegerData {
    private int[] datos;
	private int tam;
	
	//Constructores
	public IntegerData() {
		this.datos = new int[1];
	}
	public IntegerData(int tam) {
		this.tam = tam;
		this.datos = new int[tam];
	}
	public IntegerData (IntegerData a) {
		this.tam = a.tam;
		this.datos = new int [this.tam];
		for(int i = 0; i < this.tam; i++) {
			this.datos[i] = a.datos[i];
		}
		
	}
	
	//Gets y sets de los datos
	public int[] getDatos() {
		return this.datos;
	}
	public int getDatoI(int i) {
		return this.datos[i];
	}
	public void setDatoI(int dato, int i) {
		this.datos[i] = dato;
	}
	public void setDatos(int[] datos) {
		this.datos = datos;
	}
	public double [] getDatosDouble(){
		double []aux = new double[this.tam];
		for (int i = 0; i < this.tam; i++){
			aux[i] = (double) this.datos[i];
		}
		
		return aux;
	}
	
}
