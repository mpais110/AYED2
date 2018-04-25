package uy.ort.ob20181;

import Dominio.Palabra;
import TADABBPalabras.Arbol;
import TADABBPalabras.Nodo;
import uy.ort.ob20181.Retorno.Resultado;

public class Sistema implements ISistema {
    
    private Arbol palabras;

    
    public Arbol getPalabras() {
        return palabras;
    }

    public void setPalabras(Arbol palabras) {
        this.palabras = palabras;
    }
 

    //PRE CONDICIONES: Se recibe la cantidad máxima de palabras a almacenar (maxPalabras). "MaxPalabras" debe ser un entero.
    //POS CONDICIONES: Retorna ERROR_1 si maxPalabras <= 0, u OK si maxPalabras > 0 y se crearon las estructuras correspondientes.  
    @Override
    public Retorno inicializarSistema (int maxPalabras) {

        Retorno ret = new Retorno();

        ret.resultado = Resultado.NO_IMPLEMENTADA;
        ret.valorString = "NO_IMPLEMENTADA";

        //Error 1 si maxPalabras es menor o igual a 0.
        if (maxPalabras <= 0)
        {
            ret.resultado = Resultado.ERROR_1;
            ret.valorString = "ERROR_1";
        }
        else
        {
            //Arbol de palabras
            this.palabras = new Arbol();


            ret.resultado = Resultado.OK;
            ret.valorString = "OK";
        }

        return ret;   

    }

    
    
    //PRE CONDICIONES: Se consideran creadas todas las estructuras
    //POS CONDICIONES: Se devuelve OK, si se logró eliminar correctamente
    @Override
    public Retorno destruirSistema() {
        Retorno ret = new Retorno();

        //Desreferenciar arbol de palabras
        if (!palabras.esArbolVacio())
            palabras = null;
        
        
        //Indico que quiero que pase el Garbage Collector
        System.gc();
        
    
        ret.resultado = Resultado.OK;
        ret.valorString = "OK";
        
        return ret;
            
    }
    

    //PRE CONDICIONES: Se recibe una cadena de caracteres.
    //POS CONDICIONES: Retorna ERROR_1 si el texto es vacío, u OK si se pudo analizar el texto y grabarlo en las estructuras correspondientes.  
    @Override
    public Retorno analizarTexto(String texto) {
        Retorno ret = new Retorno();


        
        //Error 1 si el texto es vacío
        if(texto.equals(""))
        {
            ret.resultado = Resultado.ERROR_1;
            ret.valorString = "ERROR_1";
        }
        else
        {
            //Grabo palabras en arbol        
            
            //Paso a minusculas y separo
            texto = texto.toLowerCase();
            String[] palab = texto.split(" "); 
            
            for(int i=0; i < palab.length; i++)
            {
                //Busco si existe palabra y si está le sumo 1 al contador
                Nodo nodo = palabras.obtenerPalabra(palab[i], palabras.getRaiz());
                if (nodo != null)
                {
                    nodo.getDato().setCantidad(nodo.getDato().getCantidad() + 1);
                }
                else
                {   
                    //Inserto en arbol la palabra nueva
                    Palabra nueva = new Palabra(palab[i],0);
                    palabras.insertarElemento(nueva, palabras.getRaiz()); 
                }
            }
                        
            ret.resultado = Resultado.OK;
            ret.valorString = "OK"; 
        }

        return ret;   
    }

    
    //PRE CONDICIONES: Se recibe un valor entero.
    //POS CONDICIONES: Retorna ERROR_1 si "n" es menor o igual a cero, ERROR_2 si hay menos de "n" palabras diferentes en el texto, y un listado de las "n" palabras más usadas en otro caso.
    @Override
    public Retorno rankingPalabras(int n) {
        
        Retorno ret = new Retorno();

        //Error 1 si "n" <= 0
        if(n<=0)
        {
            ret.resultado = Resultado.ERROR_1;
            ret.valorString = "ERROR_1"; 
        }
        else
        {
            //Error 2 si hay menos de "n"·palabras
            if(palabras.cantNodos(palabras.getRaiz()) < n)
            {
                ret.resultado = Resultado.ERROR_2;
                ret.valorString = "ERROR_2";
            }
            else
            {
                //Traigo las "n" palabras más usadas

                
                ret.resultado = Resultado.OK;
                ret.valorString = "OK";         
            }
        }
        return ret;   
    }

    
    //PRE CONDICIONES: Se recibe una cadena de caracteres.
    //POS CONDICIONES: Retorna ERROR_1 si el texto es vacío, u OK si se pudo analizar el texto y grabarlo en las estructuras correspondientes.  
    @Override
    public Retorno aparicionesPalabra(String palabra) {
        
        Retorno ret = new Retorno();

        //Busco la palabra en el arbol
        Nodo nodo = palabras.obtenerPalabra(palabra, palabras.getRaiz());
        if (nodo != null)
        {
            //Retorno la cantidad de repeticiones
            ret.valorEntero = nodo.getDato().getCantidad();
            ret.resultado = Resultado.OK;
            ret.valorString = "OK";
        }
        else
        {   
            //Error 1 si no se encontró la palabra.
            ret.resultado = Resultado.ERROR_1;
            ret.valorString = "ERROR_1";
        }
        
        return ret;   
    }

    

    //POS CONDICIONES: Retorna un listado de todas las palabras almacenadas, o vacío si no hay palabras. 
    @Override
    public Retorno todasPalabras() {
        Retorno ret = new Retorno();

        ret.valorString = this.palabras.InOrderlistarPalabras();
        ret.resultado = Resultado.OK;

        return ret;
    }
	
}
