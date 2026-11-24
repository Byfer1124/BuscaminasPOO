package buscaminas.persistencia;

import java.io.IOException;
import buscaminas.modelo.Juego;

//Esta clase será la encargada de guardar y 
//recuperar las partidas mediante serialización binaria.
public class PersistenciaSerializada implements PersistenciaJuego {

    @Override
    public void guardar(Juego juego, String ruta) throws IOException {
        // Aquí implementaremos el guardado.
    }

    @Override
    public Juego cargar(String ruta) throws IOException, ClassNotFoundException {
        // Aquí implementaremos la carga.
        return null;
    }
}