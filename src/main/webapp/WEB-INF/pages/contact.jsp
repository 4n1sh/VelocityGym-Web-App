<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contact Us | Velocity Gym</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/contact.css">
</head>
<body>
    <!-- Header -->
    <jsp:include page="header.jsp"/>

    <section class="contact-section">
        <div class="container">
            <div class="contact-content">
                <div class="contact-text">
                    <h1>We are here to help!</h1>
                    <p>Let us know how we can best serve you. Use the contact form to email us or select from the topics below that best fit your needs. It's an honor to support you in your journey towards better health.</p>
                </div>
                <form class="contact-form" action="ContactServlet" method="post">
                    <input type="text" name="name" placeholder="Name" required>
                    <input type="email" name="email" placeholder="Email" required>
                    <input type="text" name="phone" placeholder="Phone number" required>
                    <textarea name="message" placeholder="Comment" required></textarea>
                    <button type="submit" class="btn">Send Message</button>
                </form>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <jsp:include page="footer.jsp"/>
</body>
</html>
