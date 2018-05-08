package utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Props {
	private static final String BUNDLE_NAME = "data.mongo.conf";

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private Props() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return key + " not found";
		}
	}
}
