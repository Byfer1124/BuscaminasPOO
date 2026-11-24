package buscaminas.pruebas;

import buscaminas.modelo.Juego;
import buscaminas.persistencia.PersistenciaSerializada;

public class PruebaPersistencia {

    public static void main(String[] args) {

    	//new Juego():	Crea una partida nueva.
    	//new PersistenciaSerializada(): Crea el objeto que se encarga de guardar y cargar.
    	//guardar(juego, "partida.dat"):	Guarda el objeto Juego en el archivo.
    	//cargar("partida.dat"): Recupera el objeto desde el archivo.
    	//getEstado(): Permite comparar el estado original con el recuperado.
        Juego juego = new Juego();
        PersistenciaSerializada persistencia = new PersistenciaSerializada();

        try {
        	
        	juego.descubrirCasilla(0, 0);
        	
            persistencia.guardar(juego, "partida.dat");
            System.out.println("Partida guardada correctamente.");

            Juego juegoCargado = persistencia.cargar("partida.dat");
            System.out.println("Partida cargada correctamente.");

            System.out.println("Estado original: " + juego.getEstado());
            System.out.println("Estado cargado: " + juegoCargado.getEstado());
            
            System.out.println("Casilla A1 descubierta: " +
            juegoCargado.getTablero().getCasilla(0, 0).estaDescubierta());

        } catch (Exception e) {
            System.out.println("Error en la persistencia: " + e.getMessage());
        }
    }
}