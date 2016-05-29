package by.trepam.factory;

import by.trepam.parser.service.DOMParser;
import by.trepam.parser.service.IParser;

public class DOMFactory {
	private static final DOMFactory factory = new DOMFactory();
	
	public static DOMFactory getInstance(){
		return factory;
	}
	
	public IParser getParser(){
		return new DOMParser();
	}

}
