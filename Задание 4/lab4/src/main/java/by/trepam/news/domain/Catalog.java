package by.trepam.news.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement( namespace = "http://www.epam.tc.com/news", name = "catalog")
public class Catalog {
	private List<Category> categories;
	
	public Catalog(){
		categories = new ArrayList<Category>();
	}
	
	@XmlElement(name = "category")
	public void setCategories(List<Category> list){
		this.categories=list;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void addCategory(Category category) {
		if(!categories.contains(category)){
			categories.add(category);	
		}
	}

	public Category getCategoryByName(String categoryN) {
		for(Category category:categories){
			if(categoryN.equals(category.getName())){
				return category;
			}
		}
		return new Category(categoryN);
	}

}
