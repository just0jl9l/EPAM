package by.rdepam.sax_and_stax.domain;

import java.util.ArrayList;
import java.util.List;

public class Description {
	private String general;
	private List<ListItem> listItem;

	public Description(String general, List<ListItem> listit) {
		this.general = general;
		this.listItem = listit;
	}

	public Description() {
		listItem = new ArrayList<ListItem>();
		general = new String();
	}

	public void addListItem(ListItem listItem2) {
		this.listItem.add(listItem2);

	}

	public void setGeneral(String currentElement) {
		this.general = currentElement;

	}

	public String getGeneral() {
		return general;
	}

	public List<ListItem> getListItems() {
		return listItem;
	}
}
