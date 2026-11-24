package buscaminas.app;

import buscaminas.modelo.Casilla;

public class PruebaCasilla {

    public static void main(String[] args) {

        Casilla casilla = new Casilla();

        System.out.println("Estado inicial:");
        System.out.println("Tiene mina: " + casilla.tieneMina());
        System.out.println("Descubierta: " + casilla.estaDescubierta());
        System.out.println("Marcada: " + casilla.estaMarcada());
        System.out.println("Minas adyacentes: "
                + casilla.getMinasAdyacentes());

        System.out.println("\nColocando una mina...");
        casilla.colocarMina();

        System.out.println("Tiene mina: " + casilla.tieneMina());

        System.out.println("\nAsignando minas adyacentes...");
        casilla.setMinasAdyacentes(3);

        System.out.println("Minas adyacentes: "
                + casilla.getMinasAdyacentes());

        System.out.println("\nMarcando casilla...");
        casilla.marcar();

        System.out.println("Marcada: " + casilla.estaMarcada());

        System.out.println("\nDesmarcando casilla...");
        casilla.marcar();

        System.out.println("Marcada: " + casilla.estaMarcada());

        System.out.println("\nDescubriendo casilla...");
        casilla.descubrir();

        System.out.println("Descubierta: "
                + casilla.estaDescubierta());

    }
}