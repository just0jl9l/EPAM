package by.trepam.like_it.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String text;
	private Account author;
	private Timestamp dateOfPosting;
	private List<Answer> answers;

	public Message() {
		name = DomainConstant.EMPTY;
		text = DomainConstant.EMPTY;
		author = new Account();
		dateOfPosting = new Timestamp(new Date().getTime());
		answers = new ArrayList<Answer>();
	}

	public Message(Integer id) {
		this.id = id;
		name = DomainConstant.EMPTY;
		text = DomainConstant.EMPTY;
		author = new Account();
		dateOfPosting = new Timestamp(new Date().getTime());
		answers = new ArrayList<Answer>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Account getAuthor() {
		return author;
	}

	public void setAuthor(Account author) {
		this.author = author;
	}

	public Date getDateOfPosting() {
		return dateOfPosting;
	}

	public void setDateOfPosting(Timestamp dateOfPosting) {
		this.dateOfPosting = dateOfPosting;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public void addAnswer(Answer answer) {
		this.answers.add(answer);
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
		Message message = (Message) object;
		if (null == id) {
			return (id == message.id);
		} else {
			if (!id.equals(message.id)) {
				return false;
			}
		}
		if (null == name) {
			return (name == message.name);
		} else {
			if (!name.equals(message.name)) {
				return false;
			}
		}
		if (null == text) {
			return (text == message.text);
		} else {
			if (!text.equals(message.text)) {
				return false;
			}
		}
		return true;
	}

	public int hashCode() {
		return (int) 31 * (((null == id) ? 0 : id.hashCode()) + ((null == text) ? 0 : text.hashCode())
				+ ((null == name) ? 0 : name.hashCode()));
	}
}
