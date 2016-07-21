package by.trepam.like_it.connection_pool.util;

import java.util.ResourceBundle;

public class DBConnectionProperty{
	private final static DBConnectionProperty property = new DBConnectionProperty();
	private ResourceBundle bundle = ResourceBundle.getBundle("db");
	
	public static DBConnectionProperty getInstance(){		
		return property;
	}

	public String getValue(String key) {
		return bundle.getString(key);
	}
}
