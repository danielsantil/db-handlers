package data;

import annotations.Table;
import utils.Props;

public abstract class DataHandlerBase<T> implements DataHandler<T> {

	protected final Class<T> type;
	protected String collectionName;
	protected Props props;

	public DataHandlerBase(Class<T> clazz, String propertiesFile) {
		this.props = new Props(propertiesFile);
		this.type = clazz;
		this.collectionName = type.getAnnotation(Table.class).value();
	}

}
