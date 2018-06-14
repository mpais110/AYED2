package uy.ort.ob20182;

import Dominio.Palabra;
import TADGrafoPalabras.GrafoMatriz;
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
    

    @Override
    public Retorno analizarTexto(String texto) 
    {
        Retorno ret = new Retorno();
        int ordenAparicion = 0;

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

            //ERROR 2 si el texto tiene más de maxPalabras diferentes.
            if(palab.length > palabras.getCantNodos())
            {
                ret.resultado = Resultado.ERROR_2;
                ret.valorString = "ERROR_2";
            }
            else
            {
                for(int i=0; i < palab.length; i++)
                {
                    //Grabo en el grafo
                    //Verifico que el grafo no esté lleno
                    if (!palabras.estaLleno())
                    {
                        //Si no existe palabra, la agrego, de lo contrario sumo 1 al contador
                        if (!palabras.existePalabra(palab[i]))
                        {
                            //Crear Palabra
                            Palabra nueva = new Palabra(palab[i],1);

                            //Calculo identificador de palabra. notar que hola y halo tendrian el mismo id;
                            int x = 0;
                            for (int j = 0; j < palab[i].length(); j++) {
                                x += (int)palab[i].charAt(j);
                            }
                            
                            //Busco indice para insertar
                            int indice = palabras.Hash(x);

                            //Agrego al grafo
                            palabras.agregarVertice(indice, nueva);
                        }
                        else
                        {
                            //Sumo 1 a la cantidad de repeticiones
                            palabras.getVertice(palab[i]).getPalabra().setCantidad(palabras.getVertice(palab[i]).getPalabra().getCantidad() + 1);
                        }

                        //Registro los tramos de acuerdo al orden de aparicion

                        int indiceOrigen = palabras.indiceDePalabra(palab[i]);
                        int indiceDestino = palabras.indiceDePalabra(palab[i-1]);

                        palabras.agregarArista(indiceOrigen, indiceDestino, ++ordenAparicion);

                    }
                }
                ret.resultado = Resultado.OK;
                ret.valorString = "OK"; 
            }
        }

        return ret;   
    }

    @Override
    public Retorno aparicionesPalabra(String palabra) {
        Retorno ret = new Retorno();

        ret.resultado = Resultado.NO_IMPLEMENTADA;
        ret.valorString = "NO_IMPLEMENTADA";
        
        Palabra palabr = new Palabra(palabra,1);
        Palabra pal = palabras.getVertice(palabra).getPalabra();
        
        if (pal != null)
        {
            //Retorno la cantidad de repeticiones
            ret.valorEntero = pal.getCantidad();
            ret.resultado = Resultado.OK;
            ret.valorString = "OK";
        }
        else
        {   
            //Error 1 si no se encontr� la palabra.
            ret.resultado = Resultado.ERROR_1;
            ret.valorString = "ERROR_1";
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
