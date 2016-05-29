package by.epam.parsing_with_xerces.main;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import by.epam.parsing_with_xerces.domain.MenuItem;
import by.epam.parsing_with_xerces.service.DOMXercesParser;
import by.epam.parsing_with_xerces.service.MenuViewer;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docbilder = factory.newDocumentBuilder();
		Document document = docbilder.parse("src/main/resources/menu.xml");
		DOMXercesParser parser = new DOMXercesParser();
		parser.parse(document.getDocumentElement());
		List<MenuItem> items = parser.getMenuItems();
		MenuViewer viewer = new MenuViewer();
		System.out.println("There is " + items.size() + " menu items:");

		for (MenuItem it : items) {
			System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println(viewer.show(it));
		}
	}

}
