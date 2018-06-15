package uy.ort.ob20182;

import Dominio.Palabra;
import TADGrafoPalabras.GrafoMatriz;
import TADListaHash.NodoListaHash;
import uy.ort.ob20182.Retorno.Resultado;

public class Sistema implements ISistema 
{

    private GrafoMatriz palabras;
    
    public GrafoMatriz getPalabras() {
        return palabras;
    }

    public void setPalabras(GrafoMatriz palabras) {
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
            this.palabras = new GrafoMatriz(maxPalabras);
            

            ret.resultado = Resultado.OK;
            ret.valorString = "OK";
        }

        return ret;
    }

    //PRE CONDICIONES: Se consideran creadas todas las estructuras
    //POS CONDICIONES: Se devuelve OK, si se logró eliminar correctamente
    @Override
    public Retorno destruirSistema() 
    {
        Retorno ret = new Retorno();

        //Desreferenciar grafo de palabras
        if (!palabras.esVacio())
            palabras = null;
        
        //Indico que quiero que pase el Garbage Collector
        System.gc();
        
    
        ret.resultado = Resultado.OK;
        ret.valorString = "OK";
        
        return ret;
    }
    

    @Override
    public Retorno analizarTexto(String texto) 
    {
        Retorno ret = new Retorno();
        int ordenAparicion = 0;
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
            String[] palab = texto.split("\\b[.:,�!?�() \\s]+");

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

                        //Incremento el indice para insertar en vector de vertices
                        indiceVecVert++;
                    }
                    else
                    {
                        //ERROR 2 si el texto tiene más de maxPalabras diferentes.
                        if(palab.length > palabras.getCantNodos())
                        {
                            ret.resultado = Resultado.ERROR_2;
                            ret.valorString = "ERROR_2";
                        }
                    }
                }
                else
                {
                    //Sería bueno modificarlo para que busque en el Hash y luego en su lista. Una vez que tengo el indice, voy al vector de vertices y lo actualizo 
                    //palabras.getVertice(palab[i]).getPalabra().setCantidad(palabras.getVertice(palab[i]).getPalabra().getCantidad() + 1);

                    //Sumo 1 a la cantidad de repeticiones
                    int posEnHash = palabras.getHash().fHash(idPalabra);
                    NodoListaHash nodoEnHash = palabras.getHash().getArr()[posEnHash].obtenerElemento(palab[i]);
                    int posEnVectVertices = nodoEnHash.getDato().getPosic();

                    Palabra pa = palabras.getNodosUsados()[posEnVectVertices].getPalabra();
                    pa.setCantidad(pa.getCantidad() + 1);

                }
                
                //Registro los tramos de acuerdo al orden de aparicion
                palabras.agregarArista(ultimoVertice, indiceVecVert, ++ordenAparicion);

                //Actualizo el último indice
                ultimoVertice = indiceVecVert;
            }
            ret.resultado = Resultado.OK;
            ret.valorString = "OK"; 
        }
        return ret;   
    }

    @Override
    public Retorno aparicionesPalabra(String palabra) {
        Retorno ret = new Retorno();

        //Obtengo posicion en el hash
        int posicHash = palabras.getHash().fHash(palabras.getHash().identifPalabra(palabra));
        
        //Busco la palabra en el Hash
        if (palabras.getHash().getArr()[posicHash].estaVacia())
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

    @Override
    public Retorno predecirPalabra(String palabra) {
            // ToDo: Implementar aqui el metodo
            return new Retorno(Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno repetirFrase(String palabraIni, String palabraFin) {
            // ToDo: Implementar aqui el metodo
            return new Retorno(Resultado.NO_IMPLEMENTADA);
    }
	
}
