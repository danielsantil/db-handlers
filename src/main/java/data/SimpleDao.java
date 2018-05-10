package data;

import java.util.List;

import mongo.MongoDbHandler;

/**
 * Generic class to execute basic database operations
 * @author DS007463
 *
 * @param <T>
 */
public class SimpleDao<T> {

	private DataHandler<T> db;

	public SimpleDao(Class<T> clazz) {
		db = new MongoDbHandler<>(clazz);
	}

	public void insert(T record) {
		db.insert(record);
	}

	public void insert(List<T> records) {
		db.insert(records);
	}

	public T getFirst() {
		return db.readFirst();
	}

	public List<T> getAll() {
		return db.readAll();
	}

}
