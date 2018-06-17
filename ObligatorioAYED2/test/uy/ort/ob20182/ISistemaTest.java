package uy.ort.ob20182;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uy.ort.ob20182.Retorno.Resultado;

public class ISistemaTest {

	ISistema sis;
	Retorno res;
	
	@Before
	public void setUp() throws Exception {
		sis = new Sistema();
	}

	@Test
	public void testInicializarSistema() {
		
		assertEquals(Resultado.ERROR_1, sis.inicializarSistema(-5).resultado);
		assertEquals(Resultado.ERROR_1, sis.inicializarSistema(-1).resultado);
		assertEquals(Resultado.ERROR_1, sis.inicializarSistema(0).resultado);
		assertEquals(Resultado.OK, sis.inicializarSistema(1).resultado);
	}

	@Test
	public void testDestruirSistema() {
		sis.inicializarSistema(1);
		assertEquals(Resultado.OK, sis.destruirSistema().resultado);
		
	}

	@Test
	public void testAnalizarTexto() {

		sis.inicializarSistema(20);
		String texto;
		texto = "";
		assertEquals(Resultado.ERROR_1, sis.analizarTexto(texto).resultado);
		texto = "hola hola.hola,hola:hola(hola)hola�hola!hola�hola?hola";			
		assertEquals(Resultado.OK, sis.analizarTexto(texto).resultado);
		
	}

	@Test
	public void testAparicionesPalabra() {
		String texto;
		
		sis.inicializarSistema(20);
		
		texto = "aaa!ccc aaa ccc.aaa,ggg:ccc!ccc(ddd)eee�eee!eee�eee?fff fff fff eee eee fff fff bbb ggg";			
		sis.analizarTexto(texto);
		assertEquals(Resultado.ERROR_1, sis.aparicionesPalabra("hhh").resultado);
		assertEquals(Resultado.ERROR_1, sis.aparicionesPalabra("ff").resultado);
		assertEquals(Resultado.ERROR_1, sis.aparicionesPalabra("eeee").resultado);
		assertEquals(Resultado.ERROR_1, sis.aparicionesPalabra("").resultado);
		res = sis.aparicionesPalabra("fff");
		assertEquals(Resultado.OK, res.resultado);
		assertEquals(5, res.valorEntero);
		res = sis.aparicionesPalabra("eee");
		assertEquals(Resultado.OK, res.resultado);
		assertEquals(6, res.valorEntero);
		res = sis.aparicionesPalabra("ggg");
		assertEquals(Resultado.OK, res.resultado);
		assertEquals(2, res.valorEntero);
	}

	@Test
	public void testPredecirPalabra() {
		String texto;
		
		sis.inicializarSistema(50);
		texto = "la madre de la madre de la abuela de la hija de la tia de la madre de la tia de la hija de la madre de la tia de la hija de la abuela de la hija de la madre de rigoberta menchu";			
		sis.analizarTexto(texto);

		assertEquals(Resultado.ERROR_1, sis.predecirPalabra("bisnieta").resultado);
		
		res = sis.predecirPalabra("la");
		assertEquals(Resultado.OK, res.resultado);
		assertEquals("madre;5|hija;4|tia;3", res.valorString);
		
		sis = new Sistema();
		sis.inicializarSistema(50);
		texto = "la madre de la madre de la abuela de la hija de la tia de la madre de la tia de la hija de la madre de la tia de la tia de la hija de la abuela de la hija de la madre de rigoberta menchu";			
		sis.analizarTexto(texto);
		
		res = sis.predecirPalabra("la");
		assertEquals(Resultado.OK, res.resultado);
		assertTrue("madre;5|hija;4|tia;4".equals(res.valorString) || "madre;5|tia;4|hija;4".equals(res.valorString));
	}

	@Test
	public void testRepetirFrase() {
		String texto;
		
		sis.inicializarSistema(50);
		texto = "la madre de la hija de la abuela de la madre de la abuela de rigoberta menchu";			
		sis.analizarTexto(texto);

		assertEquals(Resultado.ERROR_1, sis.repetirFrase("la","rigobertosa").resultado);
		assertEquals(Resultado.ERROR_1, sis.repetirFrase("rigobertosa","la").resultado);
		//assertEquals(Resultado.ERROR_2, sis.repetirFrase("menchu","madre").resultado);

		res = sis.repetirFrase("madre","abuela");
		assertEquals(Resultado.OK, res.resultado);
		assertEquals("madre de la abuela",res.valorString);
		
		res = sis.repetirFrase("madre","menchu");
		assertEquals(Resultado.OK, res.resultado);
		assertTrue(res.valorString.equals("madre de la abuela de rigoberta menchu") || res.valorString.equals("madre de rigoberta menchu"));
		
	}


}
