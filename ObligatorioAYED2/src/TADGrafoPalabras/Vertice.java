package TADGrafoPalabras;



public class Vertice {

   
    private boolean existeNodo; 
    private Object elem;

    
    
    public Vertice(boolean existeNodo, Object elem) {
        this.existeNodo = existeNodo;
        this.elem = elem;
    }

    public Vertice() {
        this.existeNodo = false;
        this.elem = null;
    }

           
    public boolean getExisteNodo() {
        return existeNodo;
    }

    public void setExisteNodo(boolean existeNodo) {
        this.existeNodo = existeNodo;
    }
    
    

    public Object getElem() {
        return elem;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }
    
}
