
package buscaminas.vista;

import buscaminas.modelo.Casilla;
import buscaminas.modelo.Tablero;

public class VistaConsola {

	public void mostrarTablero(Tablero tablero) {

		System.out.println("\n       BUSCAMINAS");
		System.out.println();

		// Mostrar encabezado de columnas.
		System.out.print("    ");

		for (int columna = 1; columna <= 10; columna++) {
			System.out.printf("%3d", columna);
		}

		System.out.println("\n");

		// Mostrar filas y contenido de cada casilla.
		for (int fila = 0; fila < 10; fila++) {

			System.out.print("  " + (char) ('A' + fila) + " ");

			for (int columna = 0; columna < 10; columna++) {

				Casilla casilla = tablero.getCasilla(fila, columna);

				if (casilla.estaMarcada()) {
					System.out.print("  F");

				} else if (!casilla.estaDescubierta()) {
					System.out.print("  #");

				} else if (casilla.tieneMina()) {
					System.out.print("  X");

				} else if (casilla.getMinasAdyacentes() == 0) {
					System.out.print("  V");

				} else {
					System.out.printf("%3d", casilla.getMinasAdyacentes());
				}
			}

			System.out.println("\n");
		}
	}

	public void mostrarReglas() {

		System.out.println("\n========== REGLAS DEL BUSCAMINAS ==========");

		System.out.println("1. El tablero tiene 10 filas y 10 columnas.");
		System.out.println("2. Existen 10 minas distribuidas aleatoriamente.");
		System.out.println("3. Las filas se identifican con letras de A a J.");
		System.out.println("4. Las columnas se identifican con números del 1 al 10.");
		System.out.println("5. Cada número indica cuántas minas hay alrededor.");
		System.out.println("6. Las casillas vacías pueden descubrir otras automáticamente.");
		System.out.println("7. Puedes marcar y desmarcar casillas sospechosas.");
		System.out.println("8. Si descubres una mina, pierdes la partida.");
		System.out.println("9. Ganas cuando descubres todas las casillas sin minas.");
		System.out.println("10. Puedes guardar y cargar tu partida.");

		System.out.println("\n========== SIGNIFICADO DE LOS SIMBOLOS ==========");

		System.out.println("#  = Casilla oculta.");
		System.out.println("F  = Casilla marcada por el jugador.");
		System.out.println("V  = Casilla vacía descubierta, sin minas alrededor.");
		System.out.println("1-8 = Cantidad de minas en las casillas adyacentes.");
		System.out.println("X  = Mina revelada.");

		System.out.println("================================================");
	}
}
