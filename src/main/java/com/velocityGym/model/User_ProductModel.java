package com.velocityGym.model;
/**
 * User_ProductModel represents the relationship between a user and a product
 * in the Velocity Gym system. It stores information about which user purchased
 * which product, the quantity purchased, and the date of purchase.
 * 
 * This model can be used to track purchases, generate invoices, or display user purchase history.
 */

public class User_ProductModel {
	private int userId;
	private int productId;
	private int quantity;
	private String date;

	/**
	 * Constructs a User_ProductModel with user ID, product ID, and date.
	 * 
	 * @param userId    ID of the user who purchased the product
	 * @param productId ID of the product purchased
	 * @param date      Date of purchase
	 */
	public User_ProductModel(int userId, int productId, String date) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.date = date;
	}

	/**
	 * Gets the user ID.
	 * 
	 * @return user ID
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Sets the user ID.
	 * 
	 * @param userId ID of the user
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Gets the product ID.
	 * 
	 * @return product ID
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * Gets the quantity of the product purchased.
	 * 
	 * @return quantity
	 */

	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * Gets the quantity of the product purchased.
	 * 
	 * @return quantity
	 */

	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity of the product.
	 * 
	 * @param quantity number of items purchased
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the purchase date.
	 * 
	 * @return date of purchase
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the purchase date.
	 * 
	 * @param date date of purchase
	 */
	public void setDate(String date) {
		this.date = date;
	}

}
