package TADGrafoPalabras;

import Dominio.Palabra;



public class Vertice {

   
    private boolean existeNodo; 
    private Palabra palabra;

    
    
    public Vertice(boolean existeNodo, Palabra elem) {
        this.existeNodo = existeNodo;
        this.palabra = elem;
    }

    public Vertice() {
        this.existeNodo = false;
        this.palabra = null;
    }

           
    public boolean getExisteNodo() {
        return existeNodo;
    }

    public void setExisteNodo(boolean existeNodo) {
        this.existeNodo = existeNodo;
    }
    
    

    public Palabra getPalabra() {
        return palabra;
    }

    public void setPalabra(Palabra elem) {
        this.palabra = elem;
    }
    
}
