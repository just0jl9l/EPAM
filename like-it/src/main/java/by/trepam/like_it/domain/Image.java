package by.trepam.like_it.domain;

import java.io.Serializable;

public class Image implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String path;
	private String format;

	public Image() {
		id = DomainConstant.DEFAULT_IMAGE_ID;
		path = DomainConstant.DEFAULT_IMAGE_PATH;
		format = DomainConstant.DEFAULT_IMAGE_FORMAT;
	}

	public Image(Integer int1) {
		id = int1;
		path = DomainConstant.EMPTY;
		format = DomainConstant.EMPTY;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPath() {
		return path + id + DomainConstant.SEPARATOR + format;
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
		Image image = (Image) object;
		if (null == id) {
			return (id == image.id);
		} else {
			if (!id.equals(image.id)) {
				return false;
			}
		}
		return true;
	}

	public int hashCode() {
		return (int) 31 * (((null == id) ? 0 : id.hashCode()));
	}
}
