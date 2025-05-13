package com.velocityGym.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.velocityGym.model.ProductModel;
import com.velocityGym.model.UserModel;
import com.velocityGym.model.User_ProductModel;
import com.velocityGym.service.BuyService;
import com.velocityGym.service.ProductService;
import com.velocityGym.service.ProfileService;

/**
 * ProductController handles both displaying and purchasing products within the
 * Velocity Gym web application. It supports search and category-based filtering
 * of products and allows logged-in users to buy a product by submitting a POST
 * request.
 * 
 * It interacts with the ProductService to fetch product data and the BuyService
 * to record a user's purchase. It also uses ProfileService to identify the user
 * based on the session.
 * 
 * This servlet maps to "/product" and supports both GET and POST methods.
 * 
 * @author
 */

@WebServlet("/product")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductService();
	ProfileService profileService = new ProfileService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Handles HTTP GET requests to display the list of available products.
	 * 
	 * Retrieves filtered products using ProductService and forwards the result to
	 * the product.jsp page for rendering.
	 * 
	 * @param request  The HttpServletRequest object containing the request
	 *                 parameters.
	 * @param response The HttpServletResponse object used to return the response.
	 * @throws ServletException If the request cannot be handled.
	 * @throws IOException      If an input or output error is detected when the
	 *                          servlet handles the request.
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String search = request.getParameter("search");
		String category = request.getParameter("category");
		request.setAttribute("searchValue", search);
		request.setAttribute("categoryValue", category);
		List<ProductModel> productList = productService.getProducts(search, category);

		request.setAttribute("productList", productList);
		request.getRequestDispatcher("WEB-INF/pages/product.jsp").forward(request, response);
	}

	/**
	 * Handles HTTP POST requests to process product purchases by users.
	 * 
	 * If the purchase is successful, it stores a success message in the session and
	 * redirects back to the product page.
	 * 
	 * @param request  The HttpServletRequest object containing the purchase
	 *                 details.
	 * @param response The HttpServletResponse object used to return the response.
	 * @throws ServletException If the request cannot be handled.
	 * @throws IOException      If an input or output error occurs.
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("productId"));
		String username = (String) request.getSession().getAttribute("username");

		if (username == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Format as "YYYY-MM-DD"
		String formattedDate = currentDate.format(formatter);
		UserModel user = profileService.getUserDetails(username);
		User_ProductModel order = new User_ProductModel(user.getId(), productId, formattedDate);
		try {
			BuyService buy = new BuyService();
			buy.savePurchase(order);
			request.getSession().setAttribute("successMessage", "Product bought successfully!");
			response.sendRedirect("product");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
