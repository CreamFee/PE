package logic;

import java.util.Random;

import interfaces.ICromosoma;
import interfaces.IFuncion;
import main.Main;
import objects.Cromosoma2;
import objects.CromosomaDouble;

public class Funcion5 implements IFuncion {
	private int genes; // Numero de genes
    private int[] tamGen; // Tamano de cada uno de los genes
    private int tamCrom; // Tamano total del cromosoma
    private double[] rangos; // Rangos de cada gen del cromosoma
    private int poblacion; // Tamano poblaci�n
    private double mutacion; // Probabilidad de mutacion
    private double cruce; // Probabilidad de cruce
    private double precision = 0.001; // Precision de la representaci�n
    private CromosomaDouble[] individuos;
    private boolean tipoCruce;
    private double[] xx;//resultado
    private Random r;
    private double elite;
    private CromosomaDouble[] elitistas;
    private double m = 10;
    public Funcion5(int poblacion, double precision, double mutacion, double cruce, boolean tipoCruce, Random r, double elite){
    	this.tamCrom = 0;
        this.genes = Main.dimension; 
        this.tamGen = new int[genes];
        this.rangos = new double[2*genes];
        this.mutacion = mutacion;
        this.cruce = cruce;
        this.xx = new double[this.genes];
        this.precision = precision;
        this.poblacion = poblacion;
        this.tipoCruce = tipoCruce;
        this.r = r;
        this.elite = elite;
        this.elitistas = new CromosomaDouble[(int)(elite * poblacion)];
        iniciar();
    }

    private void iniciar(){ 
    	for (int i = 0; i < this.genes; i++) {
    		rangos[2 * i] = 0; 
            rangos[2 * i + 1] = Math.PI;
    	}
    	for(int i = 0; i < genes; i++){
            tamGen[i] = 1;
        }
        this.individuos = new CromosomaDouble[this.poblacion];
        for(int i = 0; i < this.poblacion; i++){
            this.individuos[i] = new CromosomaDouble (this.tamCrom, this.genes, this.rangos, this.r); 
            this.individuos[i].inicializar(); 
        }
    };
    
    private double evaluar (ICromosoma c){
    	 double[] tmp = c.traducir(tamGen);
    	 double result = 0;
    	 for (int i = 0; i < this.genes; i++) {
    		 result += Math.sin(tmp[i]) * Math.pow(Math.sin(((i + 1) * Math.pow(tmp[i], 2)) / Math.PI), 2 * this.m);
    	 }
         c.setAptitud(-result);
         return -result;
    }
    
    
    public void evaluarPoblacion (){
        for(int i = 0; i < poblacion; i++) 
            evaluar(this.individuos[i]);
    }

    public void corregirAptitud() { 
    	double max = getMax();
    	if (max < 0) 
    		max *= 0.9; //Si max es negativo, le restamos un 10% para que los valores sean todos positivos
    	else
    		max *= 1.1; //Si es positivo, le sumamos un 10% por el mismo objetivo
    	
		for(int i = 0; i < this.poblacion; i++)
    		this.individuos[i].setAptitud(max - this.individuos[i].getAptitud());

    }
    public int calcularTamGen (double precision, double rango0, double rango1){
        int x = 0;
        x =  (int) (Math.log10(((rango1 - rango0) / precision) + 1) / Math.log10(2));
        this.tamCrom += x;
        return x;
    }
    
    public double getMax(){ 
        double max = 0, tmp = 0;
        for (int i = 0; i < poblacion; i++){
            tmp = this.individuos[i].getAptitud();
            if (max < tmp){
                max = tmp;
                xx = this.individuos[i].traducir(tamGen);
            }
        }
        return max;
    }
    
    public double getPromedio(){ 
        double total = 0, promedio = 0;
        for (int i = 0; i < poblacion; i++){
            total += this.individuos[i].getAptitud();
        }
        promedio = total / this.poblacion;
        return promedio;
    }

    public void cruzar(){
    	CromosomaDouble pareja = null;
        for(int i = 0; i < this.poblacion; i++){
            if(r.nextDouble() < this.cruce){
                if(pareja != null){
                    if(this.tipoCruce){
                        this.individuos[i].cruceUniforme(pareja);
                    } else {
                        this.individuos[i].cruceMonopunto(pareja);
                    }
                    pareja = null;
                } else {
                    pareja = this.individuos[i];
                }
            }
        }
    }
    
    public void mutar(){
        for(int i = 0; i < this.poblacion; i++){
            this.individuos[i].mutar(this.mutacion);
        }
    }

    public ICromosoma[] getIndividuos() {
        return this.individuos;
    }
    public void generarElite() {
    	double aptMax = 0, aptaux;
    	int[] used = new int[(int)(this.elite * this.poblacion)];
    	int pos = 0;
    	boolean find = false;
    	for (int i = 0; i < (int) (this.elite * this.poblacion); i++) { //Colocamos la elite
        	for(int j = 0; j < this.poblacion; j++) {//Buscamos el mejor individuo
        		aptaux = individuos[j].getAptitud();
        		if(aptaux > aptMax) {
        			for(int fin = 0; fin < i; fin++) {//Comprobamos que no ha sido usado
        				if(used[fin] == j) {
        					find = true;
        					break;
        				}
        				else
        					find = false;
        			}
        			if(!find) {
        				pos = j;
        				aptMax = aptaux;
        			}
        			else
        				find = false;
        		}
        	}
        	used[i] = pos;
        	//Guardar el elitista
        	elitistas[i] = new CromosomaDouble(individuos[pos]);
        	//Reiniciamos valores para seguir la busqueda
        	pos = 0;
        	aptMax = 0;
        	aptaux = 0;
        	find = false;
        }
    }
    
    public void introducirElite() {
    	for(int i = 0; i < (int)(this.elite * this.poblacion); i++) {
    		individuos[i] = new CromosomaDouble(elitistas[i]);
    	}
    }
    public void seleccionar(int[] seleccion) {
    	
    	CromosomaDouble [] antiguos = new CromosomaDouble[poblacion];
        for(int i = 0; i < poblacion; i++) { //Hacemos copias de los originales
            antiguos[i] = new CromosomaDouble (individuos[i]);
        }
        for(int i = 0; i < poblacion; i++) { //Colocamos la seleccion
            individuos[i + (int) this.elite * this.poblacion] = new CromosomaDouble(antiguos[seleccion[i]]); 
        }
    }
    
    public double[][] getFenotipos() {
        double[][] result = new double[this.poblacion][this.genes];
        for(int i = 0; i < this.poblacion; i++) {
            result[i] = individuos[i].traducir(this.tamGen);
        }
        return result;
    }
    public double getMin() { //Sera util cuando queramos maximizar una funcion con numeros negativos
    	double min = 0, tmp = 0;
        for (int i = 0; i < poblacion; i++){
            tmp = this.individuos[i].getAptitud();
            if (min > tmp) {
                min = tmp;
                xx = this.individuos[i].traducir(tamGen);
            }
        }
        return min;
    }

	@Override
	public double[] getXX() {
		return this.xx;
	}
}
