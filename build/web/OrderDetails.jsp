<%-- 
    Document   : personalPage
    Created on : Feb 23, 2023, 7:20:37 PM
    Author     : Thien Do
--%>

<%@page import="basicObject.OrderDetails"%>
<%@page import="basicObject.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="myDAO.OrderDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String name = (String) session.getAttribute("name");
            if (name == null) {
        %>   
        <p><font color='red'> You must login to view personal page</font></p>
        <p></p>
        <%} else {
        %>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <section>
            <h1> Detail of your Order </h1>
            <h4><a href="personalPage.jsp">Return</a></h4>
        </section>

        <section>
            <% String orderid = request.getParameter("orderid");
                if (orderid != null) {
                    int orderID = Integer.parseInt(orderid.trim());
                    ArrayList<OrderDetails> list = OrderDAO.getOrderDetail(orderID);
                    if (list != null && !list.isEmpty()) {
                        int money = 0;
                        for (OrderDetails detail : list) {%>
            <table class="order">
                <tr><td>Order ID</td><td>Plant ID</td><td>Plant Name</td><td>Image</td><td>quantity</td></tr>
                <tr><td><%= detail.getOrderID()%></td><td><%= detail.getPlantID()%></td>
                    <td><%= detail.getPlantName()%></td>
                    <td><img src='<%= detail.getImgPath()%>' class='product'/> <br/> <%= detail.getPrice()%></td>
                    <td><%= detail.getQuantity()%></td>
                    <%money = money + detail.getPrice() * detail.getQuantity(); %>
                </tr>
            </table>
            <%       }%>
            <h3>Total money: <%= money%> </h3>
            <%  }  
                else{ %>
            <p>You dont have any order</p>
            <%
                    }
                }
            %>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>

        <% } %>
    </body>
</html>
