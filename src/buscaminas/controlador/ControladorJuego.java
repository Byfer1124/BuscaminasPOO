
package buscaminas.controlador;

import java.util.Scanner;

import buscaminas.modelo.Juego;
import buscaminas.modelo.EstadoJuego;
import buscaminas.modelo.ResultadoDescubrimiento;
import buscaminas.vista.VistaConsola;

public class ControladorJuego {

    private Juego juego;
    private VistaConsola vista;
    private Scanner scanner;

    public ControladorJuego() {
        juego = new Juego();
        vista = new VistaConsola();
        scanner = new Scanner(System.in);
    }

    // Iniciar la partida
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

            if (!esCoordenadaValida(coordenada)) {
                System.out.println("Coordenada no válida. Intenta nuevamente.");
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

    // Validar coordenadas entre A1 y J10
    //Permite comprobar que el jugador introduzca coordenadas correctas
    private boolean esCoordenadaValida(String coordenada) {

        if (coordenada == null || coordenada.length() < 2
                || coordenada.length() > 3) {
            return false;
        }

        char letra = coordenada.charAt(0);

        if (letra < 'A' || letra > 'J') {
            return false;
        }

        try {
            int numero = Integer.parseInt(coordenada.substring(1));

            return numero >= 1 && numero <= 10;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Procesar el descubrimiento
    private void descubrir(int fila, int columna) {

        ResultadoDescubrimiento resultado =
                juego.descubrirCasilla(fila, columna);

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
    }

    // Procesar el marcado
    private void marcar(int fila, int columna) {

        boolean resultado = juego.marcarCasilla(fila, columna);

        if (resultado) {
            System.out.println("Estado de la marca actualizado.");
        } else {
            System.out.println("No se puede marcar esta casilla.");
        }
    }
}
