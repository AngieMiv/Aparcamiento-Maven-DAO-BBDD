

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import model.DaoJdbc;
import views.MainMenu;
import views.UsersView;

public class AppMain {

	
	
	public AppMain() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AppMain app = new AppMain();
		app.run();

	}
	
	public void run() {
		
		if (testConn()) {
			
	        SwingUtilities.invokeLater(() -> {
	            MainMenu menu = new MainMenu();
	            menu.setVisible(true);
	        });
		} else {
			System.out.println("No se puede acceder a la base de datos.");
			System.exit(-1);
		}
		
	}
	
	/**
	 * Comprueba la disponibilidad del servidor de base de datos
	 * @return 	true : conexión realizada con éxito
	 * 			false : se produjo algún tipo de error
	 */
	public boolean testConn() {
		boolean result = false;
		try {
			DaoJdbc dj = new DaoJdbc();		
			if (dj.getConn()!=null) { result = true; dj.closeBD();}
		} catch (Exception ex) {
			ex.printStackTrace();
			result = false;
		}
		return result;
	}

}
