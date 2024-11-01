<%@page import="model.Product"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="java.util.List"%>
<%@page import="form.CategoryFormBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f9;
	margin: 0;
	padding: 20px;
}

h2 {
	text-align: center;
	color: #333;
	font-size: 24px;
	margin-bottom: 10px;
}

hr {
	border: 1px solid #ccc;
	margin: 20px auto;
	width: 80%;
}

.search, .add-product {
	display: flex;
	align-items: center;
	justify-content: center;
	margin-bottom: 20px;
}

.search input[type="text"], .add-product input[type="text"],
	.add-product input[type="file"] {
	padding: 8px;
	margin-right: 10px;
	width: 200px;
	border: 1px solid #ddd;
	border-radius: 4px;
}

.search input[type="submit"], .add-product input[type="submit"] {
	padding: 8px 12px;
	color: #fff;
	background-color: #007bff;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.search input[type="submit"]:hover, .add-product input[type="submit"]:hover
	{
	background-color: #0056b3;
}

table {
	width: 80%;
	margin: 0 auto;
	border-collapse: collapse;
	border: 1px solid #ddd;
}

th, td {
	border: 1px solid #ddd;
	padding: 12px;
	text-align: center;
}

th {
	background-color: #f2f2f2;
	color: #333;
}

tr:nth-child(even) {
	background-color: #f9f9f9;
}

tr:hover {
	background-color: #f1f1f1;
}

.action-btn {
	padding: 6px 12px;
	color: #fff;
	background-color: #dc3545;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.add {
	background-color: #008000;
}


.action-btn:hover {
	background-color: #c82333;
}
.add:hover {
	background-color: #008000;
}
img {
	width: 50px;
	height: auto;
	border-radius: 5px;
}
</style>
</head>
<body>
	<h2>Products List</h2>
	<hr />

	<!-- Search Form -->
	<div class="search">
		<form method="post" action="products">
			<input type="text" name="name" placeholder="Search by name" required />
			<input type="submit" value="Search" name="search" />
		</form>
	</div>

	<!-- Products Table -->
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Price</th>
				<th>Photo</th>
				<th>Category</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<!-- Add New Product Form -->
			<form method="post" action="products" class="add-product" enctype="multipart/form-data">
				<tr>
					<td></td>
					<td><input type="text" name="name" required placeholder="Product name" /></td>
					<td><input type="text" name="price" required placeholder="Product price" /></td>
					<td>
<!-- 						<form method='post' action='uploadFile' enctype="multipart/form-data"> -->	
<!-- 							<input type="file" name="file" accept="image/*" /> <input type="submit" value="Upload" /> -->
							
<!-- 						</form>  -->
<%-- 						<c:if test="${not empty confirmation }"> --%>
<%-- 							<fmt:message key="upload.confirmation" /> --%>
<%-- 						</c:if>  --%>
							in progress
					</td>
					<td>
						<select>
							<option value="">Select Category</option>
					        <c:forEach var="category" items="${categoryList}">
					            <option value="${category.id}" name="category">${category.name}</option>
					        </c:forEach>
						</select>
					</td>
					<td><input type="submit" class="action-btn add" value="Add" name="add"/></td>
				</tr>
			</form>

			<% 
                // Fetch products from session
                List<Product> products = (List<Product>) session.getAttribute("products");
                if (products != null && !products.isEmpty()) {
                    for (Product product : products) {
            %>
			<tr>
				<td><%= product.getId() %></td>
				<td><%= product.getName() %></td>
				<td>$<%= product.getPrice() %></td>
				<td><img src="<%= product.getPhoto() %>" alt="Product Image" /></td>
				<td><%= product.getCategory().getName() %></td> 
				<td>
					<form method="post" action="products" style="display: inline;">
						<input type="hidden" name="id" value="<%= product.getId() %>" />
						<input type="submit" class="action-btn" value="Delete"
							name="delete" />
					</form>
				</td>
			</tr>
			<%
                    }
                } else {
            %>
			<tr>
				<td colspan="5">No products found.</td>
			</tr>
			<%
                }
            %>
		</tbody>
	</table>
</body>
</html>
