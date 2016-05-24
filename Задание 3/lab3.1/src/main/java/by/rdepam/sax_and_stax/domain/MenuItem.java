package by.rdepam.sax_and_stax.domain;

import java.util.ArrayList;
import java.util.List;

public class MenuItem {
	private List<Dish> dishs;
	private String id;
	private String name;

	public MenuItem() {
		dishs = new ArrayList<Dish>();
	}

	public void setDishs(List<Dish> dishs2) {
		dishs = dishs2;
	}

	public void setID(String id) {
		this.id = id;

	}

	public void setName(String value) {
		this.name = value;

	}

	public void addDish(Dish dish) {
		dishs.add(dish);

	}

	public String getName() {
		return name;
	}

	public String getID() {
		return id;
	}

	public List<Dish> getDishs() {
		return dishs;
	}
}
