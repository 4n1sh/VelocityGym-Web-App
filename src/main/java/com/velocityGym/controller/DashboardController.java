package com.velocityGym.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.velocityGym.service.DashboardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * DashboardController is responsible for displaying the admin dashboard.
 * 
 * This servlet retrieves and prepares dashboard statistics and recent order data
 * to be displayed on the admin panel. It interacts with the DashboardService class
 * to fetch the required data.
 * 
 * Responsibilities:
 * - Fetch and set statistics like total products, category-wise product count,
 *   total sales, number of orders, most sold product, and trending category.
 * - Fetch and display a list of recent orders.
 * 
 * Depends on:
 * - DashboardService for all business logic and data access.
 * 
 * URL Mapping: /dashboard
 * 
 * Author: Anish Shrestha
 */

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    DashboardService dashboardService = new DashboardService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Handles GET requests for the admin dashboard page.
     * 
     * - Calls DashboardService to fetch recent order data and dashboard statistics.
     * - Sets each piece of data as a request attribute to be accessed in the JSP view.
     * - Forwards the request to the dashboard.jsp page.
     * 
     * @param request  HttpServletRequest object containing the request information
     * @param response HttpServletResponse object for returning the response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			List<List<String>> recentOrder = dashboardService.getRecentOrders();
			request.setAttribute("recentOrders", recentOrder);
		    List<String> displayData = new ArrayList<>();
			displayData = dashboardService.getDashboardStats();
			request.setAttribute("totalProduct", displayData.get(0));
			request.setAttribute("totalCreatine", displayData.get(1));
			request.setAttribute("totalProtein", displayData.get(2));
			request.setAttribute("totalPre", displayData.get(3));
			request.setAttribute("totalSales", displayData.get(4));
			request.setAttribute("noOfOrder", displayData.get(5));
			request.setAttribute("mostSold", displayData.get(6));
			request.setAttribute("trend", displayData.get(7));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.getRequestDispatcher("WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
    
	}

}
