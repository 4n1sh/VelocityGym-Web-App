<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Edit Profile</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css" />
</head>
<body>
<div class="container">
  <!-- Left Section -->
  <div class="left-section">
    <img src="${pageContext.request.contextPath}/resources/profile_images/${user.imagePath}" alt="Profile Picture" class="profile-pic" />
    <h2>${user.fullName}</h2>
    <p>User ID: ${user.id}</p>
    <p>Username: ${user.username}</p>
  </div>

  <!-- Right Section -->
  <div class="right-section">
    <form action="${pageContext.request.contextPath}/editProfile" method="post">
      <!-- General Information -->
      <div class="info-box">
        <h3>Update Your Information</h3>

        <div class="form-group">
          <label for="fullname">Full Name</label>
          <input type="text" id="fullname" name="fullname" class="form-value" value="${nameValue}" required />
          <span style="color:red">${nameError}</span>
        </div>

        <div class="form-group">
          <label for="gender">Gender</label>
          <input type="text" id="gender" name="gender" class="form-value" value="${genderValue}" required />
          <span style="color:red">${genderError}</span>
        </div>

        <div class="form-group">
          <label for="phone">Phone Number</label>
          <input type="text" id="phone" name="phone" class="form-value" value="${phoneValue}" required />
          <span style="color:red">${phoneError}</span>
        </div>

        <div class="form-group">
          <label for="dob">Date of Birth</label>
          <input type="date" id="dob" name="dob" class="form-value" value="${dobValue}" required />
          <span style="color:red">${dobError}</span>
        </div>

        <div class="form-group">
          <label for="email">Email</label>
          <input type="email" id="email" name="email" class="form-value" value="${emailValue}" required />
          <span style="color:red">${emailError}</span>
        </div>

        <!-- General form error -->
        <c:if test="${not empty formError}">
          <div class="general-form-error">${formError}</div>
        </c:if>
      </div>

      <!-- Action Buttons -->
      <div class="action-buttons">
        <button type="submit" class="logout-btn">Update Profile</button>
        <a href="${pageContext.request.contextPath}/profile" class="logout-btn" id ="cancel">Cancel</a>
      </div>
    </form>
  </div>
</div>
</body>
</html>
