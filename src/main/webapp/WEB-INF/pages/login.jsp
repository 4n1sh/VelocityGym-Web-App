<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<div class="wrapper">
    <div class="login-card">
        <h1>Velocity</h1>
        <p class="tagline">Transform your body,<br>transform your life</p>
        
        <form action="login" method="post">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" placeholder="Enter your username" value="${usernameValue}"required>

            <label for="password">Password</label>
            <input type="password" name="password" id="password" placeholder="Enter your password" required>
            
            <c:if test="${not empty error}">
                <div class="error-message">${error}</div>
            </c:if>

            <button type="submit">Login</button>
        </form>

        <div class="links">
            <a href="#">Forgot password?</a>
            <span>·</span>
            <a href="signup">Create account</a>
        </div>
    </div>
</div>
</body>
</html>
