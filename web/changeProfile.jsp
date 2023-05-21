<%-- 
    Document   : changeProfile
    Created on : Mar 13, 2023, 7:33:24 PM
    Author     : Thien Do
--%>

<%@page import="basicObject.Account"%>
<%@page import="myDAO.AccountDAO"%>
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
            String email = (String) session.getAttribute("email");
            Cookie[] c = request.getCookies();
            boolean login = false;
            if (name == null) {
                String token = "";
                for (Cookie aCookie : c) {
                    if (aCookie.getName().equals("selector")) {
                        token = aCookie.getValue();
                        Account acc = AccountDAO.getAccount(token);
                        if (acc != null) {
                            name = acc.getFullname();
                            email = acc.getEmail();
                            login = true;
                        }
                    }
                }
            } else {
                login = true;
            }
            if (login) {
        %>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <section>
            <h1> Hello <%= name%> this is your profile </h1>
            <h4><a href="personalPage.jsp">Return</a></h4>
        </section>

        <section>
            <%
                Account acc = AccountDAO.getAccountToChange(email);
                if (acc!=null) {
            %>
            <form action="mainController">
                <table class="order">
                    <tr><td>Email</td><td>Password</td><td>Full Name</td><td>Phone</td><td></td></tr>
                <tr><td><input type="text" name="email" value="<%= acc.getEmail() %>" /> </td>
                    <td><input type="text" name="password" value="<%= acc.getPassword() %>"/> </td>
                    <td><input type="text" name="fullname" value="<%= acc.getFullname() %>"/> </td>
                    <td><input type="text" name="phone" value="<%= acc.getPhone() %>"/> </td>
                    <td><input type="submit" name="action" value="update"/>
                        <input type="hidden" name="accID" value="<%= acc.getAccID() %>"/>
                    </td>
                </tr>
            </table>
            </form>
            

            <% }
                               
            %>
        </section>
        <%}%>
    </body>
    <footer>
        <%@include file="footer.jsp" %>
    </footer>

</html>
