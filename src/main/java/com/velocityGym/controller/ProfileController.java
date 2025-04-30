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
 * Servlet implementation class Profile
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
