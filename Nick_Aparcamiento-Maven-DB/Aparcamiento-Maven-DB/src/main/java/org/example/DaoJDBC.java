package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * MUY IMPORTANTE! En esta clase Dao especificamos nuestra url de nuestra BBDD con la que haremos una conexión
 * en este caso es el mío, de mi maquina virtual con la conexión de mi red doméstica, indicaré que cambiar progresivamente
 */

public class DaoJDBC {

    // amiv: por regla general, IP de DOCKER: 172.16.0.0/16
    private static String server_ip = "192.168.1.138";
    // Esta IP debe cambiarse a la que aparece al hacer la conexión de ip en nuestra maquina virtual (2: inet de postgre)

    private static String server_port = "5432"; // El puerto por lo general se mantiene
    private static String _usr = "root"; 	// Usuario del docker
    private static String _pwd = "amiv"; 	// Contraseña del docker
    private static String database = "java"; // Nombre de la base de datos

    // dbcs es la url contruida con la ip, el puerto y el nombre de la bbdd
    String dbcs= "jdbc:postgresql://" + server_ip + ":" + server_port + "/" + database;
   
    // Objeto de tipo Connection, en un principio lo declaramos como null
    private static Connection c = null;
   
    // Getters

    public static String getServer_ip() {
        return server_ip;
    }

    public static String getServer_port() {
        return server_port;
    }

    public static String get_usr() {
        return _usr;
    }

    public static String get_pwd() {
        return _pwd;
    }

    public static String getDatabase() {
        return database;
    }

    /**
     *   Constructor: En el constructor usamos un try - catch para realizar la conexión con el DriveManager, ingresamos la url,
     *   el usuario y la contraseña, en ese orden, si hace conexión de forma correcta manda un mensaje, si no lo hace declara
     *   la conexión como null
     */


    public DaoJDBC() {
        try {
            c = DriverManager.getConnection(dbcs, _usr, _pwd);
            System.out.println("Conección establecida con éxito =D");
        } catch (SQLException e) {
            c = null;
        }
    }

    // Metodo para cerrar la conexión, debe usarse al final de la aplicación
    public void closeConnection() {
        try {
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // GetInstance es un metodo get que usamos para devolver la conexión, con la conexión podemos hacer consultas y sentencias
    // más detallado en DaoVehiculoJDBC

    public Connection getInstance() {
        return c;
    }

    public void setInstance(Connection c) {
        this.c = c;
    }

}
