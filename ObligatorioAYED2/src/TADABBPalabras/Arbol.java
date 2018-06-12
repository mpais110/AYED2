package TADABBPalabras;

import Dominio.Palabra;
import TADABBPalabras.Nodo;
import java.util.Comparator;

public class Arbol<T> {
    
    
    private Nodo<T> raiz;
    private Comparator<T> comp;	
    private int maxNodos;

    public Nodo<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo<T> raiz) {
        this.raiz = raiz;
    }

    public Comparator<T> getComp() {
        return comp;
    }

    public void setComp(Comparator<T> comp) {
        this.comp = comp;
    }
    
    
    public int getMaxNodos() {
		return maxNodos;
	}

	public void setMaxNodos(int maxNodos) {
		this.maxNodos = maxNodos;
	}

	public Arbol(Comparator<T> comp, int maxNodos) {
            this.comp = comp;
            this.maxNodos = maxNodos;
    }

    public Arbol(Nodo<T> raiz, Comparator<T> comp, int maxNodos) {
            this.raiz = raiz;
            this.comp = comp;
            this.maxNodos = maxNodos;
    }

    public boolean esArbolVacio() {
            return this.raiz == null;
    }

    public boolean perteneceAB(T dato) {
            return perteneceABRec(raiz, dato);
    }

    private boolean perteneceABRec(Nodo<T> nodo, T dato) {
            if (nodo == null)
                    return false;
            else if (nodo.getDato() == dato)
                    return true;
            else {
                    boolean esta = perteneceABRec(nodo.getIzq(), dato);
                    if (esta)
                            return true;
                    else
                            esta = perteneceABRec(nodo.getDer(), dato);
                    return esta;

            }

    }

    private boolean pertenece(T dato) {
            return perteneceRec(raiz, dato);
    }

    private boolean perteneceRec(Nodo<T> nodo, T dato) {
            if (nodo == null)
                    return false;
            else if (dato == nodo.getDato())
                    return true;
            else if (comp.compare(dato,nodo.getDato()) < 0)
                    return perteneceRec(nodo.getIzq(), dato);
            else
                    return perteneceRec(nodo.getDer(), dato);
    }
 
    

    public T obtenerDato(Nodo<T> nodo, T dato) {
        if (nodo == null)
                return null;
        else if (comp.compare(dato, nodo.getDato()) == 0)
                return nodo.getDato();
        else if (comp.compare(dato, nodo.getDato()) < 0)
                return obtenerDato(nodo.getIzq(), dato);
        else
                return obtenerDato(nodo.getDer(), dato);
    }
    
    
    
    
    
    public int cantNodos(Nodo<T> nodo) 
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
    
    
    
//    public String insertar(T dato) {
//    	String ret = "RAÍZ";
//    	
//            if (raiz == null)
//            			raiz = new Nodo<T>(dato);
//            			
//            
//            else
//                    ret = insertarRec(dato, raiz);
//            return ret;
//    }
//
//    private String insertarRec(T dato, Nodo<T> nodo) {
//    	String ret = " ";
//    	
//            if (dato != nodo.getDato() && this.cantNodos(raiz) < maxNodos)
//                    if (comp.compare(dato, nodo.getDato()) < 0) {
//                            if (nodo.getIzq() == null)
//                            { nodo.setIzq(new Nodo<T>(dato));
//                            		ret = "IZQUIERDA DE ";}
//                            else
//                                 ret =   insertarRec(dato, nodo.getIzq());
//                    } else {
//
//                            if (nodo.getDer() == null)
//                                    {nodo.setDer(new Nodo<T>(dato));
//                            ret = "DERECHA DE " ;}
//                            else
//                            	ret =    insertarRec(dato, nodo.getDer());
//                    }
//return ret;
//    }
    
    
    
    
    public void insertar(T dato) {
        if (raiz == null)
        			raiz = new Nodo<T>(dato);
        
        else
                insertarRec(dato, raiz);
}

