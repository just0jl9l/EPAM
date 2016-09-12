package by.trepam.like_it.domain;

import java.io.Serializable;
import java.util.Date;

public class Mark implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer value;
	private Account author;
	private Date dateOfVoting;

	public Mark() {
		this.value = DomainConstant.DEFAULT_MARK_VALUE;
		this.author = new Account();
		dateOfVoting = new Date();
	}

	public Mark(Integer value, Account author) {
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

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (null == object) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}
		Mark mark = (Mark) object;
		if (null == value) {
			return (value == mark.value);
		} else {
			if (!mark.equals(value)) {
				return false;
			}
		}
		if (null == author) {
			return (author == mark.author);
		} else {
			if (!author.equals(mark.author)) {
				return false;
			}
		}
		return true;
	}

	public int hashCode() {
		return (int) 31 * (((null == value) ? 0 : value.hashCode()) + ((null == author) ? 0 : author.hashCode()));
	}
}
