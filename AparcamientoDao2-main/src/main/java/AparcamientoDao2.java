/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import Model.Vehiculo;
import Model.Aparcamiento;
import Model.DaoVehiculoJdbc;
import Model.Files.DaoAparcamientoList;
import Model.Files.Es;
import controller.Controlador;
import controller.CrudVehiculos;
import tools.Menu;

/**
 * Aparcamiento DAO versión 2
 * 
 * - Gestión a través de clase DAO de datos mediante ArrayList
 * - Persitencia de datos en Clase DAO con acceso a Base de Datos
 * 
 * TODO
 * 
 */


/**
 *
 * @author RMS
 */
public class AparcamientoDao2 {

	/**
	 * Nombre del archivo de almacenamiento en disco.
	 */
	public static final String STORAGE = "Aparcamiento.dat";
	public Aparcamiento apm;	
	public DaoAparcamientoList aplist = new DaoAparcamientoList();
	/**
	 * Datos de prueba
	 */
	public static Vehiculo[] listaEjemplo =  {
		new Vehiculo("4444-ABC","ROJO",1900),
		new Vehiculo("5555-ABC","AZUL",1901),
		new Vehiculo("6666-ABC","ROJO",1902),
		new Vehiculo("7777-ABC","AMARILLO",1903),
		new Vehiculo("8888-ABC","ROJO",1904),
		new Vehiculo("9999-ABC","AMARILLO",1905),
		new Vehiculo("0000-ABC","ROJO",1906),
	};
	
	public AparcamientoDao2 () {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Lanzador estático
	 * @param args -- Se ignoran parámetros
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AparcamientoDao2  ap = new AparcamientoDao2 ();
//		Aparcamiento apa = new Aparcamiento("Velazquez");
//		System.out.println(apa.toJson());
		ap.run();
		
	} // end Main

	/**
	 * Lanzador no estático de la aplicación (Método principal)
	 */
	public void run() {
		
            
            DaoVehiculoJdbc dv = new DaoVehiculoJdbc();      
            ArrayList<Vehiculo> alv = new ArrayList<Vehiculo>();
            
            alv = dv.findAll();
            
            for (Vehiculo v : alv){
                System.out.println(v.toCsv());
            }
            
            System.out.println("Aplicación Terminada");
            
            
            
		Menu m = new Menu();
		Boolean salir= false;
		Integer opcion =0;
		String[] opc = {                                
				"Entrada de Vehículo",
				"Salida de Vehículo",
				"Vehículos en Aparcamiento",
				"Visor de Ocupación",
				"Estadísticas",
                "Mantenimiento Sistema"
		};
		
		init();// Operaciones de inicialización del programa ---------------------------------------------------------

		m.setTitulo("APARCAMIENTO : " + apm.getNombre());
		m.setOpciones(opc);
		Controlador c = new Controlador(apm.getNumFilas(),apm.getNumColumnas());
		c.av.showPanel(true);
		c.updatePanel(apm.getPlaza()); // Actualiza el aparcamiento
		while (!salir) {
			
			m.mostrar();// Mostrar el menú
			opcion = m.seleccionaOpc();
			
			switch(opcion) {		
				case 1:
					entradaVehiculo();
					break;
				case 2:
					salidaVehiculo();
					break;
				case 3:
					listVehiculos();
					break;
				case 4:
					System.out.println("\nOperación no Implementada...\n");
					break;
				case 5:
					estadisticas();
					break;
                                case 6:
					mantenimiento();
					break;
				case 0: 
					salir = true;
			} // opciones			
			c.updatePanel(apm.getPlaza()); // Actualiza el aparcamiento
			
		} // bucle principal de la aplicación
		
		// Operaciones de cierre de la aplicación ---------------------------------------------------------
		//System.out.println("\nVolcando información a disco...\n");
		//save(STORAGE); // Guarda la información
		c.av.showPanel(false);
		System.out.println("\nAplicación Terminada.\n");
		System.exit(0);
	} // end run

