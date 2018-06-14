package uy.ort.ob20182;


// Interfaz del sistema
// No modificar esta clase!!!!!!!!!
public interface ISistema {

	Retorno inicializarSistema (int maxPalabras);
	
	Retorno destruirSistema();
	
	Retorno analizarTexto(String texto);
	
	Retorno aparicionesPalabra(String palabra);

	Retorno predecirPalabra(String palabra);

	Retorno repetirFrase(String palabraIni, String palabraFin);
	
}
