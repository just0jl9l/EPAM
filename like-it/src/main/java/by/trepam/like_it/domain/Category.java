package by.trepam.like_it.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String description;
	private Image image;
	private List<Message> messages;

	public Category() {
		name = DomainConstant.EMPTY;
		description = DomainConstant.EMPTY;
		image = new Image();
		messages = new ArrayList<Message>();
	}

	public Category(Integer id) {
		this.id = id;
		name = DomainConstant.EMPTY;
		description = DomainConstant.EMPTY;
		image = new Image();
		messages = new ArrayList<Message>();
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
		Category category = (Category) object;
		if (null == id) {
			return (id == category.id);
		} else {
			if (!id.equals(category.id)) {
				return false;
			}
		}
		if (null == name) {
			return (name == category.name);
		} else {
			if (!name.equals(category.name)) {
				return false;
			}
		}
		if (null == description) {
			return (description == category.description);
		} else {
			if (!description.equals(category.description)) {
				return false;
			}
		}
		return true;
	}

	public int hashCode() {
		return (int) 31 * (((null == id) ? 0 : id.hashCode()) + ((null == description) ? 0 : description.hashCode())
				+ ((null == name) ? 0 : name.hashCode()));
	}
}
