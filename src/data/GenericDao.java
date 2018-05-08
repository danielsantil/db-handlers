package data;

import java.util.List;

public class GenericDao<T> {

	private DataHandler<T> db;

	public GenericDao(Class<T> clazz) {
		db = new MongoDbHandler<>(clazz);
	}

	public void insert(T emp) {
		db.insert(emp);
	}

	public void insert(List<T> emps) {
		db.insert(emps);
	}

	public T getFirst() {
		return db.readFirst();
	}

	public List<T> getAll() {
		return db.readAll();
	}

}
