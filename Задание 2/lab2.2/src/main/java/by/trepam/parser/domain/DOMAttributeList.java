package by.trepam.parser.domain;
import java.util.ArrayList;
import java.util.List;

public class DOMAttributeList {
	private List<DOMAttribute> list;
	
	public DOMAttributeList(){
		list = new ArrayList<DOMAttribute>();
	}

	public int size() {
		return list.size();
	}

	public DOMAttribute get(String name) {
		for(DOMAttribute el: list){
			if(name.equals(el.getName())){
				return el;
			}
		}
		return null;
	}

	public void set(String name, String value) {
		for(DOMAttribute el: list){
			if(name.equals(el.getName())){
				el.setValue(value);
			}
		}
	}

	public void remove(String name) {
		for(DOMAttribute el: list){
			if(name.equals(el.getName())){
				list.remove(el);
			}
		}
	}

	public void add(DOMAttribute at) {
		list.add(at);
	}

	public DOMNode get(int i) {
		return list.get(i);
	}
	
	
}
