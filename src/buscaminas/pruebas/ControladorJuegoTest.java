
package buscaminas.pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buscaminas.controlador.ControladorJuego;
import buscaminas.excepciones.CoordenadaInvalidaException;


//Como el método validarCoordenada() es privado, 
//utilizaremos Reflection de Java para acceder a él desde JUnit, sin modificar el controlador.
class ControladorJuegoTest {

    private ControladorJuego controlador;
    private Method metodoValidar;

    @BeforeEach
    void configurar() throws NoSuchMethodException {

        controlador = new ControladorJuego();

        metodoValidar = ControladorJuego.class
                .getDeclaredMethod("validarCoordenada", String.class);

        metodoValidar.setAccessible(true);
    }

    private void validar(String coordenada) throws Throwable {

        try {
            metodoValidar.invoke(controlador, coordenada);

        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    @Test
    void verificarCoordenadaValida() {

        assertDoesNotThrow(() -> validar("A5"));
        assertDoesNotThrow(() -> validar("J10"));
    }

    @Test
    void verificarFilaInvalida() {

        assertThrows(CoordenadaInvalidaException.class, () -> {
            validar("K5");
        });
    }

    @Test
    void verificarColumnaFueraDeRango() {

        assertThrows(CoordenadaInvalidaException.class, () -> {
            validar("A0");
        });

        assertThrows(CoordenadaInvalidaException.class, () -> {
            validar("A11");
        });
    }

    @Test
    void verificarColumnaNoNumerica() {

        assertThrows(CoordenadaInvalidaException.class, () -> {
            validar("Axx");
        });
    }

    @Test
    void verificarFormatoIncorrecto() {

        assertThrows(CoordenadaInvalidaException.class, () -> {
            validar("A");
        });

        assertThrows(CoordenadaInvalidaException.class, () -> {
            validar("A123");
        });
    }
}
