<%-- 
    Document   : ManageAccounts
    Created on : Mar 12, 2023, 12:39:00 AM
    Author     : Thien Do
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="mycss.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <c:import url="header_loginAdmin.jsp"/>
        <section>
            <h4><a href="AdminIndex.jsp">Return</a></h4>
        </section>
        <form action="mainController" method="post">
            <input type="text" name="txtsearch">
            <input type="submit" value="search Account" name="action">
        </form>
        <table class="order">
            <tr>
                <th>ID</th>
                <th>Email</th>
                <th>Full Name</th>
                <th>status</th>
                <th>phone</th>
                <th>role</th>
                <th>action</th>
            </tr>
            <c:forEach var="acc" items="${requestScope.accountList}">
                <tr>
                    <td><c:out value="${acc.getAccID()}"></c:out> </td>
                    <td><c:out value="${acc.getEmail()}"></c:out> </td>
                    <td><c:out value="${acc.getFullname()}"></c:out> </td>
                    <td><c:choose>
                            <c:when test="${acc.getStatus() eq 1}">active</c:when>
                            <c:otherwise>inActive</c:otherwise>
                        </c:choose>
                    </td>
                    <td><c:out value="${acc.getPhone()}"></c:out> </td>
                    <td><c:choose>
                            <c:when test="${acc.getRole() eq 1}">admin</c:when>
                            <c:otherwise>user</c:otherwise>
                        </c:choose>
                    </td>
                    <td><c:if test="${acc.getRole() eq 0}">
                            <c:url var="mylink" value="mainController">
                                <c:param name="email" value="${acc.getEmail()}"></c:param>
                                <c:param name="status" value="${acc.getStatus()}"></c:param>
                                <c:param name="action" value="updateStatusAccount"></c:param>
                            </c:url>
                            <a href="${mylink}">Block/UnBlock</a>
                        </c:if> </td>
                </tr>
            </c:forEach>
        </table>
    </body>
    <footer>
        <c:import url="footer.jsp"/> 
    </footer>
</html>
