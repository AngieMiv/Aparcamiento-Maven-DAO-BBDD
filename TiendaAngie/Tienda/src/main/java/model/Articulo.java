package model;
import com.mysql.cj.util.StringUtils;

/**
 * Clase genérica Articulo (Super clase)
 * */
public class Articulo {
     // -- Atributos
    public String nombre;
    public String marca;
    public String tipo;			// Tipo de Artículo
    private Integer stock;
    private Double precio;
    
    // Constructor
    public Articulo() { }
	public Articulo(String nombre, String marca, String tipo, Integer stock,Double precio) {
		this.nombre = nombre;
		this.marca = marca;
		this.tipo = tipo;
		this.stock = stock;
		this.precio = precio;
	}
	
    // Getters
	public String getNombre() {
		return nombre;
	}
	public String getMarca() {
		return marca;
	}
	public String getTipo() {
		return tipo;
	}
	public Double getPrecio() {
		return precio;
	}
	
	// Setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}

} // Class Articulo