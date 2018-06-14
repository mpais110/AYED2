package TADLista;

public class NodoLista<T> {
	private T dato;
	private NodoLista<T> sig;
	
	public NodoLista(T dato, NodoLista<T> sig) {
		this.dato = dato;
		this.sig = sig;
	}

	public NodoLista(T dato) {
		this.dato = dato;
	}

	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}

	public NodoLista<T> getSig() {
		return sig;
	}

	public void setSig(NodoLista<T> sig) {
		this.sig = sig;
	}

	@Override
	public String toString() {
		return this.dato.toString();
	}
	
	
	
	
	
}
