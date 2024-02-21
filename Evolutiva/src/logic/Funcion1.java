package logic;

import java.util.Random;

import objects.Cromosoma;


public class Funcion1 {
    public int gens; //Numero de genes
    public int[] genSize; //Tamanio de cad auno de los genes
    public int sizeCrom; //Tamanio total del cromosoma
    public double[] ranges; //Rangos de cada gen del cromosoma (cada gen puede tener unos valroes determinados
    public int poblacion; //tamanio poblacion
    public double mutar; //probabilidad de mutar
    public double cruce; //probabilidad de cruce
    private double precision = 0.001; //Precision de la representacion
    private Cromosoma[] individuos;
    private boolean tipoCruce;
    public Random r;
    public Funcion1(int poblacion, double precision, double mutar, double cruce, boolean tipoCruce, Random r){
        this.sizeCrom = 0;
        this.gens = 2;
        this.genSize = new int[gens];
        this.ranges = new double[2*gens];
        this.mutar = mutar;
        this.cruce = cruce;
        this.precision = precision;
        this.poblacion = poblacion;
        this.tipoCruce = tipoCruce;
        this.r = r;
        init();
        
    }
    private void init(){ //TODO
        //Inicializa la funcion 1 de la practica

        ranges[0] = -10; //2n
        ranges[1] = 10; //2n +1
        ranges[2] = -10;
        ranges[3] = 10;
        for(int i = 0; i < gens; i++){
            genSize[i] = calcSizeGen(precision, ranges[2 * i], ranges[2 * i + 1]);
        }
        this.individuos = new Cromosoma[this.poblacion];
        for(int i = 0; i < this.poblacion; i++){
            this.individuos[i] = new Cromosoma (this.sizeCrom, this.gens, this.ranges, this.r); //Creamos todos los individuos
            this.individuos[i].init(); //Arrancamos la poblacion aleatoria
        }

    };
    private double evaluate (Cromosoma c){ 
    	//Evalua el cromosoma C, calcula su fitness y lo guarda en su atributo correspondiente
        //Evalua 1 cromosoma
        double[] tmp = c.translate(genSize);
        double result = Math.pow(tmp[0], 2) + 2 * Math.pow(tmp[1],2);
        c.setFitness(result);
        return result;   
    }
    
    public void evaluarPoblacion (){ 
    	//Evalua la poblacion completa y guarda sus valores en sus respectivos atributos
    	for(int i = 0; i < poblacion; i++) {
    		this.individuos[i].setFitness(evaluate(this.individuos[i]));
    	}
    }
    
    public int calcSizeGen (double precision, double range0, double range1){
        //Calcula el tamanio del gen en funcion d elos rangos y la precision
        int x = 0;
        x =  (int) (Math.log10(((range1 - range0) / precision) + 1) / Math.log10(2));
        this.sizeCrom += x;
        return x;
    }
    public double getMax(){ 
    	//Devuelve el maximo de esta poblacion
        double max = 0, tmp = 0;
        for (int i = 0; i < poblacion; i++){
            tmp = this.individuos[i].getFitness();
            if (max < tmp){
                max = tmp;
            }
        }
        return max;
    }
    public double getAverage(){ 
    	//Calcula la media aritmetica
        double total = 0, avg = 0;
        for (int i = 0; i < poblacion; i++){
            total += this.individuos[i].getFitness();
        }
        avg = total / this.poblacion;
        return avg;
    }

    public void cruzar(){
    	//realiza el cruce de los individuos
        Cromosoma pareja = null;
        for(int i = 0; i < this.poblacion; i++){
            if(r.nextDouble() < this.cruce){
                if(pareja != null){
                    if(this.tipoCruce == true)
                        this.individuos[i].cruceUniforme(pareja);
                    else
                        this.individuos[i].cruceMonopunto(pareja);
                    pareja = null;
                }
                else 
                    pareja = this.individuos[i];
            }
        }
    }
    public void mutar(){
    	//Realiza la mutacion 
        for(int i = 0; i < this.poblacion; i++){
            this.individuos[i].mutar(this.mutar);
        }
    }

    public Cromosoma[] getIndividuos() {
    	return this.individuos;
    }
    
    public void seleccionar(int[] seleccion) {
    	//Se renueva la lista de individuos con los que se han seleccionado
    	Cromosoma [] nuevos = new Cromosoma[poblacion];
    	for(int i = 0; i < poblacion; i++) {
    		nuevos[i] = individuos[seleccion[i]].copia();
    	}
    	for(int i = 0; i < poblacion; i++) {
    		individuos[i] = nuevos[i];
    	}
    }
}
