package Model.Files;

import Model.Aparcamiento;
import Model.DaoList;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.DeserializationFeature;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Almacena Aparcamiento en ArrayList<Aparcamiento>.
 * CRUD básico
 * Métodos para cargar/guardar en JSON (comentados).
 */
public class DaoAparcamientoList implements DaoList<Aparcamiento>{

	public ArrayList<Aparcamiento> listaAparcamientos = new ArrayList<Aparcamiento>();
	private final String DAT_AP ="DatosAparcamientos.json";
	public DaoAparcamientoList() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Aparcamiento> findAll(){
		return listaAparcamientos;
	} // end findAll
	
	public Aparcamiento findOne(String key) {
		for (Aparcamiento ap : listaAparcamientos) {
			if (ap.getNombre().equalsIgnoreCase(key)) {
				return ap;
			}
		}
		return null;  // No encontrado
	} // end findOne
	
	public boolean insertOne(Aparcamiento ap) {
		//boolean result;
		listaAparcamientos.add(ap);
		return saveJson();					// Almacena en disco
	} // end insertOne
	
	public boolean deleteOne(String key) {
	    boolean removed = listaAparcamientos.removeIf(ap -> ap.getNombre().equalsIgnoreCase(key));
	    if (removed) {
	        return saveJson();  // Guardar cambios en JSON
	    }
	    return false;
	} // end deleteOne
	
	public boolean updateOne(String key,Aparcamiento updatedAparcamiento) {
	    for (int i = 0; i < listaAparcamientos.size(); i++) {
	        if (listaAparcamientos.get(i).getNombre().equalsIgnoreCase(key)) {
	            listaAparcamientos.set(i, updatedAparcamiento);
	            return saveJson();  // Guardar cambios en JSON
	        }
	    }
	    return false;  // No encontrado
	} // end updateOne

	public boolean loadJson() {

		boolean result = false;		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {// Leer el archivo JSON y mapearlo a una lista de objetos Aparcamiento
			File file = new File(DAT_AP);
			if (!file.exists()) return false;  // Si no existe, no cargamos nada

			listaAparcamientos = objectMapper.readValue(
					file,
					new TypeReference<ArrayList<Aparcamiento>>() {}
					);
			result = true;

		} catch (IOException e) {
			System.err.println("Error saving JSON: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	} // end loadJson

	public boolean saveJson() {
		String cadena = "";
		boolean result = false;
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(DAT_AP));
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);    
			cadena = objectMapper.writeValueAsString(listaAparcamientos);
			bw.write(cadena);
			bw.close();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();			// TODO Auto-generated catch block
			result = false;
		}
		return result;
	} 
}
