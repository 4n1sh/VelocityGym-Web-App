<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Velocity</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/about.css">
</head>
<body>
    <!-- Header -->
 <jsp:include page ="header.jsp"/>

    <!-- Hero Banner -->
    <section class="hero" id="home">
        <div class="container">
            <h1>FUEL YOUR FITNESS JOURNEY</h1>
            <p>Premium supplements for maximum results</p>
            <a href="product" class="btn">Shop Now</a>
        </div>
    </section>

    <!-- Featured Supplements -->
    <section class="supplements" id="supplements">
        <div class="container">
            <h2 class="section-title">OUR TOP PRODUCTS</h2>
            <div class="products-grid">
                <div class="product-card">
                    <img src="${pageContext.request.contextPath}/resources/products/ONwhey.png">
                    <h3>ON Whey Protein</h3>
                    <p class="price">Rs.2,499</p>
                    <a href="product" class="btn">View Details</a>
                </div>
                <div class="product-card">
                    <img src="${pageContext.request.contextPath}/resources/products/MBcreatine.png" alt="Creatine">
                    <h3>MB Creatine Monohydrate</h3>
                    <p class="price">Rs.1,199</p>
                    <a href="product" class="btn">View Details</a>
                </div>
                <div class="product-card">
                    <img src="${pageContext.request.contextPath}/resources/products/legionpreworkout.png" alt="Pre-Workout">
                    <h3>ON Pre-Workout</h3>
                    <p class="price">Rs.1,799</p>
                    <a href="product" class="btn">View Details</a>
                </div>
            </div>
        </div>
    </section>

    <!-- Membership Section -->
    <section class="membership" id="membership">
        <div class="container">
            <h2 class="section-title">MEMBERSHIP PLANS</h2>
            <div class="plans-grid">
                <div class="plan-card">
                    <h3>BASIC</h3>
                    <p class="price">Rs499<span>/month</span></p>
                    <ul>
                        <li>5% Discount on all products</li>
                        <li>Free delivery on orders above Rs2000</li>
                        <li>Monthly newsletter</li>
                    </ul>
                    <a href="#signup-basic" class="btn">Join Now</a>
                </div>
                <div class="plan-card featured">
                    <div class="popular-tag">MOST POPULAR</div>
                    <h3>PRO</h3>
                    <p class="price">Rs999<span>/month</span></p>
                    <ul>
                        <li>10% Discount on all products</li>
                        <li>Free delivery on all orders</li>
                        <li>Exclusive product launches</li>
                        <li>Monthly free product sample</li>
                    </ul>
                    <a href="#signup-pro" class="btn">Join Now</a>
                </div>
                <div class="plan-card">
                    <h3>ELITE</h3>
                    <p class="price">Rs1,999<span>/month</span></p>
                    <ul>
                        <li>15% Discount on all products</li>
                        <li>Free express delivery</li>
                        <li>Personal supplement consultation</li>
                        <li>Monthly supplement box (Rs2000 value)</li>
                    </ul>
                    <a href="#signup-elite" class="btn">Join Now</a>
                </div>
            </div>
        </div>
    </section>

    <!-- About Section -->
    <section class="about" id="about">
        <div class="container">
            <div class="about-content">
                <h2 class="section-title">WHY CHOOSE VELOCITY?</h2>
                <p>MuscleFuel is committed to providing the highest quality sports nutrition supplements. All our products are rigorously tested for purity and potency to ensure you get exactly what's on the label.</p>
                <div class="features">
                    <div class="feature">
                        <h3>100% Authentic</h3>
                        <p>Direct from manufacturers with verified authenticity</p>
                    </div>
                    <div class="feature">
                        <h3>Lab Tested</h3>
                        <p>Third-party tested for quality assurance</p>
                    </div>
                    <div class="feature">
                        <h3>Expert Advice</h3>
                        <p>Guidance from certified nutritionists</p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <jsp:include page ="footer.jsp"/>
</body>
</html>