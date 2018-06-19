package TADGrafoPalabras;

import java.util.Arrays;

import TADListaHash.ListaHash;

public class Hash {
	private ListaHash[] arr;
	private int tope;
	private int cantMax;
        

    public ListaHash[] getArr() {
        return arr;
    }

    public void setArr(ListaHash[] arr) {
        this.arr = arr;
    }

    public int getTope() {
        return tope;
    }

    public void setTope(int tope) {
        this.tope = tope;
    }

    public int getCantMax() {
        return cantMax;
    }

    public void setCantMax(int cantMax) {
        this.cantMax = cantMax;
    }
    
	public Hash(int cantMax) {
		
		this.tope = primoSup(cantMax);
		
		this.arr = (ListaHash[])new ListaHash[tope];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new ListaHash();
		}
		
		this.cantMax = cantMax;
		
	}
    
    public int identifPalabra (String pal) {
        //Calculo identificador de palabra. Dado que por ejemplo "hola" y "halo" tienen igual id, se utiliza hash abierto.
        int idPalabra = 0;
        for (int j = 0; j < pal.length(); j++) 
            idPalabra += (int)pal.charAt(j);  
        
        return idPalabra;
    }    
                
	public void insertar(int clave, VerticeHash dato){
		int posHash = fHash(clave);
		arr[posHash].insertar(dato);
	}
	
	public void insertar(String clave, VerticeHash dato){
		int posHash = fHash(clave.hashCode());
		arr[posHash].insertar(dato);
	}
	
	public int fHash(int clave) {
		return clave % tope;
	}

	private int primoSup(int num) {
		while(!esPrimo(++num));
		return num;
	}
	
	private boolean esPrimo(int num){
		for(int i = 2; i<= num/2; i++)
			if(num % i == 0)
				return false;
		return true;
	}

	@Override
	public String toString() {
		return Arrays.toString(arr);
	}
		
}
