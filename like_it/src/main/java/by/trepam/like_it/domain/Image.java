package by.trepam.like_it.domain;

import java.io.Serializable;

public class Image implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String path;
	private String format;

	public Image() {
		id = 0;
		path = "jsp/content/";
		format = "png";
	}

	public Image(int int1) {
		id = int1;
		path = "";
		format = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPath() {
		return path + id + '.' + format;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
