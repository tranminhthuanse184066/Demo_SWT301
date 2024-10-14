<%-- 
    Document   : top1.jsp
    Created on : Jul 13, 2024, 4:35:04 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Top1</title>
    </head>
    <body>
        UserID: ${requestScope.TOP1.userID}<br>
        fullName: ${requestScope.TOP1.fullName}
    </body>
</html>
