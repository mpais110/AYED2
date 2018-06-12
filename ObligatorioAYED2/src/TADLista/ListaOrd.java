package TADLista;

public class ListaOrd<T extends Comparable<T>> extends Lista<T> {

	@Override
	public void insertar(T elem)
	{
		if(this.inicio == null || elem.compareTo(inicio.getDato())<=0)
		{
			NodoLista<T> nuevo = new NodoLista<T>(elem,inicio);
			inicio = nuevo;
		}
		else
		{
			NodoLista<T> aux = inicio;
			while(aux.getSig() != null)
			{
				if(elem.compareTo(aux.getSig().getDato())<=0){
					NodoLista<T> nuevo = new NodoLista<T>(elem,aux.getSig());
					aux.setSig(nuevo);
					return;
				}
				else
					aux = aux.getSig();
			}
			aux.setSig(new NodoLista<T>(elem));
				
		}
	}
	
}
