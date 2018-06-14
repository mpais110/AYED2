package TADGrafoPalabras;

import Dominio.Palabra;
import TADListaHash.ListaHash;
import java.util.Arrays;
        
public class GrafoMatriz 
{    
    private int size;
    private int cantNodos;
    private Tramos[][] matrizAdyacencia;
    private Vertice[] nodosUsados;
    private ListaHash[] hash;
    
    
    //Crea el grafo vacio (sin nodos ni aristas) con capacidad de almacenamiento de "cantNodos" vértices
    public GrafoMatriz(int cantNodos) {
        this.size = 0;
        this.cantNodos = cantNodos;

        this.matrizAdyacencia = new Tramos[this.cantNodos][this.cantNodos];
        
        for (int i = 1; i <= this.cantNodos; i++)
                for (int j = 1; j <= this.cantNodos; j++)
                  this.matrizAdyacencia[i][j] = new Tramos();

        this.nodosUsados = new Vertice[this.cantNodos];
        this.hash = new ListaHash[siguientePrimo(cantNodos)];
    }
    
    
    public int siguientePrimo (int num)
    {
        boolean encontro = false;
        boolean esPrimo = true;
        int primo = num;
        
        while(!encontro && esPrimo)
        {
            int contador = 2;
            while (contador != num)
            {
              if (num % contador == 0)
                esPrimo = false;
              contador++;
            }
            
            if(!esPrimo)
            {
                encontro = true;
                primo = num;
            }
            else
                num++;
        }
        return primo;
    }

    
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

    
    
    
    public void agregarArista(int origen, int destino, int peso) {
        Tramos nuevo = new Tramos(peso);
        this.matrizAdyacencia[origen][destino] = nuevo;
    }


