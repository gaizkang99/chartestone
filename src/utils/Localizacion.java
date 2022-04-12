package utils;

import java.util.ArrayList;

public class Localizacion {
	private int numLocalizacion;
	private ArrayList<RecursoObtenido> recursoObtenido;
	private ArrayList<RecursoNecesario> recursoNecesario;
	private String j;
	
	public Localizacion(int numLocalizacion, ArrayList<RecursoNecesario> recursoNecesario, ArrayList<RecursoObtenido> recursoObtenido) {
		this.numLocalizacion = numLocalizacion;
		this.recursoNecesario = recursoNecesario;
		this.recursoObtenido = recursoObtenido;
	}
	
	public int getNumLocalizacion() {
		return numLocalizacion;
	}

	public ArrayList<RecursoNecesario> getRecursoNecesario() {
		return recursoNecesario;
	}

	public void setRecursoNecesario(ArrayList<RecursoNecesario> recursoNecesario) {
		this.recursoNecesario = recursoNecesario;
	}

	public ArrayList<RecursoObtenido> getRecursoObtenido() {
		return recursoObtenido;
	}

	public void setRecursoObtenido(ArrayList<RecursoObtenido> recursoObtenido) {
		this.recursoObtenido = recursoObtenido;
	}

	public String getJ() {
		if(j==null) return "ninguno";
		else return j;
	}

	public void setJ(String j) {
		this.j=j;		
	}

	
	
	@Override
	public String toString() {
		return "Localizacion [numLocalizacion=" + numLocalizacion + ", recursoObtenido=" + recursoObtenido.toString()
				+ ", recursoNecesario=" + recursoNecesario.toString() + ", j=" + getJ() + "]";
	}




	
	
}
