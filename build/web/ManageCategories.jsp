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
        <h1></h1>

        <table class="order">
            <tr>
                <th>Cate ID</th>
                <th>Cate Name</th>
                <th>action</th>
            </tr>
            <c:forEach var="cate" items="${requestScope.cateList}">
                <form action="mainController" method="post">
                    <tr>
                        <td><c:out value="${cate.getCateId()}"></c:out> </td>
                        <td><input type="text" name="catename" value="${cate.getCateName()}"/> </td>
                        <td><input type="submit" name="action" value="updateCate"/>
                            <input type="hidden" name="cid" value="${cate.getCateId()}"/>
                        </td>
                    </tr>
                </form>
            </c:forEach>

        </table>
        <form action="mainController" method="post">
            <p style="text-align: center;"><input type="submit" name="action" value="createCate">    </p>       
        </form>

    </body>
    <footer>
        <c:import url="footer.jsp"/> 
    </footer>
</html>
