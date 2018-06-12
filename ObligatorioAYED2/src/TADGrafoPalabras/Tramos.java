package TADgrafoPalabras;

import TADLista.Lista;


public class Tramos {
    
    private boolean existe;
    private int peso;
    private int ordenEsc; //Autogenerado
    private Lista listaInt; // ¿Para qué era?
    

    public boolean getExiste() {
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

    public Tramos() {
            this.existe = false;
            this.peso = 0;
    }

    public Tramos(int peso) {
            this.existe = true;
            this.peso = peso;
    }
}
