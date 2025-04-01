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
 * DaoVehiculoJDBC es una clase DAO (Data Access Object) que maneja la
 * interacción con la base de datos para la entidad Vehiculo.
 * 
 * Implementa la interfaz DaoList<Vehiculo>, lo que significa que
 * define métodos estándar para realizar operaciones CRUD (Create, Read, Update, Delete)
 * sobre vehículos en la base de datos.
 */

/**
 * DaoVehiculoJDBC hereda de DaoJdbc, la cual maneja la conexión a la BD
 * Implementa DaoList<Vehiculo>, lo que obliga a definir los métodos CRUD
 */
public class DaoVehiculoJDBC extends DaoJdbc implements DaoList<Vehiculo> {

	// alv es una lista de vehículos donde se almacenan los vehículos obtenidos de la BD
	public ArrayList<Vehiculo> alv = new ArrayList<Vehiculo>();

	// Llama al constructor de DaoJdbc, lo que inicializa la conexión con la BD
	// mediante el super() llama al constructor
	public DaoVehiculoJDBC() {
		super();
	}

	/*
	 * findAll() recupera todos los vehículos de la BD
	 * */
	public ArrayList<Vehiculo> findAll(){

		try {
			// .getConn() obtiene la conexión a la BD
			// .createStatement() crea un objeto Statement, que permite ejecutar consultas SQL
			Statement stm = getConn().createStatement();

			// consulta SQL para seleccionar todos los registros de la tabla vehiculo
			String ssql = "SELECT * FROM vehiculo";

			// .executeQuery(ssql) ejecuta la consulta SQL y devuelve un
			// ResulSet, que contiene los datos obtenidos
			ResultSet rs = stm.executeQuery(ssql);

			// Recorre los resultados obtenidos. Con rs.next() nos movemos al siguiente registro
			while (rs.next()) { 
				// Opcion 1
				alv.add(
						new Vehiculo(
								rs.getString("matricula"),
								rs.getString("color"),
								rs.getInt("fecha")
								)
						);	// se crea un nuevo obj Vehiculo con los datos obtenidos y se añade a alv
			}
			// Cerramos ResulSet y Statemet para liberar recursos
			rs.close();
			stm.close();

			// return new ArrayList<Vehiculo>(alv); // Copia
			// Devuelve la lista de vehículos
			return alv;  // Referencia

		} catch (SQLException ex) {
			Logger.getLogger(DaoVehiculoJDBC.class.getName()).log(Level.SEVERE, null, ex);
			// throw new RuntimeException("Error al obtener vehículos", ex);
			return null; // TODO devolver el valor correcto
		}

	}

	public Vehiculo findOne(String key){
		
		try {
			// define la sentencia SQL con un placeholder '?'
			String ssql = "Select * from vehiculo WHERE matricula = ?";
			
			// PreparedStatement evita inyecciones SQL
			PreparedStatement pstm = getConn().prepareStatement(ssql);
			
			// Asigna el '?' a la String key del parámetro
			pstm.setString(1, key);
			
			// guarda en rs la ejecución de la query
			ResultSet rs = pstm.executeQuery();
			
			if (rs.next()) {
				return new Vehiculo (
						rs.getString("matricula"),
						rs.getString("color"),
						rs.getInt("fecha")
				);
			}
			
			return null; // si no encuentra el vehículo
			
		} catch (SQLException ex) {
			Logger.getLogger(DaoAparcamientoJDBC.class.getName()).log(Level.SEVERE, null, ex);
			//throw new RuntimeException("Error al buscar vehículo", ex);
			return null;
		}
	} // end findOne(String key)

	public boolean insertOne(Vehiculo t){
		boolean result = false;
		
		if (getConn() != null) { // Si es null, no hace nada, return result = false
			try {
				// Insertar objeto aparcamiento
				String ssql = "INSERT INTO vehiculo (matricula,color,fecha) ";
				
				// define la consulta SQL con placeholders '?'
				ssql = ssql + " VALUES (?,?,?)";
				PreparedStatement pstm = getConn().prepareStatement(ssql);
				
				// asigna el 1er, 2do y 3er '?' a la matrícula, color y fecha
				pstm.setString(1,t.getMatricula());
				pstm.setString(2,t.getColor());
				pstm.setInt(3,t.getFecha());
				
				// ejecuta la inserción
				pstm.execute();
				
				// Verificar si se insertaron filas o no
				int rowsAffected = pstm.executeUpdate();
				if (rowsAffected > 0) {
				    System.out.println("Vehículo insertado correctamente.");
				} else {
				    System.out.println("Error: No se pudo insertar el vehículo.");
				}

				
				// devuelve true si fue insertado con éxito
				result = true;
				
				// libera los recursos
				pstm.close();
				
			} catch (SQLException ex) {
				Logger.getLogger(DaoAparcamientoJDBC.class.getName()).log(Level.SEVERE, null, ex);
				//throw new RuntimeException ("Error al insertar el vehículo", ex);
			}
		}
		return result;	

	}
	public boolean deleteOne (String key) {
		try {
			String ssql = "DELETE FROM vehiculo WHERE matricula = ?";
			PreparedStatement pstm = getConn().prepareStatement(ssql);
			pstm.setString(1, key);
			int rowsAffected = pstm.executeUpdate(); // executeUpdate() devuelve el numero de filas afectadas

			if (rowsAffected > 0) {
				System.out.println("Vehículo eliminado con éxito");
				return true;
			} else {
				System.out.println("No se encontró el vehículo");
				return false;
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(DaoAparcamientoJDBC.class.getName()).log(Level.SEVERE, null, ex);
			//throw new RuntimeException ("Error al eliminar el vehículo", ex);
			return false;
		}
	}

	public boolean updateOne(String key,Vehiculo t){
		try {
			String ssql = "UPDATE vehiculo SET color = ?, fecha ? WHERE MATRICULA = ?";
			PreparedStatement pstm = getConn().prepareStatement(ssql);
			pstm.setString(1, t.getColor());
			pstm.setInt(2, t.getFecha());
			pstm.setString(3, key);
			int rowsAffected = pstm.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Vehículo actualizado con éxito de matrícula " + key);
				return true;
			} else {
				System.out.println("No se encontró el vehículo");
				return false;
			}
		} catch (SQLException ex) {
			Logger.getLogger(DaoAparcamientoJDBC.class.getName()).log(Level.SEVERE, null, ex);
			//throw new RuntimeException ("Error al actualizar vehículo", ex);
			return false;
		}
	}

}
