package buscaminas.app;

import buscaminas.modelo.Tablero;
import buscaminas.modelo.Casilla;
import buscaminas.modelo.ResultadoDescubrimiento;

public class PruebaDescubrimiento {

    public static void main(String[] args) {

        Tablero tablero = new Tablero();

        int filaCero = -1;
        int columnaCero = -1;

        // Buscar una casilla sin minas adyacentes
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {

                Casilla casilla = tablero.getCasilla(fila, columna);

                if (!casilla.tieneMina()
                        && casilla.getMinasAdyacentes() == 0) {

                    filaCero = fila;
                    columnaCero = columna;
                    break;
                }
            }

            if (filaCero != -1) {
                break;
            }
        }

        if (filaCero == -1) {
            System.out.println("No se encontró una casilla vacía.");
            return;
        }

        System.out.println("Casilla seleccionada: "
                + (char) ('A' + filaCero) + (columnaCero + 1));

        // Descubrir la casilla
        ResultadoDescubrimiento resultado =
                tablero.descubrirCasilla(filaCero, columnaCero);

        System.out.println("Resultado: " + resultado);

        // Mostrar el tablero descubierto
        System.out.println("\nTablero:");

        int totalDescubiertas = 0;
        int minasDescubiertas = 0;

        for (int fila = 0; fila < 10; fila++) {

            for (int columna = 0; columna < 10; columna++) {

                Casilla casilla = tablero.getCasilla(fila, columna);

                if (casilla.estaDescubierta()) {

                    totalDescubiertas++;

                    if (casilla.tieneMina()) {
                        minasDescubiertas++;
                        System.out.print(" X ");
                    } else if (casilla.getMinasAdyacentes() == 0) {
                        System.out.print(" V ");
                    } else {
                        System.out.print(" "
                                + casilla.getMinasAdyacentes() + " ");
                    }

                } else {
                    System.out.print(" # ");
                }
            }

            System.out.println();
        }

        System.out.println("\nCasillas descubiertas: "
                + totalDescubiertas);

        System.out.println("Minas descubiertas: "
                + minasDescubiertas);
    }
}