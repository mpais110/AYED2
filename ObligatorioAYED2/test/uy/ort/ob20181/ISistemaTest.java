package uy.ort.ob20181;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uy.ort.ob20181.Retorno.Resultado;

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
	public void testRankingPalabras() {

		String texto;
		
		sis.inicializarSistema(20);
		assertEquals(Resultado.ERROR_1, sis.rankingPalabras(-5).resultado);
		assertEquals(Resultado.ERROR_1, sis.rankingPalabras(-1).resultado);
		assertEquals(Resultado.ERROR_1, sis.rankingPalabras(0).resultado);
		
		texto = "hola hola.hola,hola:hola(hola)hola¿hola!hola�hola?hola";			
		sis.analizarTexto(texto);
                res = sis.todasPalabras();
                System.out.println("Resultado obtenido: " + res.valorString);
                res = sis.rankingPalabras(2);
		assertEquals(Resultado.ERROR_2, res.resultado);
		res = sis.rankingPalabras(1);
		assertEquals(Resultado.OK, res.resultado);
		assertTrue(res.valorString.contains("hola;11"));
		
		sis = new Sistema();
		sis.inicializarSistema(20);
		texto = "aaa!ccc aaa ccc.aaa,ggg:ccc!ccc(ddd)eee¿eee!eee�eee?fff fff fff eee eee fff fff bbb ggg";			
		sis.analizarTexto(texto);
		res = sis.rankingPalabras(5);
		assertEquals(Resultado.OK, res.resultado);
		assertEquals("eee;6|fff;5|ccc;4|aaa;3|ggg;2", res.valorString);
			
	}

	@Test
	public void testAparicionesPalabra() {
		String texto;
		
		sis.inicializarSistema(20);
		
		texto = "aaa!ccc aaa ccc.aaa,ggg:ccc!ccc(ddd)eee�eee!eee¿eee?fff fff fff eee eee fff fff bbb ggg";			
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
	public void testTodasPalabras() {
		String texto;
	
		sis.inicializarSistema(20);
		
		texto = "aaa!ccc aaa ccc.aaa,ggg:ccc!ccc(ddd)eee¿eee!eee�eee?fff fff fff eee eee fff fff bbb ggg";			
		sis.analizarTexto(texto);
		res = sis.todasPalabras();
		assertEquals(Resultado.OK, res.resultado);
		assertEquals("aaa;3|bbb;1|ccc;4|ddd;1|eee;6|fff;5|ggg;2", res.valorString);
                System.out.println("Resultado esperado: aaa;3|bbb;1|ccc;4|ddd;1|eee;6|fff;5|ggg;2");
                System.out.println("Resultado obtenido: " + res.valorString);
		
	}

}
