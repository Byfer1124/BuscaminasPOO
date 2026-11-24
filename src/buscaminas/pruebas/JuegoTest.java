
package buscaminas.pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buscaminas.modelo.Juego;
import buscaminas.modelo.EstadoJuego;
import buscaminas.modelo.ResultadoDescubrimiento;
import buscaminas.excepciones.PartidaFinalizadaException;

class JuegoTest {

    private Juego juego;

    @BeforeEach
    void configurar() {
        juego = new Juego();
    }

    @Test
    void verificarEstadoInicial() {

        assertEquals(EstadoJuego.EN_CURSO, juego.getEstado());
    }

    @Test
    void verificarPerdidaDePartida() throws PartidaFinalizadaException {

        int fila = -1;
        int columna = -1;

        for (int f = 0; f < 10; f++) {
            for (int c = 0; c < 10; c++) {

                if (juego.getTablero().getCasilla(f, c).tieneMina()) {
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
                juego.descubrirCasilla(fila, columna);

        assertEquals(ResultadoDescubrimiento.MINA, resultado);
        assertEquals(EstadoJuego.PERDIDO, juego.getEstado());
    }

    @Test
    void verificarExcepcionAlMarcarDespuesDePerder()
            throws PartidaFinalizadaException {

        provocarDerrota();

        assertThrows(PartidaFinalizadaException.class, () -> {
            juego.marcarCasilla(0, 0);
        });
    }

    @Test
    void verificarExcepcionAlDescubrirDespuesDePerder()
            throws PartidaFinalizadaException {

        provocarDerrota();

        assertThrows(PartidaFinalizadaException.class, () -> {
            juego.descubrirCasilla(0, 0);
        });
    }

    @Test
    void verificarMarcadoDuranteLaPartida()
            throws PartidaFinalizadaException {

        int fila = -1;
        int columna = -1;

        for (int f = 0; f < 10; f++) {
            for (int c = 0; c < 10; c++) {

                if (!juego.getTablero().getCasilla(f, c).tieneMina()) {
                    fila = f;
                    columna = c;
                    break;
                }
            }

            if (fila != -1) {
                break;
            }
        }

        assertTrue(juego.marcarCasilla(fila, columna));
        assertTrue(juego.getTablero()
                .getCasilla(fila, columna).estaMarcada());
    }

    private void provocarDerrota() throws PartidaFinalizadaException {

        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {

                if (juego.getTablero()
                        .getCasilla(fila, columna).tieneMina()) {

                    juego.descubrirCasilla(fila, columna);
                    return;
                }
            }
        }
    }
}
