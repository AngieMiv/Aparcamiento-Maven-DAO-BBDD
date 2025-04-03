package model;

// -- Recursos para manejo de ficheros
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//-- Recursos para manejo de sql
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// -- Colecciones y Sets
import java.util.ArrayList;
import java.util.HashMap;

import java.util.logging.Level;
import java.util.logging.Logger;


public class DaoTiendaJdbc extends DaoJdbc {

	/**
	 * Constructor único de la clase
	 * @param miConn Objeto conexión hacia la fuente de dato, válido y verificado.
	 */
	public DaoTiendaJdbc() {	}

	/**
	 * Recupera una tienda de la base de datos , por su nombre
	 * @param nombre : Recupera los datos principales de la tienda
	 * @return Objeto tienda y si existe o null si no se encuentra
	 * @apiNote Sólo se recuperan los datos correspondientes a la ficha de la tienda, no
	 * sus vendedores y artículos
	 */
	public Tienda findOneByName(String t) {

		// RA8   100%
		// PREGUNTA A
		//---------------------------------------------------
		// INSERTA TU CÓDIGO AQUÍ
		Connection c = getConn();
		if (c!=null){
			try {
				String ssql = "SELECT * FROM tienda WHERE nombre = '" + t +"'";
				Statement stm = c.createStatement();
				ResultSet rs = stm.executeQuery(ssql);

				if (rs.next()) {
					Tienda t1 = new Tienda (
							rs.getString("nombre"),
							rs.getString("direccion"),
							rs.getInt("max_articulos"),
							rs.getInt("max_vendedores")
							);

					return t1;  	
				}
			} catch (SQLException ex) {
				return null;
			}

		} // c!=null
		return null;

		//---------------------------------------------------
	};

	/**
	 * Incorporar nuevos vendedores a la tienda activa. Es necesario invocar
	 * al método Contratar de la tienda para efectuar la incorporación.
	 * @param ruta	Ubicación del archivo con los datos de los vendedores.
	 * La ruta es válida y el archivo existe, y tiene datos. no se hacen comprobaciones
	 * @param t Tienda donde se incoporan los datos
	 * @apiNote El fichero csv contiene el orden de los campos en la primera línea
	 */
	public void load_vendedores_f(String ruta,Tienda t) {
		System.out.println("Incoporando vendedores de archivo externo ...");
		int tot = 0;

		// RA5 ---- 100%   RA6 ---- 30%
		// PREGUNTA B
		//---------------------------------------------------
		// --> reader
		try { 
			BufferedReader br = new BufferedReader(new FileReader(ruta));
			String linea;

			while ((linea = br.readLine())!= null) {
				Vendedor v = new Vendedor(linea);
				t.Contratar(v);
				tot += 1;
			} // end while

			br.close();

		} catch (IOException e) { 

			e.printStackTrace();
		} // end try-catch

		System.out.println("Proceso terminado ("+ tot + ") ...");
	} // end loadAll
	//---------------------------------------------------


	// load_vend_Fichero(String ruta,Tienda t) 


	/**
	 * Añade los registros de la tabla articulos al catalogo. Se debe usar el método aCatalogo de la clase Tienda
	 * @param t Tienda donde se deberán añadir los artículos
	 * @apiNote No se comprueban duplicados en la incorporación, ni se actualizan
	 * referencias ya existentes.
	 */
	public void load_articulos(Tienda t) {

		int tot = 0;
		System.out.println("Incoporando catalogo de articulos de base de datos ...\n");

		// RA9 ---- 50%   RA6 ---- 30%
		// PREGUNTA C
		//---------------------------------------------------
		// INSERTA TU CÓDIGO AQUÍ

		Connection c = getConn();
		try {
			Statement stm = getConn().createStatement();
			String ssql = "SELECT 'nombre' FROM articulos";
			ResultSet rs = stm.executeQuery(ssql);

			while (rs.next()) {
				Articulo a = new Articulo(
						((Tienda) rs).getNombre(),
						((Articulo) rs).getMarca(),
						((Articulo) rs).getTipo(),
						((Articulo) rs).getStock(),
						((Articulo) rs).getPrecio()
						);

				if (t.aCatalogo(a) != false) {
					ssql = "INSERT INTO 'articulos' (nombre, marca, tipo, stock, precio) ";
					ssql = ssql + " VALUES (?,?,?,?,?)";

					PreparedStatement pstm = getConn().prepareStatement(ssql);
					pstm.setString(1,a.getNombre());
					pstm.setString(2,a.getMarca());
					pstm.setString(3,a.getTipo());
					pstm.setInt(4, a.getStock());
					pstm.setDouble(5, a.getPrecio());

					pstm.execute();
				}
				//public Articulo(String nombre, String marca, String tipo, Integer stock,Double precio)rs.getString("nombre"));
			}
			// Cerramos para liberar
			rs.close();
			stm.close();

		} catch (SQLException ex) {
			Logger.getLogger(DaoTiendaJdbc.class.getName()).log(Level.SEVERE, null, ex);
		}

		//---------------------------------------------------
		System.out.println("Proceso terminado (" + tot + ") ...");	
	} //load_articulos(Tienda t)


	/**
	 * Inserta los vendedores asociados a la tienda en la tabla aprovechando
	 * la integridad referencial de la base de datos. 
	 * @param t Tienda activa de donde recuperar la información
	 */
	public void save_vendedores(Tienda t) {

		int tot = 0;
		System.out.println("Guardando vendedores en base de datos...\n");

		// RA9 ---- 50%
		// PREGUNTA D
		//---------------------------------------------------
		// INSERTA TU CÓDIGO AQUÍ



		//---------------------------------------------------

		System.out.println("Proceso terminado (" + tot + ")");
	} 


} // DAOTienda
