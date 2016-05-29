package by.trepam.news.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import by.trepam.news.domain.Catalog;

public class NewsJAXBMarshaller {
	public void marshal(Catalog list) throws JAXBException, FileNotFoundException{
		JAXBContext context = JAXBContext.newInstance(Catalog.class);
		Marshaller m = context.createMarshaller();
		
		m.marshal(list, new FileOutputStream("src/main/resources/new-news.xml"));
		m.marshal(list, System.out);
	}
}
