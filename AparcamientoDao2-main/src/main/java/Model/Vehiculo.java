package Model;
import java.io.Serializable;

/**
 * Represents a vehicle that can be serialized for storage or data transfer.
 * 
 * <p>This class implenets {@link Serializable}, enabling objects of
 * {@code Vehiculo} to be saved as byte stream and restored when needed.
 * This is useful for peristing vehicle data in files or databases.</p>
 * 
 * La clase Vehiculo implementa una interfaz llamada Serializable,
 * indica al compilador que un objeto del tipo Vehiculo puede ser convertido 
 * en una secuencia de bytes para transferencia de datos.
 * 
 * @see Serializable
 */
public class Vehiculo implements Serializable {

	// Indica la versión del serializador de Serializable
	private static final long serialVersionUID = 1L;	// Ensures compatibility across versions

	// demás Atributos
	private String matricula;
	private String color;
	private int fecha;

	// --- Constructores

	public Vehiculo() {
		// TODO Auto-generated constructor stub
	}

	public Vehiculo(String matricula, String color, int fecha) {
		super();
		this.matricula = matricula;
		this.color = color;
		this.fecha = fecha;
	}

	/**
	 * String tiene el formato matricula;color;fecha
	 * @param registro son los valores de un objeto Vehiculo (matricula, color, fecha)
	 */
	public Vehiculo(String registro) {

		String[] campos = registro.split(";");
		this.matricula = campos[0];					// No necesita conversion
		this.color = campos[1];						// No necesita conversion
		this.fecha = Integer.parseInt(campos[2]);	// necesista conversion
	}

	/**
	 * toCsv
	 * @return lo mismo que el anterior (public Vehiculo(String registro)) pero este ordena los valores y los separa con ";"
	 * para ser guardados en un archivo local, NO SE USA EN ESTE PROYECTO
	 */
	public String toCsv() {
		return  matricula + ";" + color + ";" + fecha ;
	}

	// Getters
	public String getMatricula() {
		return matricula;
	}
	public String getColor() {
		return color;
	}
	public int getFecha() {
		return fecha;
	}

	// Setters
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

} // end class Vehiculo implements Serializable 
