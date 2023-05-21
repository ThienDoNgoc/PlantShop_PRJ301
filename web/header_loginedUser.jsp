<%-- 
    Document   : header_loginedUser
    Created on : Feb 23, 2023, 7:20:56 PM
    Author     : Thien Do
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nav>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="changeProfile.jsp">Change profile</a></li>
            <li><a href="orderByStatusServlet?ostatus=2">Completed orders</a></li>
            <li><a href="orderByStatusServlet?ostatus=3">Canceled orders</a></li>
            <li><a href="orderByStatusServlet?ostatus=1">Processing orders</a></li>
            <li>from<input type="date" name="from">to<input type="date" name="to">
                <input type="submit" value="search"
            </li>
            
        </nav>
    </body>
</html>
