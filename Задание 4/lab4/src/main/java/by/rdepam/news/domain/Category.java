package by.rdepam.news.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class Category {
	@XmlAttribute(required = true)
	private String name;

	@XmlElement(name = "subcategory")
	private List<Subcategory> subcategories;

	public Category() {
		subcategories = new ArrayList<Subcategory>();
	}
	
	public Category(String categoryN) {
		name = categoryN;
		subcategories = new ArrayList<Subcategory>();
	}

	public void setSubcategories(List<Subcategory> list) {
		this.subcategories = list;
	}

	public List<Subcategory> getSubcategories() {
		return subcategories;
	}

	public String getName() {
		return name;
	}

	public void setName(String n) {
		name=n;
	}

	public void addSubcategory(Subcategory subcategory) {
		if(!subcategories.contains(subcategory)){
			subcategories.add(subcategory);	
		}
		
	}

	public String show() {
		String str=new String();
		str+="Category "+name+'\n';
		for (Subcategory subcategory:subcategories) {
			str+=subcategory.show();
		}
		return str;
	}

	public Subcategory getSubcategoryByName(String subcategoryN) {
		for(Subcategory subcategory:subcategories){
			if(subcategoryN.equals(subcategory.getName())){
				return subcategory;
			}
		}
		return new Subcategory(subcategoryN);
	}

}
