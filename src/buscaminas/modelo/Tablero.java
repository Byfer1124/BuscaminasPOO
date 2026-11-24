package buscaminas.modelo;

import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;

public class Tablero {

	// TAMANIO: indica que el tablero tendrá 10 filas y 10 columnas.
	// CANTIDAD_MINAS: establece que se colocarán 10 minas.

	private static final int TAMANIO = 10;
	private static final int CANTIDAD_MINAS = 10;

	private Casilla[][] casillas;

	public Tablero() {
		inicializarTablero();
		colocarMinas();
		calcularMinasAdyacentes();
	}

	// Crear las 100 casillas
	private void inicializarTablero() {
		casillas = new Casilla[TAMANIO][TAMANIO];

		for (int fila = 0; fila < TAMANIO; fila++) {
			for (int columna = 0; columna < TAMANIO; columna++) {
				casillas[fila][columna] = new Casilla();
			}
		}
	}

	// Colocar las minas aleatoriamente
	private void colocarMinas() {
		Random random = new Random(); // Esta clase nos permite generar números aleatorios.
		int minasColocadas = 0;

		while (minasColocadas < CANTIDAD_MINAS) {

			int fila = random.nextInt(TAMANIO);
			int columna = random.nextInt(TAMANIO);

			if (!casillas[fila][columna].tieneMina()) {
				casillas[fila][columna].colocarMina();
				minasColocadas++;
			}
		}
	}

	// Calcular las minas adyacentes
	private void calcularMinasAdyacentes() {

		for (int fila = 0; fila < TAMANIO; fila++) {
			for (int columna = 0; columna < TAMANIO; columna++) {

				if (!casillas[fila][columna].tieneMina()) {

					int contador = contarMinasAdyacentes(fila, columna);

					casillas[fila][columna].setMinasAdyacentes(contador);
				}
			}
		}
	}

	// Contar las minas alrededor de una casilla
	private int contarMinasAdyacentes(int fila, int columna) {

		int contador = 0;

		for (int desplazamientoFila = -1; desplazamientoFila <= 1; desplazamientoFila++) {

			for (int desplazamientoColumna = -1; desplazamientoColumna <= 1; desplazamientoColumna++) {

				int nuevaFila = fila + desplazamientoFila;
				int nuevaColumna = columna + desplazamientoColumna;

				if (estaDentroDelTablero(nuevaFila, nuevaColumna) && casillas[nuevaFila][nuevaColumna].tieneMina()) {

					contador++;
				}
			}
		}

		return contador;
	}

	// Verificar que una coordenada esté dentro del tablero
	private boolean estaDentroDelTablero(int fila, int columna) {
		return fila >= 0 && fila < TAMANIO && columna >= 0 && columna < TAMANIO;
	}

	// Consultar una casilla
	public Casilla getCasilla(int fila, int columna) {
		if (!estaDentroDelTablero(fila, columna)) {
			throw new IndexOutOfBoundsException("Coordenada fuera del tablero.");
		}

		return casillas[fila][columna];
	}

	// Descubrir una casilla seleccionada
	public ResultadoDescubrimiento descubrirCasilla(int fila, int columna) {

		if (!estaDentroDelTablero(fila, columna)) {
			throw new IndexOutOfBoundsException("Coordenada fuera del tablero.");
		}

		Casilla casilla = casillas[fila][columna];

		// Verificar si ya está descubierta o marcada
		if (casilla.estaDescubierta() || casilla.estaMarcada()) {
			return ResultadoDescubrimiento.NO_DISPONIBLE;
		}

		// Descubrir la casilla
		casilla.descubrir();

		// Comprobar si contiene una mina
		if (casilla.tieneMina()) {
			return ResultadoDescubrimiento.MINA;
		}

		// Si no tiene minas adyacentes, aplicar Flood Fill
		if (casilla.getMinasAdyacentes() == 0) {
			descubrirAdyacentes(fila, columna);
		}

		return ResultadoDescubrimiento.DESCUBIERTA;
	}

	// Descubrimiento automático mediante Queue
	private void descubrirAdyacentes(int filaInicial, int columnaInicial) {

		Queue<int[]> cola = new LinkedList<>();

		// Agregar la primera posición
		cola.add(new int[] { filaInicial, columnaInicial });

		while (!cola.isEmpty()) {

			int[] posicion = cola.poll();

			int fila = posicion[0];
			int columna = posicion[1];

			// Revisar las 8 posiciones vecinas
			for (int desplazamientoFila = -1; desplazamientoFila <= 1; desplazamientoFila++) {

				for (int desplazamientoColumna = -1; desplazamientoColumna <= 1; desplazamientoColumna++) {

					// No revisar la propia casilla
					if (desplazamientoFila == 0 && desplazamientoColumna == 0) {
						continue;
					}

					int nuevaFila = fila + desplazamientoFila;
					int nuevaColumna = columna + desplazamientoColumna;

					// Comprobar límites
					if (!estaDentroDelTablero(nuevaFila, nuevaColumna)) {
						continue;
					}

					Casilla vecina = casillas[nuevaFila][nuevaColumna];

					// Ignorar minas, casillas descubiertas y marcadas
					if (vecina.tieneMina() || vecina.estaDescubierta() || vecina.estaMarcada()) {
						continue;
					}

					// Descubrir la casilla vecina
					vecina.descubrir();

					// Si tiene cero minas adyacentes, continuar explorando
					if (vecina.getMinasAdyacentes() == 0) {
						cola.add(new int[] { nuevaFila, nuevaColumna });
					}
				}
			}
		}
	}

	// Marcar o desmarcar una casilla
	public boolean marcarCasilla(int fila, int columna) {

		if (!estaDentroDelTablero(fila, columna)) {
			throw new IndexOutOfBoundsException("Coordenada fuera del tablero.");
		}

		return casillas[fila][columna].marcar();
	}

	// Contar las casillas seguras que han sido descubiertas
	public int contarCasillasSegurasDescubiertas() {

		int contador = 0;

		for (int fila = 0; fila < TAMANIO; fila++) {
			for (int columna = 0; columna < TAMANIO; columna++) {

				Casilla casilla = casillas[fila][columna];

				if (!casilla.tieneMina() && casilla.estaDescubierta()) {
					contador++;
				}
			}
		}

		return contador;
	}

	// Comprobar si el jugador ha ganado
	public boolean hayVictoria() {

		int totalCasillasSeguras = TAMANIO * TAMANIO - CANTIDAD_MINAS;

		return contarCasillasSegurasDescubiertas() == totalCasillasSeguras;
	}

	// Revelar todas las minas del tablero
	public void revelarMinas() {

		for (int fila = 0; fila < TAMANIO; fila++) {
			for (int columna = 0; columna < TAMANIO; columna++) {

				Casilla casilla = casillas[fila][columna];

				if (casilla.tieneMina()) {
					casilla.revelar();
				}
			}
		}
	}
}