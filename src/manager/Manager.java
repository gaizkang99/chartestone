package manager;

import java.util.ArrayList;
import dao.Reader;
import dao.Writter;
import exceptions.ExecutionsExceptions;
import exceptions.LogicExceptions;
import utils.Jugador;
import utils.Localizacion;
import utils.RecursoNecesario;
import utils.RecursoObtenido;

public class Manager {
	private ArrayList<Jugador> j;
	private ArrayList<Localizacion> l;
	private int m1 = 0, m2 = 0, pos=0;

	public Manager() {
		this.j = new ArrayList<Jugador>();
		this.l = new ArrayList<Localizacion>();
		
	}
	
	public void init() {
		try {
			Reader r = new Reader("Data/entrada.txt");
			Writter w = new Writter();
			lecturaAcciones(r, w);
		} catch (ExecutionsExceptions e) {
			System.out.println(e.getMessage());
		}
	}
	// Funcion que lee la parte del texto de personajes y los añade en el Arraylist
	// de personajes
	public void lecturaPersonajes(Reader r) throws ExecutionsExceptions {
		String linea = "";
		int contador = 1;
		while (!(linea = r.read()).equals("##")) {
			String[] array = linea.substring(3).split(" ");
			j.add(new Jugador(contador, array[0], Integer.parseInt(array[1]), Integer.parseInt(array[2]),
					Integer.parseInt(array[3])));
			contador++;
		}
	}
	// Funcion que lee la parte del texto de localizaciones y los añade en el
	// Arraylist de localizaciones
	//Para la parte del extra he creado dos arraylist que se pasaran por parametro por cada una de las localizaciones 
	public void lecturaLocalizaciones(Reader r) throws ExecutionsExceptions {
		String linea = "";
		while (!(linea = r.read()).equals("##")) {
			String[] localizaciones = linea.substring(1).split(" ");
			ArrayList<RecursoNecesario> rn = new ArrayList<>();
			ArrayList<RecursoObtenido> ro = new ArrayList<>();
			boolean recursos = true;
			for(int i=1; i<localizaciones.length;i+=2){
				if(localizaciones[i].equals("=")) {
					i++;
					recursos=false;
				}
				if(recursos==true) {
					RecursoNecesario rNecesario = new RecursoNecesario(localizaciones[i], Integer.parseInt(localizaciones[i+1]));
					rn.add(rNecesario);
				}else {
					RecursoObtenido rObtenido = new RecursoObtenido(localizaciones[i], Integer.parseInt(localizaciones[i+1]));
					ro.add(rObtenido);	
				}
			}
			l.add(new Localizacion(Integer.parseInt(localizaciones[0]), rn, ro));
		}
	}
	// Funcion que lee la parte del archivo que lee las excepciones
	public void lecturaAcciones(Reader r, Writter w) throws ExecutionsExceptions {
		lecturaPersonajes(r);
		lecturaLocalizaciones(r);
		boolean partida = false;
		String linea = "";
		while (!((linea = r.read()) == null)) {
			try {
				if(linea.equals("I"))partida=true;
				String[] acciones = linea.split(" ");
				excepcionesAcciones(partida, acciones, w);
			} catch (LogicExceptions e) {
				w.write("Error: " + linea + " --> " + e.getMessage());
			}
		}
		w.closeFile();
	}
	/*Funcion que recibe la parte del fichero de las acciones y gestiona las acciones por
	 casos con sus ciertas excepciones*/
	private void excepcionesAcciones(boolean partida, String[] string, Writter w) throws LogicExceptions, ExecutionsExceptions {
		
		switch (string[0]) {
		case "I":
			w.write("NICIANDO PARTIDA");
			compruebaTamano(string.length, 1);
			break;
		case "S":
			compruebaTamano(string.length, 1);
			if (partida == false)throw new LogicExceptions(LogicExceptions.Accion_Incorrecta);
			for (int i = 0; i < j.size(); i++) {
				w.write(j.get(i).toString());
			}
			break;
		case "M":
			compruebaTamano(string.length, 3);
			if (partida == false)throw new LogicExceptions(LogicExceptions.Accion_Incorrecta);
			int contador=0;
			int jugadorJugando = Integer.parseInt(string[1])-1;
			int numLoc = Integer.parseInt(string[2])-1;
			//Comprobar si el jugador anterior es el mismo 
			compruebaMismoJugador(jugadorJugando);
			//Comprobar si el jugador ya tiene dos localizaciones
			for(int i=0; i<l.size(); i++) {
				if(l.get(i).getJ().equals(j.get(jugadorJugando).getColor())) {
					contador++;
					if(contador==2) {
						throw new LogicExceptions(LogicExceptions.DoblePersonaje_Usado);
					}
				}
			}
			//Comprobar si el jugador tiene materiales suficientes
			for(int i=0;i<l.get(numLoc).getRecursoNecesario().size();i++) {
				String materialNecesario=l.get(numLoc).getRecursoNecesario().get(i).getTipo();
				int cantidadNecesaria=l.get(numLoc).getRecursoNecesario().get(i).getCantidad();
				compruebaMateriales(materialNecesario,cantidadNecesaria, jugadorJugando);
			}
			//Actualizar recursos del jugador
			for(int i=0;i<l.get(numLoc).getRecursoObtenido().size();i++) {
				String materialObtenido=l.get(numLoc).getRecursoObtenido().get(i).getTipo();
				int cantidadObtenida=l.get(numLoc).getRecursoObtenido().get(i).getCantidad();
				actualizarMateriales(materialObtenido, cantidadObtenida, jugadorJugando);
			}
			//METER JUGADOR EN LA LOCALIZACIÓN CORRESPONDIENTE
			l.get(numLoc).setJ(j.get(jugadorJugando).getColor());
			//Comrpobar si no se han lanzado excepciones
			throw new LogicExceptions(LogicExceptions.Turno_Acabado);
		case "R":
			compruebaTamano(string.length, 2);
			if (partida == false)throw new LogicExceptions(LogicExceptions.Accion_Incorrecta);
			int jugador = Integer.parseInt(string[1])-1;
			for(int i=0;i<l.size();i++) {
				if(l.get(i).getJ().equals(j.get(jugador).getColor())) {
					l.get(i).setJ(null);
				}
			}
			break;
		case "L":
			compruebaTamano(string.length, 1);
			if (partida == false)throw new LogicExceptions(LogicExceptions.Accion_Incorrecta);
			for(int i=0;i<l.size();i++) {
				w.write(l.get(i).toString());
			}
			break;
		default:
			w.write("Esta acción no existe");
			break;
		}		
	}
	/*Comprueba el tamaño que tiene el array de la acción y el que tiene que tener 
	y si los parametros son incorrectos salta el error de parametros*/
	public void compruebaTamano(int a, int b) throws LogicExceptions {
		if (a != b)throw new LogicExceptions(LogicExceptions.Parametros_Incorrectos);
	}
	//Comprueba si esta jugando el mismo jugador dos veces seguidas y si es asi salta una excepcion
	private void compruebaMismoJugador(int jugadorJugando) throws LogicExceptions {
		if(pos==0) {
			m1=jugadorJugando;
			pos++;
		}else {
			m2=jugadorJugando;
			if(m1==m2)throw new LogicExceptions(LogicExceptions.Jugador_Repetido);
			else pos=0;
		}
	}
	//Funcion que comprueba si los jugadores tienen materiales necesarios para la localizacion deseada
	private void compruebaMateriales(String materialNecesario,int cantidadNecesaria, int jugadorJugando) throws LogicExceptions {
		switch(materialNecesario) {
		case "T":
			if(j.get(jugadorJugando).getCantidadTrigo()<cantidadNecesaria)throw new LogicExceptions(LogicExceptions.Falta_materiales);
			else j.get(jugadorJugando).setCantidadTrigo(-cantidadNecesaria);
			break;
		case"M":
			if(j.get(jugadorJugando).getCantidadMadera()<cantidadNecesaria)throw new LogicExceptions(LogicExceptions.Falta_materiales);
			else j.get(jugadorJugando).setCantidadMadera(-cantidadNecesaria);
			break;
		case"X":
			if(j.get(jugadorJugando).getPuntuacion()<cantidadNecesaria)throw new LogicExceptions(LogicExceptions.Falta_materiales);
			else j.get(jugadorJugando).setPuntuacion(-cantidadNecesaria);
			break;
		case"C":
			if(j.get(jugadorJugando).getCantidadCarbon()<cantidadNecesaria)throw new LogicExceptions(LogicExceptions.Falta_materiales);
			else j.get(jugadorJugando).setCantidadCarbon(-cantidadNecesaria);
			break;
		default:
			break;
		}
	}		
	//Actualiza los materiales del jugador que entra en una localizacion	
	private void actualizarMateriales(String materialObtenido, int cantidadObtenida, int jugadorJugando) {
		switch(materialObtenido) {
		case "T":
			j.get(jugadorJugando).setCantidadTrigo(cantidadObtenida);
			break;
		case"M":
			j.get(jugadorJugando).setCantidadMadera(cantidadObtenida);
			break;
		case"X":
			j.get(jugadorJugando).setMonedas(cantidadObtenida);
			break;
		case"C":
			j.get(jugadorJugando).setCantidadCarbon(cantidadObtenida);
			break;
		case"P":
			j.get(jugadorJugando).setPuntuacion(cantidadObtenida);
			break;
		default:
			break;
		}	
	}	
}
