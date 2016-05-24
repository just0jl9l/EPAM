package by.rdepam.parser.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import by.rdepam.parser.domain.DOMAttribute;
import by.rdepam.parser.domain.DOMAttributeList;
import by.rdepam.parser.domain.DOMConstants;
import by.rdepam.parser.domain.DOMDocument;
import by.rdepam.parser.domain.DOMElement;
import by.rdepam.parser.domain.DOMNodeList;
import by.rdepam.parser.domain.DOMText;

public class DOMParser implements IParser{
	private DOMDocument document;

	public void parse(File file) {
		FileInputStream inFile;
		try {
			inFile = new FileInputStream(file);
			byte[] str;
			str = new byte[inFile.available()];			
			inFile.read(str);
			String readDocument = new String(str);
			document = new DOMDocument();
			readDocument = remove(readDocument);
			document.setDocumentElement(findElements(readDocument, null).getFirst());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public DOMDocument getDocument() {
		return document;
	}

	private String remove(String text) {
		while (text.indexOf(DOMConstants.BEGINING_OF_XML_DECLARATION) > -1) {
			text = text.substring(0, text.indexOf(DOMConstants.BEGINING_OF_XML_DECLARATION))
					+ text.substring(text.indexOf(DOMConstants.ENDING_OF_XML_DECLARATION) + 2);
		}
		while (text.indexOf(DOMConstants.BEGINING_OF_XML_COMMENT) > -1) {
			text = text.substring(0, text.indexOf(DOMConstants.BEGINING_OF_XML_COMMENT))
					+ text.substring(text.indexOf(DOMConstants.ENDING_OF_XML_COMMENT) + 3);
		}
		while (text.indexOf(DOMConstants.BEGINING_OF_XML_DOCTYPE) > -1) {
			int positionOfOpenDoctypesTag = text.indexOf(DOMConstants.BEGINING_OF_XML_DOCTYPE);
			int numberOfOpenDoctypesTags = 1;
			int len = text.length();
			char[] chars = text.toCharArray();
			for (int i = 0; i < len; i++) {
				if (chars[i] == DOMConstants.BEGINING_OF_XML_TAG && positionOfOpenDoctypesTag < i) {
					numberOfOpenDoctypesTags++;
				}
				if (chars[i] == DOMConstants.ENDING_OF_XML_TAG && positionOfOpenDoctypesTag <= i) {
					numberOfOpenDoctypesTags--;
				}
				if (numberOfOpenDoctypesTags == 0) {
					numberOfOpenDoctypesTags = i;
					text = text.substring(0, positionOfOpenDoctypesTag) + text.substring(numberOfOpenDoctypesTags + 1);
					break;
				}
			}
		}
		return text;
	}

	private DOMNodeList findElements(String mainElement, DOMElement parent) {
		DOMNodeList nodes = new DOMNodeList();
		int openingTagPosition = mainElement.indexOf(DOMConstants.BEGINING_OF_XML_TAG);
		if (openingTagPosition < 0) {
			DOMText text = new DOMText(mainElement.trim(), parent);
			nodes.add(text);
		}
		while (openingTagPosition > -1) {
			int numberOfOpenTags = 0;
			int len = mainElement.length();
			char[] chars = mainElement.toCharArray();
			for (int i = openingTagPosition; i < len - 1; i++) {
				if (chars[i] == DOMConstants.BEGINING_OF_XML_TAG) {
					if (chars[i + 1] == DOMConstants.SLASH) {
						numberOfOpenTags--;
					} else {
						numberOfOpenTags++;
					}
				}
				if (numberOfOpenTags == 0) {
					int closingTagPosition = i;
					while (true) {
						if (chars[i] == DOMConstants.ENDING_OF_XML_TAG) {
							String curel = mainElement.substring(openingTagPosition, i + 1);
							mainElement = mainElement.substring(i + 1);
							String tag = curel.substring(curel.indexOf(DOMConstants.BEGINING_OF_XML_TAG) + 1,
									curel.indexOf(DOMConstants.ENDING_OF_XML_TAG));
							int spase = tag.indexOf(DOMConstants.SPACE);
							DOMElement element;
							if (!(spase > -1)) {
								element = new DOMElement(tag, parent);
							} else {
								element = new DOMElement(tag.substring(0, spase), parent);
								element.setAttributes(findAttributes(tag.substring(spase + 1), element));
							}
							String substr = curel.substring(curel.indexOf(DOMConstants.ENDING_OF_XML_TAG) + 1,
									closingTagPosition - openingTagPosition);
							element.setChildren(findElements(substr, element));
							nodes.add(element);
							break;
						} else {
							i++;
						}
					}
					break;
				}
			}
			openingTagPosition = mainElement.indexOf(DOMConstants.BEGINING_OF_XML_TAG);
		}
		return nodes;
	}

	private DOMAttributeList findAttributes(String str, DOMElement owner) {
		DOMAttributeList attrs = new DOMAttributeList();
		int eq = str.indexOf(DOMConstants.EQUAL_SIGN);
		while (eq > -1) {
			DOMAttribute attr = new DOMAttribute(str.substring(0, eq).trim(), owner);
			eq = str.indexOf(DOMConstants.DOUBLE_QUOTE);
			if (eq < 0) {
				eq = str.indexOf(DOMConstants.SINGLE_QUOTE);
			}
			str = str.substring(eq + 1);
			eq = str.indexOf(DOMConstants.DOUBLE_QUOTE);
			if (eq < 0) {
				eq = str.indexOf(DOMConstants.SINGLE_QUOTE);
			}
			attr.setValue(str.substring(0, eq));
			attrs.add(attr);
			str = str.substring(eq + 1);
			eq = str.indexOf(DOMConstants.EQUAL_SIGN);
		}
		return attrs;
	}
}
