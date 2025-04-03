package view;

import model.Articulo;
import model.Tienda;
import model.Vendedor;

public class Listados {

	public Listados() {	}

    /**
     * Lista los artículos de la tienda
     * @param t Tienda activa
     * @apiNote Sólo se vuelcan los datos residentes en memoria.
     */
    public void _articulos(Tienda t) {
    	
    	//  RA6 40% ---- 
    	//  PREGUNTA E
    	//---------------------------------------------------
     	// INSERTA TU CÓDIGO AQUÍ
    	
    	
    	
     	//---------------------------------------------------

    } // lista_articulos(Tienda t) 
    
    
    /**
     * Lista los vendedores de la tienda activa
     * @param t Tienda activa
     * @apiNote Sólo se vuelcan los datos residentes en memoria.
     */
    public void _vendedores(Tienda t) {
    	
    	//TODO a desarrollar en futuras versiones
    	System.out.println("\nOpción no Implementada ...\n");
    	
    } //lista_vendedores(Tienda t)

	/**
	 * Función auxiliar para justificar con espacios el contenido del string
	 * @param cad Cadena original 
	 * @param numero anchura total
	 * @return cadena rellenada con espacios justificada a la izquierda.
	 */
	@SuppressWarnings("unused")
	private String justifica(String cad,int numero) {	
		return String.format("%1$-" + numero + "s", cad); // justifica a la izq , añadir %1$- para dcha
	}
}
