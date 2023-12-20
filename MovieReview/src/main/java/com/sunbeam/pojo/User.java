package com.sunbeam.pojo;

import java.util.Date;

public class User {
	int id;
	String FirstName;
	String LastName;
	String email;
	String password;
	String mobile;
	Date birthDate;

	public User()
	{
		
	}
	public User(int id, String fname, String LastName, String email, String password,  Date birthDate,String mobile) {
		this.id = id;
		this.FirstName = fname;
		this.LastName = LastName;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String fname) {
		this.FirstName = fname;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String LastName) {
		this.LastName = LastName;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fname=" + FirstName + ", LastName=" + LastName + ", email=" + email + ", password="
				+ password + ", mobile=" + mobile + ", birthDate=" + birthDate + "]";
	}

}
