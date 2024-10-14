<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sample.shopping.ProductDTO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Tu Tu Store</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            body {
                font-family: Arial, sans-serif;
                background: url('https://images2.thanhnien.vn/zoom/686_429/Uploaded/chungntk/2021_03_14/6b69ed2bb2b341ed18a2_EXOB.jpg') no-repeat center center fixed;
                background-size: cover;
                margin: 0;
                padding: 20px;
                color: #333;
            }

            h1 {
                color: #fff;
                background-color: rgba(0, 0, 0, 0.5);
                padding: 10px;
                border-radius: 5px;
                text-align: center;
            }

            .container {
                background-color: rgba(255, 255, 255, 0.8);
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                margin-top: 20px;
            }

            .search-form, .product-table, .message, .links {
                margin-bottom: 20px;
            }

            .search-form {
                text-align: right;
            }

            input[type="text"], input[type="number"] {
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                margin-right: 10px;
            }

            input[type="submit"] {
                padding: 10px 20px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                margin-right: 10px;
            }

            input[type="submit"]:hover {
                background-color: #0056b3;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }

            th, td {
                padding: 10px;
                border: 1px solid #ccc;
            }

            th {
                background-color: #007bff;
                color: white;
            }

            td {
                background-color: white;
                color: black;
            }

            a {
                display: inline-block;
                margin: 10px 0;
                color: #007bff;
                text-decoration: none;
                padding: 10px;
                background-color: rgba(255, 255, 255, 0.8);
                border-radius: 5px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }

            a:hover {
                background-color: #0056b3;
                color: white;
            }

            .message {
                color: black;
                font-weight: bold;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <h1>Tu xin chao cac ban</h1>
        <div class="container">
            <div class="search-form">
                <form action="MainController" id="search">
                    <input type="text" name="search" value="${param.search}">
                    <input type="submit" value="Search">
                    <input type="hidden" name="action" value="Search_Product">
                </form>
            </div>
            <c:if test="${requestScope.LIST_PRODUCT != null}">
                <c:if test="${not empty requestScope.LIST_PRODUCT}">
                    <div class="product-table">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>NO</th>
                                    <th>ProductID</th>
                                    <th>Product Name</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Add</th>
                                </tr>
                            </thead>
                            <c:forEach var="product" varStatus="counter" items="${requestScope.LIST_PRODUCT}">
                                <tbody>
                                <form action="MainController">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${product.id}</td>
                                        <td>${product.name}</td>
                                        <td>${product.price}</td>
                                        <td><input type="number" name="quantity" min="1" value="1"</td>
                                        <td>
                                            <input type="submit" name="action" value="Add">
                                            <input type="hidden" name="search" value="${param.search}">
                                            <input type="hidden" name="productID" value="${product.id}">
                                            <input type="hidden" name="name" value="${product.name}">
                                            <input type="hidden" name="price" value="${product.price}">
                                        </td>
                                    </tr>
                                </form>
                                </tbody>
                            </c:forEach>
                        </table>
                    </div>
                </c:if>
            </c:if>

            <div class="message">
                <h3>${requestScope.MESSAGE}</h3>
            </div>
            <div class="link">
                <a href="user.jsp">Back</a>
                <a href="MainController?action=View&search=${param.search}">View Cart</a>
                <c:url var="viewOrderLink" value="MainController">
                    <c:param name="action" value="View_Order"></c:param>
                </c:url>
                <a href="${viewOrderLink}">View Order</a>
            </div>
        </div>
    </body>

</html>
