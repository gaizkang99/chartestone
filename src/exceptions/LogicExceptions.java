package exceptions;

import java.util.Arrays;
import java.util.List;

public class LogicExceptions extends Exception {
	public static final int Accion_Incorrecta = 0;
	public static final int Parametros_Incorrectos = 1;
	public static final int Falta_materiales = 2;
	public static final int Jugador_Repetido = 3;
	public static final int DoblePersonaje_Usado = 4;
	public static final int Turno_Acabado = 5;
	
	private int value;
	
	public LogicExceptions (int value) {
		this.value = value;
	}
	
	private List<String> message = Arrays.asList(
			"<< La acción requerida es incorreta >>",
			"<< Numero de parámetros incorrecto >>",
			"<< No se puede realizar la acción por falta de materiales >>",
			"<< El jugador no puede volver a jugar >>",
			"<< El jugador ya ha utilizado sus dos personajes >>",
			"<< Turno completado >>"
			);
			
	
	@Override
	public String getMessage() {
		return message.get(value);
	}
}
