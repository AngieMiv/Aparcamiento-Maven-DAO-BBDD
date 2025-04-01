import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainApp {
	public static void main(String[] args) throws SQLException {
		

		
		/*
		**********************************************************************************
		* !! Es preciso tener un servidor MySQL en local . el Script de generación
		* de la base de datos y la tabla está en el directorio source de este proyecto
		**********************************************************************************
		*/
		// Creamos una estructura de datos en memoria con valores para operar
				// sobre una base de datos
		ArrayList<Coche> misCoches = new ArrayList<Coche>();
		
		misCoches.add(new Coche("3307-MZX","Ferrari 555","Rojo",1986));
		misCoches.add(new Coche("8091-ABS","Asthon Martin GT","Negro",1992));
		misCoches.add(new Coche("2212-CDT","Coupe Fiat","Amarillo",1988));
		misCoches.add(new Coche("6604-UMC","Ford Mustang","Azul",1984));
		misCoches.add(new Coche("4032-RTP","Ford Capri","Verde",1965));

		Connection conn;
		Statement sent;
			// El driver se debe haber descargado y almacenado en el build path
		    
			// PASO 1: Conectarse a la base de datos
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbcoches", "root", "root");
			// PASO 2: Generar un objeto Statement para crear la sentencia SQL
			sent = conn.createStatement();
			
		// PASO 2: Definir sentencias, PASO 3: Ejecutar la sentencia, PASO 4: OPERAR con ella
			boolean valor;
		// Insertar coches en la base de datos
		String ssql = "";
		for (Coche c: misCoches) {
				ssql = "INSERT INTO coche VALUES (";
				ssql = ssql + "'" + c.getMatricula()+ "','"+c.getModelo()+ "','"+c.getColor()+ "',"+c.getYear(); 
				ssql = ssql +")";
				valor = sent.execute(ssql);
		}
		// Actualizar un coche por su matrícula
		String  ssql2 = "UPDATE Coche SET modelo ='Asthon Martin GT'";
		ssql2 = ssql2  + ", color ='Gris', year=1883"; 
		ssql2 = ssql2 + " WHERE matricula ='8091-ABS'";
		valor = sent.execute(ssql2);
		// Borrar un coche
		String  ssql3 = "DELETE FROM coche WHERE matricula='3307-MZX'";
		 valor = sent.execute(ssql3);
		// Listar 
		System.out.println("Lista de coches en Base de Datos");
		System.out.println("--------------------------------");

			ResultSet rs = sent.executeQuery("SELECT * FROM coche");
			while(rs.next()) {
				// Los campos de un resultset siempre comienzan en 1 en vez de 0 !!!
				Coche c= new Coche(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4));
				// Forma alternativa de direccionamiento por nombre
				//Coche c= new Coche(rs.getString("matricula"),rs.getString("modelo"),rs.getString("color"),rs.getInt("year"));
				System.out.println(c);
			}
			rs.close();
		// PASO 5,6,7 : Liberar objetos
			sent.close();
			conn.close();
	} //main	
} // AppMAin
