package com.appdeveloperblog.app.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="users")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id;

	/*
	 * not null : nullable = false
	 * length : length 
	*/
	@Column(name = "userId", nullable = false)
	private String userId;
	
	@Column(name = "firstName", nullable = false , length = 50)
	private String firstName;
	
	@Column(name = "lastName", nullable = false , length = 50)
	private String lastName;
	
	@Column(name = "email", nullable = false , length = 100 , unique= true)
	private String email;
	
	@Column(name = "encryptedPassword", nullable = false)
	private String encryptedPassword;
	
	@Column(name = "emailVerificationToken")
	private String emailVerificationToken;
	
	@Column(name = "emailVerificatinStatus", nullable = false)
	private Boolean emailVerificatinStatus = false;

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}

	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}

	public Boolean getEmailVerificatinStatus() {
		return emailVerificatinStatus;
	}

	public void setEmailVerificatinStatus(Boolean emailVerificatinStatus) {
		this.emailVerificatinStatus = emailVerificatinStatus;
	}
	
	

}
