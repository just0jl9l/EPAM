package by.trepam.like_it.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Answer {

	private int id;
	private String text;
	private Date dateOfPosting;
	private Account author;
	private List<Mark> marks;
	private double rating;

	public Answer() {
		text = "";
		dateOfPosting = new Date();
		author = new Account();
		marks = new ArrayList<Mark>();
	}

	public Answer(int id) {
		this.id = id;
		text = "";
		dateOfPosting = new Date();
		author = new Account();
		marks = new ArrayList<Mark>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDateOfPosting() {
		return dateOfPosting;
	}

	public void setDateOfPosting(Date dateOfPosting) {
		this.dateOfPosting = dateOfPosting;
	}

	public Account getAuthor() {
		return author;
	}

	public void setAuthor(Account author) {
		this.author = author;
	}

	public List<Mark> getMarks() {
		return marks;
	}

	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}

	public void addMark(Mark mark) {
		this.marks.add(mark);
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
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
		Answer ans = (Answer) obj;
		if (this.id != ans.getId()) {
			return false;
		}
		if (null == this.author) {
			return this.author == ans.getAuthor();
		} else {
			if (!this.author.isEquals(ans.getAuthor())) {
				return false;
			}
		}
		if (null == this.text) {
			return this.text == ans.getText();
		} else {
			if (!this.text.equals(ans.getText())) {
				return false;
			}
		}
		if (null == this.dateOfPosting) {
			return this.dateOfPosting == ans.getDateOfPosting();
		} else {
			if (!this.dateOfPosting.equals(ans.getDateOfPosting())) {
				return false;
			}
		}
		return true;
	}
}
