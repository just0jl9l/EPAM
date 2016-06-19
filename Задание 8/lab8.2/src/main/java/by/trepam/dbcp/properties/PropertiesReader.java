package by.trepam.dbcp.properties;

import java.util.HashMap;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class PropertiesReader extends DefaultHandler {
	private String currentElement;
	private HashMap<String,String> elements;
	
	public PropertiesReader(){
		elements = new HashMap<String,String>();
	}
	
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		currentElement = new String(ch, start, length);
	}

	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		if (PropertiesConstant.DRIVER.equals(localName)) {
			elements.put(PropertiesConstant.DRIVER,currentElement);
			currentElement = null;
		}
		if (PropertiesConstant.URL.equals(localName)) {
			elements.put(PropertiesConstant.URL,currentElement);
			currentElement = null;
		}

		if (PropertiesConstant.USER.equals(localName)) {
			elements.put(PropertiesConstant.USER,currentElement);
			currentElement = null;
		}
		if (PropertiesConstant.PASSWORD.equals(localName)) {
			elements.put(PropertiesConstant.PASSWORD,currentElement);
			currentElement = null;
		}
		if (PropertiesConstant.CONNECTIONS_NUMBER.equals(localName)) {
			elements.put(PropertiesConstant.CONNECTIONS_NUMBER,currentElement);
			currentElement = null;
		}
	}

	public HashMap<String,String> getProperties() {
		return elements;
	}
}
