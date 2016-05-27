package by.rdepam.news.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "subcategory")
@XmlAccessorType(XmlAccessType.FIELD)
public class Subcategory {
	@XmlAttribute(required = true)
	private String name;

	@XmlElement(name = "news")
	private List<News> news;
	
	public Subcategory(){
		news = new ArrayList<News>();
	}
	
	public Subcategory(String subcategoryN) {
		name = subcategoryN;
		news = new ArrayList<News>();
	}

	public void setNews(List<News> list) {
		this.news = list;
	}

	public List<News> getNews() {
		return news;
	}
	
	public void setName(String name2){
		name=name2;
	}
	public String getName(){
		return name;
	}

	public void addNews(News news2) {
		if(!news.contains(news2)){
			news.add(news2);	
		}
		
	}
	
	public String show() {
		String str=new String();
		str+="\nSubcategory "+name+'\n';
		for (News news:news) {
			str+=news.show();
		}
		return str;
	}
}
