package by.trepam.dbcp.properties;

import java.io.IOException;
import java.util.HashMap;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class DBConnectionProperties{
	private static DBConnectionProperties properties = new DBConnectionProperties();
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	private static int numberOfConnections;
	
	public static DBConnectionProperties getInstance(){
		XMLReader reader;
		try {
			reader = XMLReaderFactory.createXMLReader();
			PropertiesReader handler = new PropertiesReader();
			reader.setContentHandler(handler);
			InputSource i = new InputSource("src/main/resources/db_properties.xml");
			reader.parse(i);
			HashMap<String,String> result = handler.getProperties();
			driver = result.get(PropertiesConstant.DRIVER);
			url = result.get(PropertiesConstant.URL);
			user = result.get(PropertiesConstant.USER);
			password = result.get(PropertiesConstant.PASSWORD);
			numberOfConnections = new Integer(result.get(PropertiesConstant.CONNECTIONS_NUMBER));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	public String getDriver() {
		return driver;
	}

	public String getURL() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
			return password;
	}

	public int getNumberOfConnections() {
		return numberOfConnections;
	}

}
