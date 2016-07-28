package by.trepam.like_it.domain;

import java.io.Serializable;
import java.util.Date;

public class Mark implements Serializable {

	private static final long serialVersionUID = 1L;
	private int value;
	private Account author;
	private Date dateOfVoting;

	public Mark() {
		this.value = 0;
		this.author = new Account();
		dateOfVoting = new Date();
	}

	public Mark(int value, Account author) {
		this.value = value;
		this.author = author;
		dateOfVoting = new Date();
	}

	public Date getDateOfVoting() {
		return dateOfVoting;
	}

	public void setDateOfVoting(Date dateOfVoting) {
		this.dateOfVoting = dateOfVoting;
	}

	public Account getAuthor() {
		return author;
	}

	public void setAuthor(Account author) {
		this.author = author;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
