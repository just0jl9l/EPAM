package by.trepam.domain;

import java.util.Date;

public class Mark {

	private int value;
	private Account author;
	private Date dateOfVoting;
	
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
