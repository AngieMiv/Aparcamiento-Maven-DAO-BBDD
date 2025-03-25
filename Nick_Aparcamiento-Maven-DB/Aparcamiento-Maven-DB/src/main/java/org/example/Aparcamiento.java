package org.example;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

// Lo mismo que en vehículo, implementa la interfaz serializable para transferencia de datos, aun no se usan los atributos de plazas.

public class Aparcamiento implements Serializable {

	private String nombre;
	private int numFilas ;		// Filas o pasillos de aparcamiento
	private int numColumnas ;   // Plazas por fila o pasillo
	private String[][] plaza;  // Plaza de aparcamiento
	private ArrayList<Vehiculo> listVehiculos = new ArrayList<Vehiculo>();

	public DaoJDBC daojdbc = new DaoJDBC();
	public DaoVehiculoJDBC bdVehiculos = new DaoVehiculoJDBC(daojdbc);
	private static final long serialVersionUID = 1L;
	
	public Aparcamiento() {
		  
	}


	public Aparcamiento(String nombre) {
		this.nombre = nombre;
		this.numFilas = 10;
		this.numColumnas = 10;
		this.plaza = new String[numFilas][numColumnas];
		for (int i=0;i<numFilas;i++) {
			for (int j=0;j<numColumnas;j++) {
				plaza[i][j]="";					// Vacía
			}
		}
	}


	public String getNombre() 								 {	return nombre;	}
	public void setNombre(String nombre) 					 {	this.nombre = nombre;	}
	public ArrayList<Vehiculo> getListaVehiculos() 			 {	return listVehiculos;	}
	public void setListVehiculos(ArrayList<Vehiculo> plazas) {	this.listVehiculos = plazas;	}

	public int getNumFilas() 									{return numFilas;}
	public void setNumFilas(int numFilas)						{this.numFilas = numFilas;}
	public int getNumColumnas() 								{return numColumnas;}
	public void setNumColumnas(int numColumnas) 				{this.numColumnas = numColumnas;}

	public String[][] getPlaza() {
		return plaza;
	}

	public void estacionaVehiculo(String matricula){

		//Buscar una plaza libre
		Random random = new Random();
		int fila = random.nextInt(10);
		int columna = random.nextInt(10);
		for (int i = 0; i < numFilas; i++) {
			for (int j = 0; j < numColumnas; j++) {
				if (i==fila && columna == j) {
					plaza[i][j] = matricula;
				}
			}
		}

	}

	public void retirarVehiculo(String matricula) {
		for (int i = 0; i < numFilas; i++) {
			for (int j = 0; j < numColumnas; j++) {
				if (plaza[i][j] == matricula) {
					plaza[i][j] = "";
				}
			}
		}
	}

	public boolean buscarVehiculo(String matricula){

		for (int i = 0; i < numFilas; i++) {
			for (int j = 0; j < numColumnas; j++) {
				if (plaza[i][j].equals(matricula)) {
					int fila = i;
					int columna = j;
					return true;
				}
			}
		}
        return false;
    }
	
}
