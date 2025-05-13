package com.velocityGym.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.velocityGym.model.UserModel;
import com.velocityGym.service.ProfileService;
import com.velocityGym.util.CookieUtil;

/**
 * ProfileController handles requests related to viewing the profile of a
 * logged-in user.
 * 
 * It uses the session to retrieve the username of the current user and fetches
 * user details using the ProfileService. It also reads the user's role from a
 * cookie and passes both user data and role to the profile JSP for rendering.
 * 
 * This servlet maps to "/profile" and supports both GET (mainly used) and POST
 * (calls GET) requests.
 * 
 * Dependencies: - ProfileService: to fetch user details - CookieUtil: to
 * retrieve the user's role from cookies
 * 
 * Forward destination: WEB-INF/pages/profile.jsp
 * 
 * Author: Anish Shrestha
 */

@WebServlet("/profile")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProfileService profileService = new ProfileService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Handles HTTP GET requests to display the user's profile page.
	 * 
	 * - Retrieves the current user's username from the session. - Redirects to the
	 * login page if no user is logged in. - Uses ProfileService to fetch full user
	 * details based on username. - Retrieves the user's role from cookies using
	 * CookieUtil. - Passes both user details and role to the JSP for rendering.
	 * 
	 * @param request  The HttpServletRequest object.
	 * @param response The HttpServletResponse object.
	 * @throws ServletException If the request cannot be handled.
	 * @throws IOException      If an input or output error occurs while handling
	 *                          the request.
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = (String) request.getSession().getAttribute("username");
		if (username == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		// get user data
		UserModel user = profileService.getUserDetails(username);

		if (user != null) {
			request.setAttribute("user", user); // set user in request scope
		}
		String role = null;
		Cookie roleCookie = CookieUtil.getCookie(request, "role");
		if (roleCookie != null) {
			role = roleCookie.getValue();
		}
		request.setAttribute("role", role);

		request.getRequestDispatcher("WEB-INF/pages/profile.jsp").forward(request, response);
	}

}
