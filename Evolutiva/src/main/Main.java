package main;
import java.util.Random;

import interfaces.IFuncion;
import interfaces.ISeleccion;
import logic.Funcion1;
import logic.Funcion2;
import selections.SeleccionRuleta;
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
    
    private IFuncion funcion;
    private ISeleccion ruleta; //Crear el metodo preferido
    
    
    private double[] maximums; //Guarda los maximos de cada generacion
    private double[] averages; //Guarda las medias de cada generacion
    private double[] absoluteMax; //Guarda el absoluto maximo hasta el momento
    
    private Random r;
    
    public Main(){
    	this.r = new Random (System.currentTimeMillis());
    }
    public static void main(String[] args) {
    	Main main = new Main();
    	main.ejecutar();
    	MainWindow interfaz = new MainWindow(main);
    }
    
    public void init() {
        maximums = new double [this.generaciones];
        averages = new double [this.generaciones];
        absoluteMax = new double [this.generaciones];
        
        this.funcion = new Funcion2(poblacion, precision, mutar, cruce, tipoCruce, r, elitismo);
    }
    
    private void evaluarPoblacion() {
        funcion.evaluarPoblacion(); //Evalua todos los individuos
    }
    
    private void recogerDatos(int generacionActual) {
    	this.averages[generacionActual] = funcion.getPromedio();
    	this.maximums[generacionActual] = funcion.getMax();
    	if (generacionActual == 0)
    		this.absoluteMax[generacionActual] = this.maximums[generacionActual];
    	else if (this.maximums[generacionActual] > this.absoluteMax[generacionActual - 1])
    		this.absoluteMax[generacionActual] = this.maximums[generacionActual];
    	
    	else
    		this.absoluteMax[generacionActual] = this.absoluteMax[generacionActual - 1];
    }
    private void cruzar () {
    	funcion.cruzar();
    }
    private void mutar() {
    	funcion.mutar();
    }
    
    private void seleccionar() {
    	ruleta = new SeleccionRuleta(this.poblacion, funcion.getIndividuos(), this.r);
    	funcion.seleccionar(ruleta.getSelection());
    }
    private void ejecutar() {
        init();
        evaluarPoblacion();
        recogerDatos(0);
        /*
        //Con esto comprobamos las direcciones d ememoria, efectivamente son diferentes
        for(int i = 0; i < this.poblacion; i++) {
    		System.out.println(this.funcion.getIndividuos()[i].getDatos());
    	}
        
        double[][] aux;
    	aux = funcion.getFenotipos();
        for(int i = 0; i < this.poblacion; i++) {
        	System.out.println("X0 = " + aux[i][0] + " X1 = " + aux[i][1]);
        }
        */
         //Print de todos los individuos
        System.out.println("GENERACION: " + 0);
        System.out.println("Maximo de la generacion: " + maximums[0]);
        System.out.println("Maximo absoluto: " + absoluteMax[0]);
        System.out.println("Media de la generacion: " + averages[0] + "\n");
        for (int i = 1; i < this.generaciones; i++) {
            // Seleccionar individuos
        	funcion.generarElite();
        	seleccionar();
        	cruzar();
        	mutar();
        	funcion.introducirElite();
        	evaluarPoblacion();
        	recogerDatos(i);
        	System.out.println("GENERACION: " + i);
        	System.out.println("Maximo de la generacion: " + maximums[i]);
            System.out.println("Maximo absoluto: " + absoluteMax[i]);
            System.out.println("Media de la generacion: " + averages[i] + "\n");
        	try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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
 
    
    
}
