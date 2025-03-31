package org.example;

import java.io.Serializable;

/**
 * La clase VehiculoNick implementa una interfaz llamada Serializable, no es mucho texto,
 * solo indica al compilador que un objeto del tipo VehiculoNick puede ser convertido 
 * en una secuencia de bytes para transferencia de datos.
 */

public class VehiculoNick implements Serializable {
	
	// Indica la versión del serializador de Serializable
	private static final long serialVersionUID = 1L;
   
	// Atributos
    private String matricula;
    private String color;
    private int fecha;

    // --- Constructores

    public VehiculoNick() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * @param matricula
     * @param color
     * @param fecha
     */
    public VehiculoNick(String matricula, String color, int fecha) {
        super();
        this.matricula = matricula;
        this.color = color;
        this.fecha = fecha;
    }

    /**
     * String registro tiene el formato matricula;color;fecha
     * @param registro = Los valores de un objeto VehiculoNick (matricula, color, fecha)
     */
    public VehiculoNick(String registro) {

        String[] campos = registro.split(";");
        this.matricula = campos[0]; // No necesita conversion
        this.color = campos[1]; // No necesita conversion
        this.fecha = Integer.parseInt(campos[2]); // necesista conversion
    }

    /**
     * toCsv
     * @return = Lo mismo que el anterior (public VehiculoNick(String registro)) pero este ordena los valores y los separa con ";" para ser
     * guardados en un archivo local, NO SE USA EN ESTE PROYECTO
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

}