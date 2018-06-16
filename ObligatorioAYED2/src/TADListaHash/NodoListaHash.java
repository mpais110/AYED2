package TADListaHash;

import TADGrafoPalabras.VerticeHash;

public class NodoListaHash {
	private VerticeHash dato;
	private NodoListaHash sig;
	
	public NodoListaHash(VerticeHash dato, NodoListaHash sig) {
		this.dato = dato;
		this.sig = sig;
	}

	public NodoListaHash(VerticeHash dato) {
		this.dato = dato;
	}

	public VerticeHash getDato() {
		return dato;
	}

	public void setDato(VerticeHash dato) {
		this.dato = dato;
	}

	public NodoListaHash getSig() {
		return sig;
	}

	public void setSig(NodoListaHash sig) {
		this.sig = sig;
	}

	@Override
	public String toString() {
		return this.dato.toString();
	}
	
	
	
	
	
}
