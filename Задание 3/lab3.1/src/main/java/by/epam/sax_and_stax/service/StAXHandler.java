package by.epam.sax_and_stax.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.epam.sax_and_stax.domain.Description;
import by.epam.sax_and_stax.domain.Dish;
import by.epam.sax_and_stax.domain.ListItem;
import by.epam.sax_and_stax.domain.MenuConstants;
import by.epam.sax_and_stax.domain.MenuItem;
import by.epam.sax_and_stax.domain.Portion;

public class StAXHandler {
	private List<MenuItem> menuItems;
	private MenuItem item;
	private Dish dish;
	private Description description;
	private String currentElement;
	private ListItem listItem;
	private Portion portion;
	
	public void parse(XMLStreamReader reader) throws XMLStreamException {
		menuItems=new ArrayList<MenuItem>();
		while(reader.hasNext()){
			int type = reader.next();
			switch(type){
			case XMLStreamConstants.START_ELEMENT:
			{
				String localName = reader.getLocalName();
				if (MenuConstants.TAG_MENU_ITEM.equals(localName)) {
					item = new MenuItem();
					for (int i = 0; i < 2; i++) {
						if (reader.getAttributeLocalName(i) == MenuConstants.TAG_NAME) {
							item.setName(reader.getAttributeValue(i));
						}
						if (reader.getAttributeLocalName(i) == MenuConstants.TAG_ID) {
							item.setID(reader.getAttributeValue(i));
						}
					}
				}
				if (MenuConstants.TAG_DISH.equals(localName)) {
					dish = new Dish();
					dish.setID(reader.getAttributeValue(0));
				}
				if (MenuConstants.TAG_DESCRIPTION.equals(localName)) {
					description = new Description();
				}
				if (MenuConstants.TAG_LIST_ITEM.equals(localName)) {
					listItem = new ListItem();
					listItem.setNumber(reader.getAttributeValue(0));
				}
				if (MenuConstants.TAG_PORTION.equals(localName)) {
					portion = new Portion();
				}
			}break;
			case XMLStreamConstants.CHARACTERS:{
				currentElement = reader.getText().trim();
			}break;
			case XMLStreamConstants.END_ELEMENT:{
				String localName = reader.getLocalName();
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
			}break;
			}
		}
		
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

}
