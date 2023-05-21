<%-- 
    Document   : personalPage
    Created on : Feb 23, 2023, 7:20:37 PM
    Author     : Thien Do
--%>

<%@page import="myDAO.AccountDAO"%>
<%@page import="basicObject.Account"%>
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
            String email =(String) session.getAttribute("email");
            Cookie[] c = request.getCookies();
            boolean login = false;
            if (name == null) {
                String token = "";
                for (Cookie aCookie : c)
                if(aCookie.getName().equals("selector")){
                    token = aCookie.getValue();
                    Account acc = AccountDAO.getAccount(token);
                    if(acc!=null){
                        name = acc.getFullname();
                        email = acc.getEmail();
                        login = true;
                    }
                }
            }
            else  
                login = true;
            if(login){
        %>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
          <section>
              <h3> Welcome <%= name %> comeback </h3>
              <h3><a href="mainController?action=logout">Logout</a></h3>
          </section>
          
        <section>
            <%
                ArrayList<Order> list = OrderDAO.getOrders(email);
                String[] status = {" ", "processing", "complete", "canceled"};
                if (list != null && !list.isEmpty()) {
                        for (Order ord : list) {%>
            <table class="order">
                <tr><td>Order ID</td><td>Order Date</td><td>Ship Date</td><td>Order's Status</td><td>action</td></tr>
                <tr><td><%=ord.getOrderID()%> </td>
                    <td><%=ord.getOrderDate()%> </td> 
                    <td><%=ord.getShipDate()%> </td>
                    <td><%= status[ord.getStatus()]%>
                        <br><% if(ord.getStatus()== 1){%> <a href="cancelOrderServlet?orderid=<%=ord.getOrderID() %>">Cancel order</a> 
                        <%}%>
                        <br><% if(ord.getStatus()== 3){%> <a href="reOrderServlet?orderid=<%=ord.getOrderID() %>"> ReOrder</a> 
                        <%}%>
                    </td>
                    <td><a href="OrderDetails.jsp?orderid= <%=ord.getOrderID() %> ">detail</a> </td>
                </tr>
            </table>

            <% }
                               }
            else {
            %>
            <p>You dont have any order</p>
            <% } %>
        </section>
      
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
            <%}%>

    </body>
</html>
