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
        <h1></h1>
        <section>
            <h4><a href="AdminIndex.jsp">Return</a></h4>
        </section>

        <table class="order">
            <tr>
                <th>Plant ID</th>
                <th>Plant Name</th>
                <th>Price</th>
                <th>Image Path</th>
                <th>Description</th>
                <th>Status</th>
                <th>Cate ID</th>
                <th>action1</th>
            </tr>
            <c:forEach var="plt" items="${requestScope.plantList}">
                <form action="mainController" method="post">
                    <tr>
                        <td><c:out value="${plt.getId() }"></c:out> </td>
                        <td><input type="text" name="pname" value="${plt.getName()}"/> </td>
                        <td><input type="number" name="price" value="<c:out value="${plt.getPrice()}"></c:out>"/> </td>
                        <td><input type="text" name="imgpath" value="<c:out value="${plt.getImgpath()}"></c:out>"/> </td>
                        <td><input type="text" name="des" value="<c:out value="${plt.getDescription()}"></c:out>"/> </td>
                        <td><input type="number" name="status" value="<c:out value="${plt.getStatus()}"></c:out>"/> </td>
                        <td><input type="number" name="cateid" value="<c:out value="${plt.getCateid()}"></c:out>"/> </td>
                            <td><input type="submit" name="action" value="updatePlant"/>
                                <input type="hidden" name="pid" value="${plt.getId()}"/>
                        </td>
                    </tr>
                </form>
            </c:forEach>
        </table>
        <form action="mainController" method="post">
            <p style="text-align: center;"><input type="submit" name="action" value="createPlant">    </p>       
        </form>


    </body>
    <footer>
        <c:import url="footer.jsp"/> 
    </footer>
</html>
