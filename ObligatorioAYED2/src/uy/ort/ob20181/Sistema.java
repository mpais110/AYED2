package uy.ort.ob20181;

import Dominio.Palabra;
import Dominio.PalabraComparatorCantRep;
import Dominio.PalabraComparatorPalabra;
import TADABBPalabras.Arbol;
import TADABBPalabras.Nodo;
import uy.ort.ob20181.Retorno.Resultado;

public class Sistema implements ISistema {
    
    private Arbol<Palabra> palabras;
    private Arbol<Palabra> repeticiones;

    
    public Arbol<Palabra> getPalabras() {
        return palabras;
    }

    public void setPalabras(Arbol<Palabra> palabras) {
        this.palabras = palabras;
    }
    
    public Arbol<Palabra> getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(Arbol<Palabra> repeticiones) {
        this.repeticiones = repeticiones;
    }
 

    //PRE CONDICIONES: Se recibe la cantidad máxima de palabras a almacenar (maxPalabras). "MaxPalabras" debe ser un entero.
    //POS CONDICIONES: Retorna ERROR_1 si maxPalabras <= 0, u OK si maxPalabras > 0 y se crearon las estructuras correspondientes.  
    @Override
    public Retorno inicializarSistema (int maxPalabras) {

        Retorno ret = new Retorno();

        //Error 1 si maxPalabras es menor o igual a 0.
        if (maxPalabras <= 0)
        {
            ret.resultado = Resultado.ERROR_1;
            ret.valorString = "ERROR_1";
        }
        else
        {
            //Arbol de palabras
            this.palabras = new Arbol<Palabra>(new PalabraComparatorPalabra(), maxPalabras);
            this.repeticiones = new Arbol<Palabra>(new PalabraComparatorCantRep(), maxPalabras);

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
        
        if (!repeticiones.esArbolVacio())
            repeticiones = null;
        
        
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
            //Grabo palabras en arboles        
            
            //Paso a minusculas y separo en palabras
            texto = texto.toLowerCase();
            String[] palab = texto.split("\\b[.:,¡!?¿() \\s]+");

            
            for(int i=0; i < palab.length; i++)
            {
                //Busco si existe palabra en el arbol de palabras y si está le sumo 1 al contador
                Palabra palabr = new Palabra(palab[i],1);
                Palabra pal = palabras.obtenerDato(palabras.getRaiz(), palabr);
                
                if (pal != null)
                {
                	//Elimino el nodo en el arbol de repeticiones, lo actualizo y lo vuelvo a insertar
                	repeticiones.borrar(pal);
                    pal.setCantidad(pal.getCantidad() + 1);
                    repeticiones.insertar(pal);
                	
//                	if(repeticiones.borrar(pal))
//                	{System.out.println(i + ")Borro OK palabra: " + pal.getPalabra() + " Cant. Rep. " + pal.getCantidad());}
//                	else
//                		System.out.println(i + ")NO BORRÓ palabra: " + pal.getPalabra() + " Cant. Rep. " + pal.getCantidad());
//                		;
//                    pal.setCantidad(pal.getCantidad() + 1);
//                    
//                    //repeticiones.insertar(pal);
//                    System.out.println(i + ")Inserta en repeticiones: " + repeticiones.insertar(pal));
                }
                else
                {   
                    //Inserto en ambos arboles la palabra nueva
//                	System.out.println(i + ")Inserta en palabras: " + palabras.insertar(palabr));
//                	System.out.println(i + ")Inserta en repeticiones: " + repeticiones.insertar(palabr));
                	
                	
                    palabras.insertar(palabr); 
                    repeticiones.insertar(palabr);
                    
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
        if(n <= 0)
        {
            ret.resultado = Resultado.ERROR_1;
            ret.valorString = "ERROR_1"; 
        }
        else
        {
            //Error 2 si hay menos de "n" palabras

            if(repeticiones.cantNodos(repeticiones.getRaiz()) < n)
            {
                ret.resultado = Resultado.ERROR_2;
                ret.valorString = "ERROR_2";
            }
            else
            {
                //Traigo las "n" palabras más usadas
         		
            	String todasLasPal = this.repeticiones.listarDescendentePorCant();
                String[] palabritas = todasLasPal.split("\\b[|\\s]+");

                String salida = "";
                for(int i=0; i < n; i++)
                {
                	if(i <= (n-2))
                		salida = salida + palabritas[i] + "|";
                	else
                		salida = salida + palabritas[i];
                }
            	
 //           	ret.valorString = this.repeticiones.listarDescendentePorCant();
                ret.valorString = salida;
                ret.resultado = Resultado.OK;
                ret.valorEntero = this.repeticiones.cantNodos(repeticiones.getRaiz());
     
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
        Palabra palabr = new Palabra(palabra,1);
        Palabra pal = palabras.obtenerDato(palabras.getRaiz(), palabr);
        
        if (pal != null)
        {
            //Retorno la cantidad de repeticiones
            ret.valorEntero = pal.getCantidad();
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
