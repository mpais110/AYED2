package TADABBPalabras;

import Dominio.Palabra;

public class Nodo {
    
    //Atributos
    private Palabra dato;
    private Nodo der ;
    private Nodo izq ;

    
    //Constructores
    public Nodo(Palabra p){
        dato = p;
        izq = null;
        der = null;
     }

    public Nodo(Palabra p, Nodo i, Nodo d){
        dato = p;
        izq = i;
        der = d;
     }

    public Nodo() {
        dato = null;
        izq = null;
        der = null;
    }

    
    
    
    //Dato
    public Palabra getDato(){
        return dato;
    }
    
    
    void setDato(Palabra p){
        dato = p;
    }
    
    //Derecho
    public Nodo getDer(){
        return der;
    }
    
    
    void setDer(Nodo d){
       der = d;
    }
    
    //Izquierdo
    public Nodo getIzq(){
        return izq;
    }
    void setIzq(Nodo i){
        izq = i;
    }

    
    public String mostrarNodo() {
        String ret = "";
        
        if(this.izq != null)
        {
            ret += this.izq.mostrarNodo() + "|";
        }
        
        ret += this.dato.getPalabra() + ";" + this.dato.getCantidad();
        
        if(this.der != null)
        {
            ret += "|" + this.der.mostrarNodo(); 
        }
        
        return ret;
    }
    
    
    
    
}
