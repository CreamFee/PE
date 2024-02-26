package selections;
import java.util.Random;

import interfaces.ICromosoma;
import interfaces.ISeleccion;
public class SeleccionTruncamiento implements ISeleccion{
    public ICromosoma[] individuos;
    public int numIndividuos;
    private int[] seleccion;
    private double trunc;
    public Random r;
    public SeleccionTruncamiento(int tam, ICromosoma[] iCromosomas, Random r){
        this.numIndividuos = tam;
        this.individuos = iCromosomas;
        this.seleccion = new int[tam];
        this.r = r;
        trunc = 0.5;
        init();
    }

    private void init(){
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
    	
    	int iguales = (int) (1 / trunc);//2 o 10
    	int o = 0;
    	int l = 0;
    	
    	for (int i = 0; i < numIndividuos; ++i) {//repetimos o 2 o 10 veces los mejores y vamos avanzando en la lista ordenada
    		if (o == iguales) {
    			o = 0;
    			++l;
    		}
    		seleccion[i] = used[l];
    		++o;
    	}
    	
    }
    
 
    
    
    public int[] getSelection() {
    	return this.seleccion;
    }
}
