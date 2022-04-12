package utils;

public class RecursoObtenido {
	private String tipo;
	private int cantidad;
	
	public RecursoObtenido(String tipo, int cantidad) {
		this.tipo = tipo;
		this.cantidad = cantidad;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "RecursoObtenido [tipo=" + tipo + ", cantidad=" + cantidad + "]";
	}
	
	
}
