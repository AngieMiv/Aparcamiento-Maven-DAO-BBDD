/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author RMS
 */
public class DaoJdbc {
    
    private static Connection c;
    
    public DaoJdbc(){
    
    String jdbcUrl = "jdbc:mysql://localhost:5432";
    String user = "amiv";
    String password = "amiv";
    String dbname = "db_aparcamientoDao2";
 
        try {
         c = DriverManager.getConnection(jdbcUrl+"/"+dbname, user, password);
         
        } catch (SQLException ex) {
            //Logger.getLogger(DaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
            c = null;
        }
    } // Constructor DaoJdbc
    
    public Connection getConn(){
        return c;
    }
    
    public void closeBD() throws SQLException{
    c.close();
    
    }
}
