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
        p = 2;
        init();
    }

    private void init(){
    	    	
    	 for(int i = 0; i < numIndividuos; i++){
         	
         	t = new int[p];
         	
         	for (int j = 0; j < p; ++j) {//se escogen 2 individuos la azar
         		t[j] = r.nextInt(numIndividuos);
 			}

        	double random = r.nextDouble();//generamos un numero aleatorio de 0 al 1
         	
         	if (random > prob) {//si el numero aleatorio es mayor que prob (0.5), cogemos al mejor individuo, al contrario cogemos el peor
         		if (individuos[t[0]].getAptitud() > individuos[t[1]].getAptitud()) seleccion[i] = t[0];
             	else seleccion[i] = t[1];
         	}
         	else {
         		if (individuos[t[0]].getAptitud() < individuos[t[1]].getAptitud()) seleccion[i] = t[0];
             	else seleccion[i] = t[1];
         	}

         }
    }
    public int[] getSelection() {
    	return this.seleccion;
    }
}
