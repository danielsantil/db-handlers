package data;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import annotations.Table;
import utils.Props;

public class MongoDbHandler<T> implements DataHandler<T> {

	private MongoCollection<Document> collection;
	private final Class<T> type;

	public MongoDbHandler(Class<T> clazz) {
		this.type = clazz;
		String collectionName = type.getAnnotation(Table.class).value();

		this.collection = MongoClients.create(Props.getString("DB_URL"))
				.getDatabase(Props.getString("DB_NAME"))
				.getCollection(collectionName);
	}

	@Override
	public void insert(T obj) {
		String objJson = new Gson().toJson(obj);
		collection.insertOne(Document.parse(objJson));
	}

	@Override
	public void insert(List<T> objs) {
		List<Document> docs = new ArrayList<>();
		objs.forEach(obj -> {
			String objJson = new Gson().toJson(obj);
			docs.add(Document.parse(objJson));
		});

		collection.insertMany(docs);
	}

	@Override
	public T readFirst() {
		Document doc = collection.find().first();
		return new Gson().fromJson(doc.toJson(), type);
	}

	@Override
	public List<T> readAll() {
		List<T> objs = new ArrayList<>();

		List<Document> docs = new ArrayList<>();
		try (MongoCursor<Document> cursor = collection.find().iterator()) {
			while (cursor.hasNext()) {
				docs.add(cursor.next());
			}
		}

		docs.forEach(doc -> {
			objs.add(new Gson().fromJson(doc.toJson(), type));
		});
		return objs;
	}

}
