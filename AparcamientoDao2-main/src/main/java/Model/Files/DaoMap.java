package Model.Files;

import java.util.HashMap;

/**
 * Interfaz con operaciones CRUD usando HashMap<Integer, T>.
 * @param <T>
 */
public interface DaoMap<T> {

	public HashMap<Integer,T> findAll();
	public T findOne(Integer key);
	public boolean insertOne(T t);
	public boolean deleteOne(Integer key);
	public boolean updateOne(Integer key,T t);


} // interface Dao