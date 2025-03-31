package org.example;
import java.util.ArrayList;
import java.util.Scanner;

// Recomendación: Leer antes las demás clases antes del AppMainNick

public class AppMainNick {

    //Creación del aparcamiento, el DaoJDBCNick y el DaoVehiculoJDBCNick
    public AparcamientoNick apm = new AparcamientoNick("Nick's Parking");
    public DaoJDBCNick daoJDBCNick = new DaoJDBCNick();
    public DaoVehiculoJDBCNick bdVehiculos = new DaoVehiculoJDBCNick(daoJDBCNick);

    //Constructor vacío
    public AppMainNick() {

    }
    //En el metodo main (principal) declaramos un AppMainNick y ejecutamos la función run, que tendrá toda la información y métodos, esto es sólo por orden y presentación.
    public static void main(String[] args) {
        AppMainNick ap = new AppMainNick();
        ap.run();
    }
    // Metodo donde se ejecutará lo necesario
    public void run() {
        //Declaración del menu
        MenuNick m = new MenuNick();
        Boolean salir= false;
        Integer opcion =0;
        String[] opc = {
                "Añadir Vehículo",
                "Eliminar Vehículo",
                "Listar listVehiculos",
        };

        m.setTitulo("APARCAMIENTO : " + apm.getNombre());
        m.setOpciones(opc);

        while (!salir) {

            m.mostrar();// Mostrar el menú
            opcion = m.seleccionaOpc();

            switch(opcion) {
                case 1:
                    addVehiculo();
                    break;
                case 2:
                    deleteVehiculo();
                    break;
                case 3:
                    listVehiculos();
                    break;
                case 0:
                    salir = true;
            } // Menú con las opciones posibles
        }
        System.out.println("\nAplicación Terminada.\n");
    }

    public void listVehiculos() {
        //Creamos un Arraylist listV con el metodo findAll que devuelve un arraylist con los vehiculos de la bbdd
        ArrayList<VehiculoNick> listv = apm.bdVehiculos.findAll();
        if (listv==null) {
            System.out.println("La BBDD no tiene ningún vehículo registrado"); // Si la BBDD está vacía saltará este mensaje
        } else {
            //Este for es solo para realizar una linea horizontal de guiones como separación, ignorar ------------
            for (int i = 0; i < 60; i++) {
                System.out.print("-");
            }
            System.out.println();
            // Metodo JustificaCentro, debajo está el código, pero es solo por presentación, a la izquierda está el String y a la derecha cuantos espacios queremos que tenga de separación con otro elemento
            System.out.println(JustificaCentro("Matricula",20)+"|"+JustificaCentro("Color",20)+"|"+JustificaCentro("Fecha",20));
            for (VehiculoNick vehiculoNick : listv) {
                for (int i = 0; i < 60; i++) {
                    System.out.print("-");
                }
                System.out.println();
                //Imprimimos los valores de los vehiculos en listv
                System.out.println(JustificaCentro(""+vehiculoNick.getMatricula(),20)+"|"+JustificaCentro(""+vehiculoNick.getColor(),20)+"|"+JustificaCentro(""+vehiculoNick.getFecha(),20));
            }
        }
    }

    public void deleteVehiculo() {
        String matricula = pedirMatriculaV();
        //pedirMatricula está explicado más adelante en el código
        while (matricula==null) {
            System.out.println("La matrícula no cumple las condiciones requeridas, asegurate de que cumpla la siguiente estructura: (0000-AAA)");
            matricula = pedirMatriculaV();
        }
        VehiculoNick v = apm.bdVehiculos.findOne(matricula); // Una vez la matrícula sea válida la buscamos con findOne y creamos un vehiculo para tenerlo guardado
        if (v != null) { // Si no encontró un vehiculo con esa matrícula sera null, en caso contrario será diferente de null
            apm.bdVehiculos.deleteOne(matricula); //Usamos el metodo deleteOne con la matricula ingresada
            System.out.println("El vehiculo con matricula "+matricula+" ha sido eliminado exitósamente de la BBDD"); // Mensaje de confirmación
        } else {
            System.out.println("Esta matrícula NO está registrada en la base de datos. ERR"); // Mensaje en caso de que no haya un vehiculo con esa matrícula
        }
    }

