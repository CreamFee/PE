package objects;

import java.util.Random;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import interfaces.ICromosoma;
import logic.IntegerData;

public class CromosomaInteger implements ICromosoma{
    private IntegerData datos; //Array de doubles con los datos
    private int tam; //tamano cromosoma total va a coincidir con el numero de genes ya que la codificacion es en double
    private int genes; //numero genes
    private double aptitud; //valor de fitness del cromosoma
    public Random r; //Random para generar los valores aleatorios
    
    private int inicial[]; 
    public CromosomaInteger (int tamanio, int gens, Random r){
        this.tam = tamanio;
        this.genes = gens;
        this.datos = new IntegerData(this.genes);
        this.r = r;
    }
    public CromosomaInteger (IntegerData data, int tamanio, int gens, Random r, double aptitud){
    	this.datos = new IntegerData(data);
    	this.aptitud = aptitud;
        this.tam = tamanio;
        this.genes = gens;
        this.r = r;
    }
    public CromosomaInteger (CromosomaInteger a) {
    	this.datos = new IntegerData(a.datos);
    	this.aptitud = a.aptitud;
        this.tam = a.tam;
        this.genes = a.genes;
        this.r = a.r;
    }
    public void inicializar(){ //DONE, los individuos se inicializan aleatorios
        if(this.tam == 12) {
        	Integer[] intArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        	List<Integer> intList = Arrays.asList(intArray);

    		Collections.shuffle(intList);

    		intList.toArray(intArray);
    		this.datos.setDatos(intArray);
        }
        else {
        	Integer[] intArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
        	List<Integer> intList = Arrays.asList(intArray);

    		Collections.shuffle(intList);

    		intList.toArray(intArray);
    		this.datos.setDatos(intArray);
        }
    }
    
    public void mutar(double probabilidad){ //TODO Modificar ya que al ser enteros no puede mutar de este modo
        int aux;
        double tmp;
        double rango;
        for (int i = 0; i < this.genes; i++){
            tmp = r.nextDouble();
            







        }
    }
    public void crucePMX(CromosomaInteger pareja, int corte1, int corte2){ //TODO Cruce PMX, revisar cuando hay problemas de repetidos, y terminar de rellenarlos
    	CromosomaInteger izquierdo, derecho;
    	izquierdo = new CromosomaInteger (pareja);
    	derecho = new CromosomaInteger (this);
        int tmp;
        for(int i = corte1; i < corte2; i++ ){ //Sustituimos los valores de los genes entre los puntos de corte
            tmp = pareja.datos.getDatoI(i);
            pareja.datos.setDatoI(this.datos.getDatoI(i), i);
            this.datos.setDatoI(tmp, i);
        }
        boolean found = false;
        int pos = 0;
        //sustituimos aquellos sin conflicto en this
        for(int i = 0; i < this.tam; i ++) {
        	if (i < corte1  || i > corte2) {
            	for(int j = corte1; j < corte2; j++) {
            		if(this.datos.getDatoI(j) == this.datos.getDatoI(i)) {
            			found = true;
            			pos = j;
            			break;
            		}
            	}
            	if (found) {
            		found = false;
            		this.datos.setDatoI(derecho.datos.getDatoI(pos), pos);
            	}
        	}
        }
      //sustituimos aquellos sin conflicto en pareja
        for(int i = 0; i < this.tam; i ++) {
        	if (i < corte1  || i > corte2) {
            	for(int j = corte1; j < corte2; j++) {
            		if(pareja.datos.getDatoI(j) == pareja.datos.getDatoI(i)) {
            			found = true;
            			pos = j;
            			break;
            		}
            	}
            	if (found) {
            		found = false;
            		pareja.datos.setDatoI(izquierdo.datos.getDatoI(pos), pos);
            	}
        	}
        }
    }

