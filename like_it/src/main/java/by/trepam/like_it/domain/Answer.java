package by.trepam.like_it.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Answer implements Serializable{

	private static final long serialVersionUID = 1L;
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

	public String getFormatedDateOfPosting() {
		return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(dateOfPosting);
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
		this.rating = BigDecimal.valueOf(rating).setScale(3, RoundingMode.HALF_UP).doubleValue();
	}
}
