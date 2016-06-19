package by.trepam.domain;

public class Account {
	
	private int id;
	private String login;
	private String password;
	private String status;
	private String name;
	private String surname;
	private Image photo;
	private double rating;
	
	public Account(){
		login = "";
		password = "";
		status = "";
		name = "";
		surname = "";
		photo = new Image(0);
		
	}
	
	public Account(int n){
		id=n;
		login = "";
		password = "";
		status = "";
		name = "";
		surname = "";
		photo = new Image(0);
	}
	
	public int getId(){
		return id;
	}	
	public void setId(int id){
		this.id=id;
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
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
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
	
}
