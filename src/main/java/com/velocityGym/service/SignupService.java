package com.velocityGym.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

import com.velocityGym.config.DbConfig;
import com.velocityGym.model.UserModel;
import com.velocityGym.util.PasswordUtil;
/**
 * RegisterService handles the registration of new podcast users.
 * It manages database interactions for user registration.
 * @author Anish Shrestha
 * LMU ID 23048634
 */
public class SignupService {
	
	private Connection dbConn;
	
    /**
     * Constructor initializes the database connection.
     */
    public SignupService() {
        try {
            this.dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Registers a new podcast user in the database.
     *
     * @param userModel the user details to be registered
     * @return Boolean indicating the success of the operation
     */
    public Boolean addUser(UserModel userModel) {
        if (dbConn == null) {
            System.err.println("Database connection is not available.");
            return false;
        }
        String insertQuery = "INSERT INTO user (full_name, gender, phone, email, password, user_image, username, dob) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = dbConn.prepareStatement(insertQuery)) {
        	String dob=userModel.getDob();
        	LocalDate localDate = LocalDate.parse(dob);  // Converts String to LocalDate
        	Date sqlDob = Date.valueOf(localDate);  // Converts LocalDate to java.sql.Date
        	
        	 String encryptedPassword = PasswordUtil.encrypt(userModel.getUsername(), userModel.getPassword());
        	
            ps.setString(1, userModel.getFullName());
            ps.setString(2, userModel.getGender());
            ps.setString(3, userModel.getPhone());
            ps.setString(4, userModel.getEmail());
            ps.setString(5, encryptedPassword);
            ps.setString(6, userModel.getImagePath());
            ps.setString(7, userModel.getUsername());
            ps.setDate(8, sqlDob);
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Checks if a username already exists in the database.
     *
     * @param username the username to check
     * @return true if taken, false otherwise
     */
    public boolean isUsernameTaken(String username) {
    	 if (dbConn == null) {
             return false;
         }
    	
        String query = "SELECT 1 FROM user WHERE username = ?";
        try (PreparedStatement ps = dbConn.prepareStatement(query)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Checks if an email already exists in the database.
     *
     * @param email the email to check
     * @return true if taken, false otherwise
     */
    public boolean isEmailTaken(String email) {
    	 if (dbConn == null) {
             return false;
         }
        String query = "SELECT 1 FROM user WHERE email = ?";
        try (PreparedStatement ps = dbConn.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Checks if an phone already exists in the database.
     *
     * @param phone the phone to check
     * @return true if taken, false otherwise
     */
    public boolean isPhoneTaken(String phone) {
    	 if (dbConn == null) {
             return false;
         }
        String query = "SELECT 1 FROM user WHERE phone = ?";
        try (PreparedStatement ps = dbConn.prepareStatement(query)) {
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
