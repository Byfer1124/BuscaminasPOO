package buscaminas.app;


import buscaminas.modelo.Juego;
import buscaminas.modelo.ResultadoDescubrimiento;
import buscaminas.excepciones.PartidaFinalizadaException;

public class PruebaExcepcionJuego {

    public static void main(String[] args) {

        Juego juego = new Juego();

        try {
            // Buscamos una mina en el tablero.
            int filaMina = -1;
            int columnaMina = -1;

            for (int fila = 0; fila < 10; fila++) {
                for (int columna = 0; columna < 10; columna++) {

                    if (juego.getTablero()
                            .getCasilla(fila, columna).tieneMina()) {

                        filaMina = fila;
                        columnaMina = columna;
                        break;
                    }
                }

                if (filaMina != -1) {
                    break;
                }
            }

            // Descubrimos la mina.
            ResultadoDescubrimiento resultado =
                    juego.descubrirCasilla(filaMina, columnaMina);

            System.out.println("Resultado: " + resultado);
            System.out.println("Estado: " + juego.getEstado());

            // Intentamos marcar una casilla después de perder.
            System.out.println("\nIntentando marcar una casilla...");

            juego.marcarCasilla(0, 0);

        } catch (PartidaFinalizadaException e) {

            System.out.println("Excepción capturada correctamente:");
            System.out.println(e.getMessage());
        }
    }
}

