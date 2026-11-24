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

		// FileOutputStream: Crea o abre el archivo donde se guardará la partida.
		// ObjectOutputStream: Permite escribir objetos Java en formato binario.
		// writeObject(juego): Guarda el objeto Juego completo, incluyendo su tablero y
		// estado.
		// try-with-resources: Cierra automáticamente el archivo al terminar, incluso si
		// ocurre un error.

		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ruta))) {

			salida.writeObject(juego);
		}
	}

	@Override
	public Juego cargar(String ruta) throws IOException, ClassNotFoundException {

		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ruta))) {

			return (Juego) entrada.readObject();
		}
	}
}