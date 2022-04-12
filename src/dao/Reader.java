package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import exceptions.ExecutionsExceptions;

public class Reader {
	private BufferedReader bf;
	private FileReader rd;
	
	public Reader(String file) throws ExecutionsExceptions {
		try {
			this.rd = new FileReader(file);
			this.bf = new BufferedReader(this.rd);
		} catch (Exception e) {
			throw new ExecutionsExceptions(ExecutionsExceptions.ERROR_Creacion);
		}
	}
	
	public String read() throws ExecutionsExceptions {
		try {
			return this.bf.readLine();
		} catch (Exception e) {
			throw new ExecutionsExceptions(ExecutionsExceptions.ERROR_Lectura);
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
