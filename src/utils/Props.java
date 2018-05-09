package utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Props {

	private static String BUNDLE_NAME;
	private static ResourceBundle RESOURCE_BUNDLE;

	public Props(String bundleName) {
		BUNDLE_NAME = bundleName;
		RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	}

	public String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return key + " not found";
		}
	}
}
