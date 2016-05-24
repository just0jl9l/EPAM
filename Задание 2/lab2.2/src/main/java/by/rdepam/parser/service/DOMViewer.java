package by.rdepam.parser.service;

import by.rdepam.parser.domain.DOMAttribute;
import by.rdepam.parser.domain.DOMAttributeList;
import by.rdepam.parser.domain.DOMConstants;
import by.rdepam.parser.domain.DOMDocument;
import by.rdepam.parser.domain.DOMElement;
import by.rdepam.parser.domain.DOMNode;
import by.rdepam.parser.domain.DOMNodeList;

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
			str+=attribute.getName()+DOMConstants.EQUAL_SIGN+attribute.getValue();
		}
		if(node.getElementType()==DOMConstants.TEXT){
			str+=node.getNodeValue();
		}
		return str;
	}

}
