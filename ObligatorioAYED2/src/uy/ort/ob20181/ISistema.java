package uy.ort.ob20181;


// Interfaz del sistema
// No modificar esta clase!!!!!!!!!
public interface ISistema {

	Retorno inicializarSistema (int maxPalabras);
	
	Retorno destruirSistema();
	
	Retorno analizarTexto(String texto);
	
	Retorno rankingPalabras(int n);
	
	Retorno aparicionesPalabra(String palabra);
	
	Retorno todasPalabras();
	

	
}
