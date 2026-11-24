package buscaminas.excepciones;

//Esta excepción nos permitirá distinguir entre una partida 
//que sigue en curso y otra que ya terminó porque el jugador ganó o perdió.

public class PartidaFinalizadaException extends Exception {

    private static final long serialVersionUID = 1L;

    public PartidaFinalizadaException(String mensaje) {
        super(mensaje);
    }
}