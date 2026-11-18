package es.davante.edadcanina.modelo;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class EdadCaninaModeloTest {

    @Test
    public void testCasoCorrecto() {
        EdadCaninaModelo modelo = new EdadCaninaModelo();
        modelo.setEdadTexto("6");
        Validacion val = modelo.calcular();
        assertEquals(Validacion.CORRECTO, val);
        assertEquals(42, modelo.getEdadHumana());
    }

    @Test
    public void testCasoVacio() {
        EdadCaninaModelo modelo = new EdadCaninaModelo();
        modelo.setEdadTexto("");
        Validacion val = modelo.calcular();
        assertEquals(Validacion.VACIO, val);
    }

    @Test
    public void testCasoNoEsNumero() {
        EdadCaninaModelo modelo = new EdadCaninaModelo();
        modelo.setEdadTexto("abc");
        Validacion val = modelo.calcular();
        assertEquals(Validacion.NO_ES_NUMERO, val);
    }

    @Test
    public void testCasoFueraDeRango() {
        EdadCaninaModelo modelo = new EdadCaninaModelo();
        modelo.setEdadTexto("35");
        Validacion val = modelo.calcular();
        assertEquals(Validacion.FUERA_DE_RANGO, val);
    }
}