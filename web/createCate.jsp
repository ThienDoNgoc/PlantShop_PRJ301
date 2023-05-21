<%-- 
    Document   : createPlant
    Created on : Mar 15, 2023, 10:41:42 AM
    Author     : Thien Do
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:import url="header_loginAdmin.jsp"/>

        <form action="mainController" class="order">
            <table class="order">
                <tr>
                    <th>Cate Name</th>
                    <th>Action</th>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="Catename"/>
                    </td>
                    
                    <td>
                        <input type="submit" name="action" value="createNewCate"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
    <footer>
        <c:import url="footer.jsp"/> 
    </footer>
</html>
