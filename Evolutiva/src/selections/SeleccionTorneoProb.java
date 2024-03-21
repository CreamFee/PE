package selections;
import java.util.Random;

import interfaces.ICromosoma;
import interfaces.ISeleccion;
public class SeleccionTorneoProb implements ISeleccion{
    public ICromosoma[] individuos;
    public int numIndividuos;
    private int[] seleccion;
    private int[] t;
    private double prob;
    private int p;
    public Random r;
    public SeleccionTorneoProb(int tam, ICromosoma[] iCromosomas, Random r){
        this.numIndividuos = tam;
        this.individuos = iCromosomas;
        this.seleccion = new int[tam];
        this.r = r;
        prob = 0.5;
        p = 3;
        init();
    }

    private void init(){
    	    	
    	 for(int i = 0; i < numIndividuos; i++){
         	
         	t = new int[p];
         	
         	for (int j = 0; j < p; ++j) {//se escogen 2 individuos la azar
         		t[j] = r.nextInt(numIndividuos);
 			}

        	double random = r.nextDouble();//generamos un numero aleatorio de 0 al 1
         	
        	seleccion[i] = t[0];
        	
         	if (random > prob) {//si el numero aleatorio es mayor que prob (0.5), cogemos al mejor individuo, al contrario cogemos el peor      		
         		for(int j = 1; j < p; ++j) {
                	if (individuos[t[j]].getAptitud() > individuos[seleccion[i]].getAptitud()) seleccion[i] = t[j]; //comparamos y cogemos al mejor
            	}
         	}
         	else {
         		for(int j = 1; j < p; ++j) {
                	if (individuos[t[j]].getAptitud() < individuos[seleccion[i]].getAptitud()) seleccion[i] = t[j]; //comparamos y cogemos al peor
            	}
         	}

         }
    }
    public int[] getSelection() {
    	return this.seleccion;
    }
}
