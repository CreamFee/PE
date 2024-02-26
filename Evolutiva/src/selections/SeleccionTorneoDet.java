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
        p = 2;
        init();
    }

    private void init(){
    	
        for(int i = 0; i < numIndividuos; i++){
        	
        	t = new int[p];
        	
        	for (int j = 0; j < p; ++j) {//seleccionamos 2 individuos al azar
        		t[i] = r.nextInt(numIndividuos);
			}

        	if (individuos[t[0]].getAptitud() > individuos[t[1]].getAptitud()) seleccion[i] = t[0]; //comparamos y cogemos al mejor
        	else seleccion[i] = t[1];

        }
    }
    public int[] getSelection() {
    	return this.seleccion;
    }
}
