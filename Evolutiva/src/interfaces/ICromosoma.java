
package interfaces;

public interface ICromosoma{
	void inicializar();
    void mutar(double probabilidad);
    void cruceMonopunto(ICromosoma pareja);
    void cruceUniforme(ICromosoma pareja);
    void setAptitud(double fitness);
    double getAptitud();
    /*
    void setDatos(T[] datos);
    T[] getDatos();
    <T> void setDatosI(T aux, int i);*/
    int getTamano();
    int getGenes();
    double[] getRangos();
	double[] traducir();
	
}