    public void addVehiculo() {
        String matricula = pedirMatriculaV();
        while (matricula==null) {
            System.out.println("La matrícula no cumple las condiciones requeridas, asegurate de que cumpla la siguiente estructura: (0000-AAA)");
            matricula = pedirMatriculaV();
        }
        VehiculoNick v = apm.bdVehiculos.findOne(matricula);
        if (v != null) {
            System.out.println("Esta matrícula ya está registrada en la base de datos. ERR");
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.print("Dime el color :");
            String color = sc.nextLine().toUpperCase();
            System.out.print("Dime el año :");
            Integer fecha = sc.nextInt();
            v = new VehiculoNick(matricula,color,fecha);
            apm.bdVehiculos.insertOne(v);
        }
    }

    public String pedirMatriculaV() {
        //Creamos un scanner, e ingresamos una matricula, esta matricula tendrá que cumplir requisitos
        Scanner sc = new Scanner(System.in);
        String matricula;
        System.out.println("Introduce la Matricula:");
        matricula = sc.nextLine().toUpperCase(); //Que sea Uppercase para convertir minusculas a mayusculas
        if (matricula.length() != 8) { // SI la matricula no es de 8 caracteres da error
            return null;
        }
        for (int i = 0; i < 4; i++) { // Si los primeros 4 caracteres no son digitos da error
            if (!Character.isDigit(matricula.charAt(i))) {
                return null;
            }
        }
        if (matricula.charAt(4) != '-') { // Si el caracter en posición 4 no es un guión da error
            return null;
        }
        for (int i = 5; i < 8; i++) { // Si los siguientes 3 caracteres no son letras mayusculas da error
            if (!Character.isUpperCase(matricula.charAt(i))) {
                return null;
            }
        }
        return matricula; // Si las condiciones se cumplen, devuelve la matricula que ingresamos correctamente
    }

    // Metodos Justifica, en este caso solo se usa JustificaCentro

    public static String Justifica(String cadena, int numero) {
        return String.format("%1$-"+numero+"s",cadena);
    }

    public static String JustificaCentro(String cadena, int ancho) {
        //Justifica Centro es un metodo de justificar para que el texto quede centrado en un ancho determinado
        int longitudCadena = cadena.length(); //longitud Cadena será la cantidad de caracteres que le estamos ingresando
        if (longitudCadena >= ancho) {
            return cadena;
        }// Si tiene mas caracteres que espacio de ancho solo escribirá normalmente la cadena que le dimos

        int espaciosIzquierda = (ancho - longitudCadena) / 2; // Cantidad de espacios a la izq
        int espaciosDerecha = ancho - longitudCadena - espaciosIzquierda; // Cantidad de espacios a la der

        String espaciosIzq = "";//Definición del espacio en blanco a la izq

        for (int i = 0; i < espaciosIzquierda; i++) {
            espaciosIzq += " ";// Aumentamos ese espacio por cuantos tengamos almacenados a la izq
        }

        String espaciosDer = "";//Definición del espacio en blanco a la der
        for (int i = 0; i < espaciosDerecha; i++) {
            espaciosDer += " ";// Aumentamos ese espacio por cuantos tengamos almacenados a la der
        }

        return espaciosIzq + cadena + espaciosDer; //Retorna la cadena con los espacios en blanco a la izquierda y a la derecha
    }

    public static String JustificaDer(String cadena, int numero) {
        return String.format("%1$"+numero+"s",cadena);
    }

    public static String Redondeo(double numero) {
        double redondeado = Math.round(numero * 100.0) / 100.0;
        return String.format("%.2f", redondeado);
    }

}
