package mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import data.DataHandler;
import data.DataHandlerBase;

/**
 * Implementation of DataHandler for MongoDB database
 * @author DS007463
 *
 * @param <T> See documentation at {@link DataHandler}
 */
public class MongoDbHandler<T> extends DataHandlerBase<T> {

	private MongoCollection<Document> collection;
	private static final String MONGO_PROPERTIES = "mongo.mongo_conf";

	public MongoDbHandler(Class<T> clazz) {
		super(clazz, MONGO_PROPERTIES);
		this.collection = MongoClients.create(props.getString("DB_URL"))
				.getDatabase(props.getString("DB_NAME"))
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
