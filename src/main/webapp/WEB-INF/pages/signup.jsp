<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/signup.css">
</head>
<body>
    <div class="wrapper">
        <div class="signup-card">
            <h1>Velocity</h1>
            <p class="tagline">Create your account<br>and begin your journey</p>

            <form action="signup" method="post" enctype="multipart/form-data">
                <div class="form-row">
                    <div class="form-column">
                        <label for="username">Username</label>
                        <input type="text" id="username" name="username" placeholder="Choose a username"
                               value="${usernameValue}" required>
                        <div class="error">${usernameError}</div>
                    </div>
                    <div class="form-column">
                        <label for="name">Full Name</label>
                        <input type="text" id="name" name="name" placeholder="Enter your full name"
                               value="${nameValue}" required>
                        <div class="error">${nameError}</div>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-column">
                        <label for="phone">Phone</label>
                        <input type="tel" id="phone" name="phone" placeholder="Enter your phone number"
                               value="${phoneValue}" required>
                        <div class="error">${phoneError}</div>
                    </div>
                    <div class="form-column">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" placeholder="Enter your email"
                               value="${emailValue}" required>
                        <div class="error">${emailError}</div>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-column">
                        <label for="password">Password</label>
                        <input type="password" id="password" name="password" placeholder="Create a password" required>
                        <div class="error">${passwordError}</div>
                    </div>
                    <div class="form-column">
                        <label for="confirmPassword">Confirm Password</label>
                        <input type="password" id="confirmPassword" name="confirmPassword"
                               placeholder="Confirm your password" required>
                        <div class="error">${confirmPasswordError}</div>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-column">
                        <label>Gender</label>
                        <div style="display: flex; gap: 10px; color: #cccccc; font-size: 14px;">
                            <label><input type="radio" name="gender" value="male"
                                    ${genderValue == 'male' ? 'checked' : ''}> Male</label>
                            <label><input type="radio" name="gender" value="female"
                                    ${genderValue == 'female' ? 'checked' : ''}> Female</label>
                        </div>
                        <div class="error">${genderError}</div>
                    </div>
                    <div class="form-column">
                        <label for="dob">Date of Birth</label>
                        <input type="date" id="dob" name="dob" value="${dobValue}" required>
                        <div class="error">${dobError}</div>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-column full-width">
                        <label for="imagePath">Profile Image</label>
                        <input type="file" id="imagePath" name="imagePath" accept="image/*" required>
                        <small>Optional: Upload a profile picture</small>
                    </div>
                </div>

                <button type="submit">Sign Up</button>
            </form>

            <div class="links">
                <a href="login">Already have an account?</a>
            </div>
        </div>
    </div>
</body>
</html>
