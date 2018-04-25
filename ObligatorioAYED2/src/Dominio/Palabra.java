package Dominio;


public class Palabra 
{


    private String palabra;
    private int cantidad;

    
    public Palabra(String palabra, int cantidad) {
        this.cantidad = cantidad;
        this.palabra = palabra;
    }

    public Palabra() {
        this.cantidad = 1;
        this.palabra = null;
    }


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    
}
