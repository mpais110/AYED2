package TADABBPalabras;

import Dominio.Palabra;

public class Nodo<T> {
    
    //Atributos
    private T dato;
    private Nodo<T> der ;
    private Nodo<T> izq ;

    
    //Constructores

    public Nodo() {
        dato = null;
        izq = null;
        der = null;
    }
    
    
    public Nodo(T dato) {
            this.dato = dato;
    }

    public Nodo(T dato, Nodo<T> izq, Nodo<T> der) {
        this.dato = dato;
        this.izq = izq;
        this.der = der;
    }

    
    public T getDato() {
            return dato;
    }

    public void setDato(T dato) {
            this.dato = dato;
    }

    public Nodo<T> getIzq() {
            return izq;
    }

    public void setIzq(Nodo<T> izq) {
            this.izq = izq;
    }

    public Nodo<T> getDer() {
            return der;
    }

    public void setDer(Nodo<T> der) {
            this.der = der;
    }


    
    public String mostrarNodo() {
        String ret = "";
        
        
        if(this.izq != null)
        {
            ret += this.izq.mostrarNodo() + "|";
        }
        
        Palabra pal = (Palabra) this.dato;
        ret += pal.getPalabra() + ";" + pal.getCantidad();
        
        if(this.der != null)
        {
            ret += "|" + this.der.mostrarNodo(); 
        }
        
        return ret;
    }
    
    
    
    
    
        public String mostrarNodoDesc(int n) {
        String ret = "";
        
        
        if(this.der != null)
        {
            ret += this.der.mostrarNodoDesc(n) + "|";
        }
        
        Palabra pal = (Palabra) this.dato;
        n = n + 1;
        ret += pal.getPalabra() + ";" + pal.getCantidad();
        
        if(this.izq != null)
        {
            ret += "|" + this.izq.mostrarNodoDesc(n); 
        }
        
        return ret;
    }
    
    
}
