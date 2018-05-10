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
import utils.Constants;

/**
 * Implementation of DataHandler for MongoDB database
 * 
 * @author danielsantil
 *
 * @param <T> See documentation at {@link DataHandler}
 */
public class MongoDbHandler<T> extends DataHandlerBase<T> {

	private MongoCollection<Document> collection;

	public MongoDbHandler(Class<T> clazz) {
		super(clazz, Constants.MONGO_PROPS_FILE);
		this.collection = MongoClients.create(props.getString("DB_URL"))
				.getDatabase(props.getString("DB_NAME")).getCollection(collectionName);
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
		return doc != null ? new Gson().fromJson(doc.toJson(), type) : null;
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
