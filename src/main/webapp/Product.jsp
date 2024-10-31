<%@page import="model.Product"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="java.util.List"%>
<%@page import="form.CategoryFormBean"%>

<html>
<head>
    <title>Categories</title>
    <style>
        table {
            width: 50%;
            border-collapse: collapse;
            margin: 20px auto;
        }
        
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }
        
        th {
            background-color: #f2f2f2;
        }
        
        div {
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <h2 style="text-align: center;">Products List</h2>
    <hr />
	<%List<Product> products = (List<Product>) session.getAttribute("products"); %>
    <!-- Search Form -->
    <div class="search">
        <form method="post" action="products">
            <input type="text" name="name" placeholder="Search by name" required />
            <input type="submit" value="Search" name="search" />
        </form>
    </div>

    <!-- Categories Table -->
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Photo</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        
            <!-- Add New Category Form -->
            <form method="post" action="products">
                <tr>
                    <td></td>
                    <td><input type="text" name="name" required placeholder="Product name" /></td>
                    <td><input type="text" name="price" required placeholder="Product Price" /></td>
                    <td><input type="file" name="photo" required  /></td>
                    <td><input type="submit" value="Add" name="add" /></td>
                </tr>
            </form>

            <% 
                // Fetch categories from session
                
                if (products != null && !products.isEmpty()) {
                    for (Product product : products) {
            %>
                <tr>
                    <td><%= product.getId() %></td>
                    <td><%= product.getName() %></td>
                    <td><%= product.getPrice() %></td>
                    <td><img src="<%= product.getPhoto()%>"/> 
                    <td>
                        <form method="post" action="products" style="display:inline;">
                            <input type="hidden" name="id" value="<%= product.getId() %>" />
                            <input type="submit" value="Delete" name="delete" />
                        </form>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="4">No Products found.</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
