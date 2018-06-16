package TADLista;

public class ListaPalabra {
    private NodoListaPalabra inicio;
    //private NodoLista2 fin;

    //Constructor
    public void Lista(){
        this.inicio=null;
        //this.fin=null;
    }

    //Inicio
    public void setInicio(NodoListaPalabra i){
        inicio=i;
    }
    public NodoListaPalabra getInicio(){
        return inicio;
    }

//    //Fin
//    public void setFin(NodoLista2 f){
//        fin=f;
//    }
//    public NodoLista2 getFin(){
//        return fin;
//    }


    /*****Métodos Básicos*****/

    //PRE:
    //POS: Retorna true si la lista no tiene nodos
    public boolean esVacia(){
        if (this.inicio==null)
            return true;
        else
            return false;
      }

    
    public void insertaOrdenadoDesc(NodoListaPalabra nodo)
    {
        NodoListaPalabra nuevo = nodo;
        NodoListaPalabra aux = this.inicio;
        
        if (this.esVacia())
        {
            this.inicio = nuevo;
        }
        else 
        {
            if (nodo.getPeso() >= this.inicio.getPeso())
            {
                this.agregarInicio(nodo);
            }
            else
            {
                while (aux.getSig() != null && aux.getSig().getPeso() < this.inicio.getPeso())
                {
                    aux.getSig();
                }
                nuevo.setSig(aux.getSig());
                aux.setSig(nuevo);              
            }
        }
    }

    
    
    
    
    //PRE: 
    //POS: Agrega un nuevo Nodo al principio de la lista
    public void agregarInicio(NodoListaPalabra n){

        n.setSig(inicio);
        this.inicio=n;
//        if(this.fin==null)//estoy insertando el primer nodo
//            this.fin=nuevo;
        }

//    //PRE:
//    //POS: Agrega un nuevo Nodo al final de la lista
//    public void agregarFinal(int n){
//        //NodoLista2 nuevo= new NodoLista2(n);
//        if (this.esVacia())
//            this.agregarInicio(n);
//        else
//        {
//            NodoLista2 aux=this.inicio;
//            while (aux.getSig() != null)
//                aux=aux.getSig();
//            NodoLista2 nuevo= new NodoLista2(n);
//            aux.setSig(nuevo);
////            this.fin=nuevo;
//        }
//    }
//
//    //PRE:
//    //POS: Borra el primer nodo
//    public void borrarInicio(){
//        if (!this.esVacia()){
//            this.inicio=this.inicio.getSig();
//        }
//    }
//
////    //PRE:
////    //POS: Borra el último nodo
////    public void borrarFin(){
////        if (!this.esVacia()){
////            if (this.inicio==this.fin)
////                this.borrarInicio();
////            else{
////                NodoLista2 aux = this.inicio;
////                while (aux.getSig().getSig() != null)
////                    aux=aux.getSig();
//////                this.fin=aux;
//////                this.fin.setSig(null);
////            }
////        }
////    }
//
// //PRE:
//    //POS: elimina todos los nodos de una lista dada
//    public void vaciar(){
//        //en java alcanza con apuntar inicio y fin a null
//        //inicio=fin=null;
//        while (inicio!=null)
//            borrarInicio();
//    }
//

    //POS: Recorre y muestra los datos de lista
    public String mostrarTresPrimeros()
    {
        String ret = "";
        if (!this.esVacia())
        {   
            int i = 1;
            NodoListaPalabra aux = this.inicio;
            while (aux != null && i <= 3)  
            {
                if(i == 3)
                    ret += aux.getPalabra().getPalabra() + "|" + aux.getPeso();
                else
                    ret += aux.getPalabra().getPalabra() + "|" + aux.getPeso() + "|";
                
                aux = aux.getSig();
                i++;
            }         
        }
        return ret;
    }
//
//
///*****Otros Métodos (iterativos)*****/
//
//    //PRE: lista ordenada => mantiena orden
//    //POS: inserta nuevo elemento en orden ascendente
//    public void agregarOrd(int n){
//        //lista vacía o primer elemento es mayor o igual => agrego al ppio
//        if (this.esVacia() || this.inicio.getDato()>=n){
//            this.agregarInicio(n);
//            return;
//        }
//        //último elemento es menor o igual => agrego al final
////        if (this.fin.getDato()<=n){
////            this.agregarFinal(n);
////            return;
////        }
//        NodoLista2 aux=this.inicio;
//        while (aux.getSig()!=null && aux.getSig().getDato() < n)
//            aux=aux.getSig();
//        NodoLista2 nuevo=new NodoLista2(n);
//        nuevo.setSig(aux.getSig());
//        aux.setSig(nuevo);
//    }
//
//    //PRE: lista ordenada
//    //POS: Borra la primer ocurrencia de un elemento dado
//    public void borrarElemento(int n){
//        if (this.esVacia())
//            return;
//        if (this.inicio.getDato()==n)
//            this.borrarInicio();
//        else
//        {
//            NodoLista2 aux=this.inicio;
//            while (aux.getSig()!=null && aux.getSig().getDato() != n)
//                aux=aux.getSig();
//            //lo encontré o llegué al final de la lista
//            if (aux.getSig()!=null){
//                NodoLista2 borrar=aux.getSig();
//                aux.setSig(borrar.getSig());
//                borrar.setSig(null);
//            }
//        }
//    }
//    
//    //PRE: 
//    //POS: Retorna la cantidad de nodos que tiene la lista
//    public int cantElementos(){
//        int cont=0;
//        if (!this.esVacia()){
//            NodoLista2 aux=this.inicio;
//            while (aux!=null){
//                 aux=aux.getSig();
//                 cont ++;
//            }
//        }
//        return cont;
//    }
//
//    //PRE: //POS:
//    public NodoLista2 obtenerElemento(int n){
//        NodoLista2 aux=this.inicio;
//        while (aux!=null && aux.getDato()!=n)
//            aux=aux.getSig();
//        //encontré dato o llegué al final
//        return aux;
//    }
//
//    
//    /*****  Métodos RECURSIVOS  *****/
//
//    //PRE:
//    //POS: muestra los datos de la lista en orden de enlace
//    public void mostrarREC(NodoLista2 l){
//        if(l!=null){
//            System.out.println(l.getDato());
//            mostrarREC(l.getSig());
//        }
//    }
//
//    //PRE:
//    //POS: muestra los datos de la lista en orden inverso
//    public void mostrarInversoREC(NodoLista2 l){
//        if (l!=null){   
//            mostrarInversoREC(l.getSig());
//            System.out.println(l.getDato());
//        }
//    }
//
//    
//    //PRE:
//    //POS: retorna true si el elemento pertenece a la lista
//    public boolean existeDatoREC(NodoLista2 l, int n){
//        if (l != null){
//            if (l.getDato()==n)
//                return true;
//            else
//                return existeDatoREC(l.getSig(),n);
//        }
//        else
//             return false;
//    }


}