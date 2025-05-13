package com.velocityGym.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AboutController
 * 
 * This servlet handles HTTP GET and POST requests for the "/about" page.
 * It forwards the requests to the "about.jsp" page located in the WEB-INF directory.
 * The AboutController provides functionality for rendering the "About" page of the application.
 * 
 * @author Anish Shrestha
 */

@WebServlet(asyncSupported = true, urlPatterns = { "/about" })
public class AboutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AboutController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Handles HTTP GET requests for the About page.
     * Forwards the request to "/WEB-INF/pages/about.jsp" to display the About section of the application.
     *
     * @param request  the HttpServletRequest object that contains the client's request
     * @param response the HttpServletResponse object that contains the servlet's response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input or output error is detected
     */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/pages/about.jsp").forward(request, response);
	}

}
