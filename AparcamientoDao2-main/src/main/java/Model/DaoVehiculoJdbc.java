/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RMS
 */
public class DaoVehiculoJdbc extends DaoJdbc implements DaoList<Vehiculo> {
    
    
    public ArrayList<Vehiculo> alv = new ArrayList<Vehiculo>();
    
    public DaoVehiculoJdbc() {
        super();
    }
    
    public ArrayList<Vehiculo> findAll(){

        try {
            Statement stm = getConn().createStatement();
            String ssql = "SELECT * FROM vehiculo";
            ResultSet rs = stm.executeQuery(ssql);
            while (rs.next()){
                // Opcion 1
                alv.add( new Vehiculo(
                            rs.getString("matricula"),
                            rs.getString("color"),
                            rs.getInt("fecha")
                ));
            }
            rs.close();
            stm.close();
            
//            return new ArrayList<Vehiculo>(alv); // Copia
            return alv;  // Referencia
           
        } catch (SQLException ex) {
            Logger.getLogger(DaoVehiculoJdbc.class.getName()).log(Level.SEVERE, null, ex);
           return null; // TODO devolver el valor correcto
        }

    }
    public Vehiculo findOne(String key){
        
        return null;
    }
    public boolean insertOne(Vehiculo t){
        boolean result = false;
                if (getConn()!=null){
                
                    try {
                        
                        // Insertar objeto aparcamiento
                        String ssql = "INSERT INTO vehiculo (matricula,color,fecha) ";
                        ssql = ssql + " VALUES (?,?,?)";
                        PreparedStatement pstm = getConn().prepareStatement(ssql);
                        pstm.setString(1,t.getMatricula());
                        pstm.setString(2,t.getColor());
                        pstm.setInt(3,t.getFecha());
                        pstm.execute();
                        result = true;
                        pstm.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(DaoAparcamientoJdbc.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                }
		return result;	
        
    }
    public boolean deleteOne(String key){
        
        return false;
    }
    public boolean updateOne(String key,Vehiculo t){
        
        return false;
    }
    
}
