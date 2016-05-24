package by.rdepam.factory;

import by.rdepam.parser.service.DOMParser;
import by.rdepam.parser.service.IParser;

public class DOMFactory {
	private static final DOMFactory factory = new DOMFactory();
	
	public static DOMFactory getInstance(){
		return factory;
	}
	
	public IParser getParser(){
		return new DOMParser();
	}

}
