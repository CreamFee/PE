package objects;

import java.util.Random;

import interfaces.ICromosoma;
import logic.BooleanData;

public class Cromosoma2 implements ICromosoma{

	private BooleanData datos; //Array de booleanos con los datos
    private int tamano; //tamano cromosoma total
    private int genes; //numero genes
    private double[] rangos; //Valor inferior del rango de valores
    private double aptitud; //valor de aptitud del cromosoma
    public Random r; //Generador de numeros aleatorios
    
    public Cromosoma2 (int tamano, int genes, double[] rangos, Random r){
        this.tamano = tamano;
        this.genes = genes;
        this.rangos = rangos;
        this.datos =  new BooleanData(tamano);
        this.r = r;
    }
    
    public Cromosoma2 (BooleanData datos, int tamano, int genes, double[] rangos, Random r, double aptitud){
    	this.datos = datos;
    	this.aptitud = aptitud;
        this.tamano = tamano;
        this.genes = genes;
        this.rangos = rangos;
        this.r = r;
    }
    
    public Cromosoma2 (Cromosoma2 cromosoma) {
    	this.datos =  new BooleanData (cromosoma.datos);
    	this.aptitud = cromosoma.aptitud;
        this.tamano = cromosoma.tamano;
        this.genes = cromosoma.genes;
        this.rangos = cromosoma.rangos;
        this.r = cromosoma.r;
    }
    
    public void inicializar(){
        double aux;
        for (int i = 0; i < tamano; i++){
            aux = r.nextDouble();
            if(aux < 0.5)
                datos.setDatoI(false, i);
            else
            	datos.setDatoI(true, i);
        }
    }
    
    public double[] traducir(int[] tamanios){
        int offset = 0;
        int pew = 0;
        int inicio = 0;
        double [] genes = new double[this.genes];
        for (int j= 0; j < this.genes; j++){
            genes[j] = 0; 
        }
        for (int i = 0; i < this.genes; i++){ 
            offset = tamanios[i];
            for(int j = inicio; j < inicio + tamanios[i]; j++){
                if (datos.getDatoI(j) == true){
                    genes[i] += Math.pow(2, offset - 1); 
                }
                offset--;
                pew ++;
            }
            genes [i] = rangos[2 * i] + (genes[i] * (rangos[2 * i + 1] - rangos[2 * i])/((Math.pow(2, tamanios[i])) - 1));  
            inicio += pew;
            pew = 0;
        }
        return genes;
    }
    
    public void mutar(double probabilidad){
        double aux;
        for (int i = 0; i < tamano; i++){
            aux = r.nextDouble();
            if(aux <= probabilidad){ 
            	if(this.datos.getDatoI(i) == true) 
            		this.datos.setDatoI(false, i);
            	else 
            		this.datos.setDatoI(true, i);
            }
        }
    }
    
    public void cruceMonopunto(Cromosoma2 pareja){
        int posicion = r.nextInt(this.tamano - 1) + 1;
        for (int i = posicion; i < this.tamano; i++){
        	boolean aux = false; //false por defecto
        	if (this.datos.getDatoI(i)) //true si lo es el otro para evitar copias de punteros
        		aux = true;
            this.datos.setDatoI(pareja.datos.getDatoI(i), i);
            pareja.datos.setDatoI(aux, i);
        }
    }
    
    public void cruceUniforme(Cromosoma2 pareja){
        for (int i = 0; i < this.tamano; i++){
            if (r.nextDouble() < 0.5){
            	boolean aux = false; //false por defecto
            	if (this.datos.getDatoI(i)) //true si lo es el otro para evitar copias de punteros
            		aux = true;
                this.datos.setDatoI(pareja.datos.getDatoI(i), i);
                pareja.datos.setDatoI(aux, i);
            }
        }
    }
    
    public void setAptitud( double aux) {
    	this.aptitud = aux;
    }
    
    public double getAptitud() {
    	return this.aptitud;
    }

	@Override
	public int getTamano() {
		return this.tamano;
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
		//No hara nada en caso de funcion con reales
		
	}

	@Override
	public void cruceUniforme(ICromosoma pareja) {
		//No hara nada en caso de funcion con reales
		
	}
}
