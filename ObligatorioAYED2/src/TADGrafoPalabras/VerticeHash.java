package TADGrafoPalabras;


public class VerticeHash 
{
        
    private String palabra;
    private int posic;

    public VerticeHash(String elem, int posic) {
        this.palabra = elem;
        this.posic = posic;
    }

    public VerticeHash() {
        this.palabra = null;
        this.posic = -1;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String elem) {
        this.palabra = elem;
    }
    
    public int getPosic() {
        return posic;
    }

    public void setPosic(int posic) {
        this.posic = posic;
    }   
        
}
