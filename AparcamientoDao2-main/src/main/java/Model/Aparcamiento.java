package Model;
import Model.Files.DaoESList;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * Represents a parking lot that can be serialized for storage or data transfer.
 * 
 * This class implements {@link Serializable}, allowing instances of {@code Aparcamiento}
 * to be converted into a byte stream and restored later.
 * This is useful for saving the state of parking lots or 
 * transferring data across different systems.
 * 
 * La clase almacena datos de un aparcamiento y parece interactuar con DAO JDBC (DaoVehiculoJDBC),
 * que probablemente sea responsable de manejar la base de datos.
 * 
 * Lo mismo que en vehículo, implementa la interfaz serializable para transferencia de datos, 
 * aun no se usan los atributos de plazas.
 * 
 * @see Serializable
 */
public class Aparcamiento implements Serializable {
	
	private static final long serialVersionUID = 1L;	// Ensures compatibility across versions
	
	private String nombre;
	private int numFilas ;		// Filas o pasillos de aparcamiento
	private int numColumnas ;	// Plazas por fila o pasillo
	private String[][] plaza;	// Plaza de aparcamiento
	
	// Un objeto DAO que maneja la conexión con la base de datos para 
	// gestionar vehículos en el aparcamiento.
	public DaoVehiculoJDBC lstVehiculos = new DaoVehiculoJDBC();
	
	// Otra clase de Dao que maneja operaciones con una Lista de Entrada y Salida,
	// responsable de gestionar el registro de entradas y salidas de vehículo
	public DaoESList lstES = new DaoESList();	
	
	private int fila;
	private int columna;
	
	// Constructores
	public Aparcamiento() {}
	

	public Aparcamiento(String nombre, int numFilas, int numColumnas) {
		this.nombre = nombre;
		this.numFilas = numFilas;
		this.numColumnas = numColumnas;
		this.plaza = new String[numFilas][numColumnas];
		initPlazas();
		
	}
	
	
	public Aparcamiento(String nombre) {
		this.nombre = nombre;
		this.numFilas = 10;
		this.numColumnas = 10;
		this.plaza = new String[numFilas][numColumnas];
		initPlazas();
	}

	
	public void initPlazas() {
		for (int i=0;i<numFilas;i++) {
			for (int j=0;j<numColumnas;j++) {
				plaza[i][j]="";					// Vacía
			}
		}
	}
	
	public int getNumFilas() 								 {	return numFilas;				}
	public void setNumFilas(int numFilas) 					 {	this.numFilas = numFilas;		}
	public int getNumColumnas() 							 {	return numColumnas;				}
	
	
	public void setNumColumnas(int numColumnas)  			 {	this.numColumnas = numColumnas;	}
	public String[][] getPlaza() 							 {	return plaza;					}
	public String getNombre() 								 {	return nombre;					}
	public void setNombre(String nombre) 					 {	this.nombre = nombre;			}
	
	
	/** Buscar una plaza libre y la asigna
	 * 
	 * @param matricula Matrícula del vehículo a estacionar
	 */
	public boolean aparcarVehiculo(String matricula) {
				for (int i=0; i<numFilas; i++) {
					for (int j=0; j<numColumnas; j++) {	
						if (plaza[i][j].equals("")) {
							plaza[i][j] = matricula;
							return true;
						}
					} // col
				} // fil
				return false;
	} // end aparcarVehiculo
	
	/** Buscar la ubicación del Vehiculo y libera la plaza
	 * 
	 * @param matricula Matrícula del vehículo a estacionar
	 */
	public boolean retirarVehiculo(String matricula) {
			
		if (buscarVehiculo(matricula)) {
			plaza[fila][columna]="";
			return true;
		}
		return false;
	}
	
	/**
	 * Busca la plaza donde está estacionado el vehículo
	 * de con una matrícula específica.
	 * @param matricula matricula que se busca
	 * @return true : encontrado. Variables fila y columna actualizadas;
	 *         false: no encontrado.
	 */
	public boolean buscarVehiculo(String matricula) {
		
		for (int i=0; i<numFilas; i++) {
			for (int j=0; j<numColumnas; j++) {
				if (plaza[i][j].equals(matricula)) {
					fila = i;
					columna = j;
					return true;
				}
			} // columnas
			
		} // filas
		
		return false;
		
	} // end buscarVehiculos

	
} // end class Aparcamiento
