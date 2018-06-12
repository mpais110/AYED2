package TADLista;


public class Lista<T> {
    private NodoLista<T> inicio;
    int tamano;
    

    //Constructor
    public Lista()
    {
        inicio = null;
        tamano = 0;
    }
    
    
    public NodoLista<T> getInicio() {
        return inicio;
    }

    
    public void insertar(T dato)
    {
        NodoLista<T> nuevo = new NodoLista<T>(dato, inicio);
        inicio = nuevo;
    }
    
    
//    public void insertaInicioChofer(Object obj)
//    {
//        if (inicio == null)
//        {
//            inicio = new NodoLista(obj);
//        }
//        else
//        {
//            NodoLista aux = inicio;
//            NodoLista nuevo = new NodoLista(obj);
//            nuevo.setSig(aux);
//            inicio = nuevo;
//        }
//        tamano++;
//    }
    
    
   
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
            NodoLista aux = inicio;
            while(contador < ind - 1)
            {
                aux = aux.getSig();
                contador++;
            }
            aux.setSig(aux.getSig().getSig());
        }
        tamano--;
    }
}


