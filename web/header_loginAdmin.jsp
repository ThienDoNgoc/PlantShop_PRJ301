<%-- 
    Document   : header_loginAdmin
    Created on : Mar 11, 2023, 11:00:54 PM
    Author     : Thien Do
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="mycss.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <header>
            <ul>
                <li><a href="manageAccountServlet">Manage Accounts </li>
                <li><a href="manageOrderServlet">Manage Orders </li>
                <li><a href="managePlantServlet">Manage Plants </li>
                <li><a href="manageCategoriesServlet">Manage categories </li>
                <li>Welcome ${sessionScope.name} | <a href="mainController?action=logout">Logout</a></li>
            </ul>
        </header>
    </body>
</html>
