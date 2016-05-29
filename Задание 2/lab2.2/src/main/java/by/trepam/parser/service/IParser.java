package by.trepam.parser.service;

import java.io.File;

import by.trepam.parser.domain.DOMDocument;

public interface IParser {
	
	public void parse(File file);

	public DOMDocument getDocument();

}
