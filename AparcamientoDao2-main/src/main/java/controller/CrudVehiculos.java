package controller;

import Model.DaoVehiculoJDBC;
import Model.Vehiculo;
import java.util.Scanner;
import tools.Menu;

/**
 *
 * @author rmc
 */
public class CrudVehiculos {
    
    public  DaoVehiculoJDBC dvj = new DaoVehiculoJDBC();
    public CrudVehiculos() {};
    
    public void run(){


            Menu m = new Menu();
            Boolean salir= false;
            Integer opcion =0;
            String[] opc = {                                
                            "Alta de Vehículo",
                            "Baja de Vehículo",
                            "Actualización de Vehículo",
                            "Buscar Vehículo",
                            "Listar Vehículo"
            };


            m.setTitulo("MANTENIMIENTO DE VEHICULOS");
            m.setOpciones(opc);

            while (!salir) {
                    m.mostrar();// Mostrar el menú
                    opcion = m.seleccionaOpc();
                    switch(opcion) {		
                            case 1 -> addVehiculo();
                            case 2 -> deleteVehiculo();
                            case 3 -> System.out.println("\nOperación no Implementada...\n");
                            case 4 -> System.out.println("\nOperación no Implementada...\n");
                            case 5 -> System.out.println("\nOperación no Implementada...\n");
                            case 0 -> salir = true;
                    } // opciones			
            } // bucle principal de la aplicación
    } // end run
    
/**
 * Añade un vehículo nuevo al sistema
 */
	public void addVehiculo() {
		
		String matricula = pedirMatricula();
		Vehiculo v = dvj.findOne(matricula);
		if (v != null) {
			System.out.println("Vehículo ya registrado");
		}
		else {
			// Pedir el resto de datos
			Scanner sc = new Scanner(System.in);
			System.out.print("Dime el color :");
			String color = sc.nextLine().toUpperCase();
			System.out.print("Dime el año :");
			Integer fecha = sc.nextInt();
			Vehiculo vnew = new Vehiculo(matricula,color,fecha);
                        dvj.insertOne(vnew);

		}
			
	} // addVehiculo()
        
        public void deleteVehiculo() {
        String matricula = pedirMatricula();
        
		Vehiculo v = dvj.findOne(matricula);
		if (!dvj.deleteOne(matricula)) {
                    System.out.println("Vehículo eliminado");
		}
		else {
                    System.out.println("No está registrado");
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
}