<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Velocity Gym - Admin Dashboard</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css" />
</head>
<body>

    <div class="container">
        <!-- Sidebar -->
        <div class="sidebar">
            <ul class="nav">
            <li><a href="${pageContext.request.contextPath}/profile">Profile</a></li>
                <li><a href="${pageContext.request.contextPath}/dashboard">Dashboard</a></li>
                <li><a href="${pageContext.request.contextPath}/CustomerDetails">Customers</a></li>
                <li><a href="${pageContext.request.contextPath}/EditProduct">Edit Supplements</a></li>
                
            </ul>
            <div class="logout">
                <form action="${pageContext.request.contextPath}/logout" method="post">
                    <input type="submit" class="nav-button" value="Logout" style="background: none; border: none; color: #ff6200; font-weight: bold; cursor: pointer;" />
                </form>
            </div>
        </div>

        <!-- Content -->
        <div class="content">
            <!-- Header with Gym Metrics -->
            <div class="header">
                <div class="info-box">
                    <h3>Total Products</h3>
                    <p>${totalMembers}</p>
                </div>
                <div class="info-box">
                    <h3>Creatine</h3>
                    <p>${supplementSales}</p>
                </div>
                <div class="info-box">
                    <h3>Protein</h3>
                    <p>${activeSubscriptions}</p>
                </div>
                <div class="info-box">
                    <h3>Pre-Workout</h3>
                    <p>${classAttendance}</p>
                </div>
            </div>
              <div class="header">
                <div class="info-box">
                    <h3>Total Sales</h3>
                    <p>${totalMembers}</p>
                </div>
                <div class="info-box">
                    <h3>Total Customer</h3>
                    <p>$${supplementSales}</p>
                </div>
                <div class="info-box">
                    <h3>Most sold Product</h3>
                    <p>$${supplementSales}</p>
                </div>
                <div class="info-box">
                    <h3>No. of Orders</h3>
                    <p>$${supplementSales}</p>
                </div>

            </div>
            
            
            <!-- Recent Supplement Orders Table -->
            <div class="table-container">
                <h3>Recent Supplement Orders</h3>
                <table>
                    <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Member Name</th>
                            <th>Supplement</th>
                            <th>Amount</th>
                        </tr>
                    </thead>
                    <tbody>
                        ${recentOrdersHtml}
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</body>
</html>