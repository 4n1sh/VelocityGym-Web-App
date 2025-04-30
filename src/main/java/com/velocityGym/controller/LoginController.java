package com.velocityGym.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.velocityGym.model.UserModel;
import com.velocityGym.service.LoginService;
import com.velocityGym.util.CookieUtil;
import com.velocityGym.util.SessionUtil;

/**
 * LoginController handles user login requests for the VelocityGym web application.
 * 
 * It is mapped to the "/login" URL and manages both GET and POST requests:
 * 
 * - GET: Displays the login page (login.jsp).
 * - POST: Processes login credentials, authenticates the user via LoginService,
 *         and sets up session and cookie data based on the user role.
 * 
 * If login is successful, the user is redirected to the appropriate dashboard
 * (admin or regular user). If authentication fails or a database error occurs,
 * the login page is shown again with appropriate error messages.
 * @author Anish Shrestha
 * LMU ID 23048634	
 */

@WebServlet(asyncSupported = true, urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");

	    UserModel userModel = new UserModel(password, username);

	    LoginService loginService = new LoginService();
	    Boolean loginResult = loginService.loginUser(userModel);
	    String role =loginService.getDbRole(userModel);


	    if (loginResult == null) {
	        request.setAttribute("error", "Database connection error.");
	        request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
	    } else if (loginResult) {
	        // Set session attribute
	        SessionUtil.setAttribute(request, "username", username);

	        // Set role-based cookie and redirect accordingly
	        if (role.equals("admin")) {
	            CookieUtil.addCookie(response, "role", role, 5 * 30); // 150 seconds
	            response.sendRedirect(request.getContextPath() + "/dashboard");
	        } else {
	           CookieUtil.addCookie(response, "role", role, 5 * 30);
	            response.sendRedirect(request.getContextPath() + "/home");
	        }
	    } else {
	        request.setAttribute("usernameValue", username);
	        request.setAttribute("error", "Invalid username or password.");
	        request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
	    }
	}



}
