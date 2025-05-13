package com.velocityGym.model;

/**
 * ProductModel represents a product in the Velocity Gym system. It contains
 * information such as the product's ID, name, price, image path, and category.
 * This model is used to display products and manage product-related operations.
 */

public class ProductModel {

	private int id;
	private String name;
	private double price;
	private String imagePath;
	private String category;

	/**
	 * Constructs a ProductModel with the specified details.
	 * 
	 * @param id        Unique identifier for the product
	 * @param name      Name of the product
	 * @param price     Price of the product
	 * @param imagePath Path to the product's image
	 * @param category  Category to which the product belongs
	 */

	public ProductModel(int id, String name, double price, String imagePath, String category) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.imagePath = imagePath;
		this.category = category;
	}

	/**
	 * Gets the product ID.
	 * 
	 * @return product ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the product ID.
	 * 
	 * @param id product ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the product name.
	 * 
	 * @return name of the product
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the product name.
	 * 
	 * @param name name of the product
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the product price.
	 * 
	 * @return price of the product
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the product price.
	 * 
	 * @param price price of the product
	 */

	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets the image path of the product.
	 * 
	 * @return image path
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * Sets the image path of the product.
	 * 
	 * @param imagePath path to the product image
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * Gets the category of the product.
	 * 
	 * @return category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the category of the product.
	 * 
	 * @param category product category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

}
