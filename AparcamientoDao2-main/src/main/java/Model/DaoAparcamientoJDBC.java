package Model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DaoAparcamientoJDBC extends DaoJdbc implements DaoList<Aparcamiento>
{

	public ArrayList<Aparcamiento> listaAparcamientos = new ArrayList<Aparcamiento>();
	public DaoAparcamientoJDBC() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Aparcamiento> findAll(){
		
        Connection c = getConn();
        if (c!=null){
        
            try {
                
            	String ssql = "SELECT * FROM  aparcamiento order by nombre";
                Statement stm = c.createStatement();
                ResultSet rs = stm.executeQuery(ssql);

                while (rs.next()) {
                	
                	Aparcamiento ap = new Aparcamiento(
                					rs.getString("nombre"),
                					rs.getInt("numfilas"),
                					rs.getInt("numcolumnas")
                			);
                	listaAparcamientos.add(ap);		// Lo añado a la lista
                	return listaAparcamientos;
                	
                }
            } catch (SQLException ex) {
                //Logger.getLogger(DaoAparcamientoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            	return null;
            }
        
        }
		return null;
	}
	public Aparcamiento findOne(String key) {
		boolean result = false;
		
        Connection c = getConn();
        if (c!=null){
        
            try {
                
            	String ssql = "SELECT * FROM  aparcamiento WHERE nombre='" + key +"'";
                Statement stm = c.createStatement();
                ResultSet rs = stm.executeQuery(ssql);

                if (rs.next()) {
                	
                	Aparcamiento ap = new Aparcamiento(
                					rs.getString("nombre"),
                					rs.getInt("numfilas"),
                					rs.getInt("numcolumnas")
                			);
                	return ap;
                	
                }
            } catch (SQLException ex) {
                //Logger.getLogger(DaoAparcamientoJDBC.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        
        } // c!=null
        return null;
	}
	public boolean insertOne(Aparcamiento ap) {
		boolean result = false;
		listaAparcamientos.add(ap);             // Gestiona memoria
                Connection c = getConn();
                if (c!=null){
                
                    try {
                        
                        // Insertar objeto aparcamiento
                        String ssql = "INSERT INTO aparcamientos (nombre,numfilas,numcolumnas) ";
                        ssql = ssql + " VALUES (?,?,?)";
                        PreparedStatement pstm = c.prepareStatement(ssql);
                        pstm.setString(1,ap.getNombre());
                        pstm.setInt(2,ap.getNumFilas());
                        pstm.setInt(3,ap.getNumColumnas());
                        pstm.execute();
                    } catch (SQLException ex) {
                        Logger.getLogger(DaoAparcamientoJDBC.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                }
		return result;					

	}
	public boolean deleteOne(String key) {
		// TODO implementar
		return false;
	}
	public boolean updateOne(String key,Aparcamiento t) {
		// TODO implementar
		return false;
	}

}
