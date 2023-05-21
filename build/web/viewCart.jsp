<%-- 
    Document   : viewCart
    Created on : Mar 9, 2023, 11:50:01 AM
    Author     : Thien Do
--%>

<%@page import="java.util.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
        <link  rel="stylesheet" href="mycss.css" type="text/css" />
    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <section>
            <%
                String name = (String) session.getAttribute("name");
                if(name!=null){
                    %>
                    <h3>Hello <%=session.getAttribute("name") %> !!! </h3>
                    <h3><a href="personalPage.jsp">personal page</a></h3>
                    <%
                }
                %>
                <font style='color: red;'> <%= (request.getAttribute("WARNING")==null)?"":(String)request.getAttribute("WARNING") %> </font>
            <table width="100%" class="shopping" >
                
                <%
                    HashMap<String, Integer> cart = (HashMap) session.getAttribute("cart");
                    if (cart != null) { %>
                    <tr><td>Product id</td><td>quantity</td><td>action</td></tr>
                    <%
                        Set<String> pids = cart.keySet();
                        for (String pid : pids) {
                            int quantity = cart.get(pid);
                %>
                <form action ="mainController" method="post">
                    <tr><td><input type="hidden" value="<%= pid%>" name="pid"><a href="getPlantServlet?pid=<%= pid %>"> <%= pid%> </a></td>
                        <td><input type="number" name="quantity" value='<%= quantity %>' ></td>
                        <td><input type="submit" value="updateCart" name="action">
                            <input type="submit" value="deleteCart" name="action"></td>
                    </tr>

                </form>
                <%
                    }
                } else {
                %>
                <tr><td>Your cart is empty!</td></tr>
                <% }
                %>
                <tr><td>Order Date: <%= (new Date()).toString() %>  </td></tr>
                <tr><td>Ship Date: N/A</td></tr>
            </table>
        </section>
                <section><form action="mainController" method="post">
                        <input type="submit" value="saveOrder" name="action" class="submitorder">
                    </form>     
                </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
