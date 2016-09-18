package by.trepam.like_it.dao.connection_pool.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

public class DBConnectionProperty {
	private final static DBConnectionProperty property = new DBConnectionProperty();
	private static StandardPBEStringEncryptor encryptor;
	private static Properties props;
	public static final String PASSWORD = "trololo";

	private DBConnectionProperty() {
		encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(PASSWORD);
		props = new EncryptableProperties(encryptor);
		try {
			InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
			props.load(stream);
		} catch (IOException e) {
			throw new RuntimeException("Can't load connection pool properties file", e);
		}

	}

	public static DBConnectionProperty getInstance() {
		return property;
	}

	public String getValue(String key) {
		return props.getProperty(key);
	}
}
