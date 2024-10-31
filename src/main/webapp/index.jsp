<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            background-color: #f4f4f9;
        }
        h1 {
            color: #fff;
            background-color: #ff5733;
            padding: 15px;
            border-radius: 5px;
            text-align: center;
        }
        .container {
            text-align: center;
        }
        .nav-links a {
            text-decoration: none;
            color: #fff;
            background-color: #007bff;
            padding: 10px 20px;
            margin: 0 10px;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }
        .nav-links a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>E-commerce Web Application</h1>
        <div class="nav-links">
            <a href="categories">Categories</a>
            <a href="products">Products</a>
        </div>
    </div>
</body>
</html>
