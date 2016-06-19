package by.trepam.domain;

import java.util.Date;
import java.util.List;

public class Answer {

	private int id;
	private String text;
	private Date dateOfPosting;
	private Account author;
	private List<Mark> marks;
	private double rating;
	
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
	public List<Mark> getMark() {
		return marks;
	}
	public void setMark(List<Mark> mark) {
		this.marks = mark;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
}
