package by.epam.parsing_with_xerces.service;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import by.epam.parsing_with_xerces.domain.Description;
import by.epam.parsing_with_xerces.domain.Dish;
import by.epam.parsing_with_xerces.domain.ListItem;
import by.epam.parsing_with_xerces.domain.MenuItem;
import by.epam.parsing_with_xerces.domain.Portion;

public class DOMXercesParser {
	private List<MenuItem> items;

	public void parse(Element docEle) {
		NodeList list = docEle.getElementsByTagName("menu-item");
		items = new ArrayList<MenuItem>();
		if (list != null && list.getLength() > 0) {
			for (int i = 0; i < list.getLength(); i++) {
				Element el = (Element) list.item(i);
				MenuItem item = new MenuItem();
				NodeList list2 = el.getElementsByTagName("dish");
				List<Dish> dishs = new ArrayList<Dish>();
				if (list2 != null && list2.getLength() > 0) {
					for (int j = 0; j < list2.getLength(); j++) {
						Element elem = (Element) list2.item(j);
						Dish e = getDish(elem);
						dishs.add(e);
					}
				}

				String id = el.getAttribute("id");
				item.setID(id);
				item.setDishs(dishs);
				items.add(item);
			}
		}
	}

	public List<MenuItem> getMenuItems() {
		return items;
	}

	private Dish getDish(Element element) {
		String name = getTextValue(element, "name");
		String photo = getTextValue(element, "photo");
		NodeList list = element.getElementsByTagName("description");
		List<Description> des = new ArrayList<Description>();
		if (list != null && list.getLength() > 0) {
			for (int j = 0; j < list.getLength(); j++) {
				Element elem = (Element) list.item(j);
				Description d = getDescription(elem);
				des.add(d);
			}
		}
		list = element.getElementsByTagName("portion");
		List<Portion> portion = new ArrayList<Portion>();
		if (list != null && list.getLength() > 0) {
			for (int j = 0; j < list.getLength(); j++) {
				Element elem = (Element) list.item(j);
				Portion s = getPortion(elem);
				portion.add(s);
			}
		}
		String id = element.getAttribute("id");
		Dish dish = new Dish(name, id, photo, des, portion);

		return dish;
	}

	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element) nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}
		return textVal;
	}

	private Description getDescription(Element element) {
		String general = getTextValue(element, "general");
		NodeList list = element.getElementsByTagName("list-item");
		List<ListItem> listit = new ArrayList<ListItem>();
		if (list != null && list.getLength() > 0) {
			for (int j = 0; j < list.getLength(); j++) {
				Element elem = (Element) list.item(j);
				ListItem l = new ListItem(getTextValue(elem, "text"), getTextValue(elem, "price"));
				listit.add(l);
			}
		}
		Description des = new Description(general, listit);

		return des;
	}

	private Portion getPortion(Element element) {
		String amount = getTextValue(element, "amount");
		NodeList list = element.getElementsByTagName("weight");
		List<Integer> w = new ArrayList<Integer>();
		if (list != null && list.getLength() > 0) {
			for (int j = 0; j < list.getLength(); j++) {
				Element elem = (Element) list.item(j);
				Integer l = Integer.parseInt(elem.getFirstChild().getNodeValue());
				w.add(l);
			}
		}
		Portion port = new Portion(amount, w);
		return port;
	}
}
