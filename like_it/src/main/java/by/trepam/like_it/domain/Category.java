package by.trepam.like_it.domain;

import java.util.ArrayList;
import java.util.List;

public class Category {

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
		Category category = (Category) obj;
		if (this.id != category.getId()) {
			return false;
		}
		if (null == this.name) {
			return this.name == category.getName();
		} else {
			if (!this.name.equals(category.getName())) {
				return false;
			}
		}
		if (null == this.description) {
			return this.description == category.getDescription();
		} else {
			if (!this.description.equals(category.getDescription())) {
				return false;
			}
		}
		if (null == this.image) {
			return this.image == category.getImage();
		} else {
			if (!this.image.isEquals(category.getImage())) {
				return false;
			}
		}
		return true;
	}
}
