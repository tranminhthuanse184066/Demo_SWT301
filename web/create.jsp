<%-- 
    Document   : create
    Created on : Jun 12, 2024, 3:37:04 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sample.user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create User</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            body {
                font-family: Arial, sans-serif;
                background: url('https://images2.thanhnien.vn/zoom/686_429/Uploaded/chungntk/2021_03_14/6b69ed2bb2b341ed18a2_EXOB.jpg') no-repeat center center fixed;
                background-size: cover;
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }

            h1 {
                color: #333;
                margin-bottom: 20px;
                background-color: rgba(255, 255, 255, 0.8);
                padding: 10px;
                border-radius: 5px;
                text-align: center;
            }

            .container {
                background-color: rgba(255, 255, 255, 0.8);
                padding: 20px;
                border-radius: 5px;
                width: 300px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                text-align: center;
                border: 1px solid #ccc;
            }

            form {
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            input[type="text"], input[type="password"] {
                margin: 10px 0;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                width: 100%;
            }

            input[type="submit"], input[type="reset"] {
                margin: 10px 0;
                padding: 10px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                width: 100%;
            }

            input[type="submit"]:hover, input[type="reset"]:hover {
                background-color: #0056b3;
            }

            #error {
                color: red;
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        < class="container">
            <h1>Create new User</h1>
            <form action="MainController" method="POST">
                <c:if test="${requestScope.ID_GOOGLE.userID != null}">
                    User ID<input type="text" name="userID" value="${requestScope.ID_GOOGLE.userID}" readonly=""/>  
                </c:if>
                <c:if test="${requestScope.ID_GOOGLE.userID == null}">
                    User ID<input type="text" name="userID" required=""/><div id="error">${requestScope.USER_ERROR.userIDError}</div>
                </c:if>
                <c:if test="${requestScope.ID_GOOGLE.userID != null}">
                    </br>Full Name<input type="text" name="fullName" value="${requestScope.ID_GOOGLE.fullName}" readonly=""/>
                </c:if>
                <c:if test="${requestScope.ID_GOOGLE.userID == null}">
                    </br>Full Name<input type="text" name="fullName" required=""/><div id="error">${requestScope.USER_ERROR.fullNameError}</div>
                </c:if>
                </br>Role ID<input type="text" name="roleID" value="US" readonly=""/>
                </br>Password<input type="password" name="password" required=""/>
                </br>Confirm<input type="password" name="confirm" required=""/><div id="error">${requestScope.USER_ERROR.confirmError}</div>
                </br><input type="submit" name="action" value="Create"/>
                <input type="reset" value="Reset" />
                <div id="error">${requestScope.USER_ERROR.error}</div>
            </form>
        </div>
    </body>
</html>
