package com.velocityGym.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.velocityGym.config.DbConfig;
import com.velocityGym.model.UserModel;
import com.velocityGym.util.PasswordUtil;

/**
 * Service class for handling login operations. Connects to the database,
 * verifies user credentials, and returns login status.
 */
public class LoginService {

	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public LoginService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("LoginService: Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Validates the user credentials against the database records.
	 *
	 * @param userModel the UserModel object containing user credentials
	 * @return true if the user credentials are valid, false otherwise
	 */
	public Boolean loginUser(UserModel userModel) {
		if (dbConn == null) {
			System.err.println("DB connection is null in LoginService.");
			return false;
		}

		String inputUsername = userModel.getUsername().trim();

		String query = "SELECT username, password FROM user WHERE username = ?";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setString(1, inputUsername); // Trim input just in case
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				return validatePassword(result, userModel);
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception during login: " + e.getMessage());
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Validates the password retrieved from the database.
	 *
	 * @param result    the ResultSet containing the username and password from DB
	 * @param userModel the UserModel object with user credentials
	 * @return true if passwords match, false otherwise
	 * @throws SQLException if a DB access error occurs
	 */
	private boolean validatePassword(ResultSet result, UserModel userModel) throws SQLException {
		String dbUsername = result.getString("username").trim();
		String dbPassword = result.getString("password");

		String decryptedPassword = PasswordUtil.decrypt(dbPassword, dbUsername);

		return decryptedPassword.equals(userModel.getPassword());
	}

	/**
	 * Retrieves the role of a user from the database based on the provided
	 * username.
	 *
	 * @param user the UserModel object containing the username to look up
	 * @return the role of the user as a String, or null if not found or on error
	 */

	public String getDbRole(UserModel user) {
		if (dbConn == null) {
			System.err.println("DB connection is null in LoginService.");
			return null;
		}

		String inputUsername = user.getUsername().trim();

		String query = "SELECT role FROM user WHERE username = ?";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setString(1, inputUsername); // Trim input just in case
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				return result.getString("role");
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception during login: " + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}
}