    public void agregarVertice(int indiceHash, int indiceVecVert,  Palabra elem) {

        //CrearVértice
        Vertice nuevo = new Vertice(true, elem);
        VerticeHash nuevito = new VerticeHash(elem.getPalabra(), indiceHash);
        
        this.nodosUsados[indiceVecVert] = nuevo;
        this.hash[indiceHash].insertar(nuevito);
        
        this.size++;
        
    }
    
    
    public void eliminarArista(int origen, int destino) {
            Tramos nuevo = new Tramos();
            this.matrizAdyacencia[origen][destino] = nuevo;	
            this.matrizAdyacencia[destino][origen] = nuevo;	
    }

    
    public void eliminarVertice(int v) {

        Vertice nuevo = null;
        this.nodosUsados[v] = nuevo;        
                
        this.size--;

        //Elimino las aristas donde v es miembro
        for(int i = 1; i <= this.cantNodos; i++){
                this.matrizAdyacencia[i][v] = new Tramos();
                this.matrizAdyacencia[v][i] = new Tramos();
            }
    }
    
    
    public boolean esVacio() {
            return this.size == 0;
    }

    
    public boolean estaLleno() {
        return size == cantNodos/2;
    }
    
    
    public boolean sonAdyacentes(int a, int b) {
            return this.matrizAdyacencia[a][b].getExiste();
    }

    
    public boolean estaVertice(int v) {
        return this.nodosUsados[v].getExisteNodo();
    }

    
    public boolean existePalabra(String pal) {
        
        boolean encontro = false;
        int i = 1;
        
        //Busco en vector de palabras si ya existe una igual
        while (!encontro && i <= this.getCantNodos())
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
        
        //Busco en vector de puntos si ya existe uno con las coordenadas recibidas
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
    
    
//    public boolean existeTramo(double coordXI, double coordYI, double coordXF, double coordYF) {
//        boolean ret = false;
//        
//        int verticeIni = this.indiceDePunto(coordXI, coordYI);
//        int verticeFin = this.indiceDePunto(coordXF, coordYF);
//
//        if(this.matrizAdyacencia[verticeIni][verticeFin].getExiste() && this.matrizAdyacencia[verticeFin][verticeIni].getExiste())
//            ret = true;
//
//      
//        return ret;
//    }
    
    
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
    
    
    public boolean existeSiloConCapacidad(int capacidad) {
        boolean ret = false;
        boolean encontro = false;
        int i = 1;
        
        //Busco en vector de puntos si existe un Silo con capacidad igual o superior a la recibida
        while (!encontro && i <= this.getCantNodos())
        {
//            if (this.nodosUsados[i] != null && this.nodosUsados[i].getTipo() == TipoPunto.SILO) 
//            {
//                Silo unSilo = (Silo)(this.nodosUsados[i].getElem());
//                if (unSilo.getCapacidad() >= capacidad) {
//                    encontro = true;
//                    ret = true;
//                }else
//                    i++;
//            }else {
//                i++;
//            }         
        }        
        return ret;        
    }
    
    
    public int cantidadDeSilos() 
    {
        int i = 1;
        int cantidad = 0;

        //Recorro los silos
        while (i <= this.cantNodos)
        {
//            if (this.nodosUsados[i] != null && this.nodosUsados[i].getTipo() == TipoPunto.SILO)
//                cantidad++;          

            i++;         
        }    
        return cantidad;   
    }
    
    
    public int cantidadDeCiudades() 
    {
        int i = 1;
        int cantidad = 0;

        //Recorro los silos
        while (i <= this.cantNodos)
        {
//            if (this.nodosUsados[i] != null && this.nodosUsados[i].getTipo() == TipoPunto.CIUDAD)
//                cantidad++;          
//            i++;         
        }    
        return cantidad;   
    }
        
        
    public int cantidadDePlantaciones() 
    {
        int i = 1;
        int cantidad = 0;

        //Recorro los silos
        while (i <= this.cantNodos)
        {
//            if (this.nodosUsados[i] != null && this.nodosUsados[i].getTipo() == TipoPunto.PLANTACION)
//                cantidad++;          
//            i++;         
        }    
        return cantidad;   
    }
            
   
    public int Hash(int x) {       
        int ret = -1;
        int i = 1;
        boolean auxWhile = true;
                 
        while(auxWhile)
        {
            ret = ((x / this.cantNodos) + i) % this.cantNodos;
            i++;
            if(ret > 0 && this.nodosUsados[ret] == null) 
                auxWhile = false;
        }
        return ret;        
    }    
    
    
    
    
    
//    public int Hash(double coordX, double coordY) {       
//        int ret = -1;
//        int i = 1;
//        boolean auxWhile = true;
//        coordX = Math.abs(coordY * 10000000);
//        coordY = Math.abs(coordY * 10000000);
//        
//        int suma = (int)(coordX + coordY);
//          
//        while(auxWhile)
//        {
//            ret = ((suma / this.cantNodos) + i) % this.cantNodos;
//            i++;
//            if(ret > 0 && this.nodosUsados[ret] == null) 
//                auxWhile = false;
//        }
//        return ret;        
//    }
    
    
    public void mostrarMatrizAdyacencia() 
    {       
        for (int i = 0; i <= this.cantNodos; i++)
        {
            for (int j = 0; j <= this.cantNodos; j++)
                System.out.print(this.matrizAdyacencia[i][j].getPeso() + "-");
            
            System.out.println();
        }  
    }
    
    
    
//    public String caminoMinimo(int o) 
//    {
//        int[] costo = new int[cantNodos + 1];
//        int[] camino = new int[cantNodos + 1];
//        boolean[] visitado = new boolean[cantNodos + 1];
//
//        // Inicializo costos con valor INFINITO
//        for (int i = 1; i <= cantNodos; i++) {
//                if(this.nodosUsados[i] == null) {
//                        visitado[i] = true;
//                }
//                if (i != o)
//                        costo[i] = Integer.MAX_VALUE;
//        }
//
//        Plantacion laPlantacion = (Plantacion) this.nodosUsados[o].getElem();
//        int capPlantacion = laPlantacion.getCapacidad();
//
//        visitado[o] = true;
//        costo[o] = 0;
//        camino[o] = -1;
//
//        int idSilo = dijkstra(o, 0, -1, costo, camino, visitado, capPlantacion);
//
//        return crearRuta(camino, idSilo);
//
//    }
//
//	public String crearRuta(int[] camino, int o) 
//        {
//            int pos = o;
//            String retorno = "";
//            while(pos != -1) {
//                    if("".equals(retorno)) {
//                            retorno = this.nodosUsados[pos].getCoordX()+";"+this.nodosUsados[pos].getCoordY();
//                    }else {
//                            retorno = this.nodosUsados[pos].getCoordX()+";"+this.nodosUsados[pos].getCoordY()+"|"+retorno;
//                    }
//                    pos = camino[pos];
//            }
//
//            return retorno;
//	}
//	
//	public int dijkstra(int o, int costo, int anterior, int[] costos, int[] camino, boolean[] visitados, int capacidad) 
//        {
//            if(!todosVisitados(visitados)) 
//            {
//                if(this.nodosUsados[o].getTipo() == Sistema.TipoPunto.SILO) 
//                {
//                    Silo s = (Silo)this.nodosUsados[o].getElem();
//                    if(s.getCapacidad() >= capacidad) 
//                    {
//                        s.setCapacidad(s.getCapacidad()-capacidad);
//                        return o;
//                    }
//                }
//                
//                visitados[o] = true;
//                costos[o] = costo;
//                camino[o] = anterior;
//                
//                for (int j = 1; j < cantNodos; j++) 
//                {
//                    if (this.matrizAdyacencia[o][j].getExiste()) 
//                    {
//                        if (j != o) 
//                        {
//                            if (!visitados[j]) 
//                            {
//                                if (costos[j] > this.matrizAdyacencia[o][j].getPeso() + costos[0]) 
//                                {
//                                    costos[j] = this.matrizAdyacencia[o][j].getPeso() + costos[0];
//                                    camino[j] = o;
//                                }
//                            }
//                        }
//                    }
//                }
//                
//                int menor = buscoMinimo(costos, visitados);
//                return dijkstra(menor, costos[menor], o, costos, camino, visitados, capacidad);
//            }
//            return -1;
//	}
//	
//	
//        
//        
//           
//    public String caminoMinimoCiudadPlantacion(int o) 
//    {
//        String ret = "";
//
//        int[] costo = new int[cantNodos + 1];
//        int[] camino = new int[cantNodos + 1];
//        boolean[] visitado = new boolean[cantNodos + 1];
//
//        // Inicializo costos con valor INFINITO
//        for (int i = 1; i <= cantNodos; i++) 
//        {
//            if(this.nodosUsados[i] == null) 
//                visitado[i] = true;
//            
//            if (i != o)
//                costo[i] = Integer.MAX_VALUE;
//        }
//
//        visitado[o] = true;
//        costo[o] = 0;
//        camino[o] = -1;
//
//        //Recorro las plantaciones 
//        for (int i = 1; i < cantNodos; i++) 
//        {
//            if(this.nodosUsados[i] != null && this.nodosUsados[i].getTipo() == TipoPunto.PLANTACION) 
//            {   
//                int costoPlantacion = dijkstraCiudad(o, 0, -1, costo, camino, visitado, 20);
//                
//                if (costoPlantacion != -1 && costoPlantacion <= 20)
//                {
//                    ret += this.nodosUsados[i].getCoordX() + ";" + this.nodosUsados[i].getCoordY();
//                    if (i != cantNodos)
//                        ret += "|";
//
//                }
//            }
//
//        }
//
//        return ret;
//    }
//        
//        
//        
//    public int dijkstraCiudad(int o, int costo, int anterior, int[] costos, int[] camino, boolean[] visitados, int pesoMax) {
//        if(!todosVisitados(visitados)) 
//        {
//            if(this.nodosUsados[o].getTipo() == Sistema.TipoPunto.PLANTACION) 
//            {
//                return costos[o];
//            }
//
//
//            visitados[o] = true;
//            costos[o] = costo;
//            camino[o] = anterior;
//
//            for (int j = 1; j <= cantNodos; j++) 
//            {
//                if (this.matrizAdyacencia[o][j].getExiste()) 
//                {
//                    if (j != o) 
//                    {
//                        if (!visitados[j]) 
//                        {
//                            if (costos[j] > this.matrizAdyacencia[o][j].getPeso() + costos[0]) 
//                            {
//                                costos[j] = this.matrizAdyacencia[o][j].getPeso() + costos[0];
//                                camino[j] = o;
//                            }
//                        }
//                    }
//                }
//            }
//
//            int menor = buscoMinimo(costos, visitados);
//            return dijkstraCiudad(menor, costos[menor], o, costos, camino, visitados, pesoMax);
//        }
//
//        return -1;
//    }
//        
//        
//        
//        
//        
//	
//	public int buscoMinimo(int[] costos, boolean[] visitados) {
//		int minimo = Integer.MAX_VALUE;
//		int retorno = 0;
//		for(int i = 1; i <= cantNodos; i++) {
//			if(!visitados[i] && costos[i] > 0) {
//				if(minimo > costos[i]) {
//					minimo = costos[i];
//					retorno = i;
//				}
//			}
//		}
//		
//		return retorno;
//	}
//
//	public boolean todosVisitados(boolean[] visitados) {
//		for (int i = 1; i < visitados.length; i++) {
//			if (visitados[i] == false)
//				return false;
//		}
//		return true;
//	}
//
//    
//
//    
//      
//    public String imprimirCamino(int o, int d, int[] pred, String ret)
//    {
//        if(d == o || d < 0) 
//            return ret;
//        else 
//        {
//            if(this.nodosUsados[pred[d]] != null) ret += this.nodosUsados[pred[d]].getCoordX() + ";" + this.nodosUsados[pred[d]].getCoordY();
//            d = d - 1;
//            return imprimirCamino(o, d, pred, ret); 
//        }
//    }
//    
//    
//    
//    
//    
//    public String caminoMinimoCiudad(int o)
//    {
//    	int d = 0;
//		boolean encontro = false;
//		int[] costo = new int[cantNodos + 1];
//		int[] camino = new int[cantNodos + 1];
//		boolean[] visitado = new boolean[cantNodos + 1];
//
//		// Inicializo costos con valor INFINITO
//		for (int i = 1; i <= cantNodos; i++) {
//			if(this.nodosUsados[i] == null) {
//				visitado[i] = true;
//			}
//			if (i != o)
//				costo[i] = Integer.MAX_VALUE;
//		}
//
//		Plantacion laPlantacion = (Plantacion) this.nodosUsados[o].getElem();
//		int capPlantacion = laPlantacion.getCapacidad();
//		int i = 1;
//		visitado[o] = true;
//		costo[o] = 0;
//		camino[o] = -1;
//	
//		int idSilo = dijkstraCiudad(o, 0, -1, costo, camino, visitado, capPlantacion);
//		
//		return crearRuta(camino, idSilo);
//    }    
//    
//    
//     
//   
//    
//
//    public String marcasPuntos() 
//    {
//        String ret = "";
//        String label = "";
//        for (int i = 0; i < this.cantNodos; i++) 
//        {
//            if (this.nodosUsados[i] != null) 
//            {
//                if (this.nodosUsados[i].getTipo() == Sistema.TipoPunto.CIUDAD) 
//                {
//                    label = "color:red|label:C|";
//                } else if (this.nodosUsados[i].getTipo() == Sistema.TipoPunto.PLANTACION) 
//                    {
//                        label = "color:yellow|label:P|";
//                    } else 
//                        {
//                            label = "color:green|label:S|";
//                        }
//                
//                ret += "&markers=" + label + this.nodosUsados[i].getCoordX() + "," + this.nodosUsados[i].getCoordY();
//            }
//        }
//        
//      return ret;
//     }
//
// 
//
//    public String listarSilos() 
//    {
//        String ret = "";
//        
//        int i = 1;
//        int recorridos = 0;
//        
//        //Recorro los silos
//        while (i <= this.cantNodos && recorridos <= this.cantidadDeSilos())
//        {   
//            if (nodosUsados[i] != null && nodosUsados[i].getTipo() == TipoPunto.SILO)
//            {
//                Silo elSilo = (Silo)nodosUsados[i].getElem();
//                recorridos += 1;
//                ret += nodosUsados[i].getCoordX() + ";" + nodosUsados[i].getCoordY() + ";" + elSilo.getCapacidadoriginal() + ";" + elSilo.getCapacidad();
//                if (recorridos != this.cantidadDeSilos())
//                    ret += "|";
//            }
//            i++;         
//        }  
//        
//        return ret;
//    }
// 

}
