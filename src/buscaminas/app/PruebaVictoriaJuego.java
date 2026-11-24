package buscaminas.app;

import buscaminas.modelo.Juego;
import buscaminas.modelo.Casilla;
import buscaminas.modelo.EstadoJuego;
import buscaminas.modelo.ResultadoDescubrimiento;

public class PruebaVictoriaJuego {

	public static void main(String[] args) {

		Juego juego = new Juego();

		int totalDescubiertas = 0;

		for (int fila = 0; fila < 10; fila++) {
			for (int columna = 0; columna < 10; columna++) {

				Casilla casilla = juego.getTablero().getCasilla(fila, columna);

				if (!casilla.tieneMina()) {

					ResultadoDescubrimiento resultado = juego.descubrirCasilla(fila, columna);

					if (resultado == ResultadoDescubrimiento.DESCUBIERTA) {
						totalDescubiertas++;
					}
				}
			}
		}
		
		System.out.println("Casillas seguras descubiertas: " + juego.getTablero().contarCasillasSegurasDescubiertas());
		System.out.println("Estado final: " + juego.getEstado());

		System.out.println("¿El jugador ganó? " + (juego.getEstado() == EstadoJuego.GANADO));
	}
}