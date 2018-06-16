
package TADLista;

import Dominio.Palabra;


public class NodoListaPalabra{
	private int peso;
        private Palabra palabra;
	private NodoListaPalabra sig;

    //Constructor
    public NodoListaPalabra(int n, Palabra pal){
        this.peso=n;
        this.palabra = pal;
        this.sig=null;
    }

    //Dato
    public void setPeso(int d){
        this.peso=d;
    }
    public int getPeso(){
        return this.peso;
    }

    //Siguiente
    public void setSig(NodoListaPalabra s){
        this.sig=s;
    }
    public NodoListaPalabra getSig(){
        return this.sig;
    }

    public Palabra getPalabra() {
        return palabra;
    }

    public void setPalabra(Palabra palabra) {
        this.palabra = palabra;
    }
    
    
}