	/** 
         * Mantenimento de clases principales
         * TODO de momento sólo se ha implementado el crud de vehiculos
         */
        public void mantenimiento(){

            CrudVehiculos cv = new CrudVehiculos();
            cv.run();

        }
	public void demoPlano(Controlador c) {
		
		Color co = c.av.amarillo;
		c.av.squareDemo(co, 0);
		c.av.Item_Color(3, 2, co);c.av.Item_Color(3, 7, co);c.av.Item_Color(5, 4, co);c.av.Item_Color(5, 5, co);
		c.av.Item_Color(7, 3, co);c.av.Item_Color(7, 4, co);c.av.Item_Color(7, 5, co);c.av.Item_Color(7, 6, co);
		c.av.showPanel(true);
	}
/**
 * Añade un vehículo nuevo al sistema
 */
	public void entradaVehiculo() {
		
		String matricula = pedirMatricula();
                
                // -- Comprobar si está en el aparcamiento
                // -- Si no está
                // ----- Darlo de alta en la base de datos
                // ----- Actualizar objeto aparcamiento (aparcarVehiculo)
                // ----- Este método debe operar sobre la tabla Aparcamiento_Vehiculo 
                
                
//		Integer valor = comprobarMatricula(matricula);
//		if (valor != -1) {
//			System.out.println("Ya existe un vehículo en el aparcamiento");
//		}
//		else {
//			// Pedir el resto de datos
//			Scanner sc = new Scanner(System.in);
//			System.out.print("Dime el color :");
//			String color = sc.nextLine().toUpperCase();
//			System.out.print("Dime el año :");
//			Integer fecha = sc.nextInt();
//			Vehiculo v = new Vehiculo(matricula,color,fecha);
//			apm.lstVehiculos.insertOne(v);			// Añadirlo a la lista de vehiculos
//			apm.lstEs.insertOne(new Es(matricula,true,LocalDateTime.now()));
//			apm.aparcarVehiculo(matricula);
//			save(STORAGE); // Guarda la información
//		}
			
	} // entradaVehiculo()
	/**
	 * Elimina un vehículo a partir de su matrícula
	 */
	public void salidaVehiculo() {
		
		String matricula = pedirMatricula();

                                // -- Comprobar si está en el aparcamiento
                // -- Si no está
                // ----- Darlo de alta en la base de datos
                // ----- Actualizar objeto aparcamiento (retirarVehiculo)
                // ----- Este método debe operar sobre la tabla Aparcamiento_Vehiculo 
                
                
//		int valor = comprobarMatricula(matricula); // Comprobación dependiente del ArrayList
//		if (valor == -1) {
//			System.out.println("No existe un vehículo en el aparcamiento");
//		}
//		else {
//			apm.lstVehiculos.deleteOne(matricula);
//			// Registrar entrada-salida
//			apm.lstEs.insertOne(new Es(matricula,false,LocalDateTime.now()));
//			apm.retirarVehiculo(matricula);
//			save(STORAGE); // Guarda la información
//		}

	}
	
	/**
	 * Genera un listado de los vehículos registrados en el aparcamiento, utilizando
	 * la colección listVehiculos
	 */
	
	public void listVehiculos() {
		
		System.out.println("\n" + justifica("MATRICULA",10)+justifica("COLOR",10)+justifica("AÑO",5));
		System.out.println("-".repeat(25));
           
       /*
                UTILIZANDO LA OCUPACIÓN DEL APARCAMIENTO
                -- Recorremos el array de plazas y vamos recuperando uno a uno los coches por su matrícula
                UTILIZANDO LA BASE DE DATOS
                -A-
                -- Se consulta en la tabla aparcamiento_vehiculo y se traen todas las matrículas
                -- Se recuperan uno por uno los vehículos
                -B-
                -- Existe una vista en la base de datos que recuperar la lista           
       */
//		for (Vehiculo v : apm.lstVehiculos.findAll()) {
//			System.out.println(	justifica(v.getMatricula(),10)+
//								justifica(v.getColor(),10)+
//								justifica(""+ v.getFecha(),5));
//			
//		}
		System.out.println();
	}
	
	/**
	 * Genera la estadísticas del aparcamiento
	 */
	public void estadisticas() {
//		cuentaColores(apm.getListaVehiculos());
        /*
            UTILIZANDO LA BASE DE DATOS
            Disponer de un conjunto de consultas/vista de agreados y utilizarlas
        */
	};
	
	/**
	 * Devuelve un array con los colores distintos que existen en el aparcamiento
	 * @param alv Array de vehículos
	 * @return lista de colores distintos
	 */
	public ArrayList<String> distinctColor(ArrayList<Vehiculo> alv){
		ArrayList<String> als = new ArrayList<String>();
				for (int i=0; i < alv.size();i++) {
					String color = alv.get(i).getColor();
					if (!als.contains(color))
					{ als.add(color);}					
				}
		return als;
		
	}
	
