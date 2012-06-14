package classes;

import java.util.Date;

public class User {
	Date lastLogin;
	int id;
	String username;
	String name;
	
	String token;
	public User(String token,int id,String username,String name,Date  lastLoginDate) {
		this.lastLogin=lastLoginDate;
		this.token=token;
		this.id=id;
			this.name=name;
		this.username=username;
	}

	public int getId() {
		return id;
	}
	
	public String getUserName() {
		return username;
	}

	public String getFirstName() {
		return name;
	}

	public Date getLast_login_date() {
		return lastLogin;
	}
	public String getToken() {
		return token;
	}
}
