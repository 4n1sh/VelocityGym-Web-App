package com.velocityGym.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.velocityGym.config.DbConfig;
import com.velocityGym.model.ProductModel;

/**
 * Service class for handling product-related operations such as retrieval,
 * addition, updating, and deletion from the database.
 */

public class ProductService {
	/**
	 * Retrieves a list of products filtered by optional search keyword and
	 * category.
	 *
	 * @param search   Optional keyword to search in product names.
	 * @param category Optional category to filter products.
	 * @return A list of matching ProductModel objects.
	 */

	public List<ProductModel> getProducts(String search, String category) {

		List<ProductModel> products = new ArrayList<>();

		String query = "SELECT * FROM product WHERE 1=1";

		if (search != null && !search.isEmpty()) {
			query += " AND product_name LIKE ?";
		}
		if (category != null && !category.isEmpty()) {
			query += " AND category = ?";
		}
		try (Connection conn = DbConfig.getDbConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
			int index = 1;
			if (search != null && !search.isEmpty()) {
				ps.setString(index++, "%" + search + "%");
			}
			if (category != null && !category.isEmpty()) {
				ps.setString(index, category);
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				products.add(new ProductModel(rs.getInt("product_id"), rs.getString("product_name"),
						rs.getDouble("price"), rs.getString("product_image"), rs.getString("category")));
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	/**
	 * Adds a new product to the database.
	 *
	 * @param name      Name of the product.
	 * @param price     Price of the product.
	 * @param category  Category of the product.
	 * @param imagePath Path to the product's image.
	 * @throws SQLException           If a database access error occurs.
	 * @throws ClassNotFoundException If the database driver is not found.
	 */

	public void addProduct(String name, double price, String category, String imagePath)
			throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO product (product_name, price, category, product_image) VALUES (?, ?, ?, ?)";
		try (Connection conn = DbConfig.getDbConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, name);
			stmt.setDouble(2, price);
			stmt.setString(3, category);
			stmt.setString(4, imagePath);
			stmt.executeUpdate();
		}
	}

	/**
	 * Updates an existing product's details. Updates image only if imagePath is
	 * provided.
	 *
	 * @param id        ID of the product to update.
	 * @param name      New name of the product.
	 * @param price     New price of the product.
	 * @param category  New category of the product.
	 * @param imagePath Optional new image path.
	 * @throws SQLException           If a database access error occurs.
	 * @throws ClassNotFoundException If the database driver is not found.
	 */

	public void updateProduct(int id, String name, double price, String category, String imagePath)
			throws SQLException, ClassNotFoundException {
		String sql = (imagePath != null)
				? "UPDATE product SET product_name = ?, price = ?, category = ?, product_image = ? WHERE product_id = ?"
				: "UPDATE product SET product_name = ?, price = ?, category = ? WHERE product_id = ?";

		try (Connection conn = DbConfig.getDbConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, name);
			stmt.setDouble(2, price);
			stmt.setString(3, category);
			if (imagePath != null) {
				stmt.setString(4, imagePath);
				stmt.setInt(5, id);
			} else {
				stmt.setInt(4, id);
			}
			stmt.executeUpdate();
		}
	}

	/**
	 * Deletes a product from the database based on its ID.
	 *
	 * @param id ID of the product to delete.
	 * @throws SQLException           If a database access error occurs.
	 * @throws ClassNotFoundException If the database driver is not found.
	 */

	public void deleteProduct(int id) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM product WHERE product_id = ?";
		try (Connection conn = DbConfig.getDbConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
	}

}
