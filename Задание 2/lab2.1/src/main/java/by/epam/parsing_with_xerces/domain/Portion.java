package by.epam.parsing_with_xerces.domain;

import java.util.ArrayList;
import java.util.List;

public class Portion {
	private String amount;
	private List<Integer> weight;

	public Portion(String amount2, List<Integer> w) {
		amount = amount2;
		weight = w;
	}

	public Portion() {
		amount = new String();
		weight = new ArrayList<Integer>();
	}

	public void setAmount(String currentElement) {
		this.amount = currentElement;

	}

	public void setWeight(String currentElement) {
		this.weight.add(new Integer(currentElement));

	}

	public List<Integer> getWeights() {
		return weight;
	}

	public String getAmount() {
		return amount;
	}
}
