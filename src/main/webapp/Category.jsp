<%@page import="model.Category"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="form.CategoryFormBean" %>

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
    </style>
</head>
<body>
    <h2 style="text-align: center;">Categories List</h2>

    
<hr/>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Category> categories = (List<Category>) request.getAttribute("categories");
                if (categories != null) {
                    for (Category category : categories) {
            %>
                        <tr>
                            <td><%= category.getId() %></td>
                            <td><%= category.getName() %></td>
                            <td><%= category.getDescription() %></td>
                        </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="3">No categories found.</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
