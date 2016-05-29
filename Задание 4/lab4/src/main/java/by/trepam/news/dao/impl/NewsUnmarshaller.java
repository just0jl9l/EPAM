package by.trepam.news.dao.impl;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import by.trepam.news.domain.Catalog;

public class NewsUnmarshaller {	
	private Catalog newsList;
	
	public void unmarsh(String filePath) throws JAXBException{
		File file = new File(filePath);
		JAXBContext jaxb = JAXBContext.newInstance(Catalog.class);
		Unmarshaller unmarsh= jaxb.createUnmarshaller();
		newsList = (Catalog) unmarsh.unmarshal(file);		
	}
	
	public Catalog getCatalog(){
		return newsList;
	}
	
}
