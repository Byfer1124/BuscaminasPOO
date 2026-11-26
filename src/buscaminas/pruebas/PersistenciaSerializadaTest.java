package buscaminas.pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import buscaminas.modelo.Juego;
import buscaminas.modelo.EstadoJuego;
import buscaminas.persistencia.PersistenciaSerializada;

import java.nio.file.Files;
import java.nio.file.Path;

class PersistenciaSerializadaTest {

	@Test
	void verificarGuardarYCargarPartida() throws Exception {

		String ruta = "prueba_partida.dat";

		try {
			Juego juego = new Juego();
			PersistenciaSerializada persistencia = new PersistenciaSerializada();

			persistencia.guardar(juego, ruta);

			Juego juegoCargado = persistencia.cargar(ruta);

			assertNotNull(juegoCargado);
			assertEquals(EstadoJuego.EN_CURSO, juegoCargado.getEstado());

		} finally {
			Files.deleteIfExists(Path.of(ruta));
		}
	}

	// Crea una partida.
	// Descubre A1.
	// Guarda la partida.
	// Recupera la partida.
	// Comprueba que A1 continúa descubierta.
	@Test
	void verificarConservacionDeCasillasDescubiertas() throws Exception {

		String ruta = "prueba_casillas.dat";

		try {
			Juego juego = new Juego();
			PersistenciaSerializada persistencia = new PersistenciaSerializada();

			juego.descubrirCasilla(0, 0);

			persistencia.guardar(juego, ruta);

			Juego juegoCargado = persistencia.cargar(ruta);

			assertTrue(juegoCargado.getTablero().getCasilla(0, 0).estaDescubierta());

		} finally {
			Files.deleteIfExists(Path.of(ruta));
		}
	}

	// Marcamos A1.
	// Guardamos la partida.
	// Recuperamos la partida.
	// Verificamos que A1 continúa marcada.

	@Test
	void verificarConservacionDeCasillasMarcadas() throws Exception {

		String ruta = "prueba_marcas.dat";

		try {
			Juego juego = new Juego();
			PersistenciaSerializada persistencia = new PersistenciaSerializada();

			juego.marcarCasilla(0, 0);

			persistencia.guardar(juego, ruta);

			Juego juegoCargado = persistencia.cargar(ruta);

			assertTrue(juegoCargado.getTablero().getCasilla(0, 0).estaMarcada());

		} finally {
			Files.deleteIfExists(Path.of(ruta));
		}
	}

	@Test
	void noPermitirGuardarConRutaVacia() {

		PersistenciaSerializada persistencia = new PersistenciaSerializada();
		Juego juego = new Juego();

		assertThrows(IllegalArgumentException.class, () -> {
			persistencia.guardar(juego, "");
		});
	}

	@Test
	void noPermitirCargarConRutaVacia() {

		PersistenciaSerializada persistencia = new PersistenciaSerializada();

		assertThrows(IllegalArgumentException.class, () -> {
			persistencia.cargar("");
		});
	}
}