<%-- 
    Document   : viewCart
    Created on : Jun 15, 2024, 4:30:53 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sample.shopping.ProductDTO"%>
<%@page import="sample.shopping.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tu Tu Store</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                
                /*CRE: BÁO THANH NIÊN*/
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

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
                margin-bottom: 20px;
            }

            th, td {
                padding: 10px;
                border: 1px solid #ccc;
                text-align: center;
            }

            th {
                background-color: #007bff;
                color: white;
            }

            td {
                background-color: rgba(255, 255, 255, 0.8);
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

            .total {
                text-align: right;
                margin-top: 20px;
                margin-bottom: 20px;
                font-size: 20px;
                color: white;
            }

            form {
                text-align: right;
                margin-top: 20px;
                margin-bottom: 20px;
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
            .message{
                color: red;
                text-align: right;
                margin-top: 20px;
                margin-bottom: 20px;
                font-size: 20px;
            }
        </style>
    </head>
    <body>
        <h1>Your selected product here</h1>
        <c:if test="${sessionScope.CART != null}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Quantiy</th>
                        <th>Price</th>
                        <th>Total</th>
                        <th>Remove</th>
                        <th>Edit</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="total" value="0"/>
                    <c:forEach var="product" varStatus="counter" items="${sessionScope.CART.getCart().values()}">

                        <c:set var="total" value="${total + product.price * product.quantity}"/>
                        <tr>
                    <form action="MainController" method="POST">
                        <td>${counter.count}</td>
                        <td>${product.id}</td>
                        <td>${product.name}</td>
                        <td><input type="number" name="quantity" min="1" value="${product.quantity}"></td>
                        <td>${product.price}</td>
                        <td>${product.price * product.quantity}$</td>
                        <td><a href="MainController?action=Remove&id=${product.id}&search=${param.search}">Remove</a></td>
                        <td>
                            <input type="submit" name="action" value="Edit">
                            <input type="hidden" name="id" value="${product.id}">
                            <input type="hidden" name="search" value="${param.search}">
                        </td>
                    </form>
                </tr>
            </c:forEach>

        </tbody>
    </table>
</c:if>
<div class="total">
    <h1>Total: ${total}$ </h1>
</div>
<div class="message">
    <h2>${requestScope.MESSAGE}</h2>
</div>
<form action="MainController">
    <input type="submit" name="action" value="Check_Out">
    <input type="hidden" name="search" value="">
    <input type="hidden" name="total" value="${total}">
</form>
<a href="MainController?action=Search_Product&search=${param.search}">Add more!</a>
</body>
</html>
