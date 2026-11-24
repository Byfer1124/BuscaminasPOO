package buscaminas.modelo;

import java.util.Random;

public class Tablero {

	//TAMANIO: indica que el tablero tendrá 10 filas y 10 columnas.
    //CANTIDAD_MINAS: establece que se colocarán 10 minas.
  
	private static final int TAMANIO = 10;
    private static final int CANTIDAD_MINAS = 10;

    private Casilla[][] casillas;

    public Tablero() {
        inicializarTablero();
        colocarMinas();
        calcularMinasAdyacentes();
    }

    // Crear las 100 casillas
    private void inicializarTablero() {
        casillas = new Casilla[TAMANIO][TAMANIO];

        for (int fila = 0; fila < TAMANIO; fila++) {
            for (int columna = 0; columna < TAMANIO; columna++) {
                casillas[fila][columna] = new Casilla();
            }
        }
    }

    // Colocar las minas aleatoriamente
    private void colocarMinas() {
        Random random = new Random(); //Esta clase nos permite generar números aleatorios.
        int minasColocadas = 0;

        while (minasColocadas < CANTIDAD_MINAS) {

            int fila = random.nextInt(TAMANIO);
            int columna = random.nextInt(TAMANIO);

            if (!casillas[fila][columna].tieneMina()) {
                casillas[fila][columna].colocarMina();
                minasColocadas++;
            }
        }
    }

    // Calcular las minas adyacentes
    private void calcularMinasAdyacentes() {

        for (int fila = 0; fila < TAMANIO; fila++) {
            for (int columna = 0; columna < TAMANIO; columna++) {

                if (!casillas[fila][columna].tieneMina()) {

                    int contador = contarMinasAdyacentes(fila, columna);

                    casillas[fila][columna]
                            .setMinasAdyacentes(contador);
                }
            }
        }
    }

    // Contar las minas alrededor de una casilla
    private int contarMinasAdyacentes(int fila, int columna) {

        int contador = 0;

        for (int desplazamientoFila = -1;
                desplazamientoFila <= 1;
                desplazamientoFila++) {

            for (int desplazamientoColumna = -1;
                    desplazamientoColumna <= 1;
                    desplazamientoColumna++) {

                int nuevaFila = fila + desplazamientoFila;
                int nuevaColumna = columna + desplazamientoColumna;

                if (estaDentroDelTablero(nuevaFila, nuevaColumna)
                        && casillas[nuevaFila][nuevaColumna].tieneMina()) {

                    contador++;
                }
            }
        }

        return contador;
    }

    // Verificar que una coordenada esté dentro del tablero
    private boolean estaDentroDelTablero(int fila, int columna) {
        return fila >= 0 && fila < TAMANIO
                && columna >= 0 && columna < TAMANIO;
    }

    // Consultar una casilla
    public Casilla getCasilla(int fila, int columna) {
        if (!estaDentroDelTablero(fila, columna)) {
            throw new IndexOutOfBoundsException(
                    "Coordenada fuera del tablero.");
        }

        return casillas[fila][columna];
    }

}