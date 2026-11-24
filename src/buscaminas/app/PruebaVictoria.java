package buscaminas.app;

import buscaminas.modelo.Tablero;

public class PruebaVictoria {

    public static void main(String[] args) {

        Tablero tablero = new Tablero();

        System.out.println("Casillas seguras descubiertas: "
                + tablero.contarCasillasSegurasDescubiertas());

        System.out.println("¿Hay victoria? " + tablero.hayVictoria());

        System.out.println("\nRevelando todas las minas...");
        tablero.revelarMinas();

        System.out.println("Minas reveladas:");

        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {

                if (tablero.getCasilla(fila, columna).tieneMina()
                        && tablero.getCasilla(fila, columna).estaDescubierta()) {
                    System.out.print("X ");
                } else {
                    System.out.print("# ");
                }
            }
            System.out.println();
        }
    }
}