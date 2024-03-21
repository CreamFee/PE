package selections;
import java.util.Random;

import interfaces.ICromosoma;
import interfaces.ISeleccion;
public class SeleccionTorneoDet implements ISeleccion{
    public ICromosoma[] individuos;
    public int numIndividuos;
    private int[] seleccion;
    private int[] t;
    private int p;
    public Random r;
    public SeleccionTorneoDet(int tam, ICromosoma[] iCromosomas, Random r){
        this.numIndividuos = tam;
        this.individuos = iCromosomas;
        this.seleccion = new int[tam];
        this.r = r;
        p = 3;
        init();
    }

    private void init(){
    	
        for(int i = 0; i < numIndividuos; i++){
        	
        	t = new int[p];
        	
        	for (int j = 0; j < p; ++j) {//seleccionamos 2 individuos al azar
        		t[j] = r.nextInt(numIndividuos);
			}

        	seleccion[i] = t[0];

        	for(int j = 1; j < p; ++j) {
            	if (individuos[t[j]].getAptitud() > individuos[seleccion[i]].getAptitud()) seleccion[i] = t[j]; //comparamos y cogemos al mejor
        	}
        }
    }
    public int[] getSelection() {
    	return this.seleccion;
    }
}
