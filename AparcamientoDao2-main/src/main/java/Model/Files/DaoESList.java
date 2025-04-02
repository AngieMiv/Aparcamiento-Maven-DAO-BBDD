package Model.Files;

import Model.DaoList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Maneja entrada/salida de vehículos (ES).
 * Guarda los registros en LogES.csv.
 */
public class DaoESList implements DaoList<ES> {

	public ArrayList<ES> listaEntSal = new ArrayList<ES>();
	private static String rutaArchivo ="LogES.csv";
	
	public DaoESList() {
		// TODO Auto-generated constructor stub
	}

	public void setRuta(String fichero) {		rutaArchivo = fichero;	}
	/**
	 * Sin implementar
	 */
	public ArrayList<ES> findAll(){
		
		return null;
	}
	/**
	 * Sin implementar
	 */
	public ES findOne(String key) {
		
		return null;
	}
	public boolean insertOne(ES t) {
		
		boolean result = false;
		listaEntSal.add(t);		// Persitencia en memoria
		// TODO persistencia en fichero
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo,true));
			bw.write(t.toCSV());
			bw.newLine();
			bw.close();
			result = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	/**
	 * Sin implementar
	 */
	public boolean deleteOne(String key) {
		
		return true;
	}
	/**
	 * Sin implementar
	 */
	public boolean updateOne(String key,ES t) {
		
		return true;
	}
	
}
