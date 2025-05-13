package com.velocityGym.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.velocityGym.model.ProductModel;
import com.velocityGym.service.ProductService;
import com.velocityGym.util.ImageUtil;

/**
 * EditProductController handles the addition and editing of products in the
 * admin dashboard.
 * 
 * This servlet is responsible for: - Displaying all products for editing (GET
 * request) - Handling form submissions for updating or adding products (POST
 * request) - Handling image uploads using the ImageUtil utility class
 * 
 * It supports multipart form data to allow image uploads and uses
 * ProductService for business logic.
 * 
 * URL Mapping: /EditProduct
 * 
 * Dependencies: - ProductService: for accessing product-related operations -
 * ImageUtil: for handling image upload and filename extraction
 * 
 * Author: Anish Shrestha
 */

@WebServlet(asyncSupported = true, urlPatterns = { "/EditProduct" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class EditProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditProductController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Handles HTTP GET requests to display the product list in the admin edit
	 * interface.
	 * 
	 * - Fetches all products using ProductService - Sets the product list in the
	 * request attribute - Forwards the request to the admin/editProduct.jsp view
	 * for rendering
	 * 
	 * @param request  The HttpServletRequest object
	 * @param response The HttpServletResponse object
	 * @throws ServletException If the request cannot be handled
	 * @throws IOException      If an input/output error occurs
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<ProductModel> productList = productService.getProducts(null, null);

		request.setAttribute("productList", productList);
		request.getRequestDispatcher("WEB-INF/pages/admin/editProduct.jsp").forward(request, response);
	}

	/**
	 * Handles HTTP POST requests to add or update a product entry in the system.
	 * 
	 * - Reads form data including product ID, name, price, category, and image - If
	 * an image is uploaded, it uses ImageUtil to save it on the server - If
	 * productId is not provided, adds a new product - If productId is provided,
	 * updates the existing product - Redirects back to /EditProduct after
	 * processing
	 * 
	 * @param request  The HttpServletRequest object
	 * @param response The HttpServletResponse object
	 * @throws ServletException If the request cannot be handled
	 * @throws IOException      If an input/output error occurs
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String productId = request.getParameter("productId");
			String name = request.getParameter("productName");
			double price = Double.parseDouble(request.getParameter("price"));
			String category = request.getParameter("category");
			Part imagePart = request.getPart("productImage");
			String imageFileName = null;
			boolean imageUploaded = false;
			if (imagePart != null && imagePart.getSize() > 0) {
				ImageUtil imageUtil = new ImageUtil();
				String rootPath = request.getServletContext().getRealPath("/");
				String saveFolder = "products";

				imageUploaded = imageUtil.uploadImage(imagePart, rootPath, saveFolder);
				if (imageUploaded) {
					imageFileName = imageUtil.getImageNameFromPart(imagePart);

				}
			}

			if (productId == null || productId.isEmpty()) {
				productService.addProduct(name, price, category, imageFileName);
			} else {
				productService.updateProduct(Integer.parseInt(productId), name, price, category, imageFileName);
			}

			response.sendRedirect("EditProduct");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred.");
		}
	}

}
