package by.trepam.like_it.domain;

import java.util.Date;

public class Mark {

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

	public boolean isEquals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (null == obj) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Mark mark = (Mark) obj;
		if (this.value != mark.getValue()) {
			return false;
		}
		if (null == this.author) {
			return this.author == mark.getAuthor();
		} else {
			if (!this.author.isEquals(mark.getAuthor())) {
				return false;
			}
		}
		if (null == this.dateOfVoting) {
			return this.dateOfVoting == mark.getDateOfVoting();
		} else {
			if (!this.dateOfVoting.equals(mark.getDateOfVoting())) {
				return false;
			}
		}
		return true;
	}
}
