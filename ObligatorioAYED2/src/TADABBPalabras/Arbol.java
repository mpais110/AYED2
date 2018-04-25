package TADABBPalabras;

import Dominio.Palabra;

public class Arbol {
    
    
    private Nodo raiz;
    	
    
    public Arbol() {
        this.raiz = null;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    
    public boolean esArbolVacio() {
	return (raiz == null) ;
    }

    public void mostrarPreOrder(){
    	mostrarPreOrder(this.raiz);
    }
    
    
    public void mostrarPreOrder(Nodo a){
        if (a!=null){
            System.out.print(a.getDato().getPalabra()+"   ");
            mostrarPreOrder(a.getIzq());
            mostrarPreOrder(a.getDer());
        }
    }

    public void mostrarInOrder(){
    	mostrarInOrder(this.raiz);
    }
    
    public void mostrarInOrder(Nodo a){
        if (a!=null){
            mostrarInOrder(a.getIzq());
            System.out.print(a.getDato().getPalabra()+"  ");
            mostrarInOrder(a.getDer());
        }
    }
    
    
    public String InOrderlistarPalabras() 
    {
        String ret = "";
        
        if(this.raiz != null)
        {
            ret = this.raiz.mostrarNodo();
        }  
        return ret;
    }
    
        

    public void mostrarPosOrder(){
    	mostrarPosOrder(this.raiz);
    }
    
    
    public void mostrarPosOrder(Nodo a){
        if (a!=null){
            mostrarPosOrder(a.getIzq());
            mostrarPosOrder(a.getDer());
            System.out.print(a.getDato()+"  ");
        }
    }

    
        public boolean existePalabra(String palab) {
            
            Nodo nodo = null;
            nodo = obtenerPalabra(palab, this.raiz);
		
            return nodo != null;
	}
    
        
        public Nodo obtenerPalabra(String palab, Nodo nodo) {
            if(nodo == null) {
                return nodo;
            } else {
                    if(palab.compareTo(nodo.getDato().getPalabra()) == 0) 
                    {
                        return nodo;
                    } 
                    else if (palab.compareTo(nodo.getDato().getPalabra()) < 0)
                              return obtenerPalabra(palab, nodo.getIzq());
                         else 
                              return obtenerPalabra(palab, nodo.getDer());
            }
	}

    
    public int cantNodos(Nodo nodo) 
    {
	int cont = 0;
	if(nodo != null)
        {
            cont += cantNodos(nodo.getIzq()); 	//cuenta subarbol izquierdo
            cont++; 							// contabilizar el nodo visitado
            cont += cantNodos(nodo.getDer());	//cuenta subarbol derecho
            
	}
            return cont;
    }
    

    public int obtenerPeso(Nodo nodo) {
		int peso     = 0;
		int peso_izq = 0;
		int peso_der = 0;

		if(nodo != null) {
			peso_izq = cantNodos(nodo.getIzq());
			peso_der = cantNodos(nodo.getDer());
			peso = peso_izq + peso_der;
            
		}
		return peso;
	}

     
    public void insertarElemento(Palabra p, Nodo nodo) {
	
        Nodo nuevo = new Nodo(p);

        if (this.esArbolVacio())
            this.raiz = nuevo;

        else if (p.getPalabra().compareTo(nodo.getDato().getPalabra()) < 0)
        {   // p < dato => insertaré en subárbol izq.
            if(nodo.getIzq() == null)
            {
                nuevo = new Nodo(p);
                nodo.setIzq(nuevo);
             }
             else
                 insertarElemento(p, nodo.getIzq());
        }
        else if (p.getPalabra().compareTo(nodo.getDato().getPalabra()) > 0)
        {   // p > dato => insertaré en subárbol derecho
	    if(nodo.getDer() == null)
            {
		nuevo = new Nodo(p);
		nodo.setDer(nuevo);
	    }
            else
		insertarElemento(p, nodo.getDer());
        }
    }
    
    
    public int cantHojas(Nodo nodo){
    	if (nodo.getDer() == null)
    		if (nodo.getIzq() == null)
    			return 1;
    		else
    			return cantHojas(nodo.getIzq());
    	else if (nodo.getIzq()== null)
    			return cantHojas(nodo.getDer());
    		else 
    			return cantHojas(nodo.getIzq())+cantHojas(nodo.getDer());
    }

    public Nodo borrarMinimo(Nodo nodo){
    	if( nodo == null )
            return nodo;
        
        if (nodo.getIzq()!= null ) {
            nodo.setIzq(borrarMinimo( nodo.getIzq() )) ;
            return nodo;
        } else
            return nodo.getDer();
    }
    

    
    public int altura(){
       	return 0;
    }
    
}
