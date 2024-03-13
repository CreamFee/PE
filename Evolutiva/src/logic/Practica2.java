package logic;

import java.util.Random;

import interfaces.ICromosoma;
import interfaces.IFuncion;
import main.Main;
import objects.CromosomaDouble;
import objects.CromosomaInteger;
//TODO hay que implementar la seleccion por ranking, el resto estan hechas
public class Practica2 implements IFuncion {
    private int genes; // Numero de genes
    private int tamCrom; // Tamano total del cromosoma
    private double[] rangos; // Rangos de cada gen del cromosoma
    private int poblacion; // Tamano poblacion
    private double mutacion; // Probabilidad de mutacion
    private double cruce; // Probabilidad de cruce
    private double precision = 0.001; // Precision de la representacion, irrelevante cuando se trata de cromosomas del tipo double
    private CromosomaInteger[] individuos; //Lista de individuos
    private int tipoCruce; //Cruce utilizado
    private int tipoMutacion; //matacion utilizada
    private double[] xx;//resultado
    private Random r; //Random compartido en todas las diferentes partes del codigo
    private double elite; //Porcentaje de la elite
    private CromosomaInteger[] elitistas; //Listado de elitistas
    
    private double m = 10; //TODO no se que es esto
    
    public Practica2(int poblacion, double precision, double mutacion, double cruce, Random r, double elite){
    	if(Main.funcionElegida == 1)
    		this.tamCrom = 12;
    	else
    		this.tamCrom = 25;
        this.genes = this.tamCrom; 
        this.rangos = new double[2*genes];
        this.mutacion = mutacion;
        this.cruce = cruce;
        this.xx = new double[this.genes];
        this.poblacion = poblacion;
        this.tipoCruce = Main.tipoCruce;
        this.tipoMutacion = Main.tipoMutacion;
        this.r = r;
        this.elite = elite;
        this.elitistas = new CromosomaInteger[(int)(elite * poblacion)];
        iniciar();
    }

    private void iniciar(){ //Inicializa la funcion con todo lo necesario para realizar los calculos
    	for (int i = 0; i < this.genes; i++) {
    		rangos[2 * i] = 0; 
            rangos[2 * i + 1] = 12;
    	}
        this.individuos = new CromosomaInteger[this.poblacion];
        for(int i = 0; i < this.poblacion; i++){
            this.individuos[i] = new CromosomaInteger (this.tamCrom, this.genes, this.rangos, this.r); 
            this.individuos[i].inicializar(); 
        }
    };
    
    private double evaluar (ICromosoma c){ //TODO EVALUAR
    	 double[] tmp = c.traducir();
    	 double result = 0;
    	 for (int i = 0; i < this.genes; i++) { //TODO esto esta mal
    		 result += Math.sin(tmp[i]) * Math.pow(Math.sin(((i + 1) * Math.pow(tmp[i], 2)) / Math.PI), 2 * this.m);
    	 }
         c.setAptitud(-result);
         return -result;
    }
    
    
    public void evaluarPoblacion (){ //Evalua toda la poblacion
        for(int i = 0; i < poblacion; i++) 
            evaluar(this.individuos[i]);
    }

    public void corregirAptitud() { //Corrige la aptitud cuando esta tiene valores negativos, haciendo que todos sean positivos, en caso de que el
        //mayor sea negativo debemos tenerlo en cuenta y darle un multiplicador menor, de manera que nos aseguramos que todos son positivos
    	double max = getMax();
    	if (max < 0) 
    		max *= 0.9; //Si max es negativo, le restamos un 10% para que los valores sean todos positivos
    	else
    		max *= 1.1; //Si es positivo, le sumamos un 10% por el mismo objetivo
    	
		for(int i = 0; i < this.poblacion; i++)
    		this.individuos[i].setAptitud(max - this.individuos[i].getAptitud());

    }
    
    public double getMax(){  //Devuelve el maximo de la generacion actual (fitness mayor)
        double max = 0, tmp = 0;
        for (int i = 0; i < poblacion; i++){
            tmp = this.individuos[i].getAptitud();
            if (max < tmp){
                max = tmp;
                xx = this.individuos[i].traducir();
            }
        }
        return max;
    }
    
    public double getPromedio(){ //Devuelve el promedio del fitness de la poblacion actual
        double total = 0, promedio = 0;
        for (int i = 0; i < poblacion; i++){
            total += this.individuos[i].getAptitud();
        }
        promedio = total / this.poblacion;
        return promedio;
    }

    public void cruzar(){//TODO hay que revisar todos los cruces que necesitamos e implementarlos
    	int pareja = -1;
        for(int i = 0; i < this.poblacion; i++){
            if(r.nextDouble() < this.cruce){
                if(pareja != -1){
                    if(this.tipoCruce == 0){
                    	int a, b;
                    	a = r.nextInt(this.tamCrom);//Si coinciden los puntos de corte, se queda igual
                    	b = r.nextInt(this.tamCrom);
                    	if (a < b)
                    		this.individuos[i].crucePMX(this.individuos[pareja], a, b);
                    	else if (b < a) 
                    		this.individuos[i].crucePMX(this.individuos[pareja], b, a);
                    } 
                    else if (this.tipoCruce == 1){
                    	
                    }
                    else if (this.tipoCruce == 2){
                    	
                    }
                    else if (this.tipoCruce == 3){
                    	
                    }
                    pareja = -1;
                } else {
                    pareja = i;
                }
            }
        }
    }
    
    
    public void mutar(){ //Realiza la mutacion de los individuos
        for(int i = 0; i < this.poblacion; i++){
            this.individuos[i].mutar(this.mutacion);
        }
    }

    public ICromosoma[] getIndividuos() { //Devuelve la lsita de individuos
        return this.individuos;
    }
    public void generarElite() { //Genera la elite de la generacion actual
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
        	elitistas[i] = new CromosomaInteger(individuos[pos]);
        	//Reiniciamos valores para seguir la busqueda
        	pos = 0;
        	aptMax = 0;
        	aptaux = 0;
        	find = false;
        }
    }
    
    public void introducirElite() { //Introduce la lista de la elite en lso primeros individuos de la lsita de individuos
    	for(int i = 0; i < (int)(this.elite * this.poblacion); i++) {
    		individuos[i] = new CromosomaInteger(elitistas[i]);
    	}
    }
    public void seleccionar(int[] seleccion) { //Sustituye los individuos actuales por copias de los que se han seleccionado
    	
    	CromosomaInteger [] antiguos = new CromosomaInteger[poblacion];
        for(int i = 0; i < poblacion; i++) { //Hacemos copias de los originales
            antiguos[i] = new CromosomaInteger (individuos[i]);
        }
        for(int i = 0; i < poblacion; i++) { //Colocamos la seleccion
            individuos[i + (int) this.elite * this.poblacion] = new CromosomaInteger(antiguos[seleccion[i]]); 
        }
    }
    
    public double[][] getFenotipos() { //Devuelve una matriz con los fenotipos de cada gen de cada individuo
        double[][] result = new double[this.poblacion][this.genes];
        for(int i = 0; i < this.poblacion; i++) {
            result[i] = individuos[i].traducir();
        }
        return result;
    }
    public double getMin() { //Sera util cuando queramos maximizar una funcion con numeros negativos
    	double min = 0, tmp = 0;
        for (int i = 0; i < poblacion; i++){
            tmp = this.individuos[i].getAptitud();
            if (min > tmp) {
                min = tmp;
                xx = this.individuos[i].traducir();
            }
        }
        return min;
    }

	@Override
	public double[] getXX() {
		return this.xx;
	}
}
