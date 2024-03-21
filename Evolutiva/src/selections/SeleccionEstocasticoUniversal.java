

package selections;
import java.util.Random;

import interfaces.ICromosoma;
import interfaces.ISeleccion;
public class SeleccionEstocasticoUniversal implements ISeleccion{
    public ICromosoma[] individuos;
    public int numIndividuos;
    private int[] seleccion;
    private int n;
    public Random r;
    public SeleccionEstocasticoUniversal(int tam, ICromosoma[] iCromosomas, Random r){
        this.numIndividuos = tam;
        this.individuos = iCromosomas;
        this.seleccion = new int[tam];
        this.r = r;
        n = numIndividuos/2;
        init();
    }

    private void init(){

    	
      	double totalFitness = 0;
    	for (int i = 0; i < numIndividuos; i++) {
    	    totalFitness += individuos[i].getAptitud();
    	}

    	for (int i = 0; i < numIndividuos; i++) {
    	    individuos[i].setAptitud(individuos[i].getAptitud() / totalFitness);
    	}
    	
        //ordenamos los individuos
        
        double aptMax = 0, aptaux;
    	int[] used = new int[numIndividuos];
    	int pos = 0;
    	boolean find = false;
    	for (int i = 0; i < numIndividuos; i++) { //Colocamos la elite
        	for(int j = 0; j < numIndividuos; j++) {//Buscamos el mejor individuo
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

        	//Reiniciamos valores para seguir la busqueda
        	pos = 0;
        	aptMax = 0;
        	aptaux = 0;
        	find = false;
        }


  

    	// Seleccion
    	double[] cumulativeProbability = new double[numIndividuos];
    	cumulativeProbability[0] = individuos[0].getAptitud();
    	for (int i = 1; i < numIndividuos; i++) {
    	    cumulativeProbability[i] = cumulativeProbability[i - 1] + individuos[i].getAptitud();
    	}

    	int[] seleccion = new int[n];
    	double increment = 1.0 / n;
    	double threshold = increment;
    	int selected = 0;

    	for (int i = 0; i < numIndividuos && selected < n; i++) {
    	    while (cumulativeProbability[i] >= threshold && selected < n) {
    	        seleccion[selected++] = i;
    	        threshold += increment;
    	    }
    	}

    	for (int i = selected; i < n; i++) {
    	    seleccion[i] = r.nextInt(numIndividuos);
    	}

        
        	
    }
    public int[] getSelection() {
    	return this.seleccion;
    }
}
