package com.velocityGym.model;

/**
 * UserModel is a data model class that represents a user in the Velocity Gym
 * system. It encapsulates user-related attributes such as name, contact
 * information, login credentials, profile image path, and date of birth. This
 * class provides constructors for various use cases along with getter and
 * setter methods for encapsulation and data access.
 * 
 * @author Anish Shrestha LMU ID 23048634
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

	/**
	 * Constructor for full user details including ID.
	 * 
	 * @param id        User ID
	 * @param fullName  Full name of the user
	 * @param gender    Gender of the user
	 * @param phone     Phone number
	 * @param email     Email address
	 * @param imagePath Profile image path
	 * @param username  Username
	 * @param dob       Date of birth
	 */
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

	/**
	 * Constructor for creating a new user with full details (excluding ID).
	 * 
	 * @param fullName  Full name of the user
	 * @param gender    Gender of the user
	 * @param phone     Phone number
	 * @param email     Email address
	 * @param password  Password
	 * @param username  Username
	 * @param dob       Date of birth
	 * @param imagePath Profile image path
	 */
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

	/**
	 * Constructor for login validation using username and password.
	 * 
	 * @param password Password
	 * @param username Username
	 */

	public UserModel(String password, String username) {
		super();
		this.password = password;
		this.username = username;
	}

	/**
	 * Constructor with basic user information excluding credentials and image.
	 * 
	 * @param fullName Full name of the user
	 * @param gender   Gender of the user
	 * @param phone    Phone number
	 * @param email    Email address
	 * @param dob      Date of birth
	 */
	public UserModel(String fullName, String gender, String phone, String email, String dob) {
		super();
		this.fullName = fullName;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.dob = dob;
	}

	/**
	 * Constructor with partial user details, typically for displaying user info.
	 * 
	 * @param id       User ID
	 * @param fullName Full name of the user
	 * @param phone    Phone number
	 * @param email    Email address
	 * @param username Username
	 * @param dob      Date of birth
	 */
	public UserModel(int id, String fullName, String phone, String email, String username, String dob) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.username = username;
		this.dob = dob;
	}

	/**
	 * Default constructor.
	 */
	public UserModel() {
		super();
	}

	/**
	 * Gets the date of birth of the user.
	 * 
	 * @return the date of birth
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * Sets the date of birth of the user.
	 * 
	 * @param dob the date of birth
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * Gets the user's ID.
	 * 
	 * @return the user ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the user's ID.
	 * 
	 * @param id the user ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the full name of the user.
	 * 
	 * @return the full name
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Sets the full name of the user.
	 * 
	 * @param fullName the full name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Gets the gender of the user.
	 * 
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender of the user.
	 * 
	 * @param gender the gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets the phone number of the user.
	 * 
	 * @return the phone number
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone number of the user.
	 * 
	 * @param phone the phone number
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets the email address of the user.
	 * 
	 * @return the email address
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address of the user.
	 * 
	 * @param email the email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the user's password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the user's password.
	 * 
	 * @param password the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the image path for the user's profile picture.
	 * 
	 * @return the image path
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * Sets the image path for the user's profile picture.
	 * 
	 * @param imagePath the image path
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * Gets the username.
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 * 
	 * @param username the username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

}
