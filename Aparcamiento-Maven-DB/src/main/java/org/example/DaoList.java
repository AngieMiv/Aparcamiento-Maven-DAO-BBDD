package org.example;
import java.util.ArrayList;

/**
 * Defines a contract for data access operations on a collections of objects of type {@code T}.
 * 
 * <p>An interface in Java specifies a set of methods that implementing classes must define,
 * ensuring a consisten API while allowing flexibiliti in the actual implementation.
 * This interface follows de DAO (Data Access Object) pattern, providing basic CRUD operations.</p>
 * 
 * @param <T> the type of objects managed by this DAO

 * */
// Interfaz DaoList, esta interfaz hace esquemas de las funciones que pueden implementar otras clases
// T: Type parameter, indica que es de cualquier tipo, ANY TYPE

public interface DaoList<T> {
	
	/**
	 * Retrieves all elements from the data source.
	 * 
	 * @return an {@code ArrayList} containing all objects of type {@code T}.
	 */
	public ArrayList<T> findAll();
	
	/**
	 * Finds a especific element based on the given key.
	 * 
	 * @param key they unique identifier of the element to retrieve.
	 * @return the corresponding object of type {@code T}, or {@code null} if not found.
	 */
	public T findOne(String key);
	
	/**
	 * Inserts a new element into the data source.
	 * 
	 * @param t the object to insert
	 * @return {@code true} if the operation was successful, {@code false} otherwise.
	 */
	public boolean insertOne(T t); 
	
	/**
	 * Deletes an element identified by the given key.
	 * 
	 * @param key the unique identifier of the element to delete
	 * @return {@code true} if the deletion was successful, {@code false} otherwise.
	 */
	public boolean deleteOne(String key);
	
	/**
	 * Updates an existing element identified by the given key
	 * 
	 * @param key the unique identifier of the element to update
	 * @param t the updated object
	 * @return {@code true} if the update was successful, {@code false} otherwise.
	 */
	public boolean updateOne(String key,T t);
		
} // interface DaoList