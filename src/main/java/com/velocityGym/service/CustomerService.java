package com.velocityGym.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.velocityGym.config.DbConfig;
import com.velocityGym.model.UserModel;

/**
 * CustomerService provides operations related to customer (user) data. It
 * interacts with the database to retrieve user information for administrative
 * or display purposes.
 */

public class CustomerService {
	/**
	 * Retrieves a list of all users from the database.
	 * 
	 * @return a list of UserModel objects representing the users
	 */

	public List<UserModel> getUserData() {
		List<UserModel> users = new ArrayList<>();

		String query = "SELECT * FROM user WHERE 1=1";
		try (Connection conn = DbConfig.getDbConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
			ResultSet rs = ps.executeQuery(query);
			while (rs.next()) {
				users.add(new UserModel(rs.getInt("user_id"), rs.getString("full_name"), rs.getString("phone"),
						rs.getString("email"), rs.getString("username"), rs.getString("dob")));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
}
