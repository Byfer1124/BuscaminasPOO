package buscaminas.modelo;

public class Juego {

    private Tablero tablero;
    private EstadoJuego estado;

    public Juego() {
        tablero = new Tablero();
        estado = EstadoJuego.EN_CURSO;
    }

    // Descubrir una casilla
    public ResultadoDescubrimiento descubrirCasilla(int fila, int columna) {

        if (estado != EstadoJuego.EN_CURSO) {
            return ResultadoDescubrimiento.NO_DISPONIBLE;
        }

        ResultadoDescubrimiento resultado =
                tablero.descubrirCasilla(fila, columna);

        if (resultado == ResultadoDescubrimiento.MINA) {
            estado = EstadoJuego.PERDIDO;
            tablero.revelarMinas();

        } else if (resultado == ResultadoDescubrimiento.DESCUBIERTA) {

            if (tablero.hayVictoria()) {
                estado = EstadoJuego.GANADO;
            }
        }

        return resultado;
    }

    // Marcar o desmarcar una casilla
    public boolean marcarCasilla(int fila, int columna) {

        if (estado != EstadoJuego.EN_CURSO) {
            return false;
        }

        return tablero.marcarCasilla(fila, columna);
    }

    // Obtener el estado actual de la partida
    public EstadoJuego getEstado() {
        return estado;
    }

    // Obtener el tablero
    public Tablero getTablero() {
        return tablero;
    }
}