package objects;

import java.util.Random;
import logic.DoubleData;

import interfaces.ICromosoma;
public class CromosomaDouble implements ICromosoma{
    private DoubleData datos; //Array de doubles con los datos
    private int tam; //tamano cromosoma total va a coincidir con el numero de genes ya que la codificacion es en double
    private int genes; //numero genes
    private double[] rangos; //Valor inferior del rango de valores
    private double aptitud; //valor de fitness del cromosoma
    public Random r; //Random para generar los valores aleatorios
    public CromosomaDouble (int tamanio, int gens, double[] rangos, Random r){
        this.tam = tamanio;
        this.genes = gens;
        this.rangos = rangos;
        this.datos = new DoubleData(this.tam);
        this.r = r;
    }
    public CromosomaDouble (DoubleData data, int tamanio, int gens, double[] rangos, Random r, double aptitud){
    	this.datos = new DoubleData(data);
    	this.aptitud = aptitud;
        this.tam = tamanio;
        this.genes = gens;
        this.rangos = rangos;
        this.r = r;
    }
    public CromosomaDouble (CromosomaDouble a) {
    	this.datos = new DoubleData(a.datos);
    	this.aptitud = a.aptitud;
        this.tam = a.tam;
        this.genes = a.genes;
        this.rangos = a.rangos;
        this.r = a.r;
    }
    public void inicializar(){
        double aux;
        double rango;
        for (int i = 0; i < tam; i++){
            aux = r.nextDouble();
            //Valculamos la posición en la que debería encontrarse
            //Total de valores posibles = incremento en los rangos dividido 
            rango = rangos[2 * i + 1] - rangos[2 * i]; //Final menos inicial
            aux *= rango; //Ahora aux contiene el valor comprendido en nuestro rango desplazado, solo hay que desplazarlo de nuevo
            aux += rangos[2 * i];
            this.datos.setDatoI(aux, i);
        }
    }
    
    public void mutar(double probabilidad){
        double aux;
        double rango;
        for (int i = 0; i < tam; i++){
            aux = r.nextDouble();
            if(aux <= probabilidad){ 
            	aux = r.nextDouble();
            	
            	rango = rangos[2 * i + 1] - rangos[2 * i];
                aux *= rango;
                aux += rangos[2 * i];
                
                this.datos.setDatoI(aux, i);
            }
        }
    }
    
    public void cruceMonopunto(CromosomaDouble pareja){
        int posicion = r.nextInt(this.tam - 1) + 1;
        for (int i = posicion; i < this.tam; i++){
        	double aux = this.datos.getDatoI(i);
            this.datos.setDatoI(pareja.getDatos().getDatoI(i), i);
            this.datos.setDatoI(pareja.datos.getDatoI(i), i);
            pareja.datos.setDatoI(aux, i);
        }
    }
    
    public void cruceUniforme(CromosomaDouble pareja){
        for (int i = 0; i < this.tam; i++){
            if (r.nextDouble() < 0.5){
            	double aux = this.datos.getDatoI(i); 
                this.datos.setDatoI(pareja.datos.getDatoI(i), i);
                pareja.datos.setDatoI(aux, i);
            }
        }
    }
    
    public double[] traducir(int[] tamGens) {
    	return this.datos.getDatos();
    }
    public double[] traducir() {
    	return this.datos.getDatos();
    }
    public void setAptitud( double aux) {
    	this.aptitud = aux;
    }
    
    public double getAptitud() {
    	return this.aptitud;
    }

	@Override
	public int getTamano() {
		return this.tam;
	}


	@Override
	public int getGenes() {
		return this.genes;
	}

	@Override
	public double[] getRangos() {
		return this.rangos;
	}

	@Override
	public void cruceMonopunto(ICromosoma pareja) {
		cruceMonopunto(pareja);
	}

	@Override
	public void cruceUniforme(ICromosoma pareja) {
		cruceUniforme(pareja);
	}

	public DoubleData getDatos() {
		
		return this.datos;
	}
}
