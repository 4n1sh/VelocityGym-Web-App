package com.velocityGym.util;

import java.util.regex.Pattern;

import jakarta.servlet.http.Part;

/**
 * ValidationUtil is a utility class for validating various user input fields in
 * the Velocity Gym application. It includes reusable static methods to validate
 * strings, emails, phone numbers, passwords, usernames, gender, dates, and
 * image file formats. Designed to enforce consistent input validation across
 * forms and ensure data integrity.
 * 
 * @author Anish Shrestha LMU ID 23048634
 */

public class ValidationUtil {

	/**
	 * Checks if the given string is null or empty after trimming.
	 * 
	 * @param value The string to check.
	 * @return true if the string is null or empty, false otherwise.
	 */

	public static boolean isNullOrEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}

	/**
	 * Validates that the provided string contains only alphabetic characters and
	 * spaces. Useful for validating names.
	 * 
	 * @param value The string to validate.
	 * @return true if the string contains only letters and spaces, false otherwise.
	 */

	public static boolean isAlphabetic(String value) {
		return value != null && value.matches("^[a-zA-Z]+( [a-zA-Z]+)*$");
	}

	/**
	 * Validates that the provided string is alphanumeric and starts with a letter.
	 * This is typically used for validating usernames.
	 * 
	 * @param value The string to validate.
	 * @return true if the string is alphanumeric and starts with a letter, false
	 *         otherwise.
	 */

	public static boolean isAlphanumericStartingWithLetter(String value) {
		return value != null && value.matches("^[a-zA-Z][a-zA-Z0-9]*$");
	}

	/**
	 * Validates the format of an email address using regular expressions.
	 * 
	 * @param email The email address to validate.
	 * @return true if the email is in a valid format, false otherwise.
	 */

	public static boolean isValidEmail(String email) {
		String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		return email != null && Pattern.matches(emailRegex, email);
	}

	/**
	 * Validates a Nepali phone number to ensure it starts with '98' and has exactly
	 * 10 digits.
	 * 
	 * @param number The phone number to validate.
	 * @return true if the phone number matches the Nepali format, false otherwise.
	 */

	public static boolean isValidPhoneNumber(String number) {
		return number != null && number.matches("^98\\d{8}$");
	}

	/**
	 * Validates a password to ensure it meets the required complexity: at least one
	 * uppercase letter, one digit, one special character, and a minimum length of 8
	 * characters.
	 * 
	 * @param password The password to validate.
	 * @return true if the password meets the required complexity, false otherwise.
	 */

	public static boolean isValidPassword(String password) {
		String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		return password != null && password.matches(passwordRegex);
	}

	/**
	 * Checks if the given password and re-typed password match.
	 * 
	 * @param password       The password to check.
	 * @param retypePassword The re-typed password to check.
	 * @return true if the passwords match, false otherwise.
	 */

	public static boolean doPasswordsMatch(String password, String retypePassword) {
		return password != null && password.equals(retypePassword);
	}

	/**
	 * Validates that the given date is in the format yyyy-MM-dd.
	 * 
	 * @param date The date to validate.
	 * @return true if the date matches the format, false otherwise.
	 */

	public static boolean isValidDate(String date) {
		return date != null && date.matches("^\\d{4}-\\d{2}-\\d{2}$");
	}

	/**
	 * Validates the file extension of an image to ensure it is one of the
	 * following: .jpg, .jpeg, .png, or .gif.
	 * 
	 * @param imagePart The image file part to check.
	 * @return true if the image file extension is valid, false otherwise.
	 */

	public static boolean isValidImageExtension(Part imagePart) {
		if (imagePart == null || isNullOrEmpty(imagePart.getSubmittedFileName())) {
			return false;
		}
		String fileName = imagePart.getSubmittedFileName().toLowerCase();
		return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")
				|| fileName.endsWith(".gif");
	}

	/**
	 * Validates that the gender value is either "Male" or "Female"
	 * (case-insensitive).
	 * 
	 * @param gender The gender value to validate.
	 * @return true if the gender is valid, false otherwise.
	 */

	public static boolean isValidGender(String gender) {
		return gender != null && (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female"));
	}

}
