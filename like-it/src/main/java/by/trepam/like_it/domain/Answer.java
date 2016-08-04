package by.trepam.like_it.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Answer implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String text;
	private Timestamp dateOfPosting;
	private Account author;
	private List<Mark> marks;
	private Double rating;

	public Answer() {
		text = DomainConstant.EMPTY;
		dateOfPosting = new Timestamp(new Date().getTime());
		author = new Account();
		marks = new ArrayList<Mark>();
	}

	public Answer(Integer id) {
		this.id = id;
		text = DomainConstant.EMPTY;
		dateOfPosting = new Timestamp(new Date().getTime());
		author = new Account();
		marks = new ArrayList<Mark>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public void setDateOfPosting(Timestamp dateOfPosting) {
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

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = BigDecimal.valueOf(rating).setScale(3, RoundingMode.HALF_UP).doubleValue();
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
		Answer answer = (Answer) object;
		if (null == id) {
			return (id == answer.id);
		} else {
			if (!answer.equals(answer.id)) {
				return false;
			}
		}
		if (null == text) {
			return (text == answer.text);
		} else {
			if (!text.equals(answer.text)) {
				return false;
			}
		}
		return true;
	}

	public int hashCode() {
		return (int) 31 * (((null == id) ? 0 : id.hashCode()) + ((null == text) ? 0 : text.hashCode())
				+ ((null == dateOfPosting) ? 0 : dateOfPosting.hashCode()) + ((null == author) ? 0 : author.hashCode())
				+ ((null == marks) ? 0 : marks.hashCode()) + ((null == rating) ? 0 : rating.hashCode()));
	}
}
