package by.rdepam.parsing_with_xerces.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import domain.Description;
import domain.Dish;
import domain.ListItem;
import domain.MenuItem;
import domain.Portion;

public class Main {
		
		public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docbilder = factory.newDocumentBuilder();
			Document document = docbilder.parse("src/main/resources/menu.xml");
			Element docEle = document.getDocumentElement();
			NodeList list = docEle.getElementsByTagName("menu-item");
			List<MenuItem> items=new ArrayList<MenuItem>();
			if(list != null && list.getLength() > 0) {
				for(int i = 0; i < list.getLength(); i++) {
					Element el = (Element)list.item(i);
					MenuItem item=new MenuItem();
					NodeList list2 = el.getElementsByTagName("dish");
					List<Dish> dishs=new ArrayList<Dish>();
					if(list2 != null && list2.getLength() > 0) {
						for(int j = 0; j < list2.getLength(); j++) {
							Element elem = (Element)list2.item(j);
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
			System.out.println("There is " + items.size() + " menu items:");
			
			Iterator it = items.iterator();
			while(it.hasNext()) {
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println(it.next().toString());
			}
		}
		
		private static Dish getDish(Element element) {
			String name = getTextValue(element,"name");
			String photo = getTextValue(element,"photo");
			NodeList list = element.getElementsByTagName("description");
			List<Description> des=new ArrayList<Description>();
			if(list != null && list.getLength() > 0) {
				for(int j = 0; j < list.getLength(); j++) {
					Element elem = (Element)list.item(j);
					Description d = getDescription(elem);
					des.add(d);
				}
			}				
			list = element.getElementsByTagName("portion");
			List<Portion> portion=new ArrayList<Portion>();
			if(list != null && list.getLength() > 0) {
				for(int j = 0; j < list.getLength(); j++) {
					Element elem = (Element)list.item(j);
					Portion s = getPortion(elem);
					portion.add(s);
				}
			}	
			String id = element.getAttribute("id");			
			Dish dish = new Dish(name,id,photo,des,portion);
			
			return dish;
		}

		private static String getTextValue(Element ele, String tagName) {
			String textVal = null;
			NodeList nl = ele.getElementsByTagName(tagName);
			if(nl != null && nl.getLength() > 0) {
				Element el = (Element)nl.item(0);
				textVal = el.getFirstChild().getNodeValue();
			}	
			return textVal;
		}
	
		private static Description getDescription(Element element) {
			String general = getTextValue(element,"general");
			NodeList list = element.getElementsByTagName("list-item");
			List<ListItem> listit=new ArrayList<ListItem>();
			if(list != null && list.getLength() > 0) {
				for(int j = 0; j < list.getLength(); j++) {
					Element elem = (Element)list.item(j);
					ListItem l = new ListItem(getTextValue(elem,"text"),getTextValue(elem,"price"));
					listit.add(l);
				}
			}
			Description des = new Description(general,listit);
			
			return des;
		}
	
		private static Portion getPortion(Element element) {
			String amount = getTextValue(element,"amount");
			NodeList list = element.getElementsByTagName("weight");
			List<Integer> w=new ArrayList<Integer>();
			if(list != null && list.getLength() > 0) {
				for(int j = 0; j < list.getLength(); j++) {
					Element elem = (Element)list.item(j);
					Integer l=Integer.parseInt(elem.getFirstChild().getNodeValue());
					w.add(l);
				}
			}
			Portion port = new Portion(amount,w);			
			return port;
		}
}
