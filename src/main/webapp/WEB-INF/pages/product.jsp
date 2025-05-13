<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.velocityGym.model.ProductModel"%>

<jsp:include page="header.jsp" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/product.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/footer.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/home.css">

<section class="product-section">
	<div class="container">
<%
    String successMessage = (String) session.getAttribute("successMessage");
    if (successMessage != null) {
%>
    <div id="popup-message" class="popup"><%= successMessage %></div>
<%
        session.removeAttribute("successMessage");
    }
%>

	
		<h2 class="section-title">Explore Our Supplements</h2>

		<!-- Search & Filter -->
		<form action="product" method="get" class="product-search-bar">
			<input type="text" name="search" placeholder="Search products..."
				class="search-input" value="${searchValue}">
			<%
			String selectedCategory = (String) request.getAttribute("categoryValue");
			if (selectedCategory == null)
				selectedCategory = "";
			%>
			<select name="category" class="category-select">
				<option value="" <%=selectedCategory.equals("") ? "selected" : ""%>>All
					Categories</option>
				<option value="Protein"
					<%=selectedCategory.equals("Protein") ? "selected" : ""%>>Protein</option>
				<option value="Pre-Workout"
					<%=selectedCategory.equals("Pre-Workout") ? "selected" : ""%>>Pre-Workout</option>
				<option value="Creatine"
					<%=selectedCategory.equals("Creatine") ? "selected" : ""%>>Creatine</option>
			</select>
			<button type="submit" class="search-button">Search</button>
		</form>

		<div class="product-gallery">
			<%
			List<ProductModel> products = (List<ProductModel>) request.getAttribute("productList");
			if (products != null && !products.isEmpty()) {
				for (ProductModel product : products) {
			%>
			<div class="product-card">
				<img
					src="<%=request.getContextPath() + "/resources/products/" + product.getImagePath()%>"
					alt="<%=product.getName()%>">
				<h3><%=product.getName()%></h3>
				<p class="price">
					Rs.
					<%=product.getPrice()%></p>
				<p class="product-category"><%=product.getCategory()%></p>
				<form action="product" method="post">
					<input type="hidden" name="productId" value="<%=product.getId()%>">
					<button type="submit" class="btn">Buy Now</button>
				</form>

			</div>
			<%
			}
			} else {
			%>
			<p class="no-products">No products found.</p>
			<%
			}
			%>
		</div>
	</div>
</section>
<script>
    // Hide popup after 2 seconds
    setTimeout(function () {
        var popup = document.getElementById("popup-message");
        if (popup) {
            popup.style.display = "none";
        }
    }, 2000);
</script>

<jsp:include page="footer.jsp" />
