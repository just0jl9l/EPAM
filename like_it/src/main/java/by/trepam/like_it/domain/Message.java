package by.trepam.like_it.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Message {

	private int id;
	private String name;
	private String text;
	private Account author;
	private Date dateOfPosting;
	private List<Answer> answers;

	public Message() {
		name = "";
		text = "";
		author = new Account();
		dateOfPosting = new Date();
		answers = new ArrayList<Answer>();
	}

	public Message(int id) {
		this.id = id;
		name = "";
		text = "";
		author = new Account();
		dateOfPosting = new Date();
		answers = new ArrayList<Answer>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public void setDateOfPosting(Date dateOfPosting) {
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
		Message message = (Message) obj;
		if (this.id != message.getId()) {
			return false;
		}
		if (null == this.author) {
			return this.author == message.getAuthor();
		} else {
			if (!this.author.isEquals(message.getAuthor())) {
				return false;
			}
		}
		if (null == this.name) {
			return this.name == message.getName();
		} else {
			if (!this.name.equals(message.getName())) {
				return false;
			}
		}
		if (null == this.text) {
			return this.text == message.getText();
		} else {
			if (!this.text.equals(message.getText())) {
				return false;
			}
		}
		if (null == this.dateOfPosting) {
			return this.dateOfPosting == message.getDateOfPosting();
		} else {
			if (!this.dateOfPosting.equals(message.getDateOfPosting())) {
				return false;
			}
		}
		return true;
	}
}
