package by.rdepam.sax_and_stax.domain;

import java.util.List;

public class Dish {
	private String id;
	private String photo;
	private String name;
	private Description descr;
	private Portion portion;

	public Dish(String name2, String id2, String photo2, List<Description> des, List<Portion> portion2) {
		id = id2;
		photo = photo2;
		name = name2;
		if (des.size() > 0) {
			descr = des.get(0);
		} else {
			descr = new Description();
		}
		if (portion2.size() > 0) {
			portion = portion2.get(0);
		} else {
			portion = new Portion();
		}
	}

	public Dish() {
		id = new String();
		photo = new String();
		name = new String();
		descr = new Description();
		portion = new Portion();
	}

	public void setID(String value) {
		this.id = value;

	}

	public void setName(String currentElement) {
		this.name = currentElement;

	}

	public void addPhoto(String currentElement) {
		this.photo = currentElement;

	}

	public void addDescription(Description description) {
		this.descr = description;

	}

	public void setPortion(Portion portion2) {
		this.portion = portion2;

	}

	public String getID() {
		return id;
	}

	public String getPhoto() {
		return photo;
	}

	public String getName() {
		return name;
	}

	public Description getDescription() {
		return descr;
	}

	public Portion getPortion() {
		return portion;
	}

}
