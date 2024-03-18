package selections;

import java.util.Random;

import interfaces.ICromosoma;
import interfaces.ISeleccion;

public class SeleccionRanking implements ISeleccion{
    public ICromosoma[] individuos;
    public int numIndividuos;
    private int[] seleccion;
    private double beta;
    public Random r;
    public SeleccionRanking(int tam, ICromosoma[] iCromosomas, Random r){
        this.numIndividuos = tam;
        this.individuos = iCromosomas;
        this.seleccion = new int[tam];
        this.r = r;
        beta = 2;
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
    	
    	double accPunc = 0.0;//para que esto??
    	for (int i = 0; i < numIndividuos; ++i) {
	    	double probOfIth = (double)i/numIndividuos;
	    	probOfIth *= 2*(beta-1);
	    	probOfIth = beta - probOfIth;
	    	probOfIth = (double)probOfIth * ((double)1/numIndividuos);
	    	//individuos[i].setAccPunc(accPunc);
	    	individuos[used[i]].setAptitud(probOfIth);
	    	accPunc += probOfIth;
    	}
    	
    	
    	//ruleta
    	 double total = 0;
         double cuenta;
        for(int i = 0; i < numIndividuos; i++){
            //Comprobaremos con el random cual ha sido seleccionado y lo aniadimos
            //Total pasa a valer ahora el numero aleatorio seleccionado
            total = r.nextDouble(); //Numero que toca
            cuenta = 0;
            for(int j = 0; j < numIndividuos; j++){
                cuenta += individuos[j].getAptitud();
                if(cuenta >= total){ //Cuando alcanzamos el numero que toca, guardamos ese individuo
                    seleccion[i] = j;
                    break;
                }
                else if (j == numIndividuos -1) { //Si se trata del ultimo individuo, guardamos ese
                	seleccion[i] = j;
                    break;
                }
            }
        }
    	
    }
    
 
    
    
    public int[] getSelection() {
    	return this.seleccion;
    }
}

