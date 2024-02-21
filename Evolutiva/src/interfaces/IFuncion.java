package interfaces;

public interface IFuncion {
    void evaluarPoblacion();
    double getMax();
    double getPromedio();
    void cruzar();
    void mutar();
    ICromosoma[] getIndividuos();
    void seleccionar(int[] seleccion);
    double[][] getFenotipos();
}