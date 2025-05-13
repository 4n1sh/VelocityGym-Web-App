package com.velocityGym.service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.velocityGym.config.DbConfig;
import com.velocityGym.model.User_ProductModel;

/**
 * BuyService handles the logic for saving a user's product purchase into the
 * database. It interacts with the user_product table and uses the database
 * configuration from DbConfig.
 */

public class BuyService {
	/**
	 * Saves the purchase details of a user into the user_product table.
	 * 
	 * @param order An instance of User_ProductModel containing purchase information
	 * @throws Exception if any SQL or database error occurs during the process
	 */

	public void savePurchase(User_ProductModel order) throws Exception {
		Connection conn = DbConfig.getDbConnection();

		String sql = "INSERT INTO user_product (user_id, product_id, date) VALUES (?, ?, ?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, order.getUserId());
		stmt.setInt(2, order.getProductId());
		stmt.setString(3, order.getDate());
		stmt.executeUpdate();
		stmt.close();
		conn.close();

	}
}
