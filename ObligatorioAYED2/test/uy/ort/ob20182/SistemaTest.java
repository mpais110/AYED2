/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.ort.ob20182;

import uy.ort.ob20182.Retorno.Resultado;
import TADGrafoPalabras.GrafoMatriz;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gonzalo
 */
    
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
    	sis = new Sistema();
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
        
        sis.inicializarSistema(20);
        String texto;
        texto = "ADASDFVSCcsdvsfsdfdsf654684vdxfsdvfvdvxdvsfv";	
        
        Resultado expResult = Retorno.Resultado.OK;
        Retorno result = sis.analizarTexto(texto);
        assertEquals(expResult, result.resultado);
        //result = sis.todasPalabras();
        
        System.out.println("Resultado esperado: adasdfvsccsdvsfsdfdsf654684vdxfsdvfvdvxdvsfv;1");
        System.out.println("Resultado obtenido: " + result.valorString);
        System.out.println("______________________________________________________________________________");
    }
}
