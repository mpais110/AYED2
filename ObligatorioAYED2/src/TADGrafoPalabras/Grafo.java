package TADGrafoPalabras;

import Dominio.Palabra;
import TADCola.Cola;
import TADLista.ListaPalabra;
import TADLista.NodoListaPalabra;

        
public class Grafo 
{    
    private int size;
    private int cantNodos;
    private Tramos[][] matrizAdyacencia;
    private Vertice[] nodosUsados;
    private Hash hash;
    
      
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCantNodos() {
        return cantNodos;
    }

    public void setCantNodos(int cantNodos) {
        this.cantNodos = cantNodos;
    }

    public Tramos[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
    }

    public void setMatrizAdyacencia(Tramos[][] matrizAdyacencia) {
        this.matrizAdyacencia = matrizAdyacencia;
    }

    
    public Vertice[] getNodosUsados() {
        return nodosUsados;
    }

    public void setNodosUsados(Vertice[] nodosUsados) {
        this.nodosUsados = nodosUsados;
    }

    public Hash getHash() {
        return hash;
    }
    
    //Crea el grafo vacio (sin nodos ni aristas) con capacidad de almacenamiento de "cantNodos" vértices
    public Grafo(int cantNodos) {
        this.size = 0;
        this.cantNodos = cantNodos;

        this.matrizAdyacencia = new Tramos[this.cantNodos][this.cantNodos];
        
        for (int i = 0; i < this.cantNodos; i++)
                for (int j = 0; j < this.cantNodos; j++)
                  this.matrizAdyacencia[i][j] = new Tramos();

        this.nodosUsados = new Vertice[this.cantNodos];
        this.hash = new Hash(cantNodos);
    }
    
    // Pre: existeVertice(c)
    public ListaPalabra verticesAdyacentes(int posVert) {
        ListaPalabra ret = new ListaPalabra();
        for (int i = 0; i < matrizAdyacencia.length; i++) {
                if (this.matrizAdyacencia[posVert][i].isExiste()) {
                    
                    NodoListaPalabra nuevo = new NodoListaPalabra(matrizAdyacencia[posVert][i].getPeso(), nodosUsados[i].getPalabra());
                        ret.insertaOrdenadoDesc(nuevo);
                }
        }
        return ret;
    }
    
    public void agregarArista(int origen, int destino, int orden) 
    {
        //Si ya existe, le sumo 1 a peso e ingreso el orden en la lista de int, de lo contrario, doy de alta la arista
        if (this.matrizAdyacencia[origen][destino].isExiste())
        {
            this.matrizAdyacencia[origen][destino].setPeso(this.matrizAdyacencia[origen][destino].getPeso() + 1);
            this.matrizAdyacencia[origen][destino].getOrden().agregarInicio(orden);
        }
        else 
        {
            Tramos nuevo = new Tramos(orden);
            this.matrizAdyacencia[origen][destino] = nuevo;
        }
    }

    public void agregarVertice(int indiceVecVert,  Palabra elem, int idPalabra) 
    {
        //CrearVértice
        Vertice nuevo = new Vertice(true, elem);
        VerticeHash nuevito = new VerticeHash(elem.getPalabra(), indiceVecVert);
        
        this.nodosUsados[indiceVecVert] = nuevo;
        this.hash.insertar(idPalabra, nuevito);
        
        this.size++;
        
    }
    
    public boolean esVacio() {
            return this.size == 0;
    }

    
    public boolean estaLleno() {
        return size == cantNodos;
    }
    
    public boolean existePalabra(String pal) {
        
        boolean encontro = false;
        int i = 0;
        
        //Busco en vector de palabras si ya existe una igual
        while (!encontro && i < this.getCantNodos())
        {
            if (this.nodosUsados[i] != null && this.nodosUsados[i].getPalabra().getPalabra().equals(pal))
                encontro = true;
            else
                i++;         
        }
        
        return encontro;                    
    }  

    public int indiceDePalabra(String pal) {
        
        int indice = 0;
        boolean encontro = false;
        int i = 1;
        
        //Busco en vector de puntos
        while (!encontro && i <= this.getCantNodos())
        {
            if (this.nodosUsados[i] != null && this.nodosUsados[i].getPalabra().getPalabra().equals(pal))
            {
                encontro = true;
                indice = i;             
            }
            else
                i++;         
        }    
        return indice;                    
    }
    
    public Vertice getVertice(String palab) {
        Vertice ret = null;
        boolean encontro = false;
        int i = 1;
        
        //Busco en vector de palabras si ya existe la recibida por parametrro
        while (!encontro && i <= this.getCantNodos())
        {
            if (this.nodosUsados[i] != null && this.nodosUsados[i].getPalabra().getPalabra().equals(palab)) 
            {
                ret = this.nodosUsados[i];
                encontro = true;
            }else {
                i++;
            }         
        }        
        return ret;        
    }

   public String BusquedaBFS(String palIni, String palFin)
    {
        String ret = "";
        int prev[] = new int[cantNodos];
        int actual = 0;

        //Obtengo los indices de las palabras en el vector de vertices
        int ini = indiceDePalabra(palIni);
        int fin = indiceDePalabra(palFin);
        

        boolean[] vis = new boolean[cantNodos];
        // Inicializo el vector en false
        for(int i = 0; i < vis.length; vis[i++] = false); 
        prev[ini] = -1;
        
        //Marco el inicio como visitado
        vis[ini] = true;
        
        Cola<Integer> q = new Cola<Integer>();
        q.encolar(ini);
   

        while (!q.esVacia())
        {
            actual = q.desencolar();
            if(actual == fin)
            	break;

            
            for( int i = 0 ; i < matrizAdyacencia.length ; i++ )
            {	
            	//Adyacentes a nodo actual
                Tramos v = matrizAdyacencia[actual][i];
                if( v.isExiste() && !vis[ i ] )
                {				
                	//Si es no visitado, lo agregamos a la cola
                    //System.out.println( actual +" -> "+ i); //Ir viendo el recorrido del BFS
                    prev[i] = actual;//vamos guardando el recorrido
                    vis[i] = true;
                    q.encolar(i);
                }
            }
        }

        
        int posActual = fin;
        int lleguePor = -1;
        boolean termino = false;
        String textoInvert = "";
        while(!termino) 
        {   
            lleguePor = prev[posActual];
            if(posActual == fin)
                textoInvert += nodosUsados[posActual].getPalabra().getPalabra();
            else
                textoInvert += " " + nodosUsados[posActual].getPalabra().getPalabra();
            posActual = lleguePor;
          
            if(posActual == -1)
                termino = true;
        }
        

        String[] palab = textoInvert.split("\\b[ \\s]+");

        for(int i = (palab.length - 1); i >= 0; i--)
        {
            if (i == 0)
                ret += palab[i];
            else
                ret += palab[i] + " ";
        }
                    
        return ret;
    }
   
}
