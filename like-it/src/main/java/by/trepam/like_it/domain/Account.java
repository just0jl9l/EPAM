package by.trepam.like_it.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String login;
	private String password;
	private String status;
	private String name;
	private String surname;
	private Image photo;
	private Double rating;

	public Account() {
		login = DomainConstant.EMPTY;
		password = DomainConstant.EMPTY;
		status = DomainConstant.EMPTY;
		name = DomainConstant.EMPTY;
		surname = DomainConstant.EMPTY;
		photo = new Image();
	}

	public Account(Integer n) {
		id = n;
		login = DomainConstant.EMPTY;
		password = DomainConstant.EMPTY;
		status = DomainConstant.EMPTY;
		name = DomainConstant.EMPTY;
		surname = DomainConstant.EMPTY;
		photo = new Image();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Image getPhoto() {
		return photo;
	}

	public void setPhoto(Image photo) {
		this.photo = photo;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = BigDecimal.valueOf(rating).setScale(3, RoundingMode.HALF_UP).doubleValue();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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
		Account account = (Account) object;
		if (null == id) {
			return (id == account.id);
		} else {
			if (!id.equals(account.id)) {
				return false;
			}
		}
		return true;
	}

	public int hashCode() {
		return (int) 31 * (((null == id) ? 0 : id.hashCode()) );
	}

}
