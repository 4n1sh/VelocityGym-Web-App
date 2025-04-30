package com.velocityGym.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Enumeration;

import com.velocityGym.model.UserModel;
import com.velocityGym.service.ProfileService;
import com.velocityGym.util.ValidationUtil;

/**
 * Servlet implementation class EditProfile
 */
@WebServlet("/editProfile")
public class EditProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProfileService profileService = new ProfileService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = (String) request.getSession().getAttribute("username");
		 if (username == null) {
	            response.sendRedirect(request.getContextPath()+"/login");
	            return;
	        }

	        // get user data
	        UserModel user = profileService.getUserDetails(username);

	        if (user != null) {
	        	request.setAttribute("user", user); 
	        	request.setAttribute("nameValue", user.getFullName());
		        request.setAttribute("genderValue", user.getGender());
		        request.setAttribute("dobValue", user.getDob());
		        request.setAttribute("phoneValue", user.getPhone());
		        request.setAttribute("emailValue", user.getEmail());// set user in request scope
	        }

	        request.getRequestDispatcher("WEB-INF/pages/editProfile.jsp").forward(request, response);
	    }
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		validateUpdate(request, response);
	}
	public void validateUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    String username = (String) request.getSession().getAttribute("username");

	    UserModel user1 = profileService.getUserDetails(username);
	    request.setAttribute("user", user1);
	    
		String fullName = request.getParameter("fullname");
		String gender = request.getParameter("gender");
		String dob = request.getParameter("dob");
		String phoneNumber = request.getParameter("phone");
		String email = request.getParameter("email");
		
		
		 // Validate full name
	    if (ValidationUtil.isNullOrEmpty(fullName)) {
	        request.setAttribute("nameError", "Full name is required.");
	    } else if (!ValidationUtil.isAlphabetic(fullName)) {
	        request.setAttribute("nameError", "Full name must not contain numbers or special characters.");
	    }
	    
	    if (ValidationUtil.isNullOrEmpty(gender)) {
	        request.setAttribute("genderError", "Please select a gender.");
	    } else if (!ValidationUtil.isValidGender(gender)) {
	        request.setAttribute("genderError", "Gender must be either 'Male' or 'Female'.");
	    }

	    // Validate DOB
	    if (ValidationUtil.isNullOrEmpty(dob)) {
	        request.setAttribute("dobError", "Date of birth is required.");
	    } else {
	        try {
	            LocalDate birthDate = LocalDate.parse(dob);
	            if (birthDate.isAfter(LocalDate.now())) {
	                request.setAttribute("dobError", "Date of birth cannot be in the future.");
	            }
	        } catch (DateTimeException e) {
	            request.setAttribute("dobError", "Invalid date format. Please use yyyy-MM-dd.");
	        }
	    }
	 // Validate phone number
	    if (ValidationUtil.isNullOrEmpty(phoneNumber)) {
	        request.setAttribute("phoneError", "Phone number is required.");
	    } else if (!ValidationUtil.isValidPhoneNumber(phoneNumber)) {
	        request.setAttribute("phoneError", "Phone number must start with '98' and be 10 digits long.");
	    }
	    if (ValidationUtil.isNullOrEmpty(email)) {
	        request.setAttribute("emailError", "Email is required.");
	    } else if (!ValidationUtil.isValidEmail(email)) {
	        request.setAttribute("emailError", "Invalid email format.");
	    }
	    if (!ValidationUtil.isNullOrEmpty(email) && profileService.isEmailTaken(email, username)) {
	        request.setAttribute("emailError", "This email is already registered.");
	    }

	    if (!ValidationUtil.isNullOrEmpty(phoneNumber) && profileService.isPhoneTaken(phoneNumber, username)) {
	        request.setAttribute("phoneError", "This phone number is already registered.");
	    }
	    boolean hasErrors = false;
	    Enumeration<String> attrNames = request.getAttributeNames();
	    while (attrNames.hasMoreElements()) {
	        String attr = attrNames.nextElement();
	        if (attr.endsWith("Error")) {
	            hasErrors = true;
	            break;
	        }
	    }
	    if (hasErrors) {
	        // Repopulate form fields on error
	        request.setAttribute("nameValue", fullName);
	        request.setAttribute("genderValue", gender);
	        request.setAttribute("dobValue", dob);
	        request.setAttribute("phoneValue", phoneNumber);
	        request.setAttribute("emailValue", email);
	        
	        request.getRequestDispatcher("WEB-INF/pages/editProfile.jsp").forward(request, response);
	        return;
	    }

	    UserModel user = new UserModel(fullName,gender, phoneNumber,email,dob);
	    boolean success = profileService.editUser(user,username);
	    if (success) {
	    	
	        response.sendRedirect(request.getContextPath() + "/profile");
	    } else {
	        request.setAttribute("formError", "Something went wrong while registering. Please try again.");
	        request.getRequestDispatcher("WEB-INF/pages/signup.jsp").forward(request, response);
	    }
	}

}
