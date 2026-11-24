
package buscaminas.modelo;

import buscaminas.excepciones.PartidaFinalizadaException;
import java.io.Serializable;

public class Juego implements Serializable {

	private static final long serialVersionUID = 1L;

	private Tablero tablero;
	private EstadoJuego estado;

	public Juego() {
		tablero = new Tablero();
		estado = EstadoJuego.EN_CURSO;
	}

	public ResultadoDescubrimiento descubrirCasilla(int fila, int columna) throws PartidaFinalizadaException {

		if (estado != EstadoJuego.EN_CURSO) {
			throw new PartidaFinalizadaException("La partida ya ha finalizado. No puedes descubrir más casillas.");
		}

		ResultadoDescubrimiento resultado = tablero.descubrirCasilla(fila, columna);

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

	public boolean marcarCasilla(int fila, int columna) throws PartidaFinalizadaException {

		if (estado != EstadoJuego.EN_CURSO) {
			throw new PartidaFinalizadaException("La partida ya ha finalizado. No puedes marcar casillas.");
		}

		return tablero.marcarCasilla(fila, columna);
	}

	public EstadoJuego getEstado() {
		return estado;
	}

	public Tablero getTablero() {
		return tablero;
	}
}
