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
                    <th>Plant Name</th>
                    <th>Plant Price</th>
                    <th>Plant Image Path</th>
                    <th>Plant Description</th>
                    <th>Plant Status</th>
                    <th>Plant Cate ID</th>
                    <th>Action</th>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="Plantname"/>
                    </td>
                    <td>
                        <input type="number" name="price"/> 
                    </td>
                    <td>
                        <input type="text" name="imgPath"/>
                    </td>
                    <td>
                        <input type="text" name="des"/>
                    </td>
                    <td>
                        <input type="number" name="status"/>
                    </td>
                    <td>
                        <input type="number" name="cateid"/> 
                    </td>
                    <td>
                        <input type="submit" name="action" value="createNewPlant"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
    <footer>
        <c:import url="footer.jsp"/> 
    </footer>
</html>
