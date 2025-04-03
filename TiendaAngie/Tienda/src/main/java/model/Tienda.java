package model;

import java.util.ArrayList;

public class Tienda {

	private String nombre;
	private String direccion;
	private Integer max_Articulos = 0;
	private Integer max_Vendedores = 0;
	public ArrayList<Articulo> artTienda = new ArrayList<Articulo>();		// Catálogo de artículos
	public ArrayList<Vendedor> vendTienda = new ArrayList<Vendedor>();		// Vendedores en tienda

	public Tienda() {};

	/**
	 * Constructor de Tienda()
	 * @param nombre
	 * @param direccion
	 * @param max_Articulos
	 * @param max_Vendedores
	 */
	public Tienda(String nombre, String direccion, Integer max_Articulos, Integer max_Vendedores) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.max_Articulos = max_Articulos;
		this.max_Vendedores = max_Vendedores;
	}

	// Getters
	public String getNombre() {
		return nombre;  
	}
	public String getDireccion() {
		return direccion;  
	}
	public int getMaxArticulos() {
		return max_Articulos;  
	}
	public int getMaxVendedores() {
		return max_Vendedores;  
	}
	public ArrayList<Articulo> getArtTienda() {
		return artTienda;
	}
	public ArrayList<Vendedor> getVendTienda() {
		return vendTienda;
	}

	// Setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public void setMaxArticulos(int numArticulos) {
		this.max_Articulos = numArticulos;
	}
	public void setMaxVendedores(int numVendedores) {
		this.max_Vendedores = numVendedores;
	}


	public String toString() {
		String str = "\nFICHA DE LA TIENDA" + "\n";
		str = str +  "------------------------------------------------" + "\n";
		str = str +  "Nombre    : " + this.nombre+"\n";
		str = str +  "Dirección : " + this.direccion+"\n";
		str = str +  "Vendedores: " + this.vendTienda.size() + "/" + this.max_Vendedores+"\n";
		str = str +  "Articulos : " + this.artTienda.size() + "/" + this.max_Articulos+"\n";
		str = str +  "------------------------------------------------" + "\n";

		return str;
	} // end public String toString()

	/**
	 * Contratar vendedor: Incorpora el objeto vendedor  a la lista de vendedores de la tienda
	 * sólo si no existe previamente (mismo número de matricula).
	 * @param v1 Vendedor a contratar
	 * @return true : operación OK, false : El vendedor ya existe
	 */
	public boolean Contratar(Vendedor v1) {
		boolean result = true;
		// Comprobar que no esté contratado previamente ( por el número de matrícula)
		for (int i = 0; i< this.vendTienda.size();i++) {
			if (this.vendTienda.get(i).getMatricula() == v1.getMatricula()) {
				result = false;
				i = this.vendTienda.size();
			}
		}
		if (result) this.vendTienda.add(v1);
		return result;
	} // end public boolean Contratar(Vendedor v1)

	/**
	 * aCatalogo: Incorpora el objeto Articulo  a la lista de Articulos de la tienda
	 * sólo si no existe previamente (mismo número de matricula).
	 * @param a1 Articulo a incorporar
	 * @return true : operación OK, false : Ya existe
	 */
	public boolean aCatalogo(Articulo a1) {

		boolean result = true;
		// Comprobar que no esté en stock  ( por el nombre)
		for (int i = 0; i< this.artTienda.size();i++) {
			if (this.artTienda.get(i).getNombre().equals(a1.getNombre())) {
				result = false;
				i = this.artTienda.size();
			}
		}
		if (result) this.artTienda.add(a1);
		return result;
	} // end public boolean aCatalogo(Articulo a1)

} // end class Tienda
