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
	private Gson gson;

	public MongoDbHandler(Class<T> clazz) {
		super(clazz, Constants.MONGO_PROPS_FILE);
		this.collection = MongoClients.create(props.getString("DB_URL"))
				.getDatabase(props.getString("DB_NAME")).getCollection(collectionName);
		this.gson = new Gson();
	}

	@Override
	public void insert(T obj) {
		String objJson = gson.toJson(obj);
		collection.insertOne(Document.parse(objJson));
	}

	@Override
	public void insert(List<T> objs) {
		List<Document> docs = new ArrayList<>();
		objs.forEach(obj -> docs.add(Document.parse(gson.toJson(obj))));
		collection.insertMany(docs);
	}

	@Override
	public T readFirst() {
		Document doc = collection.find().first();
		return doc != null ? gson.fromJson(doc.toJson(), this.type) : null;
	}

	@Override
	public List<T> readAll() {
		List<T> objs = new ArrayList<>();
		try (MongoCursor<Document> cursor = collection.find().iterator()) {
			while (cursor.hasNext()) {
				objs.add(gson.fromJson(cursor.next().toJson(), this.type));
			}
		}
		return objs;
	}

}
