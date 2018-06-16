
package TADLista;


public class NodoLista2{
	private int dato;
	private NodoLista2 sig;

    //Constructor
    public NodoLista2(int n){
        this.dato=n;
        this.sig=null;
    }

    //Dato
    public void setDato(int d){
        this.dato=d;
    }
    public int getDato(){
        return this.dato;
    }

    //Siguiente
    public void setSig(NodoLista2 s){
        this.sig=s;
    }
    public NodoLista2 getSig(){
        return this.sig;
    }
}
