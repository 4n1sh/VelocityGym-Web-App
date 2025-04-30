package com.velocityGym.service;

import com.velocityGym.config.DbConfig;
import com.velocityGym.model.UserModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ProfileService {
	private Connection dbConn;

	public ProfileService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("ProfileService: Database connection error: " + e.getMessage());
		}
	}

	/**
	 * Fetch all user details by username
	 */
	public UserModel getUserDetails(String username) {
		if (dbConn == null) {
			System.err.println("DB connection is null in ProfileService.");
			return null;
		}

		String query = "SELECT * FROM user WHERE username = ?";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				UserModel user = new UserModel();
				user.setId(rs.getInt("user_id"));
				user.setFullName(rs.getString("full_name"));
				user.setGender(rs.getString("gender"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setImagePath(rs.getString("user_image"));
				user.setUsername(rs.getString("username"));
				user.setDob(rs.getDate("dob").toString());
				return user;
			}

		} catch (SQLException e) {
			System.err.println("Error fetching user details: " + e.getMessage());
		}

		return null;
	}

	public Boolean editUser(UserModel userModel,String username) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return false;
		}
		String updateQuery = "UPDATE user SET full_name = ?, gender = ?, phone = ?, email = ?, dob = ? WHERE username = ?";

		try (PreparedStatement ps = dbConn.prepareStatement(updateQuery)) {
			String dob = userModel.getDob();
			LocalDate localDate = LocalDate.parse(dob); // Converts String to LocalDate
			Date sqlDob = Date.valueOf(localDate); // Converts LocalDate to java.sql.Date


			ps.setString(1, userModel.getFullName());
			ps.setString(2, userModel.getGender());
			ps.setString(3, userModel.getPhone());
			ps.setString(4, userModel.getEmail());
			ps.setDate(5, sqlDob);
			ps.setString(6, username);
			int rowsInserted = ps.executeUpdate();
			return rowsInserted > 0;

		} catch (SQLException e) {
			System.err.println("Error inserting user: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	 public boolean isEmailTaken(String email ,String username) {
    	 if (dbConn == null) {
             return false;
         }
        String query = "SELECT 1 FROM user WHERE email = ? AND username !=?";
        try (PreparedStatement ps = dbConn.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, username);
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
    public boolean isPhoneTaken(String phone, String username) {
    	 if (dbConn == null) {
             return false;
         }
        String query = "SELECT 1 FROM user WHERE phone = ? AND username !=?";
        try (PreparedStatement ps = dbConn.prepareStatement(query)) {
            ps.setString(1, phone);
            ps.setString(2, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
