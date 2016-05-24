package by.rdepam.sax_and_stax.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.rdepam.sax_and_stax.domain.MenuItem;
import by.rdepam.sax_and_stax.service.MenuViewer;
import by.rdepam.sax_and_stax.service.SAXHandler;
import by.rdepam.sax_and_stax.service.StAXHandler;

public class Main {
	public static void main(String[] args) throws SAXException, IOException, XMLStreamException {
		XMLReader reader = XMLReaderFactory.createXMLReader();
		SAXHandler handler = new SAXHandler();
		reader.setContentHandler(handler);
		reader.parse(new InputSource("src/main/resources/menu.xml"));
		List<MenuItem> items = handler.getMenuItems();
		System.out.println("SAX found " + items.size() + " menu items:");
		MenuViewer viewer = new MenuViewer();
		for (MenuItem it : items) {
			System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println(viewer.show(it));
		}
		
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		InputStream input = new FileInputStream("src/main/resources/menu.xml");
		XMLStreamReader reader2 = inputFactory.createXMLStreamReader(input);
		StAXHandler handler2 = new StAXHandler();
		handler2.parse(reader2);
		items = handler2.getMenuItems();
		System.out.println("StAX found " + items.size() + " menu items:");
		viewer = new MenuViewer();
		for (MenuItem it : items) {
			System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println(viewer.show(it));
		}
	}
}
