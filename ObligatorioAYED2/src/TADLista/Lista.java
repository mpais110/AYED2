package TADLista;

import java.util.Iterator;

public class Lista<T> implements Iterable<T> 
{
    protected NodoLista<T> inicio;

    public Lista() {
	this.inicio = null;
    }

    public void insertar(T dato)
    {
            NodoLista<T> nuevo = new NodoLista<T>(dato, inicio);
            inicio = nuevo;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            NodoLista<T> aux = inicio;

            @Override
            public boolean hasNext() {
                    return aux != null;
            }

            @Override
            public T next() {
                    T ret = aux.getDato();
                    aux = aux.getSig();
                    return ret;
            }

        };
    }

	
}
