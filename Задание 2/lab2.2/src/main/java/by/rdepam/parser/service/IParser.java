package by.rdepam.parser.service;

import java.io.File;

import by.rdepam.parser.domain.DOMDocument;

public interface IParser {
	
	public void parse(File file);

	public DOMDocument getDocument();

}
