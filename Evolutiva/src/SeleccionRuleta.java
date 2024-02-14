import java.util.Random;
public class SeleccionRuleta {
    public Cromosoma[] individuos;
    public int numIndividuos;
    private int[] seleccion;
    public SeleccionRuleta(int tam, Cromosoma[] inds){
        this.numIndividuos = tam;
        this.individuos = inds;
        this.seleccion = new int[tam];
        init();
    }

    private void init(){
        //En este metodo de seleccion no estan ordenados los cromosomas por ningun orden especifico
        double total = 0;
        double cuenta;
        Random r = new Random(System.currentTimeMillis());
        for(int i = 0; i < numIndividuos; i++){
            //suma total de las aptitudes
            total += individuos[i].getFitness();
        }
        for(int i = 0; i < numIndividuos; i++){
            //Vamos a calcular su probabilidad de seleccion de cada individuo
            individuos[i].setFitness(individuos[i].getFitness() / total);
        }
        for(int i = 0; i < numIndividuos; i++){
            //Comprobaremos con el random cual ha sido seleccionado y lo aniadimos
            //Total pasa a valer ahora el numero aleatorio seleccionado
            total = r.nextDouble();
            cuenta = 0;
            for(int j = 0; j < numIndividuos; j++){
                cuenta += individuos[j].getFitness();
                if(cuenta < total){
                    seleccion[i] = j;
                    break;
                }
                else if (j == numIndividuos -1) {
                	seleccion[i] = j;
                    break;
                }
            }
        }
    }
    public int[] getSelection() {
    	return this.seleccion;
    }
    //TODO falta meter que se devuelva de alguna manera el array de seleccionados
}
