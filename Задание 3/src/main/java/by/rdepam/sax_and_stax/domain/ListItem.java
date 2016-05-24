package by.rdepam.sax_and_stax.domain;

public class ListItem {
	private String text;
	private String price;
	private String number;

	public ListItem(String textValue, String textValue2) {
		text = textValue;
		price = textValue2;
	}

	public ListItem() {
		text = new String();
		price = new String();
	}

	public void setNumber(String value) {
		this.number = value;

	}

	public void setText(String currentElement) {
		this.text = currentElement;
	}

	public void setPrice(String currentElement) {
		this.price = currentElement;

	}

	public String getText() {
		return text;
	}

	public String getPrice() {
		return price;
	}

	public String getNumber() {
		return number;
	}
}
