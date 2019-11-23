package com.stackroute.userservice.domain;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "userId")
	@NotNull
	@Size(min = 1, message = "Username should have atleast 3 characters")
	private String userId;

	@Column(name = "password")
	@NotNull
	@Size(min = 1, message = "Password should have atleast 3 characters")
	private String password;

	@Column(name = "firstName")
	@NotNull
	@Size(min = 1, message = "Firstname should have atleast 3 characters")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "isAdmin")
	private Boolean isAdmin = false;

	@CreationTimestamp
	private Date createdDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User(String userId, String password, String firstName, String lastName, Boolean isAdmin, Date createdDate) {
		super();
		this.userId = userId;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isAdmin = isAdmin;
		this.createdDate = createdDate;
	}

	public User() {
		super();
	}

}
