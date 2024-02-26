

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
        n = 50;
        init();
    }

    private void init(){
        
        //En este metodo de seleccion no estan ordenados los cromosomas por ningun orden especifico
        double total = 0;
        double cuenta;
        for(int i = 0; i < numIndividuos; i++){
            //suma total de las aptitudes
            total += individuos[i].getAptitud();
        }
        for(int i = 0; i < numIndividuos; i++){
            //Vamos a calcular su probabilidad de seleccion de cada individuo
            individuos[i].setAptitud(individuos[i].getAptitud() / total);
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
        
    	double inc = r.nextDouble(1/n);
    
        int i = 0;
 
        cuenta = 0;
        for(int j = 0; j < numIndividuos; j++){
            cuenta += individuos[j].getAptitud();
            if(cuenta >= inc){ //Cuando alcanzamos el numero que toca, guardamos ese individuo
                seleccion[i] = j;
                inc += inc;
                ++i;
                break;
            }
        }
        
        for(int j = i; j < numIndividuos; j++){
        	seleccion[j] = r.nextInt(numIndividuos);
        }
        
        	
    }
    public int[] getSelection() {
    	return this.seleccion;
    }
}
