package TADGrafoPalabras;

import TADLista.Lista2;


public class Tramos 
{
    private boolean existe;
    private int peso;
    private Lista2 orden;

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Lista2 getOrden() {
        return orden;
    }

    public void setOrden(Lista2 orden) {
        this.orden = orden;
    }
    
    public Tramos(int orden) {
        this.existe = true;
        this.peso = 1;
        this.orden = new Lista2();
        this.orden.agregarInicio(orden);
    }    
    
    public Tramos() 
    {
        this.existe = false;
        this.peso = 0;
        this.orden = new Lista2();
    }
    
}
