<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>About Us</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/about.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css">
</head>
<body>
    <jsp:include page="header.jsp"/>

    <section class="about-section">
        <div class="container">
            <div class="about-wrapper">
                <!-- Left text -->
                <div class="about-text">
                    <h1>About Velocity Gym</h1>
                    <p>At Velocity Gym, we are committed to helping you achieve your fitness goals through cutting-edge equipment, certified trainers, and high-quality supplements. Whether you're just starting out or you're a seasoned athlete, our facilities and community are here to support you every step of the way.</p>
                    <p>With a strong focus on authenticity, performance, and results, we bring you the best in gym memberships and nutritional products to fuel your journey.</p>
                </div>

                <!-- Right image -->
                <div class="about-image">
                    <img src="${pageContext.request.contextPath}/resources/about.jpg" alt="About Velocity Gym">
                </div>
            </div>
        </div>
    </section>

    <jsp:include page="footer.jsp"/>
</body>
</html>
