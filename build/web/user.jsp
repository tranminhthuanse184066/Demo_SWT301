<%-- 
    Document   : user
    Created on : May 29, 2024, 4:52:38 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
                margin: 0;
                padding: 20px;
                color: #333;
            }

            .container {
                max-width: 600px;
                margin: 20px auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }

            h1 {
                color: #007bff;
                text-align: center;
            }

            .user-info {
                margin-bottom: 20px;
            }

            .user-info p {
                margin: 10px 0;
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
        <div class="container">
            <h1>User Information</h1>
            <div class="user-info">
                User ID: ${sessionScope.LOGIN_USER.userID} 
                <br> Full Name: ${sessionScope.LOGIN_USER.fullName} 
                <br> Role ID: ${sessionScope.LOGIN_USER.roleID}
                <br> Password: ***
            </div>
            <br><c:url var="shoppingLink" value="MainController">
                <c:param name="action" value="Search_Product"></c:param>
                <c:param name="search" value=""></c:param>
            </c:url>
            <a href="${shoppingLink}">Shopping</a>
            <c:url var="logoutLink" value="MainController">
                <c:param name="action" value="Logout"></c:param>
            </c:url>
            <br><a href="${logoutLink}">Logout</a>
        </div>
    </body>
</html>