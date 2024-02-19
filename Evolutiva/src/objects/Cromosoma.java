package objects;

import java.util.Random;
public class Cromosoma {
    private boolean [] data; //Array de booleanos con los datos
    private int tam; //tamanio cromosoma total
    private int gens; //numero genes
    private double[] ranges; //Valor inferior del rango de valores
    private double fitness; //valor de fitness del cromosoma
    public Cromosoma (int tamanio, int gens, double[] ranges){
        this.tam = tamanio;
        this.gens = gens;
        this.ranges = ranges;
        this.data = new boolean[tam];
    }
    public void init(){
        double aux;
        Random r = new Random(System.currentTimeMillis()); //Valor random al inicializar la seed
        for (int i = 0; i < tam; i++){
            aux = r.nextDouble();
            
            if(aux < 0.5){ //Si los decimales son inferiores a 0.5 sera false, sino sera true
                this.data[i] = false;
            }
            else{
                this.data[i] = true;
            }
        }

    };
    public double[] translate(int[] tams){
        int offset = 0;
        int pew = 0;
        int start = 0;
        double [] genes = new double[gens];
        for (int j= 0; j < gens; j++){
            genes[j] = 0; //Inicializamos el array de traduccion
        }
        for (int i = 0; i < gens; i++){ //Vamos a traducir todos los genes del cromosoma y los devolvemos como doubles
            offset = tams[i];
            for(int j = start; j < start + tams[i]; j++){
                if (this.data[j]){
                    genes[i] += Math.pow(2, offset - 1); //Calculamos el valor decimal de las potencias de dos
                }
                offset--;
                pew ++;
            }
            genes [i] = ranges[2 * i] + (genes[i] * (ranges[2 * i + 1] - ranges[2 * i])/((Math.pow(2, tams[i])) - 1));  //traducimos al valor real en el rango dado
            start += pew;
            pew = 0;
        }
        return genes;
    }
    public void mutar(double prob){ //prob debe estar en decimales reales, no porcentaje
        Random r = new Random (System.currentTimeMillis());
        double aux;
        for (int i = 0; i < tam; i++){
            aux = r.nextDouble();
            if(aux <= prob){ 
                this.data[i] = !this.data[i];
            }
        }
    }
    public void cruceMonopunto(Cromosoma pareja){ //El cruce monopunto lo haremos directamente aqui
        Random r = new Random (System.currentTimeMillis());
        int pos = r.nextInt(this.tam - 1) + 1;
        for (int i = pos; i < this.tam; i++){
            boolean aux = this.data[i];
            this.data[i] = pareja.data[i];
            pareja.data[i] = aux;
        }

    }
    public void cruceUniforme(Cromosoma pareja){ //El cruce uniforme lo haremos directamente aqui
        Random r = new Random (System.currentTimeMillis());
        for (int i = 0; i < this.tam; i++){
            if (r.nextDouble() < 0.5){
                boolean aux = this.data[i];
                this.data[i] = pareja.data[i];
                pareja.data[i] = aux;
            }
        }
    }
    public void setFitness( double aux) {
    	this.fitness = aux;
    }
    public double getFitness() {
    	return this.fitness;
    }
}
