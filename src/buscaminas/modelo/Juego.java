package buscaminas.modelo;

public class Juego {

    private Tablero tablero;
    private EstadoJuego estado;

    public Juego() {
        tablero = new Tablero();
        estado = EstadoJuego.EN_CURSO;
    }

    // Descubrir una casilla
    //Este método delega el descubrimiento al tablero y recibe el resultado.
    //Si encuentra una mina, cambia el estado a PERDIDO y revela las minas.
    //Si descubre una casilla segura, comprueba si se alcanzó la victoria.
    //Si la partida ya terminó, no permite continuar jugando.
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