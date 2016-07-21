package by.trepam.like_it.domain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {

	private int id;
	private String path;
	private String format;
	private BufferedImage image;

	public Image() {
		id = 0;
		path = "E:/!����!/��������";
		format = "jpg";
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
		return path;
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

	public BufferedImage getImage() throws IOException {
		image = ImageIO.read(new File(path + '/' + id + '.' + format));
		return image;
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
