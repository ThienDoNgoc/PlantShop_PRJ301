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
            int ostatus = Integer.parseInt(request.getParameter("ostatus"));
            ArrayList<Order> list = OrderDAO.orderByStatus(ostatus);
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
              <h3> There are your Orders</h3>
              <h4><a href="personalPage.jsp">Return</a></h4>
          </section>
          
        <section>
            <%
                String[] status = {" ", "processing", "complete", "canceled"};
                if (list != null && !list.isEmpty()) {
                        for (Order ord : list) {%>
            <table class="order">
                <tr><td>Order ID</td><td>Order Date</td><td>Ship Date</td><td>Order's Status</td></tr>
                <tr>
                    <td><%=ord.getOrderID()%> </td>
                    <td><%=ord.getOrderDate()%> </td> 
                    <td><%=ord.getShipDate()%> </td>
                    <td><%= status[ord.getStatus()]%></td>
                </tr>
            </table>

            <% }
                               }
            %>
        </section>
      
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
            <%}%>

    </body>
</html>
