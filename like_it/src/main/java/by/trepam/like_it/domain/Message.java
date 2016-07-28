package by.trepam.like_it.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.trepam.like_it.domain.util.AnswersDateOfPostingComparator;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
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

	public String getFormatedDateOfPosting() {
		return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(dateOfPosting);
	}

	public List<Answer> getAnswers() {
		answers.sort(new AnswersDateOfPostingComparator());
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public void addAnswer(Answer answer) {
		this.answers.add(answer);
	}
}
