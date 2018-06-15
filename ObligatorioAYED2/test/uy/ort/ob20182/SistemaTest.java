package uy.ort.ob20182;

import TADGrafoPalabras.GrafoMatriz;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uy.ort.ob20182.Retorno.Resultado;


public class SistemaTest {
    
    ISistema sis;
    Retorno res;
	
    
    public SistemaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void SISTEMAtestInicializarSistemaConMaxPalabrasPositivo() {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("SISTEMA testInicializarSistemaConMaxPalabrasPositivo");
        int maxPalabras = 15;
        Sistema instance = new Sistema();
        Resultado expResult = Retorno.Resultado.OK;
        Retorno result = instance.inicializarSistema(maxPalabras);
        assertEquals(expResult, result.resultado);
        System.out.println("Resultado esperado: OK");
	System.out.println("Resultado obtenido: " + result.valorString);
        System.out.println("______________________________________________________________________________");
    }
    
    
    
    @Test
    public void SISTEMAtestInicializarSistemaConMaxPalabrasNegativo() {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("SISTEMA testInicializarSistemaConMaxPalabrasNegativo");
        int maxPalabras = -15;
        Sistema instance = new Sistema();
        Resultado expResult = Retorno.Resultado.ERROR_1;
        Retorno result = instance.inicializarSistema(maxPalabras);
        assertEquals(expResult, result.resultado);
        System.out.println("Resultado esperado: ERROR_1");
	System.out.println("Resultado obtenido: " + result.valorString);
        System.out.println("______________________________________________________________________________");
    }

    
    @Test
    public void SISTEMAtestDestruirSistema() {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("SISTEMA testDestruirSistema");
        int maxPalabras = 15;
        Sistema instance = new Sistema();
        instance.inicializarSistema(maxPalabras);
        
        Resultado expResult = Retorno.Resultado.OK;
        Retorno result = instance.destruirSistema();
        assertEquals(expResult, result.resultado);
        System.out.println("Resultado esperado: OK");
	System.out.println("Resultado obtenido: " + result.valorString);
        System.out.println("______________________________________________________________________________");
    }
    
 
    
    @Test
    public void testAnalizarTextoSinSeparadores() {
    	System.out.println("------------------------------------------------------------------------------");
        System.out.println("PALABRAS AnalizarTextoSinSeparadores");
        Sistema instance = new Sistema();
	instance.inicializarSistema(20);
	String texto;
	texto = "ADASDFVSCcsdvsfsdfdsf654684vdxfsdvfvdvxdvsfv";		
	Resultado expResult = Retorno.Resultado.OK;
        Retorno result = instance.analizarTexto(texto);
        assertEquals(expResult, result.resultado);
        //result = sis.todasPalabras();
        System.out.println("Resultado esperado: adasdfvsccsdvsfsdfdsf654684vdxfsdvfvdvxdvsfv;1");
        System.out.println("Resultado obtenido: " + result.valorString);
        System.out.println("______________________________________________________________________________");
    }
    
    
    
    
    @Test
    public void testAnalizarTextoConSeparadoresSinPalabrasRepetidas() {
    	System.out.println("------------------------------------------------------------------------------");
        System.out.println("PALABRAS AnalizarTextoConSeparadoresSinPalabrasRepetidas");
        Sistema instance = new Sistema();
        instance.inicializarSistema(20);
        String texto;
        texto = "aaa!CCC BBB DDD.F,G:HHH!ii(jjjjj)kkk�ll!m�nnn?ooooo pppp QQq RRR";		
        Resultado expResult = Retorno.Resultado.OK;
        Retorno result = instance.analizarTexto(texto);
        assertEquals(expResult, result.resultado);
//        result = sis.todasPalabras();
        System.out.println("Resultado esperado: aaa;1|bbb;1|ccc;1|ddd;1|f;1|g;1|hhh;1|ii;1|jjjjj;1|kkk;1|ll;1|m;1|nnn;1|ooooo;1|pppp;1|qqq;1|rrr;1");
        System.out.println("Resultado obtenido: " + result.valorString);
        System.out.println("______________________________________________________________________________");
    }
    
    
    
    @Test
    public void testAnalizarTextoConSeparadoresConPalabrasRepetidas() {
    	System.out.println("------------------------------------------------------------------------------");
        System.out.println("PALABRAS AnalizarTextoConSeparadoresConPalabrasRepetidas");
        Sistema instance = new Sistema();
        instance.inicializarSistema(20);
        String texto;
        texto = "aaa!CCC AAA DDD.ddd,G:HHH!ii(jjjjj)JJJJJ�ll!m�nnn?ooooo OOO QQq RRR";		
        Resultado expResult = Retorno.Resultado.OK;
        Retorno result = instance.analizarTexto(texto);
        assertEquals(expResult, result.resultado);
//        result = sis.todasPalabras();
        System.out.println("Resultado esperado: aaa;2|ccc;1|ddd;2|g;1|hhh;1|ii;1|jjjjj;2|ll;1|m;1|nnn;1|ooo;1|ooooo;1|qqq;1|rrr;1");
        System.out.println("Resultado obtenido: " + result.valorString);
        System.out.println("______________________________________________________________________________");
    }
    


    @Test
    public void testAparicionesPalabra() {
    	System.out.println("------------------------------------------------------------------------------");
        System.out.println("PALABRAS AparicionesPalabra");
        Sistema instance = new Sistema();
        instance.inicializarSistema(100);
        String texto;
        texto = "aaa!ccc aaaccc.aaa,ggg:ccc! agua ccc(ddd)eee�eee!eee�eee?fff fff fff eee eee fff fff bbb ggg agua agua agua";
        Resultado expResult = Retorno.Resultado.OK;
        Retorno result = instance.analizarTexto(texto);
        assertEquals(expResult, result.resultado);
        result = instance.aparicionesPalabra("agua");
        System.out.println("Resultado esperado: 4");
        System.out.println("Resultado obtenido: " + result.valorEntero);
        result = instance.aparicionesPalabra("ccc");
        System.out.println("Resultado esperado: 3");
        System.out.println("Resultado obtenido: " + result.valorEntero);
        result = instance.aparicionesPalabra("aaaccc");
        System.out.println("Resultado esperado: 1");
        System.out.println("Resultado obtenido: " + result.valorEntero);
        result = instance.aparicionesPalabra("fff");
        System.out.println("Resultado esperado: 5");
        System.out.println("Resultado obtenido: " + result.valorEntero);        

        System.out.println("______________________________________________________________________________");
    }
    
    
    @Test
    public void testAparicionesPalabraConPalabraNoExiste() {
    	System.out.println("------------------------------------------------------------------------------");
        System.out.println("PALABRAS AparicionesPalabraConPalabraNoExiste");
        Sistema instance = new Sistema();
        instance.inicializarSistema(30);
        String texto;
        texto = "aaa!CCC AAA DDD.ddd,G:HHH!ii(jjjjj)JJJJJ�ll!m�nnn?ooooo OOO QQq RRR 152 874";		
        Resultado expResult = Retorno.Resultado.OK;
        Retorno result = instance.analizarTexto(texto);
        assertEquals(expResult, result.resultado);
        result = instance.aparicionesPalabra("pdqbqe");
        System.out.println("Resultado esperado: ERROR_1");
        System.out.println("Resultado obtenido: " + result.valorString);
        System.out.println("______________________________________________________________________________");
    }

}
