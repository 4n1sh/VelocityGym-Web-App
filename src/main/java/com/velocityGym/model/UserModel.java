package com.velocityGym.model;
/**
 * UserModel is a data model class that represents a user in the Velocity Gym system.
 * It encapsulates user-related attributes such as name, contact information,
 * login credentials, profile image path, and date of birth.
 * This class provides constructors for various use cases along with
 * getter and setter methods for encapsulation and data access.
 * 
 * @author Anish Shrestha
 * LMU ID 23048634
 */

public class UserModel {
	private int id;
	private String fullName;
	private String gender;
	private String phone;
	private String email;
	private String password;
	private String imagePath;
	private String username;
	private String dob;

	public UserModel(int id, String fullName, String gender, String phone, String email, String imagePath,
			String username, String dob) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.imagePath = imagePath;
		this.username = username;
		this.dob = dob;
	}

	public UserModel(String fullName, String gender, String phone, String email, String password, String username,
			String dob, String imagePath) {
		super();
		this.fullName = fullName;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.username = username;
		this.dob = dob;
		this.imagePath = imagePath;
	}

	public UserModel(String password, String username) {
		super();
		this.password = password;
		this.username = username;
	}
	

	public UserModel(String fullName, String gender, String phone, String email, String dob) {
		super();
		this.fullName = fullName;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.dob = dob;
	}

	public UserModel() {
		super();
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
