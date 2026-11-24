
package buscaminas.pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buscaminas.modelo.Tablero;
import buscaminas.modelo.ResultadoDescubrimiento;

class TableroTest {

    private Tablero tablero;

    @BeforeEach
    void configurar() {
        tablero = new Tablero();
    }

    @Test
    void verificarDimensionDelTablero() {

        assertNotNull(tablero.getCasilla(0, 0));
        assertNotNull(tablero.getCasilla(9, 9));
    }

    @Test
    void verificarCantidadDeMinas() {

        int contador = 0;

        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {

                if (tablero.getCasilla(fila, columna).tieneMina()) {
                    contador++;
                }
            }
        }

        assertEquals(10, contador);
    }

    @Test
    void verificarCoordenadasFueraDelTablero() {

        assertThrows(IndexOutOfBoundsException.class, () -> {
            tablero.getCasilla(10, 0);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            tablero.getCasilla(0, 10);
        });
    }

    @Test
    void verificarMarcadoYDesmarcado() {

        int fila = -1;
        int columna = -1;

        for (int f = 0; f < 10; f++) {
            for (int c = 0; c < 10; c++) {

                if (!tablero.getCasilla(f, c).tieneMina()) {
                    fila = f;
                    columna = c;
                    break;
                }
            }

            if (fila != -1) {
                break;
            }
        }

        assertTrue(tablero.marcarCasilla(fila, columna));
        assertTrue(tablero.getCasilla(fila, columna).estaMarcada());

        assertTrue(tablero.marcarCasilla(fila, columna));
        assertFalse(tablero.getCasilla(fila, columna).estaMarcada());
    }

    @Test
    void verificarDescubrimientoDeCasillaSegura() {

        int fila = -1;
        int columna = -1;

        for (int f = 0; f < 10; f++) {
            for (int c = 0; c < 10; c++) {

                if (!tablero.getCasilla(f, c).tieneMina()) {
                    fila = f;
                    columna = c;
                    break;
                }
            }

            if (fila != -1) {
                break;
            }
        }

        ResultadoDescubrimiento resultado =
                tablero.descubrirCasilla(fila, columna);

        assertEquals(ResultadoDescubrimiento.DESCUBIERTA, resultado);
        assertTrue(tablero.getCasilla(fila, columna).estaDescubierta());
    }

    @Test
    void verificarDescubrimientoDeMina() {

        int fila = -1;
        int columna = -1;

        for (int f = 0; f < 10; f++) {
            for (int c = 0; c < 10; c++) {

                if (tablero.getCasilla(f, c).tieneMina()) {
                    fila = f;
                    columna = c;
                    break;
                }
            }

            if (fila != -1) {
                break;
            }
        }

        ResultadoDescubrimiento resultado =
                tablero.descubrirCasilla(fila, columna);

        assertEquals(ResultadoDescubrimiento.MINA, resultado);
        assertTrue(tablero.getCasilla(fila, columna).estaDescubierta());
    }

    @Test
    void verificarRevelarMinas() {

        tablero.revelarMinas();

        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {

                var casilla = tablero.getCasilla(fila, columna);

                if (casilla.tieneMina()) {
                    assertTrue(casilla.estaDescubierta());
                }
            }
        }
    }
}
