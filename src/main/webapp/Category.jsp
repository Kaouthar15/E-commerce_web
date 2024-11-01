<%@page import="model.Category"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="java.util.List"%>
<%@page import="form.CategoryFormBean"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Categories</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 20px;
        }
        .add {
        	background-color: #008000;
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
        
        .search, .add-category {
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 20px;
        }
        
        .search input[type="text"], .add-category input[type="text"] {
            padding: 8px;
            margin-right: 10px;
            width: 200px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        
        .search input[type="submit"], .add-category input[type="submit"] {
            padding: 8px 12px;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        
        .search input[type="submit"]:hover, .add-category input[type="submit"]:hover {
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
        
        .action-btn:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
    <h2>Categories List</h2>
    <hr />
    
    <!-- Search Form -->
    <div class="search">
        <form method="post" action="categories">
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
                <th>Description</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <!-- Add New Category Form -->
            <form method="post" action="categories" class="add-category">
                <tr>
                    <td></td>
                    <td><input type="text" name="name" required placeholder="Category name" /></td>
                    <td><input type="text" name="description" required placeholder="Category description" /></td>
                    <td><input type="submit" value="Add" name="add" /></td>
                </tr>
            </form>

            <% 
                // Fetch categories from session
                List<Category> categories = (List<Category>) session.getAttribute("categories");
                if (categories != null && !categories.isEmpty()) {
                    for (Category category : categories) {
            %>
                <tr>
                    <td><%= category.getId() %></td>
                    <td><%= category.getName() %></td>
                    <td><%= category.getDescription() %></td>
                    <td>
                        <form method="post" action="categories" style="display:inline;">
                            <input type="hidden" name="id" value="<%= category.getId() %>" />
                            <input type="submit" class="action-btn" value="Delete" name="delete" />
                        </form>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="4">No categories found.</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
