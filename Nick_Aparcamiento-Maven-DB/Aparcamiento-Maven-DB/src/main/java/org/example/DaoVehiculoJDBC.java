package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

// La clase DaoVehiculoJDBC implementa la interfaz DaoList<Vehiculo>, todas las funciones estarán especificadas acá
// Usando la conexión a la BBDD Postgre

public class DaoVehiculoJDBC extends DaoJDBC implements DaoList<Vehiculo> {

    public ArrayList<Vehiculo> alv = new ArrayList<Vehiculo>();
    // Creamos un ArrayList porque más adelante una función la va a utilizar
    DaoJDBC dao = new DaoJDBC();

    //Constructor DaoVehiculoJDBC que indica que necesita un DaoJDBC para funcionar, primero se crea un DaoJDBC y posteriormente un DaoVehiculoJDBC
    public DaoVehiculoJDBC(DaoJDBC daoJDBC) {
        this.dao = daoJDBC;
    }


    //Metodo findAll, Busca en la base de datos valores de tipo vehiculo, y los guarda en un arraylist como vehiculos
    public ArrayList<Vehiculo> findAll() {
        ArrayList<Vehiculo> alv = new ArrayList<>();
        //try - catch para hacer la conexión
        try {
            Connection connection = dao.getInstance();
            if (connection == null) {
                System.err.println("Error: Conexión es null."); //En caso de que la conexión sea null saltará este error
                return null;
            }
            Statement stmt = connection.createStatement(); //Creación del Statement, es un objeto que lanza sentencias a la BBDD
            String ssq1 = "SELECT * FROM vehiculo"; // UN string con nuestra consulta PostgreSQL
            ResultSet rs = stmt.executeQuery(ssq1); // Ejecutamos esa consulta con el statement
            while (rs.next()) {
                alv.add(new Vehiculo(rs.getString("matricula"),
                        rs.getString("color"),
                        rs.getInt("fecha")));
            } //Bucle, mientras tenga vehiculos la base de datos el arraylist incluirá esos datos de todos los vehículos
            rs.close();
            stmt.close();// Siempre cerrar el result set y el statement
            return alv;// Retornar el arraylist
        } catch (Exception ex) {
            Logger.getLogger(DaoVehiculoJDBC.class.getName()).log(Level.SEVERE, null, ex); // Display de excepción de Netbeans, en Eclipse quizás esto de error, solo usar un throw Exception e y listo
            return null;
        }
    }

    public Vehiculo findOne(String key) {
        try {
            Statement stmt = dao.getInstance().createStatement();
            String ssq1 = ("SELECT * FROM vehiculo WHERE matricula = "+"'"+key+"'");
            ResultSet rs = stmt.executeQuery(ssq1);
            if (rs.next()) {
                Vehiculo v1 = new Vehiculo(rs.getString("matricula"),
                        rs.getString("color"),
                        rs.getInt("fecha")
                );
                rs.close();
                stmt.close();
                return v1;
            } else {
                rs.close();
                stmt.close();
                return null;
            }
        } catch (Exception ex) {
            Logger.getLogger(DaoVehiculoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean insertOne(Vehiculo v) {
        try {
            Statement stmt = dao.getInstance().createStatement();
            //Invocamos la matricula, el color y la fecha del vehiculo que le damos
            String ssq1 = ("insert into vehiculo(matricula, color, fecha) values ('"+v.getMatricula()+"', '"+v.getColor()+"', "+v.getFecha())+");";
            //Mucho cuidado al hacer las sentencias, recordar siempre la estructura al implementarla en java
            int rowsAffected = stmt.executeUpdate(ssq1);
            stmt.close();
            return rowsAffected > 0;
        } catch (Exception ex) {
            Logger.getLogger(DaoVehiculoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deleteOne(String key){
         try {
             Statement stmt = getInstance().createStatement();
             String ssq1 = ("delete from vehiculo where matricula = '"+key+"';");
             int rowsAffected = stmt.executeUpdate(ssq1);
             stmt.close();
             return rowsAffected > 0;
         } catch (Exception ex) {
             Logger.getLogger(DaoVehiculoJDBC.class.getName()).log(Level.SEVERE, null, ex);
             return false;
         }
    }
    public boolean updateOne(String key,Vehiculo v){
        try {
            Statement stmt = getInstance().createStatement();
            String ssq1 = ("UPDATE FROM vehiculo SET color = "+v.getColor()+", fecha "+v.getFecha()+" WHERE matricula = "+key);
            ResultSet rs = stmt.executeQuery(ssq1);
            rs.close();
            stmt.close();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(DaoVehiculoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
