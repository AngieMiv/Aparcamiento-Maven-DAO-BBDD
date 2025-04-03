import model.*;
import view.*;
/**
 * Examen 3Eva de 1WET curso 24/25
 * @author RMS
 *
 */
public class PtoVenta {

	Tienda myStore;

	public static void main(String[] args) throws Exception {

		System.out.println("\n\n\nGestión de Tiendas version: R1");
		System.out.println("-----------------------------------");

		PtoVenta pv = new PtoVenta();

		if (pv.testDb()) {
			System.out.println("Sistema en linea\n");
			pv.run();				// Módulo para la carga externa de datos
		} else { 
			System.out.println("\nNO ES POSIBLE CONECTAR CON LA BASE DE DATOS!");
		}

		System.out.println("-----------------------------------");
		System.out.println("Aplicación terminada\n");
	} // main

	void run() {

		String[] opcs = {
				"Información de la tienda",
				"Importar Vendedores",
				"Cargar Articulos",
				"Listar Articulos",
				"Salvar Datos Vendedores"
		};

		Menu mppal = new Menu("CARGA EXTERNA DE DATOS",opcs);
		Boolean salir = false;
		Integer opcion = 0;
		DaoTiendaJdbc  mdt = new DaoTiendaJdbc();			// Objeto para acceso a datos relacionados con la tienda
		Listados listar = new Listados();
		Tienda myStore = new Tienda();						// tienda nula
		myStore = mdt.findOneByName("Drinks4Geeks");		// Recupera la tienda del Examen
		if (myStore == null) {
			System.out.println("No se encontró la tienda solicitada\n");
			//salir = true;				// Si no se encuentra se cierra el programa
		}
		while (!salir) {									// Gestionar menú de opciones
			mppal.mostrar();
			opcion = mppal.seleccionaOpc();
			switch(opcion) {
			case 1:										//-- Ficha con información relevante sobre la tienda
				System.out.println(myStore);	
				break;
			case 2:										//-- Carga de personal externo y asociación a tienda
				mdt.load_vendedores_f("C:\\RMS\\DATOS\\vendedores.csv",myStore);
				break;
			case 3: 									//-- Carga de catálgo externo e inclusión en catálogo de tienda
				mdt.load_articulos(myStore);
				break;
			case 4:										//-- Lista el catálogo ( listado de artículos vinculados a la tienda)
				listar._articulos(myStore);
				break;
			case 5:										//-- Almacena en Base de datos los vendedores contratados
				mdt.save_vendedores(myStore);
				break;	
			case 0:										//-- Salir de la aplicación
				salir = true;
				break;
			} // procesa opción de menú        		
		} // fin bucle principal
	} // void run( )

	public boolean testDb() {
		DaoJdbc tdb = new DaoJdbc();
		System.out.println("Comprobando acceso a base de datos ...\n");
		return (tdb.getConn() == null)? false: true;
	}

} // Class PtoVenta
