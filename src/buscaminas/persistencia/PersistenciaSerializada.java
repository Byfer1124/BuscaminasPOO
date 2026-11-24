package buscaminas.persistencia;

import java.io.IOException;
import buscaminas.modelo.Juego;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

//Esta clase será la encargada de guardar y 
//recuperar las partidas mediante serialización binaria.
public class PersistenciaSerializada implements PersistenciaJuego {

	@Override
	public void guardar(Juego juego, String ruta) throws IOException {
		
		//FileOutputStream: crea o abre el archivo donde se guardará la partida.
		//ObjectOutputStream: permite escribir objetos Java en formato binario.
		//writeObject(juego): guarda el objeto Juego completo, incluyendo su tablero y estado.
		//try-with-resources: cierra automáticamente el archivo al terminar, incluso si ocurre un error.
		
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ruta))) {

			salida.writeObject(juego);
		}
	}

	@Override
	public Juego cargar(String ruta) throws IOException, ClassNotFoundException {
		// Aquí implementaremos la carga.
		return null;
	}
}