private void insertarRec(T dato, Nodo<T> nodo) {
        if (dato != nodo.getDato() && this.cantNodos(raiz) < maxNodos)
                if (comp.compare(dato, nodo.getDato()) < 0) {
                        if (nodo.getIzq() == null)
                                nodo.setIzq(new Nodo<T>(dato));
                        else
                                insertarRec(dato, nodo.getIzq());
                } else {

                        if (nodo.getDer() == null)
                                nodo.setDer(new Nodo<T>(dato));
                        else
                                insertarRec(dato, nodo.getDer());
                }

}




    public void listarAscendente() {
            listarAscRec(raiz);
    }

    private void listarAscRec(Nodo<T> nodo) {
            if (nodo != null) {
                    listarAscRec(nodo.getIzq());
                    System.out.println(nodo);
                    listarAscRec(nodo.getDer());
            }
    }



    
    
    public String listarDescendentePorCant() 
    {        
        String ret = "";
        
        if(this.raiz != null)
        {
            ret = this.raiz.mostrarNodoDesc();
        }  
        return ret;
        
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
    
    
    
    public void listarDescendente() {
            listarDescRec(raiz);
    }
   
    
    private void listarDescRec(Nodo<T> nodo) {
            if (nodo != null) 
            {
                    listarDescRec(nodo.getDer());
                    System.out.println(nodo);
                    listarDescRec(nodo.getIzq());
            }
    }
    
    
    

    

    
    

    // Pre: !esVacio()
    public T borrarMinimo() {
            // if(raiz != null){
            if (raiz.getIzq() == null) {
                    T ret = raiz.getDato();
                    raiz = raiz.getDer();
                    return ret;
            } else
                    return borrarMinRec(raiz);
            // }
    }

    private T borrarMinRec(Nodo<T> nodo) {
            if (nodo.getIzq().getIzq() == null) {
                    T ret = nodo.getIzq().getDato();
                    nodo.setIzq(nodo.getIzq().getDer());
                    return ret;
            } else
                    return borrarMinRec(nodo.getIzq());
    }
    
    
    
    public Nodo<T> obtenerPalabra(String palab, Nodo nodo) {
        if(nodo == null) {
            return nodo;
        } else {
                Palabra pal = (Palabra) nodo.getDato();
                if(palab.compareTo(pal.getPalabra()) == 0) 
                {
                    return nodo;
                } 
                else if (palab.compareTo(pal.getPalabra()) < 0)
                          return obtenerPalabra(palab, nodo.getIzq());
                     else 
                          return obtenerPalabra(palab, nodo.getDer());
        }
    }

    
    
//    //EL DE BOOLEAN QUE ANDA
//    // Pre: pertenece(dato)
//    public boolean borrar(T dato) {
//    	boolean ret = false;
//            if (raiz.getDato() == dato) {
//                    if (raiz.getIzq() == null && raiz.getDer() == null) // Caso simple
//                    		{raiz = null;
//                    		ret = true;}
//                    else if (raiz.getIzq() == null || raiz.getDer() == null) { // Caso
//                    // intermedio
//                            if (raiz.getIzq() == null)
//                            {raiz = raiz.getDer();
//                            		ret = true;}
//                            else
//                                    {raiz = raiz.getIzq();
//                            		ret = true;}
//                    } else { // Caso complicado
//                            if (raiz.getDer().getIzq() == null) {
//                                    raiz.setDato(raiz.getDer().getDato());
//                                    raiz.setDer(raiz.getDer().getDer());
//                                    ret = true;}
//                             else
//                                    raiz.setDato(borrarMinRec(raiz.getDer()));
//                    }
//            }
//            else
//                    ret = borrarRec(raiz, dato);
//            
//            return ret;
//    }
//
//    private boolean borrarRec(Nodo<T> nodo, T dato) {
//    	
//    	boolean ret = false;
//            if(comp.compare(dato,nodo.getDato()) < 0)
//            {
//                    if (nodo.getIzq().getDato() == dato) 
//                    {
//                            if (nodo.getIzq().getIzq() == null && nodo.getIzq().getDer() == null) // Caso simple
//                            { 
//                            	nodo.setIzq(null);
//                            	ret = true;
//                            }
//                            else if (nodo.getIzq().getIzq() == null || nodo.getIzq().getDer() == null) 
//                            { // Caso
//                                                                                                                                                    // intermedio
//                                    if (nodo.getIzq().getIzq() == null)
//                                    { 
//                                    	nodo.setIzq(nodo.getIzq().getDer());
//                                    	ret = true;
//                                    }
//                                    else
//                                    {  
//                                    	nodo.setIzq(nodo.getIzq().getIzq());
//                                    	ret = true;
//                                    }
//                            } 
//                            else 
//                            { // Caso complicado
//                                    if (nodo.getIzq().getDer().getIzq() == null) 
//                                    {
//                                            nodo.getIzq().setDato(nodo.getIzq().getDer().getDato());
//                                            nodo.getIzq().setDer(nodo.getIzq().getDer().getDer());
//                                            ret = true;
//                                    }
//                                     else
//                                     { 
//                                    	 nodo.getIzq().setDato(borrarMinRec(nodo.getIzq().getDer())); 
//                                    	 ret = true;
//                                    }
//                            }
//                    }
//                    else
//                        ret =    borrarRec(nodo.getIzq(), dato);
//            } 
//            else if(comp.compare(dato,nodo.getDato()) >= 0)
//            {
//                    if (nodo.getDer().getDato() == dato) 
//                    {
//                            if (nodo.getDer().getIzq() == null && nodo.getDer().getDer() == null) // Caso simple
//                                    {
//                            			nodo.setDer(null);
//                            			ret = true;
//                            		}
//                            else if (nodo.getDer().getIzq() == null || nodo.getDer().getDer() == null) 
//                            { // Caso intermedio
//                                    if (nodo.getDer().getIzq() == null)
//                                    {
//                                    	nodo.setDer(nodo.getDer().getDer());
//                                    	ret = true;
//                                    }
//                                    else
//                                    {  
//                                    	nodo.setDer(nodo.getDer().getIzq());
//                                    	ret = true;
//                                    }
//                            } 
//                            else 
//                            { // Caso complicado
//                                    if (nodo.getDer().getDer().getIzq() == null) 
//                                    {
//                                            nodo.getDer().setDato(nodo.getDer().getDer().getDato());
//                                            nodo.getDer().setDer(nodo.getDer().getDer().getDer());
//                                            ret = true;
//                                    }
//                                     else
//                                     {   
//                                    	 nodo.getDer().setDato(borrarMinRec(nodo.getDer().getDer())); 
//                                    	 ret = true;
//                                     }
//                            }
//                    }
//             }
//             else
//             {
////            	 if (nodo.getDato() == dato) 
////            	 {
////            		 nodo.setDato(null);
////            	 	 nodo.setDer(null);
////            	 	 ret = true;
////            	 }
////            	 else
////            	 {
//            		 ret =    borrarRec(nodo.getDer(), dato);
////         	 }
//             }
//            
//            
//            return ret;
//    }    
    
     
    
    
    
  
//EL COMUN QUE CORREGI PERO ROMPE TODO
  // Pre: pertenece(dato)
  public void borrar(T dato) {
          if (raiz.getDato() == dato) {
                  if (raiz.getIzq() == null && raiz.getDer() == null) // Caso simple
                  		{raiz = null;
                  		}
                  else if (raiz.getIzq() == null || raiz.getDer() == null) { // Caso intermedio
                          if (raiz.getIzq() == null)
                          {raiz = raiz.getDer();
                          		}
                          else
                                  {raiz = raiz.getIzq();
                          		}
                  } else { // Caso complicado
                          if (raiz.getDer().getIzq() == null) {
                                  raiz.setDato(raiz.getDer().getDato());
                                  raiz.setDer(raiz.getDer().getDer());
                                  }
                           else
                                  raiz.setDato(borrarMinRec(raiz.getDer()));
                  }
          }
          else
        	    borrarRec(raiz, dato);
          
  }

  private void borrarRec(Nodo<T> nodo, T dato) {
  	
          if(comp.compare(dato,nodo.getDato()) < 0)
          {
                  if (nodo.getIzq().getDato() == dato) 
                  {
                          if (nodo.getIzq().getIzq() == null && nodo.getIzq().getDer() == null) // Caso simple
                          { 
                          	nodo.setIzq(null);
                          
                          }
                          else if (nodo.getIzq().getIzq() == null || nodo.getIzq().getDer() == null) 
                          { // Caso
                                                                                                                                                  // intermedio
                                  if (nodo.getIzq().getIzq() == null)
                                  { 
                                  	nodo.setIzq(nodo.getIzq().getDer());
                         
                                  }
                                  else
                                  {  
                                  	nodo.setIzq(nodo.getIzq().getIzq());
                  
                                  }
                          } 
                          else 
                          { // Caso complicado
                                  if (nodo.getIzq().getDer().getIzq() == null) 
                                  {
                                          nodo.getIzq().setDato(nodo.getIzq().getDer().getDato());
                                          nodo.getIzq().setDer(nodo.getIzq().getDer().getDer());
                                  
                                  }
                                   else
                                   { 
                                  	 nodo.getIzq().setDato(borrarMinRec(nodo.getIzq().getDer())); 
         
                                  }
                          }
                  }
                  else
                	    //borrarRec(nodo.getIzq(), dato);
                  
                  
                  {
     		      	 if (nodo.getIzq().getDato() == dato && nodo.getIzq().getIzq() == null ) 
     		      	 {
     		      	 	 nodo.setIzq(null);
     		
     		      	 }
     		      	 else
     		      	 {
     		      		  borrarRec(nodo.getIzq(), dato);
     		      	 }
                
                  }
          } 
          else if(comp.compare(dato,nodo.getDato()) > 0)
          {
                  if (nodo.getDer().getDato() == dato) 
                  {
                          if (nodo.getDer().getIzq() == null && nodo.getDer().getDer() == null) // Caso simple
                                  {
                          			nodo.setDer(null);
                          			
                          		}
                          else if (nodo.getDer().getIzq() == null || nodo.getDer().getDer() == null) 
                          { // Caso intermedio
                                  if (nodo.getDer().getIzq() == null)
                                  {
                                  	nodo.setDer(nodo.getDer().getDer());
                                 
                                  }
                                  else
                                  {  
                                  	nodo.setDer(nodo.getDer().getIzq());
                      
                                  }
                          } 
                          else 
                          { // Caso complicado
                                  if (nodo.getDer().getDer().getIzq() == null) 
                                  {
                                          nodo.getDer().setDato(nodo.getDer().getDer().getDato());
                                          nodo.getDer().setDer(nodo.getDer().getDer().getDer());
                               
                                  }
                                   else
                                   {   
                                  	 nodo.getDer().setDato(borrarMinRec(nodo.getDer().getDer())); 
                                 
                                   }
                          }
                  }
           }
           else
           
             {
		      	 if (nodo.getDer().getDato() == dato && nodo.getDer().getDer() == null ) 
		      	 {
		      	 	 nodo.setDer(null);
		
		      	 }
		      	 else
		      	 {
		      		  borrarRec(nodo.getDer(), dato);
		      	 }
           
             }
   
  }    
      
    
    
    
    
    
//  ORIGINAL QUE NO BORRA BIEN  
// // Pre: pertenece(dato)
//    public void borrar(T dato) {
//            if (raiz.getDato() == dato) {
//                    if (raiz.getIzq() == null && raiz.getDer() == null) // Caso simple
//                            raiz = null;
//                    else if (raiz.getIzq() == null || raiz.getDer() == null) { // Caso
//                    // intermedio
//                            if (raiz.getIzq() == null)
//                                    raiz = raiz.getDer();
//                            else
//                                    raiz = raiz.getIzq();
//                    } else { // Caso complicado
//                            if (raiz.getDer().getIzq() == null) {
//                                    raiz.setDato(raiz.getDer().getDato());
//                                    raiz.setDer(raiz.getDer().getDer());
//                            } else
//                                    raiz.setDato(borrarMinRec(raiz.getDer()));
//                    }
//            }
//            else
//                    borrarRec(raiz, dato);
//    }
//
//    private void borrarRec(Nodo<T> nodo, T dato) {
//            if(comp.compare(dato,nodo.getDato()) < 0){
//                    if (nodo.getIzq().getDato() == dato) {
//                            if (nodo.getIzq().getIzq() == null && nodo.getIzq().getDer() == null) // Caso simple
//                                    nodo.setIzq(null);
//                            else if (nodo.getIzq().getIzq() == null || nodo.getIzq().getDer() == null) { // Caso
//                                                                                                                                                    // intermedio
//                                    if (nodo.getIzq().getIzq() == null)
//                                            nodo.setIzq(nodo.getIzq().getDer());
//                                    else
//                                            nodo.setIzq(nodo.getIzq().getIzq());
//                            } else { // Caso complicado
//                                    if (nodo.getIzq().getDer().getIzq() == null) {
//                                            nodo.getIzq().setDato(nodo.getIzq().getDer().getDato());
//                                            nodo.getIzq().setDer(nodo.getIzq().getDer().getDer());
//                                    } else
//                                            nodo.getIzq().setDato(borrarMinRec(nodo.getIzq().getDer()));
//                            }
//                    }
//                    else
//                            borrarRec(nodo.getIzq(), dato);
//            } else if(comp.compare(dato,nodo.getDato()) > 0){
//                    if (nodo.getDer().getDato() == dato) {
//                            if (nodo.getDer().getIzq() == null && nodo.getDer().getDer() == null) // Caso simple
//                                    nodo.setDer(null);
//                            else if (nodo.getDer().getIzq() == null || nodo.getDer().getDer() == null) { // Caso intermedio
//                                    if (nodo.getDer().getIzq() == null)
//                                            nodo.setDer(nodo.getDer().getDer());
//                                    else
//                                            nodo.setDer(nodo.getDer().getIzq());
//                            } else { // Caso complicado
//                                    if (nodo.getDer().getDer().getIzq() == null) {
//                                            nodo.getDer().setDato(nodo.getDer().getDer().getDato());
//                                            nodo.getDer().setDer(nodo.getDer().getDer().getDer());
//                                    } else
//                                            nodo.getDer().setDato(borrarMinRec(nodo.getDer().getDer()));
//                            }
//                    }
//                    else
//                            borrarRec(nodo.getDer(), dato);
//            }
//    }    
//       

    
    
    
    
    

}
