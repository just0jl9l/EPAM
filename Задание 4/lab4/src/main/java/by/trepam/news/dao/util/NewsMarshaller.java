package by.trepam.news.dao.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import by.trepam.news.domain.Catalog;

public class NewsMarshaller {
	public void marshal(Catalog list,String file) throws JAXBException, FileNotFoundException{
		JAXBContext context = JAXBContext.newInstance(Catalog.class);
		Marshaller m = context.createMarshaller();
		
		m.marshal(list, new FileOutputStream(file));
	}
}
