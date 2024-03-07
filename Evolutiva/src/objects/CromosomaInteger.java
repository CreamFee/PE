package objects;

import java.util.Random;

import interfaces.ICromosoma;
import logic.IntegerData;

public class CromosomaInteger implements ICromosoma{
    private IntegerData datos; //Array de doubles con los datos
    private int tam; //tamano cromosoma total va a coincidir con el numero de genes ya que la codificacion es en double
    private int genes; //numero genes
    private double[] rangos; //Valor inferior del rango de valores
    private double aptitud; //valor de fitness del cromosoma
    public Random r; //Random para generar los valores aleatorios
    
    public CromosomaInteger (int tamanio, int gens, double[] rangos, Random r){
        this.tam = tamanio;
        this.genes = gens;
        this.rangos = rangos;
        this.datos = new IntegerData(this.genes);
        this.r = r;
    }
    public CromosomaInteger (IntegerData data, int tamanio, int gens, double[] rangos, Random r, double aptitud){
    	this.datos = new IntegerData(data);
    	this.aptitud = aptitud;
        this.tam = tamanio;
        this.genes = gens;
        this.rangos = rangos;
        this.r = r;
    }
    public CromosomaInteger (CromosomaInteger a) {
    	this.datos = new IntegerData(a.datos);
    	this.aptitud = a.aptitud;
        this.tam = a.tam;
        this.genes = a.genes;
        this.rangos = a.rangos;
        this.r = a.r;
    }
    public void inicializar(){ //TODO modificar para inicializar correctamente los individuos
        int aux;
        double rango;
        for (int i = 0; i < this.genes; i++){
            aux = r.nextInt();
            //Valculamos la posici�n en la que deber�a encontrarse
            //Total de valores posibles = incremento en los rangos dividido 
            rango = rangos[2 * i + 1] - rangos[2 * i]; //Final menos inicial
            aux *= rango; //Ahora aux contiene el valor comprendido en nuestro rango desplazado, solo hay que desplazarlo de nuevo
            aux += rangos[2 * i];
            this.datos.setDatoI(aux, i);
        }
    }
    
    public void mutar(double probabilidad){ //TODO Modificar ya que al ser enteros no puede mutar de este modo
        double aux;
        double rango;
        for (int i = 0; i < this.genes; i++){
            aux = r.nextDouble();
            if(aux <= probabilidad){ 
            	aux = r.nextDouble();
            	
            	rango = rangos[2 * i + 1] - rangos[2 * i];
                aux *= rango;
                aux += rangos[2 * i];
                
                this.datos.setDatoI(aux, i);
            }
        }
    }
    
    public void cruceMonopunto(CromosomaInteger pareja){ //TODO Verificar cruces
        int posicion = r.nextInt(this.genes - 1) + 1;
        for (int i = posicion; i < this.genes; i++){
        	double aux = this.datos.getDatoI(i);
            this.datos.setDatoI(pareja.getDatos().getDatoI(i), i);
            this.datos.setDatoI(pareja.datos.getDatoI(i), i);
            pareja.datos.setDatoI(aux, i);
        }
    }
    
    public void cruceUniforme(CromosomaInteger pareja){ //TODO Verificar cruces
        for (int i = 0; i < this.tam; i++){
            if (r.nextDouble() < 0.5){
            	double aux = this.datos.getDatoI(i); 
                this.datos.setDatoI(pareja.datos.getDatoI(i), i);
                pareja.datos.setDatoI(aux, i);
            }
        }
    }
    
    public void cruceArimetrico(CromosomaInteger pareja){ //TODO Verificar cruces
    	 if (r.nextDouble() < 0.5){
    		double hijo;
	    	for (int i = 0; i < this.tam; i++){
	            	hijo = (( this.datos.getDatoI(i) + pareja.datos.getDatoI(i)) / 2);   
	            	this.datos.setDatoI(hijo, i);
	        }
	    	
    	 }
    }
    
    public void cruceBLX(CromosomaInteger pareja){ //TODO Verificar cruces
    	double max, min, I, alpha, hMax, hMin;
        for (int i = 0; i < this.tam; i++){
        	
        	if(this.datos.getDatoI(i) > pareja.datos.getDatoI(i)) {
				max = this.datos.getDatoI(i);
				min = pareja.datos.getDatoI(i);
			}
			else {
				min = this.datos.getDatoI(i);
				max = pareja.datos.getDatoI(i);
			}
        	
        	I = max - min;
        	alpha = r.nextDouble();
        	hMax = max + (I * alpha);
        	hMin = min - (I * alpha);
        	
        	this.datos.setDatoI(r.nextDouble(hMin, hMax), i);
            pareja.datos.setDatoI(r.nextDouble(hMin, hMax), i);
        }
    }
    
    public void crucePMX(CromosomaInteger pareja, int corte1, int corte2){ //TODO Cruce PMX, revisar cuando hay problemas de repetidos, y terminar de rellenarlos
        
        int tmp;
        for(int i = (corte1 - 1); i < corte2; i++ ){
            tmp = pareja.datos.getDatoI(i);
            pareja.datos.setDatoI(this.datos.getDatoI(i), i);
            this.datos.setDatoI(tmp, i);
        }
    }

    public void cruceOX(CromosomaInteger pareja, int corte1, int corte2){ //TODO Cruce OX, revisar cuando hay problemas de repetidos, y terminar de rellenarlos
        
        int tmp;
        for(int i = (corte1 - 1); i < corte2; i++ ){
            tmp = pareja.datos.getDatoI(i);
            pareja.datos.setDatoI(this.datos.getDatoI(i), i);
            this.datos.setDatoI(tmp, i);
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
    public double[] traducir(int[] tamGens) { 
    	return this.datos.getDatosDouble();
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
		return this.rangos;
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
}
