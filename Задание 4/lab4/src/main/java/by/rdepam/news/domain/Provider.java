package by.rdepam.news.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "provider")
@XmlAccessorType(XmlAccessType.FIELD)
public class Provider {
	@XmlElement(name = "author")
	private List<String> authors;

	public Provider() {
		authors = new ArrayList<String>();
	}

	public void setProviders(List<String> list) {
		this.authors = list;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void addAuthor(String string) {
		authors.add(string);

	}

	public String show() {
		String str = new String();
		int l = authors.size();
		for (int i = 0; i < l; i++) {
			str += "\n * author: " + authors.get(i);
		}
		return str;
	}

	public boolean hasAuthor(String s) {
		int l = authors.size();
		for (int i = 0; i < l; i++) {
			if(s.equals(authors.get(i))){
				return true;
			}
		}
		return false;
	}

}
