<%-- 
    Document   : orer
    Created on : Jul 5, 2024, 6:54:48 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
                margin: 0;
                padding: 20px;
                color: #333;
            }

            h1, h2 {
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
        <h1>Your Order</h1>
        <c:if test="${requestScope.LIST_ORDER != null}">
            <c:if test="${not empty requestScope.LIST_ORDER}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>OrderID</th>
                            <th>UserID</th>
                            <th>Total</th>
                            <th>Date</th>
                            <th>Detail</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${requestScope.LIST_ORDER}">
                            <tr>
                                <td>${order.orderID}</td>
                                <td>${order.userID}</td>
                                <td>${order.total}</td>
                                <td>${order.date}</td>
                                <td>
                                    <c:url var="viewOrderDetailLink" value="MainController">
                                        <c:param name="action" value="View_Order_Detail"></c:param>
                                        <c:param name="orderID" value="${order.orderID}"></c:param>
                                    </c:url>
                                    <a href="${viewOrderDetailLink}">Detail</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </c:if>
        <h2>${requestScope.MESSAGE}</h2>
        <a href="MainController?action=Search_Product&search=">Back</a>
    </body>
</html>
