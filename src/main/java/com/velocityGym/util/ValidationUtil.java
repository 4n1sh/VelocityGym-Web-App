package com.velocityGym.util;

import java.util.regex.Pattern;

import jakarta.servlet.http.Part;
/**
 * ValidationUtil is a utility class for validating various user input fields
 * in the Velocity Gym application. It includes reusable static methods to 
 * validate strings, emails, phone numbers, passwords, usernames, gender,
 * dates, and image file formats. Designed to enforce consistent input 
 * validation across forms and ensure data integrity.
 * 
 * @author Anish Shrestha
 * LMU ID 23048634
 */

public class ValidationUtil {

    // 1. Check if the field is null or empty
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

 // 2. Check if full name contains only letters and spaces
    public static boolean isAlphabetic(String value) {
        return value != null && value.matches("^[a-zA-Z]+( [a-zA-Z]+)*$");
    }


    // 3. Check if value is alphanumeric and starts with a letter (for usernames)
    public static boolean isAlphanumericStartingWithLetter(String value) {
        return value != null && value.matches("^[a-zA-Z][a-zA-Z0-9]*$");
    }

    // 4. Validate email format
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email != null && Pattern.matches(emailRegex, email);
    }

    // 5. Validate Nepali phone number (starts with 98 and 10 digits)
    public static boolean isValidPhoneNumber(String number) {
        return number != null && number.matches("^98\\d{8}$");
    }

    // 6. Validate strong password: at least 1 uppercase, 1 number, 1 special char, 8+ characters
    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password != null && password.matches(passwordRegex);
    }

    // 7. Check if password and confirm password match
    public static boolean doPasswordsMatch(String password, String retypePassword) {
        return password != null && password.equals(retypePassword);
    }

    // 8. Validate date format if needed (e.g., yyyy-MM-dd)
    public static boolean isValidDate(String date) {
        return date != null && date.matches("^\\d{4}-\\d{2}-\\d{2}$");
    }
    
    public static boolean isValidImageExtension(Part imagePart) {
        if (imagePart == null || isNullOrEmpty(imagePart.getSubmittedFileName())) {
            return false;
        }
        String fileName = imagePart.getSubmittedFileName().toLowerCase();
        return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png") || fileName.endsWith(".gif");
    }
 // 9. Validate gender (only "Male" or "Female", case-insensitive)
    public static boolean isValidGender(String gender) {
        return gender != null && (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female"));
    }

}
