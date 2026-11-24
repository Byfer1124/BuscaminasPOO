package buscaminas.app;

import buscaminas.controlador.ControladorJuego;

public class Main {

    public static void main(String[] args) {

        ControladorJuego controlador = new ControladorJuego();

        controlador.iniciarPartida();
    }
}