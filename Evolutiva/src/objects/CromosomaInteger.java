package objects;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import interfaces.ICromosoma;
import logic.IntegerData;
import main.Main;

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
        double tmp;
        int inter, inserciones = 2;
        double rango;
        tmp = r.nextDouble();
        int pos1 = r.nextInt(this.tam), pos2 = r.nextInt(this.tam);
        if(tmp < probabilidad) {
        	switch (Main.tipoMutacion) {
        	case 0:
        		//Mutacion por insercion, con "inserciones" inserciones
        		for(int i = 0; i < inserciones; i++) {
        			//Reservamos datos[pos2]
        			int reserva = this.datos.getDatoI(pos2);
        			
        			//Insertamos en datos[pos2], datos[pos1]
        			this.datos.setDatoI(this.datos.getDatoI(pos1), pos2); 
        			
        			//desplazamos datos[pos1 - 1] o +1 si se ha movido hacia adelante, y repetimos con todos el desplazamiento de las demas ciudades
        			
        			if(pos1 > pos2) {
        				for(int j = pos1 - 1; j > pos2; j--) {
        					this.datos.setDatoI(this.datos.getDatoI(j), j + 1);
        				}
        				this.datos.setDatoI(reserva, pos2 + 1);
        			}
        			else if(pos1 < pos2){
        				for(int j = pos1 + 1; j < pos2; j++) {
        					this.datos.setDatoI(this.datos.getDatoI(j), j - 1);
        				}
        				this.datos.setDatoI(reserva, pos2 - 1);
        			}
        			
        			pos1 = r.nextInt(this.tam); 
					pos2 = r.nextInt(this.tam);
        		}
        		break;
        		
			case 1:
			    //Mutacion por intercambio
				//Se intercambian los genes elegidos
				inter = this.datos.getDatoI(pos1);
				this.datos.setDatoI(this.datos.getDatoI(pos2), pos1);
				this.datos.setDatoI(inter, pos2);
				break;
				
			case 2:
				//mutacion por inversion
				//Se invierte el orden de los valores entre los dos puntos definidos
				
				if(pos1 < pos2) {
					int j = pos2;
					int aux = this.datos.getDatoI(pos1);
					for(int i = 0; i < pos2 - pos1; i++) {
						this.datos.setDatoI(this.datos.getDatoI(j), i);  
						this.datos.setDatoI(aux, j);
						j--;
						i++;
						if( i >= j) break;
					}
				}
				else if(pos2 < pos1) {
					int j = pos1;
					int aux = this.datos.getDatoI(pos2);
					for(int i = pos2; i < pos1; i++) {
						this.datos.setDatoI(this.datos.getDatoI(j), i);  
						this.datos.setDatoI(aux, j);
						j--;
						i++;
						if( i >= j) break;
					}
				}
				break;
			case 3: //TODO falta
				//Mutacion heuristica
				break;
			case 4://TODO falta
				//Nuestra propia mutacion
				
				break;
			default:
				break;
        	}
        }
    }
    public void crucePMX(CromosomaInteger pareja, int corte1, int corte2){ //TODO Revisar
    	CromosomaInteger izquierdo, derecho;
    	izquierdo = new CromosomaInteger (pareja);
    	derecho = new CromosomaInteger (this);
        int[] inserted1 = new int[this.tam];
        int[] inserted2 = new int[this.tam];
        for(int i = 0; i < this.tam; i++) {
        	inserted1[i] = -1;
        	inserted2 [i] = -1;
        }
        for(int i = 0; i < this.tam; i++ ){//inicializamos dos auxiliares a 0 para saber que posiciones estan cogidas
            izquierdo.datos.setDatoI(0, i);
            derecho.datos.setDatoI(0, i);
        }
        int cont = 0;
        for(int i = corte1; i < corte2; i++ ){ //Sustituimos los valores de los genes entre los puntos de corte
            izquierdo.datos.setDatoI(pareja.datos.getDatoI(i), i);
            inserted1[cont] = pareja.datos.getDatoI(i);
            derecho.datos.setDatoI(this.datos.getDatoI(i), i);
            inserted2[cont] = this.datos.getDatoI(i);
            cont++;
        }
        cont = 0;
        for (int i = 0; i < this.tam; i++) {
        	//Hijo izquierdo
        	if(izquierdo.datos.getDatoI(i) == 0) {
        		if(contieneValor(inserted1, this.datos.getDatoI(i))) {
            		izquierdo.datos.setDatoI(inserted2[getIndex(inserted1, this.datos.getDatoI(i))], i);
            	}
            	else {
            		izquierdo.datos.setDatoI(this.datos.getDatoI(i), i);
            	}
        	}
        	//Hijo derecho
        	if(derecho.datos.getDatoI(i) == 0) {
	        	if(contieneValor(inserted2, pareja.datos.getDatoI(i))) {
	        		derecho.datos.setDatoI(inserted1[getIndex(inserted2, pareja.datos.getDatoI(i))], i);
	        	}
	        	else {
	        		derecho.datos.setDatoI(pareja.datos.getDatoI(i), i);
	        	}
        	}
        }
        pareja.datos.setDatos(derecho.datos.getDatos());
  	  	this.datos.setDatos(izquierdo.datos.getDatos());
    }

    private int getIndex (int[] array, int value) {
    	int aux = 0;
    	for (int i = 0; i < array.length; i++) {
    		if(array[i] == value)
    			return i;
    	}
    	return aux;
    }
    public void cruceOX(CromosomaInteger pareja, int corte1, int corte2){ //TODO Revisar!!
        CromosomaInteger izquierdo, derecho;
    	izquierdo = new CromosomaInteger (pareja);
    	derecho = new CromosomaInteger (this);
    	 for(int i = 0; i < this.tam; i++ ){//inicializamos dos auxiliares a 0 para saber que posiciones estan cogidas
             izquierdo.datos.setDatoI(0, i);
             derecho.datos.setDatoI(0, i);
         }
    	  for(int i = corte1; i < corte2; i++ ){//no seria corte1 a secas? sin el menos 1?
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
    	  pareja.datos.setDatos(izquierdo.datos.getDatos());
    	  this.datos.setDatos(derecho.datos.getDatos());

    }


	public void cruceOXpp(CromosomaInteger pareja){ //Funciona!
        //Hay que elegir unas posiciones aleatorias, no se cuantas, voy a crear una variable para modificarlo al gusto
		int posiciones = (int) this.tam / 3;
		int pos1 = 0;
        int[] usados2 = new int[this.tam];
        int[] usados1 = new int[this.tam];
        CromosomaInteger izquierdo, derecho;
    	izquierdo = new CromosomaInteger (pareja);
    	derecho = new CromosomaInteger (this);
		for(int i = 0; i < this.tam; i++ ){
			//inicializamos dos auxiliares a 0 para saber que posiciones estan cogidas
			izquierdo.datos.setDatoI(0, i);
		    derecho.datos.setDatoI(0, i);
		}
		for (int i = 0; i < posiciones;  i ++) {
			
			//Hijo izquierdo
			pos1 = r.nextInt(this.tam);
			while(izquierdo.datos.getDatoI(pos1) != 0) {
				pos1 += 1;
				pos1 %= this.tam;
			}
			izquierdo.datos.setDatoI(pareja.datos.getDatoI(pos1), pos1);
			usados1[i] = pareja.datos.getDatoI(pos1);
			
			derecho.datos.setDatoI(this.datos.getDatoI(pos1), pos1);
			usados2[i] = this.datos.getDatoI(pos1);
		}
		int contador1 = 0, contador2 = 0;
		for (int i  = 0; i < this.tam; i++) {
			//Hijo izquierdo, relleno del resto
			if (izquierdo.datos.getDatoI(i) == 0) {
				while(contieneValor(usados1, this.datos.getDatoI(contador1))) {
					contador1++;
				}
				izquierdo.datos.setDatoI(this.datos.getDatoI(contador1), i);
				contador1++;
			}
			//Hijo derecho relleno del resto
			if (derecho.datos.getDatoI(i) == 0) {
				while(contieneValor(usados2, pareja.datos.getDatoI(contador2))) {
					contador2++;
				}
				derecho.datos.setDatoI(pareja.datos.getDatoI(contador2), i);
				contador2++;
			}
		}
		usados1 = izquierdo.getDatos().getDatos();
		usados2 = derecho.getDatos().getDatos();
		//izquierdo contiene los datos de uno de los hijos y derecho del otro
		this.datos.setDatos(usados1);   //Acoplamos a this
		pareja.datos.setDatos(usados2);   //Acoplamos a la pareja
    }
	
	private boolean contieneValor(int[] array, int valor) {
        for (int i : array) {
            if (i == valor) {
                return true;
            }
        }
        return false;
    }
	
	
	//Este no es obligatorio, el OXOP
	/*
    public void cruceOXop(CromosomaInteger pareja){ // Cruce OX orden prioritario, revisar cuando hay problemas de repetidos, y terminar de rellenarlos
    	
        CromosomaInteger izquierdo, derecho;
    	izquierdo = new CromosomaInteger (pareja);
    	derecho = new CromosomaInteger (this);
    	
      	 for(int i = 0; i < this.tam; i++ ){//inicializamos dos auxiliares a 0 para saber que posiciones estan cogidas
             izquierdo.datos.setDatoI(0, i);
             derecho.datos.setDatoI(0, i);
         }
    	
    	int num = 3; //numero de posiciones a intercambiar
    	List<Integer> posiciones = new ArrayList<Integer>();//posiciones para el segundo cromosoma
    	posiciones.add(num - 1, -1);
    	int numeros[] = new int[num];//numeros del primer cromosoma

    	
    	for (int i = 0; i < num; ++i) {//cogemos posiciones al azar
    		numeros[i] = this.datos.getDatoI(r.nextInt(this.tam));
    	}
    	int j = 0;
    	while(posiciones.size() < num) {
    		for (int i = 0; i < this.tam; ++i) {
    			if (numeros[j] == pareja.datos.getDatoI(i)) {
    				posiciones.add(j, i);
    				++j;
    			}
    		}
    	}
    	 Collections.sort(posiciones);
    	 
    	 int k = 0;
    	 for(int i = 0; i < this.tam; i++ ){
    	 
    		 if (i == posiciones.get(k)) {
    			 izquierdo.datos.setDatoI(i, numeros[k]);
    			 ++k;
    		 }
    		 else izquierdo.datos.setDatoI(i, pareja.datos.getDatoI(i));
    	 }
    	 
    	 
        	posiciones = new ArrayList<Integer>();//posiciones para el segundo cromosoma
        	posiciones.add(num - 1, -1);
        	numeros = new int[num];//numeros del primer cromosoma

        	
        	for (int i = 0; i < num; ++i) {//cogemos posiciones al azar
        		numeros[i] = pareja.datos.getDatoI(r.nextInt(this.tam));
        	}
        	j = 0;
        	while(posiciones.size() < num) {
        		for (int i = 0; i < this.tam; ++i) {
        			if (numeros[j] == this.datos.getDatoI(i)) {
        				posiciones.add(j, i);
        				++j;
        			}
        		}
        	}
        	 Collections.sort(posiciones);
        	 
        	 k = 0;
        	 for(int i = 0; i < this.tam; i++ ){
        	 
        		 if (i == posiciones.get(k)) {
        			 derecho.datos.setDatoI(i, numeros[k]);
        			 ++k;
        		 }
        		 else derecho.datos.setDatoI(i, this.datos.getDatoI(i));
        	 }
        
        	 pareja.datos.setDatos(izquierdo.datos.getDatos());
        	 this.datos.setDatos(derecho.datos.getDatos());
    }
    */

    public void cruceCX (CromosomaInteger pareja){//TODO a veces se queda en bucle
        //Cruce por ciclos
    	
    	CromosomaInteger izquierdo, derecho;
     	izquierdo = new CromosomaInteger (pareja);
     	derecho = new CromosomaInteger (this);
     	int current, first;
     	
     	first = this.datos.getDatoI(0);
     	current = pareja.datos.getDatoI(0);
     	
     	 for(int i = 0; i < this.tam; i++ ){//inicializamos dos auxiliares a 0 para saber que posiciones estan cogidas
             izquierdo.datos.setDatoI(0, i);
             derecho.datos.setDatoI(0, i);
         }
    	
     	
        while(first != current){
        	 for(int i = 0; i < this.tam; i ++){
                 if (this.datos.getDatoI(i) == current) {
                	 derecho.datos.setDatoI(i, current);
                	 current = pareja.datos.getDatoI(i);
                	 break;
                 }
             }

        }
        
     	first = pareja.datos.getDatoI(0);
     	current = this.datos.getDatoI(0);
     	
        while(first != current){
       	 for(int i = 0; i < this.tam; i ++){
                if (this.datos.getDatoI(i) == current) {
               	 izquierdo.datos.setDatoI(i, current);
               	 current = this.datos.getDatoI(i);
               	 break;
                }
            }

       }
        for(int i = 0; i < this.tam; i ++){//rellenamos las posiciones restantes
            if (izquierdo.datos.getDatoI(i) == 0) izquierdo.datos.setDatoI(i, this.datos.getDatoI(i));
            if (derecho.datos.getDatoI(i) == 0) derecho.datos.setDatoI(i, pareja.datos.getDatoI(i));
        }
        
        
        pareja.datos.setDatos(izquierdo.datos.getDatos());
  	  	this.datos.setDatos(derecho.datos.getDatos());
    }
    
    public void cruceCO (CromosomaInteger pareja){//TODO falta
        //Cruce por ciclos
    	CromosomaInteger izquierdo = new CromosomaInteger(this);
    	CromosomaInteger derecho = new CromosomaInteger(pareja);
        for(int i = 0; i < this.tam; i ++){ //Comprobar 
            
            

        }
        for(int i = 0; i < this.tam; i ++){
            
        }
        pareja.datos.setDatos(izquierdo.datos.getDatos());
  	  	this.datos.setDatos(derecho.datos.getDatos());
    }
    
    public void crucePROPIO (CromosomaInteger pareja){//TODO falta
        
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
