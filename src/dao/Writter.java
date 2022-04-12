package dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import exceptions.ExecutionsExceptions;

public class Writter {
	private BufferedWriter bf;
	
	
	public Writter() throws ExecutionsExceptions {
		try {
			FileWriter fw = new FileWriter("Data/salida.txt");
			this.bf = new BufferedWriter(fw);
		} catch (Exception e) {
			throw new ExecutionsExceptions(ExecutionsExceptions.ERROR_Creacion);
		}
	}
	
	public void write (String linea) throws ExecutionsExceptions {
		try {
			this.bf.append(linea+"\n");
		} catch (Exception e) {
			throw new ExecutionsExceptions(ExecutionsExceptions.ERROR_Escritura);
		}
	}

	public void closeFile() throws ExecutionsExceptions {
		try {
			this.bf.close();
		} catch (Exception e) {
			throw new ExecutionsExceptions(ExecutionsExceptions.ERROR_Cerrando);
		}
	}
}