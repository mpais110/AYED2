package uy.ort.ob20182;

import uy.ort.ob20182.Retorno.Resultado;

public class Sistema implements ISistema {


	public Retorno inicializarSistema (int maxPalabras) {
		// ToDo: Implementar aqui el metodo
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}
	
	public Retorno destruirSistema() {
		// ToDo: Implementar aqui el metodo
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	public Retorno analizarTexto(String texto) {
		// ToDo: Implementar aqui el metodo
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	public Retorno aparicionesPalabra(String palabra) {
		// ToDo: Implementar aqui el metodo
		return new Retorno(Resultado.NO_IMPLEMENTADA);
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
