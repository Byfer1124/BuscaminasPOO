package buscaminas.vista;

import buscaminas.modelo.Casilla;
import buscaminas.modelo.Tablero;

public class VistaConsola {

    public void mostrarTablero(Tablero tablero) {

        System.out.println("\n       BUSCAMINAS");
        System.out.println();

        // Mostrar encabezado de columnas
        System.out.print("    ");

        for (int columna = 1; columna <= 10; columna++) {
            System.out.printf("%3d", columna);
        }

        System.out.println("\n");

        // Mostrar filas
        //Símbolo	Significado
        //#	Casilla oculta
        //F	Casilla marcada
        //V	Casilla vacía descubierta
        //1 a 8	Cantidad de minas adyacentes
        //X	Mina revelada
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
                    System.out.printf("%3d",
                            casilla.getMinasAdyacentes());
                }
            }

            System.out.println("\n");
        }
    }
}