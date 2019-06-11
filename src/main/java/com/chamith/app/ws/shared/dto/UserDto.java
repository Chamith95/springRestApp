package com.chamith.app.ws.shared.dto;

import java.io.Serializable;

public class UserDto implements Serializable {


	private static final long serialVersionUID = -5402462254550457449L;
	private long id;
	private String userId;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String encrtptedPassword;
	private String emailVerificationToken;
	private Boolean emailVerificationStatus=false;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEncrtptedPassword() {
		return encrtptedPassword;
	}
	public void setEncrtptedPassword(String encrtptedPassword) {
		this.encrtptedPassword = encrtptedPassword;
	}
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}
	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}
	
	
}
