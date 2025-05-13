package com.velocityGym.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.velocityGym.config.DbConfig;

/**
 * DashboardService provides statistical data and recent activity summaries for
 * the Velocity Gym dashboard. It gathers information such as product counts,
 * sales statistics, trending categories, and recent orders by querying the
 * database.
 */

public class DashboardService {
	/**
	 * Retrieves various dashboard statistics such as product counts by category,
	 * total sales amount, number of orders today, most sold product, and trending
	 * category.
	 *
	 * @return an ArrayList of Strings containing different dashboard statistics
	 * @throws Exception if a database error occurs
	 */

	public ArrayList<String> getDashboardStats() throws Exception {
		ArrayList<String> output = new ArrayList<>();
		String totalProduct = "SELECT COUNT(*) FROM product";
		String creatine = "SELECT COUNT(*) FROM product WHERE category ='Creatine'";
		String protein = "SELECT COUNT(*) FROM product WHERE category ='Protein'";
		String pre = "SELECT COUNT(*) FROM product WHERE category ='Pre-Workout'";
		String totalSales = "SELECT SUM(p.price) FROM user_product up JOIN product p ON up.product_id = p.product_id";
		String noOfOrders = "SELECT COUNT(*) FROM user_product WHERE DATE(date) = CURDATE();";
		String mostSold = "SELECT p.product_name, SUM(up.quantity) AS total_sold  FROM user_product up "
				+ "JOIN product p ON up.product_id = p.product_id GROUP BY p.product_id "
				+ "ORDER BY total_sold DESC LIMIT 1";
		String trend = "SELECT p.category, SUM(up.quantity) AS total_sold " + "FROM user_product up "
				+ "JOIN product p ON up.product_id = p.product_id " + "GROUP BY p.category "
				+ "ORDER BY total_sold DESC " + "LIMIT 1";

		try (Connection conn = DbConfig.getDbConnection()) {
			output.add(String.valueOf(getIntResult(conn, totalProduct)));
			output.add(String.valueOf(getIntResult(conn, creatine)));
			output.add(String.valueOf(getIntResult(conn, protein)));
			output.add(String.valueOf(getIntResult(conn, pre)));
			output.add(String.valueOf(getIntResult(conn, totalSales)));
			output.add(String.valueOf(getIntResult(conn, noOfOrders)));
			output.add(String.valueOf(getStringResult(conn, mostSold)));
			output.add(String.valueOf(getStringResult(conn, trend)));
		}
		return output;
	}

	/**
	 * Executes a query that returns a single integer result (e.g., count or sum).
	 *
	 * @param conn  the active database connection
	 * @param query the SQL query to be executed
	 * @return the integer result from the query or 0 if not found
	 * @throws SQLException if a database access error occurs
	 */

	private String getStringResult(Connection conn, String query) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
			if (rs.next()) {
				return rs.getString(1);
			}
		}
		return null;
	}

	/**
	 * Executes a query that returns a single integer result (e.g., count or sum).
	 *
	 * @param conn  the active database connection
	 * @param query the SQL query to be executed
	 * @return the integer result from the query or 0 if not found
	 * @throws SQLException if a database access error occurs
	 */

	private int getIntResult(Connection conn, String query) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
			if (rs.next()) {
				return rs.getInt(1);
			}
		}
		return 0;
	}

	/**
	 * Retrieves the five most recent product orders with member name, product name,
	 * and total amount spent.
	 *
	 * @return a list of recent orders, where each order is a list of strings
	 * @throws Exception if a database error occurs
	 */

	public List<List<String>> getRecentOrders() throws Exception {
		List<List<String>> orders = new ArrayList<>();

		String query = "SELECT up.order_id, u.full_name AS member_name, p.product_name, "
				+ "(p.price * up.quantity) AS amount " + "FROM user_product up "
				+ "JOIN user u ON up.user_id = u.user_id " + "JOIN product p ON up.product_id = p.product_id "
				+ "ORDER BY up.date DESC " + "LIMIT 5";

		try (Connection conn = DbConfig.getDbConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				List<String> orderDetails = new ArrayList<>();
				orderDetails.add(String.valueOf(rs.getInt("order_id"))); // Order ID
				orderDetails.add(rs.getString("member_name")); // Member Name
				orderDetails.add(rs.getString("product_name")); // Product Name
				orderDetails.add("Rs. " + rs.getDouble("amount")); // Amount

				orders.add(orderDetails); // Add this order's details to the outer list
			}
		}

		return orders;
	}
}