	/**
	 * Algoritmo para generar las estadísticas de colores de los vehículos registrados
	 * @param alv Colección ArrayList de vehículos
	 */
	public void cuentaColores(ArrayList<Vehiculo> alv) {
		ArrayList<String> als = distinctColor(alv);
		Integer[] numero = new Integer[als.size()];
		// Hay que inicializarlos a cero
		for (int j=0; j< numero.length;j++) {
			 numero [j]=0;
		}
		for (int i = 0;i<alv.size();i++) {
			String color = alv.get(i).getColor();
			int pos = als.indexOf(color);
			numero[pos]++;
		}
		for (int j=0; j< numero.length;j++) {
			System.out.println(als.get(j) + " : " + numero [j]);
		}
	}
	
	/**
	 * Solicita la entrada de la matrícula por consola
	 * TODO:  no se hace ninguna comprobación de formato
	 * @return matrícula con los 3 caracteres de letra en mayúscula
	 * 
	 */
	public String pedirMatricula() {
		Scanner sc = new Scanner(System.in);
			System.out.println("Introduce Matricula");
			String matricula = sc.nextLine();
		return matricula.toUpperCase();
	}
	
	/**
	 * Recupera el índice del vehículo que tiene la matricula especificada
	 * de la colección listVehiculos del aparcamiento
	 * @param matricula Matrícula de búsqueda
	 * @return -1 : No encontrada >=0 : Índice dentro de la lista
	 */
	public int comprobarMatricula(String matricula) {
		int result =  -1;
		ArrayList<Vehiculo> al;
		al = apm.lstVehiculos.findAll();
		for (int i = 0; i < al.size();i++) {
			Vehiculo v1 = al.get(i);
			if (v1.getMatricula().equals(matricula)) {
				result = i;
				break;
			}
		} // recorrer listaVehiculos
		
		return result;
		
	}
	
	/**
	 * Método de inicialización de la aplicación
	 * Recupera los datos base del aparcamiento de la base de datos
	 * si no existe un aparcamiento lo crea
	 * 
	 */
	public void init() {
		
		
		
//	      File file = new File(STORAGE); // Verifica si el archivo existe
//	      if (file.exists()) {
//	        	
//	        	System.out.println("\nRecuperando información almacenada...\n");
//	            load(STORAGE);			// Método simple recupera información
//	        }
//	        else {
//	        		System.out.println("\nGenerando datos de prueba ...\n");
//					this.apm = new Aparcamiento("GOYA");
//					for (int i=0;i<listaEjemplo.length;i++ ) {
//						this.apm.lstVehiculos.insertOne(listaEjemplo[i]);
//						String matricula = listaEjemplo[i].getMatricula();
//						this.apm.lstEs.insertOne(new Es(matricula,true,LocalDateTime.now()));
//						this.apm.aparcarVehiculo(matricula);
//					}
//					aplist.insertOne(apm);
//					save(STORAGE);
//	        }
	} // init()
	/**
	 * Hace una copia completa del contenido del objeto Aparcamiento
	 * que utiliza la aplicación. Se salvan también todos los Vehículos registrados
	 * 
	 * @return operación realizada con éxito true, false en caso contrario
	 */
	public boolean save(String fichero) {
		boolean result = false;
//		
//		// TODO salvar aparcamiento completo	
//		try {
//			apm.lstVehiculos.saveAll(fichero);  // Salvo lista vehiculos;
//			aplist.saveJson();					// Salvo objeto aparcamiento;
//		} catch (IOException e) {
//			e.printStackTrace();
//		} // salva lstVehiculos;
//		
		return result;
	} // save
	
	/**
	 * Recupera la copia del último contenido del objeto Aparcamiento
	 * almacenado. Se salvan también todos los Vehículos registrados
	 * 
	 * @return operación realizada con éxito true, false en caso contrario
	 */
	public boolean load(String fichero) {

		boolean result = false;
 	
//		aplist.loadJson();
//		if (aplist.listaAparcamientos.size()>=1) {
//		this.apm = aplist.listaAparcamientos.get(0); // recupero el primero solo
//		apm.lstVehiculos.loadAll(fichero); // recupera lstVehiculos;
//		}
//		else {
//			System.out.println("Error al recuperar datos, terminado aplicación");
//			System.exit(0);
//		}
		return result;
		
	}
	
	/**
	 * Función auxiliar para justificar con espacios el contenido del string
	 * @param cad Cadena original 
	 * @param numero anchura total
	 * @return cadena rellenada con espacios justificada a la izquierda.
	 */
	private String justifica(String cad,int numero) {	
		return String.format("%1$-" + numero + "s", cad); // justifica a la izq , añadir %1$- para dcha
	}
}
