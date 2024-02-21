package logic;

import java.util.Random;

import interfaces.IFuncion;
import objects.Cromosoma2;

public class Funcion1 implements IFuncion{
    public int genes; // Número de genes
    public int[] tamGen; // Tamaño de cada uno de los genes
    public int tamCrom; // Tamaño total del cromosoma
    public double[] rangos; // Rangos de cada gen del cromosoma
    public int poblacion; // Tamaño población
    public double mutacion; // Probabilidad de mutación
    public double cruce; // Probabilidad de cruce
    private double precision = 0.001; // Precisión de la representación
    private Cromosoma2[] individuos;
    private boolean tipoCruce;
    public Random r;

    public Funcion1(int poblacion, double precision, double mutacion, double cruce, boolean tipoCruce, Random r){
        this.tamCrom = 0;
        this.genes = 2;
        this.tamGen = new int[genes];
        this.rangos = new double[2*genes];
        this.mutacion = mutacion;
        this.cruce = cruce;
        this.precision = precision;
        this.poblacion = poblacion;
        this.tipoCruce = tipoCruce;
        this.r = r;
        iniciar();
    }

    private void iniciar(){ 
        rangos[0] = -10; 
        rangos[1] = 10; 
        rangos[2] = -10;
        rangos[3] = 10;
        for(int i = 0; i < genes; i++){
            tamGen[i] = calcularTamGen(precision, rangos[2 * i], rangos[2 * i + 1]);
        }
        this.individuos = new Cromosoma2[this.poblacion];
        for(int i = 0; i < this.poblacion; i++){
            this.individuos[i] = new Cromosoma2 (this.tamCrom, this.genes, this.rangos, this.r); 
            this.individuos[i].inicializar(); 
        }
    };
    
    private double evaluar (Cromosoma2 c){ 
        double[] tmp = c.traducir(tamGen);
        double result = Math.pow(tmp[0], 2) + 2 * Math.pow(tmp[1],2);
        c.setAptitud(result);
        return result;   
    }
    
    public void evaluarPoblacion (){ 
        for(int i = 0; i < poblacion; i++) {
            this.individuos[i].setAptitud(evaluar(this.individuos[i]));
        }
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
        Cromosoma2 pareja = null;
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

    public Cromosoma2[] getIndividuos() {
        return this.individuos;
    }
    
    public void seleccionar(int[] seleccion) {
        Cromosoma2 [] antiguos = new Cromosoma2[poblacion];
        for(int i = 0; i < poblacion; i++) {
            antiguos[i] = new Cromosoma2 (individuos[i]);
        }
        for(int i = 0; i < poblacion; i++) {
            individuos[i] = new Cromosoma2(antiguos[seleccion[i]]); 
        }
    }
    
    public double[][] getFenotipos() {
        double[][] result = new double[this.poblacion][this.genes];
        for(int i = 0; i < this.poblacion; i++) {
            result[i] = individuos[i].traducir(this.tamGen);
        }
        return result;
    }

}
