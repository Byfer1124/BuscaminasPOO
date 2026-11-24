
package buscaminas.controlador;

import java.util.Scanner;

import buscaminas.modelo.Juego;
import buscaminas.modelo.EstadoJuego;
import buscaminas.modelo.ResultadoDescubrimiento;
import buscaminas.vista.VistaConsola;
import buscaminas.excepciones.CoordenadaInvalidaException;

import buscaminas.excepciones.PartidaFinalizadaException;

public class ControladorJuego {

	private Juego juego;
	private VistaConsola vista;
	private Scanner scanner;

	public ControladorJuego() {
		juego = new Juego();
		vista = new VistaConsola();
		scanner = new Scanner(System.in);
	}

	public void iniciarPartida() {

		System.out.println("================================");
		System.out.println("          BUSCAMINAS");
		System.out.println("================================");

		while (juego.getEstado() == EstadoJuego.EN_CURSO) {

			vista.mostrarTablero(juego.getTablero());

			System.out.println("\n1. Descubrir casilla");
			System.out.println("2. Marcar / desmarcar casilla");
			System.out.print("\nSelecciona una opción: ");

			String opcion = scanner.nextLine().trim();

			if (!opcion.equals("1") && !opcion.equals("2")) {
				System.out.println("Opción no válida.");
				continue;
			}

			System.out.print("Introduce una coordenada (ejemplo A5): ");

			String coordenada = scanner.nextLine().trim().toUpperCase();

			try {
				validarCoordenada(coordenada);

			} catch (CoordenadaInvalidaException e) {
				System.out.println(e.getMessage());
				continue;
			}

			int fila = coordenada.charAt(0) - 'A';
			int columna = Integer.parseInt(coordenada.substring(1)) - 1;

			if (opcion.equals("1")) {
				descubrir(fila, columna);
			} else {
				marcar(fila, columna);
			}
		}

		vista.mostrarTablero(juego.getTablero());

		if (juego.getEstado() == EstadoJuego.GANADO) {
			System.out.println("\n¡FELICIDADES! HAS GANADO.");
		} else {
			System.out.println("\n¡HAS PERDIDO! Encontraste una mina.");
		}
	}

	private void validarCoordenada(String coordenada) throws CoordenadaInvalidaException {

		if (coordenada == null || coordenada.length() < 2 || coordenada.length() > 3) {
			throw new CoordenadaInvalidaException("La coordenada debe tener formato A1 hasta J10.");
		}

		char letra = coordenada.charAt(0);

		if (letra < 'A' || letra > 'J') {
			throw new CoordenadaInvalidaException("La fila debe estar entre A y J.");
		}

		int numero;

		try {
			numero = Integer.parseInt(coordenada.substring(1));

		} catch (NumberFormatException e) {
			throw new CoordenadaInvalidaException("La columna debe ser un número.");
		}

		if (numero < 1 || numero > 10) {
			throw new CoordenadaInvalidaException("La columna debe estar entre 1 y 10.");
		}
	}

	private void descubrir(int fila, int columna) {

		try {
			ResultadoDescubrimiento resultado = juego.descubrirCasilla(fila, columna);

			switch (resultado) {

			case DESCUBIERTA:
				System.out.println("Casilla descubierta.");
				break;

			case MINA:
				System.out.println("¡Cuidado! Descubriste una mina.");
				break;

			case NO_DISPONIBLE:
				System.out.println("Esta casilla ya está descubierta o marcada.");
				break;
			}

		} catch (PartidaFinalizadaException e) {
			System.out.println(e.getMessage());
		}
	}

	private void marcar(int fila, int columna) {

		try {
			boolean resultado = juego.marcarCasilla(fila, columna);

			if (resultado) {
				System.out.println("Estado de la marca actualizado.");
			} else {
				System.out.println("No se puede marcar esta casilla.");
			}

		} catch (PartidaFinalizadaException e) {
			System.out.println(e.getMessage());
		}
	}
}
