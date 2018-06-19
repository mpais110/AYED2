package uy.ort.ob20182;

import Dominio.Palabra;
import TADGrafoPalabras.Grafo;
import TADLista.ListaPalabra;
import TADListaHash.NodoListaHash;
import uy.ort.ob20182.Retorno.Resultado;

public class Sistema implements ISistema 
{

    private Grafo palabras;
    
    public Grafo getPalabras() {
        return palabras;
    }

    public void setPalabras(Grafo palabras) {
        this.palabras = palabras;
    }

    //PRE CONDICIONES: Se recibe la cantidad máxima de palabras a almacenar (maxPalabras). maxPalabras debe ser un entero.
    //POS CONDICIONES: Retorna ERROR_1 si maxPalabras <= 0, u OK si maxPalabras > 0 y se crearon las estructuras correspondientes.  
    @Override
    public Retorno inicializarSistema (int maxPalabras) 
    {
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
            //Grafo de palabras
            this.palabras = new Grafo(maxPalabras);
            

            ret.resultado = Resultado.OK;
            ret.valorString = "OK";
        }

        return ret;
    }

    //PRE CONDICIONES: Se consideran creadas todas las estructuras
    //POS CONDICIONES: Se devuelve OK, si se logrÃ³ eliminar correctamente
    @Override
    public Retorno destruirSistema() 
    {
        Retorno ret = new Retorno();
        ret.resultado = Resultado.NO_IMPLEMENTADA;
        ret.valorString = "NO_IMPLEMENTADA";

        //Desreferenciar grafo de palabras
        if (!palabras.esVacio())
            palabras = null;
        
        //Indico que quiero que pase el Garbage Collector
        System.gc();
        
    
        ret.resultado = Resultado.OK;
        ret.valorString = "OK";
        
        return ret;
    }
    
    //PRE CONDICIONES: Se recibe una cadena de caracteres.
    //POS CONDICIONES: Retorna ERROR_1 si el texto es vacío, ERROR_2 si el texto tiene más de maxPalabras diferentes, u OK si se pudo analizar el texto y grabarlo en las estructuras correspondientes.  
    @Override
    public Retorno analizarTexto(String texto) 
    {
        Retorno ret = new Retorno();
        ret.resultado = Resultado.NO_IMPLEMENTADA;
        ret.valorString = "NO_IMPLEMENTADA";
        
        int ordenAparicion = 1;
        int indiceVecVert = 0;
        int ultimoVertice = 0;

        //Error 1 si el texto es vacio
        if(texto.equals(""))
        {
            ret.resultado = Resultado.ERROR_1;
            ret.valorString = "ERROR_1";
        }
        else
        {           
            //Paso a minusculas y separo en palabras
            texto = texto.toLowerCase();
            String[] palab = texto.split("\\b[.:,¡¿!?() \\s]+");

            for(int i=0; i < palab.length; i++)
            {
                //Calculo identificador de palabra.
                int idPalabra = palabras.getHash().identifPalabra(palab[i]);

                //Si no existe palabra, la agrego, de lo contrario sumo 1 al contador
                if (!palabras.existePalabra(palab[i]))
                {
                    if (!palabras.estaLleno())
                    {
                        //Crear Palabra
                        Palabra nueva = new Palabra(palab[i],1);

                        //Agrego al grafo
                        palabras.agregarVertice(indiceVecVert, nueva, idPalabra);
                        
                        //Registro los tramos de acuerdo al orden de aparicion
                        if(ordenAparicion > 1)
                            palabras.agregarArista(ultimoVertice, indiceVecVert, ordenAparicion);

                        //Actualizo el Ãºltimo indice
                        ultimoVertice = indiceVecVert;
                        
                        //Incremento el indice para insertar en vector de vertices
                        indiceVecVert++;
                    }
                    else
                    {
                        //ERROR 2 si el texto tiene mÃ¡s de maxPalabras diferentes.
                        if(palab.length > palabras.getCantNodos())
                        {
                            ret.resultado = Resultado.ERROR_2;
                            ret.valorString = "ERROR_2";
                        }
                    }
                }
                else
                {
                    //SerÃ­a bueno modificarlo para que busque en el Hash y luego en su lista. Una vez que tengo el indice, voy al vector de vertices y lo actualizo 
                    //palabras.getVertice(palab[i]).getPalabra().setCantidad(palabras.getVertice(palab[i]).getPalabra().getCantidad() + 1);

                    //Sumo 1 a la cantidad de repeticiones
                    int posEnHash = palabras.getHash().fHash(idPalabra);
                    NodoListaHash nodoEnHash = palabras.getHash().getArr()[posEnHash].obtenerElemento(palab[i]);
                    int posEnVectVertices = nodoEnHash.getDato().getPosic();

                    Palabra pa = palabras.getNodosUsados()[posEnVectVertices].getPalabra();
                    pa.setCantidad(pa.getCantidad() + 1);
                    
                    //Registro los tramos de acuerdo al orden de aparicion
                    palabras.agregarArista(ultimoVertice, posEnVectVertices, ordenAparicion);
                    
                    ultimoVertice = posEnVectVertices;

                }
                
                ordenAparicion++;
                          
            }
            ret.resultado = Resultado.OK;
            ret.valorString = "OK"; 
        }
        return ret;   
    }

    //PRE CONDICIONES: Se recibe una cadena de caracteres.
    //POS CONDICIONES: Retorna ERROR_1 si no encontró la palabra en el texto, u OK si la encontró y se devuelven la cantidad de repeticiones.  
    @Override
    public Retorno aparicionesPalabra(String palabra) {
        Retorno ret = new Retorno();
        ret.resultado = Resultado.NO_IMPLEMENTADA;
        ret.valorString = "NO_IMPLEMENTADA";

        //Obtengo posicion en el hash
        int posicHash = palabras.getHash().fHash(palabras.getHash().identifPalabra(palabra));
        
        //Busco la palabra en el Hash
        if (palabras.getHash().getArr()[posicHash].estaVacia() && palabras.getHash().getArr()[posicHash].obtenerElemento(palabra)== null)
        {
            ret.resultado = Resultado.ERROR_1;
            ret.valorString = "ERROR_1";
        }
        else
        {
            int posicVectVert = palabras.getHash().getArr()[posicHash].obtenerElemento(palabra).getDato().getPosic();
        
            //Traigo del vector de vertices la cantidad de repeticiones
            int cantRep = palabras.getNodosUsados()[posicVectVert].getPalabra().getCantidad();

            ret.resultado = Resultado.OK;
            ret.valorString = "OK";
            ret.valorEntero = cantRep;
        }
      
        return ret;
    }
    
    //PRE CONDICIONES: Se recibe una cadena de caracteres.
    //POS CONDICIONES: Retorna ERROR_1 si no encontró la palabra en el texto, u OK si las tres palabras siguientes posibles.  
    @Override
    public Retorno predecirPalabra(String palabra) {
        
        Retorno ret = new Retorno();
        ret.resultado = Resultado.NO_IMPLEMENTADA;
        ret.valorString = "NO_IMPLEMENTADA";
        
        //Obtengo posicion en el hash
        int posicHash = palabras.getHash().fHash(palabras.getHash().identifPalabra(palabra));
        
        //Error 1 si la palabra no se encontro en el texto.
        if (palabras.getHash().getArr()[posicHash].estaVacia() || palabras.getHash().getArr()[posicHash].obtenerElemento(palabra)== null)
        {
            ret.resultado = Resultado.ERROR_1;
            ret.valorString = "ERROR_1";
        }
        else
        {
            //Obtengo la posicion en el vector de verices
            int posicVectVert = palabras.getHash().getArr()[posicHash].obtenerElemento(palabra).getDato().getPosic();
            
            //Busco sus adyacentes y los guardo en una lista ordenada descendente
            ListaPalabra listaPal = palabras.verticesAdyacentes(posicVectVert);
            ret.valorString = listaPal.mostrarTresPrimeros();
            ret.resultado = Resultado.OK;
        }

        return ret;
    }
    
    //PRE CONDICIONES: Se reciben dos cadenas de caracteres (palabras).
    //POS CONDICIONES: Retorna ERROR_1 si no encontró al menos una de las palabras en el texto, ERROR_2 si no hay frase entre esas dos palabras, u OK y el texto de menos palabras entre la de inicio y fin
    @Override
    public Retorno repetirFrase(String palabraIni, String palabraFin) 
    {
        Retorno ret = new Retorno();
        ret.resultado = Resultado.NO_IMPLEMENTADA;
        ret.valorString = "NO_IMPLEMENTADA";
        
        //Obtengo posicion en el hash
        int posicHashIni = palabras.getHash().fHash(palabras.getHash().identifPalabra(palabraIni));
        int posicHashFin = palabras.getHash().fHash(palabras.getHash().identifPalabra(palabraFin));
        
        //Error 1 si una de las palabras no se encontro en el texto.
        if (palabras.getHash().getArr()[posicHashIni].estaVacia() || palabras.getHash().getArr()[posicHashIni].obtenerElemento(palabraIni) == null ||
            palabras.getHash().getArr()[posicHashFin].estaVacia() || palabras.getHash().getArr()[posicHashFin].obtenerElemento(palabraFin) == null)
        {
            ret.resultado = Resultado.ERROR_1;
            ret.valorString = "ERROR_1";
        }
        else  
        { 
            int posicVVIni = palabras.indiceDePalabra(palabraIni);
            int posicVVFin = palabras.indiceDePalabra(palabraFin);
        	
            if(posicVVIni > posicVVFin)
            {
            	ret.resultado = Resultado.ERROR_2;
                ret.valorString = "ERROR_2";
            }
            else
            {
                String retorno = palabras.BusquedaBFS(palabraIni, palabraFin);

                if(retorno.equals(""))
                {
                    ret.resultado = Resultado.ERROR_2;
                    ret.valorString = "ERROR_2";
                }
                else
                {
                    ret.resultado = Resultado.OK;
                    ret.valorString = retorno;
                }  
            }
        	        
        } 
            
        return ret;
    }
	
}
