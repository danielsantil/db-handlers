package data;

import java.util.List;

import mongo.MongoDbHandler;

/**
 * Generic class to execute basic database operations
 * 
 * @author danielsantil
 *
 * @param <T> Generic type representing the record to be processed
 */
public class SimpleDao<T> {

	private DataHandler<T> db;

	/**
	 * Constructor
	 * 
	 * @param clazz Exact class of generic type passed during instantiation
	 */
	public SimpleDao(Class<T> clazz) {
		db = new MongoDbHandler<>(clazz);
	}

	/**
	 * Inserts a record
	 * 
	 * @param record Object
	 */
	public void insert(T record) {
		db.insert(record);
	}

	/**
	 * Inserts multiple records
	 * 
	 * @param records List of objects
	 */
	public void insert(List<T> records) {
		db.insert(records);
	}

	/**
	 * Retrieves the first record
	 * 
	 * @return Object
	 */
	public T getFirst() {
		return db.readFirst();
	}

	/**
	 * Retrieves all records
	 * 
	 * @return List of objects
	 */
	public List<T> getAll() {
		return db.readAll();
	}

}
