package by.trepam.parser.domain;
import java.util.ArrayList;
import java.util.List;

public class DOMNodeList {
	private List<DOMNode> list;
	
	public DOMNodeList(){
		list = new ArrayList<DOMNode>();
	}

	public DOMNodeList getByTagName(String name) {
		DOMNodeList newlist = new DOMNodeList();
		for(DOMNode el: list){
			if(el.getElementType()==0 & name.equals(el.getNodeValue())){
				newlist.add(el);
			}
		}
		return newlist;
	}

	public DOMText getTextElement() {
		for(DOMNode el: list){
			if(el.getElementType()==2){
				return (DOMText) el;
			}
		}
		return null;
	}

	public void add(DOMNode n) {
		list.add(n);		
	}

	public DOMElement getFirst() {
		if(list.size()>0){
			return (DOMElement) list.get(0);
		}else{
			return null;
		}
	}

	public int size() {
		return list.size();
	}

	public DOMNode get(int i) {
		return list.get(i);
	}
	
}
