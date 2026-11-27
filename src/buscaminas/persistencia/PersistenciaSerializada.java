package buscaminas.persistencia;

import java.io.IOException;

import buscaminas.modelo.Juego;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

//Esta clase será la encargada de guardar y 
//recuperar las partidas mediante serialización binaria.
public class PersistenciaSerializada implements PersistenciaJuego {

	@Override
	public void guardar(Juego juego, String ruta) throws IOException {

		//comprueba que la ruta no sea nula.
		//comprueba que no esté vacía ni contenga únicamente espacios.
		if (ruta == null || ruta.isBlank()) {
			throw new IllegalArgumentException("La ruta de guardado no puede estar vacía.");//informa que el argumento recibido no es válido.
		}

		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ruta))) {

			salida.writeObject(juego);
		}
	}

	@Override
	public Juego cargar(String ruta) throws IOException, ClassNotFoundException {

		if (ruta == null || ruta.isBlank()) {
			throw new IllegalArgumentException("La ruta de carga no puede estar vacía.");
		}

		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ruta))) {

			return (Juego) entrada.readObject();
		}
	}
}