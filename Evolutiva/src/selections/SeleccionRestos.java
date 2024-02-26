package selections;
import java.util.Random;

import interfaces.ICromosoma;
import interfaces.ISeleccion;
public class SeleccionRestos implements ISeleccion{
    public ICromosoma[] individuos;
    public int numIndividuos;
    private int[] seleccion;
    public Random r;
    public SeleccionRestos(int tam, ICromosoma[] iCromosomas, Random r){
        this.numIndividuos = tam;
        this.individuos = iCromosomas;
        this.seleccion = new int[tam];
        this.r = r;
        init();
    }

    private void init(){

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
        int cont = 0;
        for(int i = 0; i < numIndividuos; i++){
        	if (individuos[i].getAptitud() * numIndividuos >= 1) {
        		seleccion[cont] = i;
        		++cont;
        	}  
        }
        
        for (int i = cont; i < numIndividuos; ++i) {
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
