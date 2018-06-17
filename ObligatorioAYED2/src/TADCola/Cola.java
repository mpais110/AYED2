package TADCola;

public class Cola<T> {
	private NodoCola<T> inicio;
	private NodoCola<T> fin;
	private int largo;
	
	public Cola() {
		
	}
	
	public void encolar(T dato)
	{
		if(inicio == null)
		{
			inicio = fin = new NodoCola<T>(dato);
		}else{
			fin.setSig(new NodoCola<T>(dato));
			fin = fin.getSig();
		}
		largo++;
	}
	
	//Pre: !esVacia()
	public T desencolar(){
		T dato = inicio.getDato();
		inicio = inicio.getSig();
		largo--;
		if(inicio == null)
			fin = null;
		return dato;
	}
	
	public boolean esVacia(){
		return largo == 0;
	}
	
}
