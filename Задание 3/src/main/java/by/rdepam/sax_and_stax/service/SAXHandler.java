package by.rdepam.sax_and_stax.service;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.rdepam.sax_and_stax.domain.Description;
import by.rdepam.sax_and_stax.domain.Dish;
import by.rdepam.sax_and_stax.domain.ListItem;
import by.rdepam.sax_and_stax.domain.MenuConstants;
import by.rdepam.sax_and_stax.domain.MenuItem;
import by.rdepam.sax_and_stax.domain.Portion;

public class SAXHandler extends DefaultHandler {
	private List<MenuItem> menuItems;
	private MenuItem item;
	private Dish dish;
	private Description description;
	private String currentElement;
	private ListItem listItem;
	private Portion portion;

	public SAXHandler() {
		menuItems = new ArrayList<MenuItem>();
		currentElement = new String();
		dish = new Dish();
		item = new MenuItem();
	}

	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
		if (MenuConstants.TAG_MENU_ITEM.equals(localName)) {
			item = new MenuItem();
			int len = atts.getLength();
			for (int i = 0; i < len; i++) {
				if (atts.getLocalName(i) == MenuConstants.TAG_NAME) {
					item.setName(atts.getValue(i));
				}
				if (atts.getLocalName(i) == MenuConstants.TAG_ID) {
					item.setID(atts.getValue(i));
				}
			}
		}
		if (MenuConstants.TAG_DISH.equals(localName)) {
			dish = new Dish();
			dish.setID(atts.getValue(0));
		}
		if (MenuConstants.TAG_DESCRIPTION.equals(localName)) {
			description = new Description();
		}
		if (MenuConstants.TAG_LIST_ITEM.equals(localName)) {
			listItem = new ListItem();
			if (atts != null && atts.getLength() > 0) {
				listItem.setNumber(atts.getValue(0));
			}
		}
		if (MenuConstants.TAG_PORTION.equals(localName)) {
			portion = new Portion();
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		currentElement = new String(ch, start, length);
	}

	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		if (MenuConstants.TAG_MENU_ITEM.equals(localName)) {
			menuItems.add(item);
			item = null;
		}
		if (MenuConstants.TAG_NAME.equals(localName)) {
			dish.setName(currentElement);
			currentElement = null;
		}
		if (MenuConstants.TAG_DISH.equals(localName)) {
			item.addDish(dish);
			dish = null;
		}
		if (MenuConstants.TAG_PHOTO.equals(localName)) {
			dish.addPhoto(currentElement);
			currentElement = null;
		}
		if (MenuConstants.TAG_DESCRIPTION.equals(localName)) {
			dish.addDescription(description);
			description = null;
		}
		if (MenuConstants.TAG_LIST_ITEM.equals(localName)) {
			description.addListItem(listItem);
			listItem = null;
		}
		if (MenuConstants.TAG_TEXT.equals(localName)) {
			listItem.setText(currentElement);
			currentElement = null;
		}
		if (MenuConstants.TAG_GENERAL.equals(localName)) {
			description.setGeneral(currentElement);
			currentElement = null;
		}
		if (MenuConstants.TAG_PORTION.equals(localName)) {
			dish.setPortion(portion);
			portion = null;
		}
		if (MenuConstants.TAG_PRICE.equals(localName)) {
			listItem.setPrice(currentElement);
			currentElement = null;
		}
		if (MenuConstants.TAG_AMOUNT.equals(localName)) {
			portion.setAmount(currentElement);
			currentElement = null;
		}
		if (MenuConstants.TAG_WEIGHT.equals(localName)) {
			portion.setWeight(currentElement);
			currentElement = null;
		}
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

}
