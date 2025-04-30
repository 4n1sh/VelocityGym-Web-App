package com.velocityGym.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.velocityGym.config.DbConfig;
import com.velocityGym.model.ProductModel;

public class ProductService {

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
				products.add(new ProductModel(rs.getInt("product_id"), rs.getString("product_name"), rs.getDouble("price"),
						rs.getString("product_image"), rs.getString("category")));
			}
		}
	
	catch(Exception e)
	{
        e.printStackTrace();
    }return products;
}}
