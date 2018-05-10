package data;

import annotations.Table;
import utils.Props;

/**
 * Abstract class which sets initial attributes values
 * 
 * @author danielsantil
 *
 * @param <T> Generic type representing the record to be processed
 */
public abstract class DataHandlerBase<T> implements DataHandler<T> {

	/**
	 * Class of the generic type received during instantiation. This is further used
	 * for deserialization.
	 */
	protected final Class<T> type;
	/**
	 * Name of the collection/table
	 */
	protected String collectionName;
	/**
	 * Name of properties file about implementation specifics
	 */
	protected Props props;

	/**
	 * Base constructor
	 * 
	 * @param clazz Exact class of generic type passed during instantiation
	 * @param propertiesFile Name of properties file to be used
	 */
	public DataHandlerBase(Class<T> clazz, String propertiesFile) {
		this.props = new Props(propertiesFile);
		this.type = clazz;
		String collectionName = type.getAnnotation(Table.class) != null
				? type.getAnnotation(Table.class).value()
				: type.getSimpleName();
		this.collectionName = collectionName;
	}

}
