<%-- 
    Document   : orderDetail
    Created on : Jul 5, 2024, 9:51:17 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
                margin: 0;
                padding: 20px;
                color: #333;
            }

            h1 {
                color: #007bff;
                text-align: center;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
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
                background-color: #fff;
                color: #333;
            }

            a {
                display: inline-block;
                margin: 10px 0;
                padding: 10px 20px;
                background-color: #007bff;
                color: #fff;
                text-decoration: none;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }

            a:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <h1>Your Order Detail</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>OrderID</th>
                    <th>ProductID</th>
                    <th>Price</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="orderDetail" items="${requestScope.LIST_ORDER_DETAIL}">
                    <tr>  
                        <td>${orderDetail.orderID}</td>
                        <td>${orderDetail.id}</td>
                        <td>${orderDetail.price}</td>
                        <td>${orderDetail.quantity}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="MainController?action=View_Order">Back</a>
    </body>
</html>
