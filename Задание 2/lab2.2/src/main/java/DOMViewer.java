
public class DOMViewer {

	public String show(DOMDocument document) {
		return viewNode(document.getDocumentElement());	
	}

	private String viewNode(DOMNode node) {
		String str=new String();
		if(node.getElementType()==0){		//DOMElement
			DOMElement element = (DOMElement) node;
			str+='<'+ element.getNodeValue();
			DOMAttributeList listAtt=element.getAttributes();
			int len = listAtt.size();
			for(int i=0;i<len;i++){
				str+=' '+viewNode(listAtt.get(i));
			}
			str+='>';
			DOMNodeList listCh=element.getChildNodes();
			len = listCh.size();
			for(int i=0;i<len;i++){
				str+=viewNode(listCh.get(i));
			}
			str+="</"+ element.getNodeValue()+'>';			
		}
		if(node.getElementType()==1){		//DOMAttribute
			DOMAttribute attribute = (DOMAttribute) node;
			str+=attribute.getName()+" = "+attribute.getValue();
		}
		if(node.getElementType()==2){		//DOMText
			str+=node.getNodeValue();
		}
		return str;
	}

}
