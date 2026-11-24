package buscaminas.app;

import buscaminas.modelo.Tablero;
import buscaminas.modelo.Casilla;

public class PruebaTablero {

    public static void main(String[] args) {

        Tablero tablero = new Tablero();

        int minasEncontradas = 0;

        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {

                Casilla casilla = tablero.getCasilla(fila, columna);

                if (casilla.tieneMina()) {
                    minasEncontradas++;
                }
            }
        }

        System.out.println("Minas colocadas: " + minasEncontradas);

        System.out.println("\nTablero interno de prueba:");

        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {

                Casilla casilla = tablero.getCasilla(fila, columna);

                if (casilla.tieneMina()) {
                    System.out.print(" X ");
                } else {
                    System.out.print(" " 
                            + casilla.getMinasAdyacentes() + " ");
                }
            }

            System.out.println();
        }
    }
}
