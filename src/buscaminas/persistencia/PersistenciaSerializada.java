
package buscaminas.persistencia;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import buscaminas.modelo.Juego;

// Esta clase será la encargada de guardar y
// recuperar las partidas mediante serialización binaria.
public class PersistenciaSerializada implements PersistenciaJuego {

    @Override
    public void guardar(Juego juego, String ruta) throws IOException {

        // Validar la ruta antes de guardar.
        validarRuta(ruta);

        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ruta))) {

            salida.writeObject(juego);
        }
    }

    @Override
    public Juego cargar(String ruta) throws IOException, ClassNotFoundException {

        // Validar la ruta antes de cargar.
        validarRuta(ruta);

        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ruta))) {

            return (Juego) entrada.readObject();
        }
    }

    // Método privado que centraliza la validación de las rutas.
    private void validarRuta(String ruta) {

        if (ruta == null || ruta.isBlank()) {
            throw new IllegalArgumentException("La ruta no puede estar vacía.");
        }
    }
}
