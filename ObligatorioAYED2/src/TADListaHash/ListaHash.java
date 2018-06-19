package TADListaHash;

import TADGrafoPalabras.VerticeHash;


public class ListaHash {
    private NodoListaHash inicio;
    int tamano;
   
    //Constructor
    public ListaHash()
    {
        inicio = null;
        tamano = 0;
    }
        
    public NodoListaHash getInicio() {
        return inicio;
    }
 
    public void insertar(VerticeHash dato)
    {
        NodoListaHash nuevo = new NodoListaHash(dato, inicio);
        inicio = nuevo;
    }
    
    public int tamano() 
    {
        return tamano;
    }
    
    public boolean estaVacia()
    {
        return (inicio == null);
    }
    
    public void eliminarPrimero()
    {
        inicio = inicio.getSig();
        tamano--;
    }
    
    public void vaciarLista() {
        this.inicio = null;
        this.tamano = 0;
    }
    
    public void eliminarEnIndice(int ind)
    {
        if (ind == 0)
        {
            inicio = inicio.getSig();
        }
        else
        {
            int contador = 0;
            NodoListaHash aux = inicio;
            while(contador < ind - 1)
            {
                aux = aux.getSig();
                contador++;
            }
            aux.setSig(aux.getSig().getSig());
        }
        tamano--;
    }
      
    public NodoListaHash obtenerElemento(String palab){
        NodoListaHash aux = this.inicio;
        while (aux != null && !aux.getDato().getPalabra().equals(palab))
            aux = aux.getSig();
        //encontré dato o llegué al final
        return aux;
    }
    
}