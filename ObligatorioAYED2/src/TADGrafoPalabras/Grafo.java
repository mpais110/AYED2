package TADGrafoPalabras;

import Dominio.Palabra;
import TADCola.Cola;
import TADLista.Lista2;
import TADLista.ListaPalabra;
import TADLista.NodoLista2;
import TADLista.NodoListaPalabra;
import TADListaHash.ListaHash;
import java.util.Arrays;
        
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
    
//    
//    public void eliminarArista(int origen, int destino) {
//            Tramos nuevo = new Tramos();
//            this.matrizAdyacencia[origen][destino] = nuevo;	
//            this.matrizAdyacencia[destino][origen] = nuevo;	
//    }
//
//    
//    public void eliminarVertice(int v) {
//
//        Vertice nuevo = null;
//        this.nodosUsados[v] = nuevo;        
//                
//        this.size--;
//
//        //Elimino las aristas donde v es miembro
//        for(int i = 1; i <= this.cantNodos; i++){
//                this.matrizAdyacencia[i][v] = new Tramos();
//                this.matrizAdyacencia[v][i] = new Tramos();
//            }
//    }
//    
    
    public boolean esVacio() {
            return this.size == 0;
    }

    
    public boolean estaLleno() {
        return size == cantNodos;
    }
    
    
//    public boolean sonAdyacentes(int a, int b) {
//            return this.matrizAdyacencia[a][b].isExiste();
//    }
//
//    
//    public boolean estaVertice(int v) {
//        return this.nodosUsados[v].getExisteNodo();
//    }

    
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

    
    
    // Pre: existeVertice(pal)
    public String BFS(String palIni, String palFin) 
    {
        String ret = "";
        boolean[] vis = new boolean[cantNodos];
        // Inicializo el vector en false
        for(int i = 0; i < vis.length; vis[i++] = false); 

        int sig = indiceDePalabra(palIni);
        int fin = indiceDePalabra(palFin);

        do{
            ret += BFSRec(sig, vis, fin); 
            sig = -1;
            int i = 0;
            while(!vis[fin] && i < vis.length)
            {
                 if(!vis[i])
                    {
                        sig = i;
                        break;
                    }         
                i++;
            }
        } while(sig != -1);

        return ret;
    }


    private String BFSRec(int vec, boolean[] vis, int fin) 
    {
            String ret = "";
            Cola<Integer> q = new Cola<Integer>();
            q.encolar(vec);
            while(!q.esVacia())
            {
                    int prox = q.desencolar();
                    vis[prox] = true;
                    ret += nodosUsados[prox].getPalabra().getPalabra() + " ";
                    for (int i = 0; i < matrizAdyacencia.length; i++) {
                            if(matrizAdyacencia[prox][i].isExiste() && !vis[i])
                            {
                                q.encolar(i);
                            }
                    }
            }
            
            return ret;
    }
    
    public String BusquedaBFS(String palIni, String palFin)
    {
        String ret = "";

        int sig = indiceDePalabra(palIni);
        int fin = indiceDePalabra(palFin);
        

        boolean[] vis = new boolean[cantNodos];
        // Inicializo el vector en false
        for(int i = 0; i < vis.length; vis[i++] = false); 

        //Marco el inicio como visitado
        vis[sig] = true;
        
        Cola<Integer> q = new Cola<Integer>();
        q.encolar(sig);
   

        while (!q.esVacia() && !vis[fin])
        {
            sig = q.desencolar();
            ret += nodosUsados[sig].getPalabra().getPalabra() + " ";

            for (int i = 0; i < matrizAdyacencia.length; i++) 
            {
                while(matrizAdyacencia[sig][i].isExiste() && !vis[i] && !vis[fin])
                {
                    vis[i] = true;
                    q.encolar(i);
                }
            }
        }
        
        if(vis[fin])
            ret += palFin;
        else
            ret = "";
        
        return ret;
    }
    
//    
//    public String BusqBFS(String palIni, String palFin)
//    {
//        String ret = "";
//
//        int ini = indiceDePalabra(palIni);
//        int fin = indiceDePalabra(palFin);
//        int orden = 0;
//
//        boolean[] vis = new boolean[cantNodos];
//        // Inicializo el vector en false
//        for(int i = 0; i < vis.length; vis[i++] = false); 
//
//        //Marco el inicio como visitado
//        vis[fin] = true;
//        ret += nodosUsados[fin].getPalabra().getPalabra() + " ";
//        Lista2 ordenFin = matrizAdyacencia[ini][fin].getOrden();
//        boolean encontro = false;
//        int j = 0;
//        int elem = ordenFin.getInicio().getDato();
//        
//        //Busco en incidentes
//        while (!vis[ini])
//        {
//            for (int i = 0; i < matrizAdyacencia.length; i++) 
//            {
//                //Recorro incidentes para sacar los que llegaron a el
//                if(matrizAdyacencia[i][fin].isExiste() && !vis[i])
//                {
//                    while (!encontro && j < matrizAdyacencia.length )
//                    {
//                        Lista2 listaAAnalizar = matrizAdyacencia[i][fin].getOrden();
//                        NodoLista2 elemAAnalizar = listaAAnalizar.getInicio();
//                        while(!encontro && elemAAnalizar != null)
//                        {
//                            if(elemAAnalizar.getDato() < elem && elem - elemAAnalizar.getDato() == 1)
//                            {
//                                encontro = true;
//                                orden = elemAAnalizar.getDato();
//                                ret += nodosUsados[ini].getPalabra().getPalabra() + " ";
//                            }
//                            elemAAnalizar = elemAAnalizar.getSig();
//                        }
//                    }
//                    
//                    
//                    ret += nodosUsados[ini].getPalabra().getPalabra() + " ";
//                    vis[i] = true;
//                    
//                }
//            }
//        }
//        
//        ret += nodosUsados[ini].getPalabra().getPalabra() + " ";
//        
//        return ret;
//    }
}
