package TADLista;

public class ListaPalabra {
    private NodoListaPalabra inicio;
    //private NodoLista2 fin;

    //Constructor
    public ListaPalabra(){
        this.inicio=null;
        //this.fin=null;
    }

    //Inicio
    public void setInicio(NodoListaPalabra i){
        inicio=i;
    }
    public NodoListaPalabra getInicio(){
        return inicio;
    }

//    //Fin
//    public void setFin(NodoLista2 f){
//        fin=f;
//    }
//    public NodoLista2 getFin(){
//        return fin;
//    }


    /*****Métodos Básicos*****/

    //PRE:
    //POS: Retorna true si la lista no tiene nodos
    public boolean esVacia(){
        if (this.inicio==null)
            return true;
        else
            return false;
      }

    
    public void insertaOrdenadoDesc(NodoListaPalabra nodo)
    {
        NodoListaPalabra nuevo = nodo;
        NodoListaPalabra aux = this.inicio;
        
        if (this.esVacia())
        {
            this.inicio = nuevo;
        }
        else 
        {
            if (nodo.getPeso() >= this.inicio.getPeso())
            {
                this.agregarInicio(nodo);
            }
            else
            {
                while (aux.getSig() != null && aux.getSig().getPeso() >= nodo.getPeso())
                {
                    aux = aux.getSig();
                }
                nuevo.setSig(aux.getSig());
                aux.setSig(nuevo);              
            }
        }
    }

    //PRE: 
    //POS: Agrega un nuevo Nodo al principio de la lista
    public void agregarInicio(NodoListaPalabra n){

        n.setSig(inicio);
        this.inicio=n;
//        if(this.fin==null)//estoy insertando el primer nodo
//            this.fin=nuevo;
    }

    //POS: Recorre y muestra los datos de lista
    public String mostrarTresPrimeros()
    {
        String ret = "";
        if (!this.esVacia())
        {   
            int i = 1;
            NodoListaPalabra aux = this.inicio;
            while (aux != null && i <= 3)  
            {
                if(i == 3)
                    ret += aux.getPalabra().getPalabra() + ";" + aux.getPeso();
                else
                    ret += aux.getPalabra().getPalabra() + ";" + aux.getPeso() + "|";
                
                aux = aux.getSig();
                i++;
            }         
        }
        return ret;
    }

}