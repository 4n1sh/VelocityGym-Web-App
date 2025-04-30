<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Velocity Gym - Admin Dashboard</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #1a1a1a, #333333);
            color: #ffffff;
        }

        .container {
            display: flex;
            min-height: 100vh;
        }

        .sidebar {
            width: 250px;
            background-color: #1e1e1e;
            padding: 20px;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            box-shadow: 2px 0 8px rgba(0, 0, 0, 0.6);
        }

        .sidebar .nav {
            list-style-type: none;
        }

        .sidebar .nav li {
            margin-bottom: 20px;
        }

        .sidebar .nav li a {
            text-decoration: none;
            color: #ff6200;
            display: block;
            font-size: 1.3rem;
            font-weight: bold;
            padding: 12px 15px;
            border-radius: 8px;
            transition: background-color 0.3s ease, color 0.3s ease;
        }

        .sidebar .nav li a:hover {
            background-color: #ff6200;
            color: #1a1a1a;
        }

        .logout {
            margin-top: 20px;
            padding: 10px;
            background-color: #333333;
            text-align: center;
            border-radius: 8px;
            cursor: pointer;
            color: #ff6200;
            font-weight: bold;
        }

        .content {
            flex: 1;
            padding: 20px;
        }

        .table-container {
            background-color: #2a2a2a;
            padding: 20px;
            border-radius: 10px;
        }

        .table-container h3 {
            color: #ffffff;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        table th, table td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #444444;
            color: #ffffff;
        }

        table th {
            background-color: #333333;
            color: #ff6200;
        }

        table tr:hover {
            background-color: #3a3a3a;
        }

        .btn-edit, .btn-delete {
            background-color: #ff6200;
            color: white;
            padding: 8px 12px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }

        .btn-edit:hover, .btn-delete:hover {
            background-color: #e74c3c;
        }

        form {
            display: inline;
        }

        .add-product-form {
            background-color: #333333;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 20px;
        }

        .add-product-form input, .add-product-form select {
            margin-bottom: 10px;
            padding: 8px;
            width: 100%;
            border-radius: 5px;
            border: 1px solid #444444;
            background-color: #1a1a1a;
            color: #ffffff;
        }

        .add-product-form button {
            background-color: #ff6200;
            padding: 10px 15px;
            border: none;
            color: white;
            font-size: 1rem;
            font-weight: bold;
            cursor: pointer;
            border-radius: 5px;
            width: 100%;
        }

        .add-product-form button:hover {
            background-color: #e74c3c;
        }

        /* Modal Styles */
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
            padding-top: 60px;
        }

        .modal-content {
            background-color: #333333;
            margin: 5% auto;
            padding: 20px;
            border-radius: 8px;
            width: 60%;
            max-width: 500px;
        }

        .close {
            color: #aaa;
            font-size: 28px;
            font-weight: bold;
            position: absolute;
            top: 10px;
            right: 20px;
        }

        .close:hover,
        .close:focus {
            color: #ff6200;
            text-decoration: none;
            cursor: pointer;
        }

        #addProductBtn {
            margin-left: auto;
            display: block;
            margin-bottom: 20px;
        }
    </style>
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
            <button id="addProductBtn" class="btn-edit">Add Product</button>

            <div class="table-container">
                <h3>Manage Products</h3>
                <table>
                    <thead>
                        <tr>
                            <th>Product Name</th>
                            <th>Price</th>
                            <th>Category</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Dummy Data -->
                        <tr>
                            <td>Protein Powder</td>
                            <td>$29.99</td>
                            <td>Supplements</td>
                            <td>
                                <button class="btn-edit" onclick="openEditModal('1', 'Protein Powder', '29.99', 'Supplements', '')">Edit</button>
                                <form action="${pageContext.request.contextPath}/deleteProduct" method="post" style="display:inline;">
                                    <input type="hidden" name="productId" value="1" />
                                    <input type="submit" value="Delete" class="btn-delete" />
                                </form>
                            </td>
                        </tr>
                        <tr>
                            <td>Dumbbells</td>
                            <td>$49.99</td>
                            <td>Equipment</td>
                            <td>
                                <button class="btn-edit" onclick="openEditModal('2', 'Dumbbells', '49.99', 'Equipment', '')">Edit</button>
                                <form action="${pageContext.request.contextPath}/deleteProduct" method="post" style="display:inline;">
                                    <input type="hidden" name="productId" value="2" />
                                    <input type="submit" value="Delete" class="btn-delete" />
                                </form>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Modal for Add/Edit Product -->
    <div id="productModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h3 id="modalTitle">Add New Product</h3>
            <form id="productForm" action="${pageContext.request.contextPath}/addOrUpdateProduct" method="post" enctype="multipart/form-data">
                <input type="hidden" name="productId" id="productId" />
                <input type="text" name="productName" id="productName" placeholder="Product Name" required />
                <input type="number" name="price" id="price" placeholder="Price" step="0.01" min="0" required />
                <select name="category" id="category" required>
                    <option value="">Select Category</option>
                    <option value="Supplements">Supplements</option>
                    <option value="Equipment">Equipment</option>
                    <option value="Apparel">Apparel</option>
                </select>
                <input type="file" name="productImage" id="productImage" accept="image/*" />
                <button type="submit" id="submitBtn">Save Product</button>
            </form>
        </div>
    </div>

    <script>
        // Get the modal
        var modal = document.getElementById("productModal");
        var form = document.getElementById("productForm");

        // Get the button that opens the modal
        var btn = document.getElementById("addProductBtn");

        // Get the <span> element that closes the modal
        var span = document.getElementsByClassName("close")[0];

        // When the user clicks the button, open the modal in "add" mode
        btn.onclick = function() {
            document.getElementById("modalTitle").innerHTML = "Add New Product";
            document.getElementById("productId").value = "";
            document.getElementById("productName").value = "";
            document.getElementById("price").value = "";
            document.getElementById("category").value = "";
            modal.style.display = "block";
        }

        // When the user clicks on <span> (x), close the modal
        span.onclick = function() {
            modal.style.display = "none";
        }

        // Close the modal when clicking outside of it
        window.onclick = function(event) {
            if (event.target === modal) {
                modal.style.display = "none";
            }
        }

        // Open the modal with existing product data for editing
        function openEditModal(id, name, price, category, imageUrl) {
            document.getElementById("modalTitle").innerHTML = "Edit Product";
            document.getElementById("productId").value = id;
            document.getElementById("productName").value = name;
            document.getElementById("price").value = price;
            document.getElementById("category").value = category;
            modal.style.display = "block";
        }
    </script>

</body>
</html>