    public void cruceOX(CromosomaInteger pareja, int corte1, int corte2){ //TODO Cruce OX, revisar cuando hay problemas de repetidos, y terminar de rellenarlos
        
        int tmp;
        for(int i = (corte1 - 1); i < corte2; i++ ){
            tmp = pareja.datos.getDatoI(i);
            pareja.datos.setDatoI(this.datos.getDatoI(i), i);
            this.datos.setDatoI(tmp, i);
        }
        
        CromosomaInteger izquierdo, derecho;
    	izquierdo = new CromosomaInteger (pareja);
    	derecho = new CromosomaInteger (this);
    	 for(int i = 0; i < this.tam; i++ ){//inicializamos dos auxiliares a 0 para saber que posiciones estan cogidas
             izquierdo.datos.setDatoI(0, i);
             derecho.datos.setDatoI(0, i);
         }
    	  for(int i = (corte1 - 1); i < corte2; i++ ){//no seria corte1 a secas? sin el menos 1?
              izquierdo.datos.setDatoI(this.datos.getDatoI(i), i);
              derecho.datos.setDatoI(pareja.datos.getDatoI(i), i);
          }
    	
    	  int j = corte2;
    	  int k = j;
    	  while (derecho.datos.getDatoI(corte1 - 2) == 0) {//mientras el ultimo en cambiar sea 0
    		 
    		  if (j == this.tam) j = 0;//cuando llega al final del individuo vuelve a la posicion 0
    		  if (k == this.tam) k = 0;
    		  if(!izquierdo.contains(this.datos.getDatoI(j))) {//si no contiene el numero a bajar, baja el numero y salta a la siguiente posicion a llenar
    			  izquierdo.datos.setDatoI(this.datos.getDatoI(j), k);
    			  ++k;
    		  }
    		  ++j;
    		  
    	  }
    	  j = corte2;//igual para el derecho
    	  k = j;
    	  while (derecho.datos.getDatoI(corte1 - 2) == 0) {//mientras el ultimo en cambiar sea 0
    		 
    		  if (j == this.tam) j = 0;//cuando llega al final del individuo vuelve a la posicion 0
    		  if (k == this.tam) k = 0;
    		  if(!izquierdo.contains(pareja.datos.getDatoI(j))) {
    			  derecho.datos.setDatoI(pareja.datos.getDatoI(j), k);
    			  ++k;
    		  }
    		  ++j;
    	  }
    }


	public void cruceOXpp(CromosomaInteger pareja){ //TODO Cruce OX posicion prioritaria, revisar cuando hay problemas de repetidos, y terminar de rellenarlos
        //Hay que elegir unas posiciones aleatorias, no se cuantas
        //Luego hay que ver las ciudades en este cromosoma (this) que estan en esas posiciones,
        //ponemos las ciudades de la pareja en this (las de esas posiciones)
        //El resto las metemos en orden segun estaban en this anteriormente

        
    }
//Este no es obligatorio, el OXOP
    public void cruceOXop(CromosomaInteger pareja){ //TODO Cruce OX orden prioritario, revisar cuando hay problemas de repetidos, y terminar de rellenarlos
        //Hay que elegir unas posiciones aleatorias, no se cuantas
        //Luego hay que ver las ciudades en este cromosoma (this) que estan en esas posiciones,
        //Tras eso, buscamos las posiciones de estas cidudades en el otro cromosoma (pareja),
        //copiamos todas las ciudades de la pareja que no estan en las elegidas aleatoriamente, colocamos las que habiamos elegido en this,
        //El otro hijo lo mismo pero eliges las posiciones de la pareja, las guardas, rellenas con el resto de ciudades de this, colocas las de pareja elegidas


        
    }

    public void cruceCX (CromosomaInteger pareja){//TODO revisar
        //Cruce por ciclos
        IntegerData tmp1 = new IntegerData(this.datos), tmp2 = new IntegerData(pareja.datos);
        for(int i = 0; i < this.tam; i ++){ //Comprobar 
            this.datos.setDatoI(tmp1.getDatoI(i), getPos(tmp1.getDatoI(i)));
            

        }
        for(int i = 0; i < this.tam; i ++){
            
        }
    }

    private int getPos(int city){
        int aux = 0, contador = 0;
        while(aux != city){
            aux = this.datos.getDatoI(contador);
            if(aux != city){
                contador++;
            }
        }
        return contador;
    }
    public double[] traducir() { 
        return this.datos.getDatosDouble();
    }
    public void setAptitud( double aux) {
    	this.aptitud = aux;
    }
    
    public double getAptitud() {
    	return this.aptitud;
    }

	@Override
	public int getTamano() {
		return this.tam;
	}


	@Override
	public int getGenes() {
		return this.genes;
	}

	@Override
	public double[] getRangos() {
		return null;
	}

	@Override
	public void cruceMonopunto(ICromosoma pareja) {
		cruceMonopunto(pareja);
	}

	@Override
	public void cruceUniforme(ICromosoma pareja) {
		cruceUniforme(pareja);
	}

	public IntegerData getDatos() {
		
		return this.datos;
	}
    private boolean contains(int datoI) {
    	for (int i = 0; i < this.tam; ++i) {
    		if (this.datos.getDatoI(i) == datoI) return true;
    	}
    	
    	return false;
	}
}
