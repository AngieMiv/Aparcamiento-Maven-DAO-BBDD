package org.example;
import java.util.ArrayList;

// Interfaz DaoList, en esta interfaz haremos los esquemas de las funciones que puede implementar otra clase

//T: Type parameter, indicates that that is ANY TYPE

public interface DaoList<T> {

		public ArrayList<T> findAll();
		public T findOne(String key);
		public boolean insertOne(T t); 
		public boolean deleteOne(String key);
		public boolean updateOne(String key,T t);
		
	
} // interface DaoList