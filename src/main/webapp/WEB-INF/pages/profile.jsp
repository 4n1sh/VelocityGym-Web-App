<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.velocityGym.model.UserModel"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Velocity - User Profile</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/profile.css" />
</head>
<body>

<div style="position: absolute; top: 20px; right: 20px;">
    <c:choose>
        <c:when test="${role == 'admin'}">
            <a href="${pageContext.request.contextPath}/dashboard" class="logout-btn" style="text-decoration: none;">Go to Dashboard</a>
        </c:when>
        <c:otherwise>
            <a href="${pageContext.request.contextPath}/home" class="logout-btn" style="text-decoration: none;">Back to Home</a>
        </c:otherwise>
    </c:choose>
</div>

	

	<div class="container">
		<!-- Left Section -->
		<div class="left-section">
			<img
				src="${pageContext.request.contextPath}/resources/profile_images/${user.imagePath}"
				alt="Profile Picture" class="profile-pic" />

			<h2>${user.fullName}</h2>
			<p>User ID: ${user.id}</p>
			<p>Username: ${user.username}</p>

			<!-- Logout Button -->
			<form action="${pageContext.request.contextPath}/logout"
				method="post" style="margin-top: 20px;">
				<button type="submit" class="logout-btn">Logout</button>
			</form>

		</div>

		<!-- Right Section -->
		<div class="right-section">
			<!-- General Information -->
			<div class="info-box">
				<h3>General Information</h3>
				<div class="form-group">
					<label>Full Name</label>
					<div class="form-value">${user.fullName}</div>
				</div>
				<div class="form-group">
					<label>Gender</label>
					<div class="form-value">${user.gender}</div>
				</div>
				<div class="form-group">
					<label>Phone Number</label>
					<div class="form-value">${user.phone}</div>
				</div>
				<div class="form-group">
					<label>Date of Birth</label>
					<div class="form-value">${user.dob}</div>
				</div>
				<div class="form-group">
					<label>Email</label>
					<div class="form-value">${user.email}</div>
				</div>
			</div>

			<!-- Other Information -->
			<div class="info-box other-info">
				<h3>Other Information</h3>
				<p>
					<strong>Purchased Products:</strong>
				</p>
				<p>Whey Protein</p>
				<p>Gym Gloves</p>
				<p>Creatine</p>
			</div>

			<!-- Edit Button -->
			<div style="text-align: right;">
				<a href="${pageContext.request.contextPath}/editProfile"
					class="logout-btn"
					style="text-decoration: none; text-align: center;">Edit
					Information</a>
			</div>
		</div>
	</div>
</body>
</html>
