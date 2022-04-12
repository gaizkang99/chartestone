package utils;

public class Jugador {
	private String color;
	private int cantidadCarbon, cantidadTrigo, cantidadMadera, puntuacion, monedas, numJugador;
	
	public Jugador(int numJugador, String color, int carbon, int trigo, int madera) {
		this.color = color;
		this.cantidadCarbon = carbon;
		this.cantidadMadera = madera;
		this.cantidadTrigo = trigo;
		this.puntuacion=0;
		this.monedas=0;
		this.numJugador=numJugador;
	}

	public String getColor() {
		return color;
	}

	public int getCantidadCarbon() {
		return cantidadCarbon;
	}

	public void setCantidadCarbon(int cantidadCarbon) {
		this.cantidadCarbon = this.cantidadCarbon + cantidadCarbon;
	}

	public int getCantidadTrigo() {
		return cantidadTrigo;
	}

	public void setCantidadTrigo(int cantidadTrigo) {
		this.cantidadTrigo = this.cantidadTrigo + cantidadTrigo;
	}

	public int getCantidadMadera() {
		return cantidadMadera;
	}

	public void setCantidadMadera(int cantidadMadera) {
		this.cantidadMadera = this.cantidadMadera + cantidadMadera;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = this.puntuacion + puntuacion;
	}

	public int getMonedas() {
		return monedas;
	}

	public void setMonedas(int monedas) {
		this.monedas = this.monedas + monedas;
	}
	
	@Override
	public String toString() {
		return "\tJugador " + numJugador + "[color=" + color + ", puntuacion=" + puntuacion + ", madera=" + cantidadMadera
				+ ", carbon=" + cantidadCarbon + ", trigo=" + cantidadTrigo + ", monedas=" + monedas + "]";
	}
	
	
}
