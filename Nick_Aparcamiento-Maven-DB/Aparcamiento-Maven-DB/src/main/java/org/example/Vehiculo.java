package org.example;

import java.io.Serializable;

/**
 * La clase Vehiculo implementa una interfaz llamada Serializable, no es mucho texto, solo indica al compilador que un objeto del tipo Vehiculo
 * puede ser convertido en una secuencia de bytes para transferencia de datos
 */

public class Vehiculo implements Serializable {

    // Atributos

    private String matricula;
    private String color;
    private int fecha;

    private static final long serialVersionUID = 1L; //Indica la versión del serializador de Serializable

    // Constructores

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
     *  String tiene el formato matricula;color;fecha
     * @param registro = Los valores de un objeto Vehiculo (matricula, color, fecha)
     */
    public Vehiculo(String registro) {

        String[] campos = registro.split(";");
        this.matricula = campos[0]; // No necesita conversion
        this.color = campos[1]; // No necesita conversion
        this.fecha = Integer.parseInt(campos[2]); // necesista conversion
    }

    /**
     * toCsv
     * @return = Lo mismo que el anterior (el de arriba) pero este ordena los valores y los separa con ";" para ser
     * guardados en un archivo local, NO SE USA EN ESTE PROYECTO
     */
    public String toCsv() {
        return  matricula + ";" + color + ";" + fecha ;
    }

    // Getters y Setters

    public String getMatricula() {		return matricula;	}
    public void setMatricula(String matricula) {		this.matricula = matricula;	}
    public String getColor() {		return color;	}
    public void setColor(String color) {		this.color = color;	}
    public int getFecha() {		return fecha;	}
    public void setFecha(int fecha) {		this.fecha = fecha;	}


}