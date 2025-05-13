<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Velocity Gym - Admin Dashboard</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/editProfile.css" />
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
				<div class="table-header">
					<h3>Manage Products</h3>
					<button id="addProductBtn" class="btn-edit">
						<i class="fas fa-plus"></i> Add Product
					</button>
				</div>
				<table>
					<thead>
						<tr>
							<th>Product Name</th>
							<th>Price</th>
							<th>Category</th>
							<th>Image path</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="product" items="${productList}">
							<tr>
								<td>${product.name}</td>
								<td>Rs${product.price}</td>
								<td>${product.category}</td>
								<td>${product.imagePath}</td>
								<td>
									<div class="action-buttons">
										<button class="btn-edit edit-product"
											data-id="${product.id}"
											data-name="${product.name}"
											data-price="${product.price}"
											data-category="${product.category}"
											data-image="${product.imagePath}">
											<i class="fas fa-edit"></i>
										</button>
										<button class="btn-delete delete-product"
											data-id="${product.id}">
											<i class="fas fa-trash"></i>
										</button>
									</div>
								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>
	</div>

	<!-- Modal for Add/Edit Product -->
	<div class="modal-overlay" id="productModal">
		<div class="modal">
			<div class="modal-header">
				<h4 class="modal-title" id="modalTitle">Add New Product</h4>
				<button class="close-modal">&times;</button>
			</div>
			<div class="modal-body">
				<form id="productForm"
					action="${pageContext.request.contextPath}/EditProduct"
					method="post" enctype="multipart/form-data">
					<input type="hidden" name="productId" id="productId" />
					<div class="form-group">
						<label for="productName">Product Name</label> <input type="text"
							class="form-control" name="productName" id="productName"
							placeholder="Enter product name" required />
					</div>
					<div class="form-group">
						<label for="price">Price (Rs)</label> <input type="number"
							class="form-control" name="price" id="price"
							placeholder="Enter price" step="0.01" min="0" required />
					</div>
					<div class="form-group">
						<label for="category">Category</label> <select
							class="form-control" name="category" id="category" required>
							<option value="">Select Category</option>
							<option value="Creatine">Creatine</option>
							<option value="Protein">Protein</option>
							<option value="Pre-Workout">Pre-Workout</option>
						</select>
					</div>
					<div class="form-group">
						<label for="productImage">Product Image</label> <input type="file"
							class="form-control" name="productImage" id="productImage"
							accept="image/*" />
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn-edit" id="saveProduct">Save
					Product</button>
				<button type="button" class="btn-delete close-btn">Cancel</button>
			</div>
		</div>
	</div>

	<!-- Delete Confirmation Modal -->
	<div class="modal-overlay confirm-modal" id="deleteModal">
		<div class="modal">
			<div class="modal-header">
				<h4 class="modal-title">Confirm Deletion</h4>
				<button class="close-modal">&times;</button>
			</div>
			<div class="modal-body">
				<div class="modal-icon">
					<i class="fas fa-exclamation-triangle"></i>
				</div>
				<h4>Are you sure you want to delete this product?</h4>
				<p>This action cannot be undone.</p>
			</div>
			<div class="modal-footer">
				<button class="btn-delete" id="confirmDelete">Delete</button>
				<button class="btn-edit close-btn">Cancel</button>
			</div>
		</div>
	</div>

	<script>
        // Modal functionality
        const productModal = document.getElementById('productModal');
        const deleteModal = document.getElementById('deleteModal');
        const addProductBtn = document.getElementById('addProductBtn');
        const closeButtons = document.querySelectorAll('.close-modal, .close-btn');
        const modalTitle = document.getElementById('modalTitle');
        const productForm = document.getElementById('productForm');
        
        // Add Product button
        addProductBtn.addEventListener('click', function() {
            modalTitle.textContent = 'Add New Product';
            productForm.reset();
            document.getElementById('productId').value = '';
            productModal.style.display = 'flex';
        });
        
        // Edit buttons
        const editButtons = document.querySelectorAll('.edit-product');
        editButtons.forEach(button => {
            button.addEventListener('click', function() {
                const productId = this.getAttribute('data-id');
                const productName = this.getAttribute('data-name');
                const price = this.getAttribute('data-price');
                const category = this.getAttribute('data-category');
                
                modalTitle.textContent = 'Edit Product';
                document.getElementById('productId').value = productId;
                document.getElementById('productName').value = productName;
                document.getElementById('price').value = price;
                document.getElementById('category').value = category;
                
                productModal.style.display = 'flex';
            });
        });
        
        // Delete buttons
        const deleteButtons = document.querySelectorAll('.delete-product');
        let productIdToDelete = null;
        
        deleteButtons.forEach(button => {
            button.addEventListener('click', function() {
                productIdToDelete = this.getAttribute('data-id');
                deleteModal.style.display = 'flex';
            });
        });
        
        // Close modals
        closeButtons.forEach(button => {
            button.addEventListener('click', function() {
                productModal.style.display = 'none';
                deleteModal.style.display = 'none';
            });
        });
        
        // Save product
        document.getElementById('saveProduct').addEventListener('click', function() {
            // Submit the form
            document.getElementById('productForm').submit();
        });
        
        // Confirm delete
        document.getElementById('confirmDelete').addEventListener('click', function() {
            // Create and submit a form to delete the product
            const deleteForm = document.createElement('form');
            deleteForm.method = 'POST';
            deleteForm.action = '${pageContext.request.contextPath}/DeleteProduct';
            
            const productIdInput = document.createElement('input');
            productIdInput.type = 'hidden';
            productIdInput.name = 'productId';
            productIdInput.value = productIdToDelete;
            
            deleteForm.appendChild(productIdInput);
            document.body.appendChild(deleteForm);
            deleteForm.submit();
            
            deleteModal.style.display = 'none';
        });
        
        // Close modals when clicking outside
        window.addEventListener('click', function(event) {
            if (event.target === productModal) {
                productModal.style.display = 'none';
            }
            if (event.target === deleteModal) {
                deleteModal.style.display = 'none';
            }
        });
    </script>

</body>
</html>