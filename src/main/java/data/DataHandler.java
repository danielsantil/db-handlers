package data;

import java.util.List;

/**
 * Interface for basic database operations
 * 
 * @author danielsantil
 * @param <T> Generic type representing the record to be processed
 */
public interface DataHandler<T> {

	/**
	 * Inserts a single record
	 * 
	 * @param doc Object to be inserted
	 */
	void insert(T doc);

	/**
	 * Inserts multiple records
	 * 
	 * @param docs List of objects to be inserted
	 */
	void insert(List<T> docs);

	/**
	 * Retrieves the first record
	 * 
	 * @return Object
	 */
	T readFirst();

	/**
	 * Retrieves all records
	 * 
	 * @return List of objects
	 */
	List<T> readAll();

}