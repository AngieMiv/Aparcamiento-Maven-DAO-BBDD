package org.example;
import java.util.ArrayList;

// Interfaz DaoListNick, esta interfaz hace esquemas de las funciones que pueden implementar otras clases
// T: Type parameter, indica que es de cualquier tipo, ANY TYPE

public interface DaoListNick<T> {
	
	public ArrayList<T> findAll();
	
	public T findOne(String key);
	
	public boolean insertOne(T t); 
	
	public boolean deleteOne(String key);
	
	public boolean updateOne(String key,T t);
		
} // interface DaoListNick