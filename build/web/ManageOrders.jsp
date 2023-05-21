<%-- 
    Document   : ManageOrders
    Created on : Mar 14, 2023, 3:55:31 PM
    Author     : Thien Do
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <input type="submit" value="search Order by AccountID" name="action">
        </form>
        <table class="order">
            <tr>
                <th>Order ID</th>
                <th>Order Date</th>
                <th>Ship Date</th>
                <th>Status ID</th>
                <th>status</th>
                <th>Account ID</th>
                <th>Action</th>
            </tr>
            <c:forEach var="ord" items="${requestScope.orderList}">
                <tr>
                    <td><c:out value="${ord.getOrderID()}"></c:out> </td>
                    <td><c:out value="${ord.getOrderDate()}"></c:out> </td>

                        <td><c:choose>
                            <c:when test="${ord.getShipDate() eq null}">N/A</c:when>
                            <c:otherwise> <c:out value="${ord.getShipDate()}"></c:out>  </c:otherwise>
                        </c:choose>
                        <form action="mainController" method="post">
                            <td>
                                <select name="statusO">
                                    <option ${ord.getStatus()==1 ?"selected":""}>1</option>
                                    <option ${ord.getStatus()==2 ?"selected":""}>2</option>
                                    <option ${ord.getStatus()==3 ?"selected":""}>3</option> 
                                </select>
                            </td>

                            <td><c:choose>
                                    <c:when test="${ord.getStatus() eq 1}">Processing</c:when>
                                    <c:when test="${ord.getStatus() eq 2}">Completed</c:when>
                                    <c:when test="${ord.getStatus() eq 3}">Canceled</c:when>
                                </c:choose>
                            </td>
                            <td><c:out value="${ord.getAccID()}"></c:out> </td>

                                <td><input type="submit" name="action" value="updateOrder"/>
                                    <input type="hidden" name="ordid" value="${ord.getOrderID()}">
                            </td>
                        </form>

                </tr>
            </c:forEach>
        </table>

    </body>
    <footer>
        <c:import url="footer.jsp"/> 
    </footer>
</html>
