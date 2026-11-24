package buscaminas.modelo;

import java.io.Serializable;

public class Casilla implements Serializable {

	private static final long serialVersionUID = 1L;
	// Utilizamos private para que otras clases no puedan modificar directamente los
	// atributos de la casilla.
	private boolean tieneMina;
	private int minasAdyacentes;
	private boolean descubierta;
	private boolean marcada;

	// Este constructor inicializa cada casilla con los siguientes valores:
	// No contiene una mina.
	// Tiene cero minas adyacentes.
	// Está cubierta.
	// No está marcada.

	public Casilla() {
		this.tieneMina = false;
		this.minasAdyacentes = 0;
		this.descubierta = false;
		this.marcada = false;
	}

	// Consultar si contiene una mina
	public boolean tieneMina() {
		return tieneMina;
	}

	// Consultar cantidad de minas adyacentes
	public int getMinasAdyacentes() {
		return minasAdyacentes;
	}

	// Consultar si está descubierta
	public boolean estaDescubierta() {
		return descubierta;
	}

	// Consultar si está marcada
	public boolean estaMarcada() {
		return marcada;
	}

	// Colocar una mina
	public void colocarMina() {
		tieneMina = true;
	}

	// Asignar minas adyacentes
	public void setMinasAdyacentes(int cantidad) {
		if (cantidad < 0 || cantidad > 8) {
			throw new IllegalArgumentException("La cantidad de minas debe estar entre 0 y 8.");
		}

		minasAdyacentes = cantidad;
	}

	// permite informar a otras clases si la acción se pudo realizar.
	// true: se descubrió la casilla.
	// false: no se pudo descubrir.
	// Descubrir la casilla
	public boolean descubrir() {
		if (descubierta || marcada) {
			return false;
		}

		descubierta = true;
		return true;
	}

	// Marcar o desmarcar la casilla
	public boolean marcar() {
		if (descubierta) {
			return false;
		}

		marcada = !marcada;
		return true;
	}

	public void revelar() {
		descubierta = true;
		marcada = false;
	}
}