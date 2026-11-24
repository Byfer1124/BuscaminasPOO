package buscaminas.app;

import buscaminas.modelo.Juego;
import buscaminas.vista.VistaConsola;

public class PruebaVista {

    public static void main(String[] args) {

        Juego juego = new Juego();

        VistaConsola vista = new VistaConsola();

        System.out.println("TABLERO INICIAL:");

        vista.mostrarTablero(juego.getTablero());

        System.out.println("\nDESCUBRIENDO LA CASILLA A1:");

        juego.descubrirCasilla(0, 0);

        vista.mostrarTablero(juego.getTablero());
    }
}