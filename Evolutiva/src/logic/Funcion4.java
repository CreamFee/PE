package logic;

import java.util.Random;

import interfaces.ICromosoma;
import interfaces.IFuncion;
import main.Main;
import objects.Cromosoma2;

public class Funcion4 implements IFuncion {
	private int genes; // Numero de genes
    private int[] tamGen; // Tamano de cada uno de los genes
    private int tamCrom; // Tamano total del cromosoma
    private double[] rangos; // Rangos de cada gen del cromosoma
    private int poblacion; // Tamano poblaci�n
    private double mutacion; // Probabilidad de mutacion
    private double cruce; // Probabilidad de cruce
    private double precision = 0.001; // Precision de la representaci�n
    private Cromosoma2[] individuos;
    private double[] xx;//resultado
    private int tipoCruce; //Tipo de cruce utilizado
    private Random r; //Instancia de random utilizada en todo el codigo
    private double elite; //Porcentaje de la poblacion que supone la elite
    private Cromosoma2[] elitistas; //Miembros de la elite
    private double m = 10; //TODO No se lo que es eso
    public Funcion4(int poblacion, double precision, double mutacion, double cruce, int tipoCruce, Random r, double elite){
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
        this.elitistas = new Cromosoma2[(int)(elite * poblacion)];
        iniciar();
    }

    private void iniciar(){ //inicializa la funcion con todo loq ue necesita para realizar los calculos correspondientes
    	for (int i = 0; i < this.genes; i++) {
    		rangos[2 * i] = 0; 
            rangos[2 * i + 1] = Math.PI;
    	}
    	for(int i = 0; i < genes; i++){
            tamGen[i] = calcularTamGen(precision, rangos[2 * i], rangos[2 * i + 1]);
        }
        this.individuos = new Cromosoma2[this.poblacion];
        for(int i = 0; i < this.poblacion; i++){
            this.individuos[i] = new Cromosoma2 (this.tamCrom, this.genes, this.rangos, this.r); 
            this.individuos[i].inicializar(); 
        }
    };
    
    private double evaluar (ICromosoma c){//evalua el individuo proporcionado como parametro
    	 double[] tmp = c.traducir(tamGen);
    	 double result = 0;
    	 for (int i = 0; i < this.genes; i++) {
    		 result += Math.sin(tmp[i]) * Math.pow(Math.sin(((i + 1) * Math.pow(tmp[i], 2)) / Math.PI), 2 * this.m);
    	 }
         c.setAptitud(-result);
         return -result;
    }
    
    public void evaluarPoblacion (){ //Evalua la poblacion al completo
        for(int i = 0; i < poblacion; i++) 
            evaluar(this.individuos[i]);
    }

    public void corregirAptitud() { //Corrige el fitness de la poblacion, para evitar numeros negativos en su fitnes que puedan generar errores durante la seleccion
    	double max = getMax();
    	if (max < 0) 
    		max *= 0.9; //Si max es negativo, le restamos un 10% para que los valores sean todos positivos
    	else
    		max *= 1.1; //Si es positivo, le sumamos un 10% por el mismo objetivo
    	
		for(int i = 0; i < this.poblacion; i++)
    		this.individuos[i].setAptitud(max - this.individuos[i].getAptitud());

    }
    public int calcularTamGen (double precision, double rango0, double rango1){ //Calcula el tamano del gen necesario para representar los individuos 
        //con la precision y el rango solicitados
        int x = 0;
        x =  (int) (Math.log10(((rango1 - rango0) / precision) + 1) / Math.log10(2));
        this.tamCrom += x;
        return x;
    }
    
    public double getMax(){ //Devuelve el valor maximo de la poblacion actual(de su fitness)
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
    
    public double getPromedio(){ //Devuelve el promedio de la generacion actual (de su fitness)
        double total = 0, promedio = 0;
        for (int i = 0; i < poblacion; i++){
            total += this.individuos[i].getAptitud();
        }
        promedio = total / this.poblacion;
        return promedio;
    }

    public void cruzar(){ //Realiza el cruce de los individuos de la poblacion para obtener los nuevos individuos
        int pareja = -1;
        for(int i = 0; i < this.poblacion; i++){
            if(r.nextDouble() < this.cruce){
                if(pareja != -1){
                    if(this.tipoCruce == 1){
                        this.individuos[i].cruceUniforme(this.individuos[pareja]);
                    } else {
                        this.individuos[i].cruceMonopunto(this.individuos[pareja]);
                    }
                    pareja = -1;
                } else {
                    pareja = i;
                }
            }
        }
    }
    
    public void mutar(){ //Realiza la mutacion de los individuos de la generacion actual
        for(int i = 0; i < this.poblacion; i++){
            this.individuos[i].mutar(this.mutacion);
        }
    }

    public ICromosoma[] getIndividuos() { //Devuelve la lsita de individuos actual
        return this.individuos;
    }
    public void generarElite() { //Genera la elite de los individuos de esta generacion
    	double aptMax = 0, aptaux;
    	int[] used = new int[(int)(this.elite * this.poblacion)];
    	int pos = 0;
    	boolean find = false;
    	for (int i = 0; i < (int)(this.elite * this.poblacion); i++) { //Colocamos la elite
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
        	elitistas[i] = new Cromosoma2(individuos[pos]);
        	//Reiniciamos valores para seguir la busqueda
        	pos = 0;
        	aptMax = 0;
        	aptaux = 0;
        	find = false;
        }
    }
    
    public void introducirElite() { //Introduce la elite que hemos generado con anterioridad
    	for(int i = 0; i < (int)(this.elite * this.poblacion); i++) {
    		individuos[i] = new Cromosoma2(elitistas[i]);
    	}
    }
    public void seleccionar(int[] seleccion) { //Actualiza la lista de individuos con la lista actual de individuos que hemos seleccionado antes
        Cromosoma2 [] antiguos = new Cromosoma2[poblacion];
        for(int i = 0; i < poblacion; i++) { //Hacemos copias de los originales
            antiguos[i] = new Cromosoma2 (individuos[i]);
        }
        for(int i = 0; i < poblacion; i++) { //Colocamos la seleccion
            individuos[i + (int) this.elite * this.poblacion] = new Cromosoma2(antiguos[seleccion[i]]); 
        }
    }
    
    public double[][] getFenotipos() { //Devuelve una matriz con los fenotipos de cada gen de cada individuo de la generacion actual
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
	@Override
	public void cruzarA() { //El cruce aritmetico solo es efectivo en la funcion 5
		//solo para funcion 5
		
	}

}
