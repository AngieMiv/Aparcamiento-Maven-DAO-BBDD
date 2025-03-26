package org.example;
import java.util.ArrayList;

/**
 * Defines a contract for data acces operations on a collections of objects of type{@code T}.
 * 
 * <p>An interface in Java specifies a set of methods that implementing classes must define,
 * ensuring a consisten API while allowing flexibiliti in the actual implementation.
 * This interface follows de DAO (Data Access Object) pattern, providing basic CRUD operations.
 * 
 * @param <T> the type of objects managed by this DAO

 * */
// Interfaz DaoList, esta interfaz hace esquemas de las funciones que pueden implementar otras clases
//T: Type parameter, indica que es de cualquier tipo, ANY TYPE

public interface DaoList<T> {
	
	/**
	 * Retrieves all elements from the data source.
	 * @return an {@code ArrayList} containing all objects of type {@code T}.
	 */
	public ArrayList<T> findAll();
	
	
	public T findOne(String key);
	
	public boolean insertOne(T t); 
	
	public boolean deleteOne(String key);
	
	public boolean updateOne(String key,T t);
		
} // interface DaoList