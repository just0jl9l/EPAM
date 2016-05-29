package by.trepam.parser.service;

import by.trepam.parser.domain.DOMAttribute;
import by.trepam.parser.domain.DOMAttributeList;
import by.trepam.parser.domain.DOMConstants;
import by.trepam.parser.domain.DOMDocument;
import by.trepam.parser.domain.DOMElement;
import by.trepam.parser.domain.DOMNode;
import by.trepam.parser.domain.DOMNodeList;

public class DOMViewer {

	public String show(DOMDocument document) {
		return showNode(document.getDocumentElement());	
	}

	private String showNode(DOMNode node) {
		String str=new String();
		if(node.getElementType()==DOMConstants.ELEMENT){
			DOMElement element = (DOMElement) node;
			str+=DOMConstants.BEGINING_OF_XML_TAG+ element.getNodeValue();
			DOMAttributeList listAtt=element.getAttributes();
			int len = listAtt.size();
			for(int i=0;i<len;i++){
				str+=DOMConstants.SPACE+showNode(listAtt.get(i));
			}
			str+=DOMConstants.ENDING_OF_XML_TAG;
			DOMNodeList listCh=element.getChildNodes();
			len = listCh.size();
			for(int i=0;i<len;i++){
				str+=showNode(listCh.get(i));
			}
			str+=DOMConstants.BEGINING_OF_XML_CLOSING_TAG+ element.getNodeValue()+DOMConstants.ENDING_OF_XML_TAG;			
		}
		if(node.getElementType()==DOMConstants.ATTRIBUTE){
			DOMAttribute attribute = (DOMAttribute) node;
			str+=attribute.getName()+DOMConstants.EQUAL_SIGN+DOMConstants.DOUBLE_QUOTE+attribute.getValue()+DOMConstants.DOUBLE_QUOTE;
		}
		if(node.getElementType()==DOMConstants.TEXT){
			str+=node.getNodeValue();
		}
		return str;
	}

}
