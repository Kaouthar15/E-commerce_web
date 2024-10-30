<%@page import="model.Category"%>
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
    <h2 style="text-align: center;">Categories List</h2>
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
            <form method="post" action="categories">
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
                            <input type="submit" value="Delete" name="delete" />
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
