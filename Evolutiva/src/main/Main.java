package main;
import java.util.Random;

import interfaces.IFuncion;
import interfaces.ISeleccion;
import logic.Funcion1;
import logic.Funcion2;
import logic.Funcion3;
import logic.Funcion4;
import logic.Funcion5;
import selections.SeleccionEstocasticoUniversal;
import selections.SeleccionRestos;
import selections.SeleccionRuleta;
import selections.SeleccionTorneoDet;
import selections.SeleccionTorneoProb;
import selections.SeleccionTruncamiento;
import view.MainWindow;

public class Main {
	public static int poblacion = 100; //Cantidad de individuos
    public static double mutar = 0.05; //probabilidad de mutacion
    public static double cruce = 0.6; //probabilidad de cruce
    public static double precision = 0.001; // double para indicar 
    public static boolean tipoCruce = false; //false para monopunto, true para uniforme
    public static int generaciones = 100; // numero de generaciones
    public static int seleccion = 1; // de 1 a 6
    public static double elitismo = 0.02; //Elitismo, cantidad de poblacion que va a mantenerse de una a otra
    public static int dimension = 1; // a partir de 1 para la funcion 5

    public static int funcionElegida = 2; // de 1 a 5 para elegir funcion
    
    private IFuncion funcion;
    private ISeleccion selec; //Crear el metodo preferido
    
    
    private double[] maximums; //Guarda los maximos de cada generacion
    private double[] averages; //Guarda las medias de cada generacion
    private double[] absoluteMax; //Guarda el absoluto maximo hasta el momento
    private double[] puntosX;//guarda el resultaod de las x
    private int cont; //para saber cuantos resultados iguales hay
    
    private Random r; //Random utilizado en toda la ejecucion
    
    public Main(){
    	this.r = new Random (System.currentTimeMillis());
    }
    public static void main(String[] args) {
    	Main main = new Main();
    	MainWindow interfaz = new MainWindow(main);
    }
    
    public void init() {
        maximums = new double [this.generaciones];
        averages = new double [this.generaciones];
        absoluteMax = new double [this.generaciones];
        puntosX = new double [32];
    }
    
    private void evaluarPoblacion() {
        funcion.evaluarPoblacion(); //Evalua todos los individuos
    }
    
    private void recogerDatos(int generacionActual) {
    	this.averages[generacionActual] = funcion.getPromedio();

    	if(funcionElegida == 1) {
    		this.maximums[generacionActual] = funcion.getMax();	
    		if (generacionActual == 0) {
        		this.absoluteMax[generacionActual] = this.maximums[generacionActual];
    			this.puntosX = funcion.getXX();
    		}
        	else if (this.maximums[generacionActual] > this.absoluteMax[generacionActual - 1]) {
        		this.absoluteMax[generacionActual] = this.maximums[generacionActual];
    			this.puntosX = funcion.getXX();
    			
        	}

        	else
        		this.absoluteMax[generacionActual] = this.absoluteMax[generacionActual - 1];
    	}
    	else {
    		this.maximums[generacionActual] = funcion.getMin();	
    		if (generacionActual == 0) {
        		this.absoluteMax[generacionActual] = this.maximums[generacionActual];
    			this.puntosX = funcion.getXX();
    		}
        	else if (this.maximums[generacionActual] < this.absoluteMax[generacionActual - 1]) {
        		this.absoluteMax[generacionActual] = this.maximums[generacionActual];
    			this.puntosX = funcion.getXX();
        	}

        	else
        		this.absoluteMax[generacionActual] = this.absoluteMax[generacionActual - 1];
    	}
    	
    }
    private void cruzar () {
    	funcion.cruzar();
    }
    private void mutar() {
    	funcion.mutar();
    }
    
    private void seleccionar() {
    	
    	switch (seleccion){
    		case 1:
    	    	selec = new SeleccionRuleta(this.poblacion, funcion.getIndividuos(), this.r);
    			break;
    		case 2:
    	    	selec = new SeleccionTorneoDet(this.poblacion, funcion.getIndividuos(), this.r);
    			break;
    		case 3:
    	    	selec = new SeleccionTorneoProb(this.poblacion, funcion.getIndividuos(), this.r);
    			break;
    		case 4:
    	    	selec = new SeleccionEstocasticoUniversal(this.poblacion, funcion.getIndividuos(), this.r);
    			break;
    		case 5:
    	    	selec = new SeleccionTruncamiento(this.poblacion, funcion.getIndividuos(), this.r);
    			break;
    		case 6:
    	    	selec = new SeleccionRestos(this.poblacion, funcion.getIndividuos(), this.r);
    			break;

    	}
    	
    	funcion.seleccionar(selec.getSelection());
    }
    private void ejecutar() {
        init();
        evaluarPoblacion();
        recogerDatos(0);
//        System.out.println("GENERACION: " + 0);
//        System.out.println("Maximo de la generacion: " + maximums[0]);
//        System.out.println("Maximo absoluto: " + absoluteMax[0]);
//        System.out.println("Media de la generacion: " + averages[0] + "\n");
        for (int i = 1; i < this.generaciones; i++) {
            // Seleccionar individuos
        	this.funcion.corregirAptitud();
        	this.funcion.generarElite();
        	seleccionar();
        	cruzar();
        	mutar();
        	this.funcion.introducirElite();
        	evaluarPoblacion();
        	recogerDatos(i);
        	/*System.out.println("GENERACION: " + i); //usado para pruebas
        	System.out.println("Maximo de la generacion: " + maximums[i]);
            System.out.println("Maximo absoluto: " + absoluteMax[i]);
            System.out.println("Media de la generacion: " + averages[i] + "\n");
        	*/
        }
        
    }
    
    public double[] getAvr(){
    	return this.averages;
    }

	 public double[] getAbsMax(){
	    	return this.absoluteMax;
	    }
	 public double[] getMax(){
	 	return this.maximums;
	 }
	 public int getCont() {
		 return this.cont;
	 }
	 public double[] getXfinal() {
		 return this.puntosX;
	 }
	 
	 public void set_funcion(int f) {
		 this.funcionElegida = f;
		 switch (f) {
 	 	case 1:
 	 		this.funcion = new Funcion1(poblacion, precision, mutar, cruce, tipoCruce, r, elitismo);
 	 		break;
 	 	case 2:
 	 		this.funcion = new Funcion2(poblacion, precision, mutar, cruce, tipoCruce, r, elitismo);
 	 		break;
 	 	case 3:
 	 		this.funcion = new Funcion3(poblacion, precision, mutar, cruce, tipoCruce, r, elitismo);
 	 		break;
 	 	case 4:
 	 		this.funcion = new Funcion4(poblacion, precision, mutar, cruce, tipoCruce, r, elitismo);
 	 		break;
 	 	case 5:
 	 		this.funcion = new Funcion5(poblacion, precision, mutar, cruce, tipoCruce, r, elitismo);
 	 		break;
 	 
      }
		 this.ejecutar();
	 }
	 
    
}
