package by.rdepam.sax_and_stax.service;

import java.util.List;

import by.rdepam.sax_and_stax.domain.Description;
import by.rdepam.sax_and_stax.domain.Dish;
import by.rdepam.sax_and_stax.domain.ListItem;
import by.rdepam.sax_and_stax.domain.MenuItem;
import by.rdepam.sax_and_stax.domain.Portion;

public class MenuViewer {

	public String show(MenuItem it) {
		String str = " Name: " + it.getName() + "; ";
		str += " ID: " + it.getID() + ":\n\n";
		List<Dish> dishes = it.getDishs();
		for (Dish d : dishes) {
			str += show(d);
			str += '\n';
			str += ("-------------------------------------\n");
		}

		return str;
	}

	private String show(Dish d) {
		String str = " ID: " + d.getID();
		String photo = d.getPhoto();
		String name = d.getName();
		Description descr = d.getDescription();
		Portion portion = d.getPortion();
		if (photo != null && photo.length() > 0) {
			str += ";\n Photo: " + photo;
		}
		if (name != null && name.length() > 0) {
			str += ";\n Name: " + name;
		}
		if (descr != null && descr.getListItems().size()>0) {
			str += ";\n Description: " + show(descr);
		}
		if (portion != null) {
			str += ";\n Portion: " + show(portion) + ".";
		}
		return str;
	}

	private String show(Description descr) {
		String str = new String();
		String general = descr.getGeneral();
		List<ListItem> listItem = descr.getListItems();
		if (general != null && general.length() > 0) {
			str += "\n   -General: " + general;
		}
		if (listItem.size() > 0) {
			str += "\n   -List: ";
		}
		for (ListItem l : listItem) {
			str += show(l);
		}
		return str;
	}

	private String show(ListItem l) {
		String str = new String();
		String text = l.getText();
		String price = l.getPrice();
		String number = l.getNumber();
		if (number != null && number.length() > 0) {
			str += "\n      *Number: " + number;
		}
		if (text != null && text.length() > 0) {
			str += "\n      *Text: " + text;
		}
		if (price != null && price.length() > 0) {
			str += "\n      *Price: " + price;
		}
		return str;
	}

	private String show(Portion portion) {
		String str = new String();
		String amount = portion.getAmount();
		List<Integer> weight = portion.getWeights();
		if (amount != null && amount.length() > 0) {
			str += "\n   -Amount: " + amount + ".";
		}
		if (weight.size() > 0) {
			str += "\n   -Weight: ";
			for (Integer d : weight) {
				str += d;
				str += '/';
			}
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

}
