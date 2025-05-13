<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Velocity Gym - Customers</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/customerDetails.css">
</head>
<body>

	<div class="container">
		<!-- Sidebar -->
		<div class="sidebar">
			<ul class="nav">
				<li><a href="${pageContext.request.contextPath}/profile">Profile</a></li>
				<li><a href="${pageContext.request.contextPath}/dashboard">Dashboard</a></li>
				<li><a
					href="${pageContext.request.contextPath}/CustomerDetails">Customers</a></li>
				<li><a href="${pageContext.request.contextPath}/EditProduct">Edit
						Supplements</a></li>

			</ul>
			<div class="logout">
				<form action="${pageContext.request.contextPath}/logout"
					method="post">
					<input type="submit" class="nav-button" value="Logout"
						style="background: none; border: none; color: #ff6200; font-weight: bold; cursor: pointer;" />
				</form>
			</div>
		</div>

		<!-- Content -->
		<div class="content">
			<div class="table-container">
				<h3>Customer List</h3>
				<table>
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Phone</th>
							<th>Email</th>
							<th>Username</th>
							<th>Date of Birth</th>
						</tr>
					</thead>
					<tbody>

                    <c:forEach var="customer" items="${customerList}">
                        <tr>
                            <td>${customer.id}</td>
                            <td>${customer.fullName}</td>
                            <td>${customer.phone}</td>
                            <td>${customer.email}</td>
                            <td>${customer.username}</td>
                            <td>${customer.dob}</td>
                        </tr>
                    </c:forEach>
					</tbody>
				</table>

			</div>
		</div>
	</div>

</body>
</html>
