package by.trepam.like_it.domain;

public class Image {

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
		Image image = (Image) obj;
		if (this.id != image.getId()) {
			return false;
		}
		return true;
	}

}
