package com.velocityGym.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.velocityGym.service.ProductService;

/**
 * Servlet implementation class DeleteProduct
 */
@WebServlet("/DeleteProduct")
public class DeleteProductControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductControler() {
        super();
        // TODO Auto-generated constructor stub
    }


    /**
     * Handles HTTP POST requests to delete a product based on the provided product ID.
     * Calls the ProductService to perform the deletion, and then redirects to the "EditProduct" page.
     *
     * @param request  the HttpServletRequest object containing the product ID to delete
     * @param response the HttpServletResponse object used to redirect after deletion
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input or output error is detected during redirection
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int productId = Integer.parseInt(request.getParameter("productId"));
		 ProductService productService = new ProductService();
		 try {
			productService.deleteProduct(productId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 response.sendRedirect("EditProduct");
	}

}
