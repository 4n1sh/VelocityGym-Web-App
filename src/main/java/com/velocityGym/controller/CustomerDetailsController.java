package com.velocityGym.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.velocityGym.model.UserModel;
import com.velocityGym.service.CustomerService;

/**
 * Servlet implementation class CustomerDetails
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/CustomerDetails" })
public class CustomerDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       CustomerService customerService = new CustomerService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerDetailsController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Handles HTTP GET requests to display customer details in the admin panel.
     * Retrieves a list of all users from the service layer and sets it as a request attribute.
     * Forwards the request to "customerDetails.jsp" for rendering.
     *
     * @param request  the HttpServletRequest object that contains the client's request
     * @param response the HttpServletResponse object that contains the servlet's response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input or output error is detected
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<UserModel> users = customerService.getUserData();
		request.setAttribute("customerList", users);
		request.getRequestDispatcher("WEB-INF/pages/admin/customerDetails.jsp").forward(request, response);
	}
}
