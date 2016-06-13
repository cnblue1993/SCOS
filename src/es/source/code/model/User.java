package es.source.code.model;

import java.io.Serializable;

public class User implements Serializable{
	
	private String userName;
	private String password;
	private Boolean oldUser;
	
	public final String getUserName() {
		return userName;
	}
	public final void setUserName(String userName) {
		this.userName = userName;
	}
	public final String getPassword() {
		return password;
	}
	public final void setPassword(String password) {
		this.password = password;
	}
	public final Boolean getOldUser() {
		return oldUser;
	}
	public final void setOldUser(Boolean oldUser) {
		this.oldUser = oldUser;
	}
	
	
	

}
