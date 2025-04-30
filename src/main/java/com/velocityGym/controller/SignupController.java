package com.velocityGym.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Enumeration;

import com.velocityGym.model.UserModel;
import com.velocityGym.service.SignupService;
import com.velocityGym.util.ImageUtil;
import com.velocityGym.util.ValidationUtil;

/**
 * SignupController handles HTTP requests related to user registration 
 * in the Velocity Gym web application. It serves as the controller in 
 * the MVC architecture, managing form input, validation, image upload, 
 * and interaction with the SignupService to persist user data.
 * 
 * This servlet supports both GET (to display the signup page) and POST 
 * (to process registration form submission) requests. It performs 
 * server-side validation and redirects the user accordingly based on 
 * the outcome.
 * 
 * @author Anish Shrestha
 * LMU ID 23048634
 */

@WebServlet(asyncSupported = true, urlPatterns = { "/signup"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final SignupService registerService = new SignupService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/pages/signup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		validateRegistrationForm(request, response);

	}
	public void validateRegistrationForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Extract all parameters
		String fullName = request.getParameter("name");
		String userName = request.getParameter("username");
		String gender = request.getParameter("gender");
		String dob = request.getParameter("dob");
		String phoneNumber = request.getParameter("phone");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		
		  // Handle image file upload
		
        Part imagePart = request.getPart("imagePath");  // Get the image part from the form
        String imageFileName = null;
        boolean imageUploaded = false;

        if (imagePart != null && imagePart.getSize() > 0) {
            ImageUtil imageUtil = new ImageUtil();
            String rootPath = request.getServletContext().getRealPath("/");
            String saveFolder = "profile_images";  // Folder name to store the images

            imageUploaded = imageUtil.uploadImage(imagePart, rootPath, saveFolder);
            if (imageUploaded) {
                // Get the uploaded image name
                imageFileName = imageUtil.getImageNameFromPart(imagePart);
            }
        }
		
		 // Validate full name
	    if (ValidationUtil.isNullOrEmpty(fullName)) {
	        request.setAttribute("nameError", "Full name is required.");
	    } else if (!ValidationUtil.isAlphabetic(fullName)) {
	        request.setAttribute("nameError", "Full name must not contain numbers or special characters.");
	    }

	    // Validate username
	    if (ValidationUtil.isNullOrEmpty(userName)) {
	        request.setAttribute("usernameError", "User name is required.");
	    } else if (!ValidationUtil.isAlphanumericStartingWithLetter(userName)) {
	        request.setAttribute("usernameError", "User name should start with a letter and can contain numbers.");
	    }

	    // Validate gender
	    if (ValidationUtil.isNullOrEmpty(gender)) {
	        request.setAttribute("genderError", "Please select a gender.");
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

	    // Validate email
	    if (ValidationUtil.isNullOrEmpty(email)) {
	        request.setAttribute("emailError", "Email is required.");
	    } else if (!ValidationUtil.isValidEmail(email)) {
	        request.setAttribute("emailError", "Invalid email format.");
	    }

	    // Validate password
	    if (ValidationUtil.isNullOrEmpty(password)) {
	        request.setAttribute("passwordError", "Password is required.");
	    } else if (!ValidationUtil.isValidPassword(password)) {
	        request.setAttribute("passwordError",
	            "Password must be at least 8 characters long, with 1 uppercase letter, 1 number, and 1 symbol.");
	    }

	    // Confirm password
	    if (ValidationUtil.isNullOrEmpty(confirmPassword)) {
	        request.setAttribute("confirmPasswordError", "Please retype the password.");
	    } else if (!ValidationUtil.doPasswordsMatch(password, confirmPassword)) {
	        request.setAttribute("confirmPasswordError", "Passwords do not match.");
	    }
	    
	    if (!ValidationUtil.isNullOrEmpty(userName) && registerService.isUsernameTaken(userName)) {
	        request.setAttribute("usernameError", "This username is already taken.");
	    }

	    if (!ValidationUtil.isNullOrEmpty(email) && registerService.isEmailTaken(email)) {
	        request.setAttribute("emailError", "This email is already registered.");
	    }

	    if (!ValidationUtil.isNullOrEmpty(phoneNumber) && registerService.isPhoneTaken(phoneNumber)) {
	        request.setAttribute("phoneError", "This phone number is already registered.");
	    }

	    // Check for any error flags
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
	        request.setAttribute("usernameValue", userName);
	        request.setAttribute("genderValue", gender);
	        request.setAttribute("dobValue", dob);
	        request.setAttribute("phoneValue", phoneNumber);
	        request.setAttribute("emailValue", email);

	        request.getRequestDispatcher("WEB-INF/pages/signup.jsp").forward(request, response);
	        return;
	    }
	    // --- IF VALID, ADD TO DATABASE ---
	    UserModel user = new UserModel(fullName,gender, phoneNumber,email,password,userName,dob,imageFileName);
	    boolean success = registerService.addUser(user);
	    if (success) {
	        response.sendRedirect(request.getContextPath() + "/login");
	    } else {
	        request.setAttribute("formError", "Something went wrong while registering. Please try again.");
	        request.getRequestDispatcher("WEB-INF/pages/signup.jsp").forward(request, response);
	    }
	}
	}


