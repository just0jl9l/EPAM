package by.trepam.like_it.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import by.trepam.like_it.domain.util.MessagesDateOfPostingComparator;

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String description;
	private Image image;
	private List<Message> messages;

	public Category() {
		name = "";
		description = "";
		image = new Image();
		messages = new ArrayList<Message>();
	}

	public Category(int id) {
		this.id=id;
		name = "";
		description = "";
		image = new Image();
		messages = new ArrayList<Message>();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Message> getMessages() {
		messages.sort(new MessagesDateOfPostingComparator());
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public void addMessage(Message message) {
		this.messages.add(message);
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
