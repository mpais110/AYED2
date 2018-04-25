package uy.ort.ob20181;

import uy.ort.ob20181.Retorno.Resultado;
import TADABBPalabras.Arbol;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class SistemaTest {
    
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
        System.out.println("SISTEMAtestInicializarSistemaConMaxPalabrasPositivo");
        int maxPalabras = 15;
        Sistema instance = new Sistema();
        Resultado expResult = Retorno.Resultado.OK;
        Retorno result = instance.inicializarSistema(maxPalabras);
        assertEquals(expResult, result.resultado);
        System.out.println("Resultado esperado: OK, Resultado obtenido: " + result.valorString);
        System.out.println("______________________________________________________________________________");
    }
    
    
    
    @Test
    public void SISTEMAtestInicializarSistemaConMaxPalabrasNegativo() {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("SISTEMAtestInicializarSistemaConMaxPalabrasNegativo");
        int maxPalabras = -15;
        Sistema instance = new Sistema();
        Resultado expResult = Retorno.Resultado.ERROR_1;
        Retorno result = instance.inicializarSistema(maxPalabras);
        assertEquals(expResult, result.resultado);
        System.out.println("Resultado esperado: ERROR_1, Resultado obtenido: " + result.valorString);
        System.out.println("______________________________________________________________________________");
    }

    
    @Test
    public void SISTEMAtestDestruirSistema() {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("SISTEMAtestDestruirSistema");
        int maxPalabras = 15;
        Sistema instance = new Sistema();
        instance.inicializarSistema(maxPalabras);
        
        Resultado expResult = Retorno.Resultado.OK;
        Retorno result = instance.destruirSistema();
        assertEquals(expResult, result.resultado);
        System.out.println("Resultado esperado: OK, Resultado obtenido: " + result.valorString);
        System.out.println("______________________________________________________________________________");
    }
    
    
    
    
    
    @Test
    public void testAnalizarTexto() {
        System.out.println("analizarTexto");
        String texto = "";
        Sistema instance = new Sistema();
        Retorno expResult = null;
        Retorno result = instance.analizarTexto(texto);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }


    @Test
    public void testRankingPalabras() {
        System.out.println("rankingPalabras");
        int n = 0;
        Sistema instance = new Sistema();
        Retorno expResult = null;
        Retorno result = instance.rankingPalabras(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    @Test
    public void testAparicionesPalabra() {
        System.out.println("aparicionesPalabra");
        String palabra = "";
        Sistema instance = new Sistema();
        Retorno expResult = null;
        Retorno result = instance.aparicionesPalabra(palabra);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    @Test
    public void testTodasPalabras() {
        System.out.println("todasPalabras");
        Sistema instance = new Sistema();
        Retorno expResult = null;
        Retorno result = instance.todasPalabras();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
