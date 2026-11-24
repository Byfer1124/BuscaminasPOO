package buscaminas.app;

import buscaminas.modelo.Juego;
import buscaminas.modelo.EstadoJuego;
import buscaminas.modelo.Casilla;
import buscaminas.modelo.ResultadoDescubrimiento;

public class PruebaJuego {

    public static void main(String[] args) {

        Juego juego = new Juego();

        System.out.println("Estado inicial: " + juego.getEstado());

        // Buscar una mina para probar la derrota
        int filaMina = -1;
        int columnaMina = -1;

        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {

                Casilla casilla = juego.getTablero()
                        .getCasilla(fila, columna);

                if (casilla.tieneMina()) {
                    filaMina = fila;
                    columnaMina = columna;
                    break;
                }
            }

            if (filaMina != -1) {
                break;
            }
        }

        System.out.println("\nDescubriendo una mina...");

        ResultadoDescubrimiento resultado =
                juego.descubrirCasilla(filaMina, columnaMina);

        System.out.println("Resultado: " + resultado);
        System.out.println("Estado final: " + juego.getEstado());

        System.out.println("\nIntentando continuar la partida...");

        boolean marcada = juego.marcarCasilla(0, 0);

        System.out.println("¿Se pudo marcar otra casilla? " + marcada);

        if (juego.getEstado() == EstadoJuego.PERDIDO) {
            System.out.println("\nLa partida terminó correctamente.");
        }
    }
}