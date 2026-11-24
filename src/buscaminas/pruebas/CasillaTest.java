
package buscaminas.pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buscaminas.modelo.Casilla;

class CasillaTest {

    private Casilla casilla;

    @BeforeEach
    void configurar() {
        casilla = new Casilla();
    }

    @Test
    void verificarEstadoInicial() {

        assertFalse(casilla.tieneMina());
        assertEquals(0, casilla.getMinasAdyacentes());
        assertFalse(casilla.estaDescubierta());
        assertFalse(casilla.estaMarcada());
    }

    @Test
    void verificarColocacionDeMina() {

        casilla.colocarMina();

        assertTrue(casilla.tieneMina());
    }

    @Test
    void verificarMinasAdyacentes() {

        casilla.setMinasAdyacentes(5);

        assertEquals(5, casilla.getMinasAdyacentes());
    }

    @Test
    void verificarMarcadoYDesmarcado() {

        assertTrue(casilla.marcar());
        assertTrue(casilla.estaMarcada());

        assertTrue(casilla.marcar());
        assertFalse(casilla.estaMarcada());
    }

    @Test
    void verificarDescubrimiento() {

        assertTrue(casilla.descubrir());
        assertTrue(casilla.estaDescubierta());
    }

    @Test
    void noPermitirDescubrirCasillaMarcada() {

        casilla.marcar();

        assertFalse(casilla.descubrir());
        assertFalse(casilla.estaDescubierta());
    }

    @Test
    void noPermitirMarcarCasillaDescubierta() {

        casilla.descubrir();

        assertFalse(casilla.marcar());
        assertFalse(casilla.estaMarcada());
    }

    @Test
    void verificarRevelarCasilla() {

        casilla.marcar();
        casilla.revelar();

        assertTrue(casilla.estaDescubierta());
        assertFalse(casilla.estaMarcada());
    }

    @Test
    void noPermitirCantidadInvalidaDeMinas() {

        assertThrows(IllegalArgumentException.class, () -> {
            casilla.setMinasAdyacentes(9);
        });
    }
}
