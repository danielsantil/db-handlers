package data;

import java.util.List;

public interface DataHandler<T> {

	void insert(T doc);

	void insert(List<T> docs);

	T readFirst();

	List<T> readAll();

}