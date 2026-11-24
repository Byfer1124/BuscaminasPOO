package buscaminas.excepciones;

//extends Exception: La clase hereda de la excepción general comprobada de Java.
//serialVersionUID: Identifica la versión de la clase para su serialización.
//super(mensaje): Envía el mensaje al constructor de la clase padre.

public class CoordenadaInvalidaException extends Exception {

    private static final long serialVersionUID = 1L;

    public CoordenadaInvalidaException(String mensaje) {
        super(mensaje);
    }
}