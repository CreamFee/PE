package logic;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import interfaces.ICromosoma;
import interfaces.IFuncion;
import main.Main;
import objects.CromosomaInteger;
//TODO hay que implementar la seleccion por ranking, el resto estan hechas
public class Practica2 implements IFuncion {
    private int genes; // Numero de genes
    private int tamCrom; // Tamano total del cromosoma
    private int poblacion; // Tamano poblacion
    private double mutacion; // Probabilidad de mutacion
    private double cruce; // Probabilidad de cruce
    private CromosomaInteger[] individuos; //Lista de individuos
    private int tipoCruce; //Cruce utilizado
    private int tipoMutacion; //mutacion utilizada
    private double[] xx;//resultado
    private Random r; //Random compartido en todas las diferentes partes del codigo
    private double elite; //Porcentaje de la elite
    private CromosomaInteger[] elitistas; //Listado de elitistas
    private float[][] mapaSEP = {{1 , (float)1.5, 2}, {1, (float)1.5, (float)1.5},{1, 1, 1}}; //Tiempos entre aviones de los diferentes tipos, comun a todos los aeropuertos
    Map<Integer, String[]> mapaAvionesinit;
    
    private Map<Integer, List<Integer>> mapaTEL;
    private double[][] TLALIST;
    private int[][] vueloList;
    
    private int m; //numero de pistas de aterrizaje
    
    public Practica2(int poblacion, double precision, double mutacion, double cruce, Random r, double elite){
    	if(Main.funcionElegida == 1)
    		this.tamCrom = 12;
    	else
    		this.tamCrom = 25;
    	this.m = Main.mapaTEL.get(1).size();
    	
        TLALIST = new double[m][this.tamCrom + 1];
        for(int j= 0; j < m; j++){
            for(int i = 0; i < this.tamCrom + 1; i++)//Inicializamos la matriz de TLA
                TLALIST[j][i] = 0;
        }
        this.vueloList = new int[m][this.tamCrom];
        for(int i = 0; i < this.tamCrom; i++) {
        	for(int j = 0; j  < m; j++) {
        		this.vueloList[j][i] = 0;
        	}
        }
        this.mapaAvionesinit = Main.mapaAviones;
        this.genes = this.tamCrom; 
        this.mutacion = mutacion;
        this.cruce = cruce;
        this.mapaTEL = Main.mapaTEL;	 
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

        this.individuos = new CromosomaInteger[this.poblacion];
        for(int i = 0; i < this.poblacion; i++){
            this.individuos[i] = new CromosomaInteger (this.tamCrom, this.genes, this.r); 
            this.individuos[i].inicializar(); 
        }
    };
    
    private double evaluar (ICromosoma c){
    	double fitness = 0;
        int punto = 0;
        int vuelo[] = new int[m + 1];//Empezamos en el vuelo 1 ya que el 0 se mantiene en 0 siempre
        for(int i = 0; i < m + 1; i++) {
        	vuelo [i] = 1;
        }
        double menor = 100;
        double tmp = 100;
    	for(int i = 0; i < this.tamCrom; i++){
            //Calcular TLA a cada pista (en funcion del avion anterior), es el valor mayor entre el TLA del vuelo anterior (0 para el primero)
            // + el SEP entre este vuelo y el anterior (el inicial tiene 0) y entre el TEL a la pista en cuestion

            for(int j= 0; j < m; j++){
            	if (this.vueloList[j][vuelo[j] - 1] != 0) {
            		tmp = Math.max(TLALIST[j][vuelo[j] - 1] + 
                    		sep(this.mapaAvionesinit.get(this.vueloList[j][vuelo[j] - 1])[1].charAt(0), 
                				this.mapaAvionesinit.get(c.getDatos().getDatoI(i))[1].charAt(0)), 
                    		TEL(c.getDatos().getDatoI(i), j));
            	}
            	else { //Cuando no hay vuelo anterior, el sep es 0
            		tmp = TEL(c.getDatos().getDatoI(i), j);
            	}
                if(tmp < menor){
                    menor = tmp;
                    punto = j;
                }
                
            }
            vuelo[punto]++;
            this.TLALIST[punto][vuelo[punto] -1] = tmp;
            menor = (double)TEL(c.getDatos().getDatoI(i), 0);
            
            //Comprobamos los TEL del vuelo a cada pista
            for(int x = 0; x < m; x++) {
            	if( menor > TEL(c.getDatos().getDatoI(i), x))
            		menor = TEL(c.getDatos().getDatoI(i), x);
            }
            
            //hacemos la operacion:
            fitness += Math.pow(this.TLALIST[punto][vuelo[punto] - 1] - menor, 2);

            //reset al menor
            menor = 100;
            punto = 0;
        }
    	c.setAptitud(fitness);
    	return fitness;
    }
    private double TEL(int vuelo, int pista){
        List<Integer> aux = this.mapaTEL.get(vuelo);
        if(pista < 0 || pista > m - 1){
            return 0;
        }
        return aux.get(pista);
    }
    private float sep(char a, char b) {
    	//mirar mapa de separaciones y comparar los dos aviones
    	int i = 0, j = 0;
    	if(a == 'W')
            i = 0;
        else if(a == 'G')
            i = 1;
        else
            i = 2;
        if(b == 'W')
            i = 0;
        else if(b == 'G')
            i = 1;
        else
            i = 2;
        
    	return this.mapaSEP[i][j];
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
    	double min = this.individuos[0].getAptitud(), tmp = 0;
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
