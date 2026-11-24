package buscaminas.modelo;

public enum EstadoJuego {
    EN_CURSO, //La partida todavía está activa.
    GANADO, //El jugador descubrió todas las casillas que no tienen minas.
    PERDIDO //El jugador descubrió una mina.
}