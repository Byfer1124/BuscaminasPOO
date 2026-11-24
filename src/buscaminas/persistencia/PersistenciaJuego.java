package buscaminas.persistencia;

import java.io.IOException;
import buscaminas.modelo.Juego;

public interface PersistenciaJuego {

    void guardar(Juego juego, String ruta) throws IOException; //Recibe una partida y una ruta donde se almacenará.

    Juego cargar(String ruta) throws IOException, ClassNotFoundException;//recibe la ruta del archivo y devuelve la partida recuperada.
}