<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 26.08.2024
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cars Table</title>
</head>
<body>
<%--Declaration--%>
<c:set var="counter" scope="session" value="${0}"/>

<h4>Counter
    <c:out value="${counter = counter + 1}"/>
</h4>
<%--JSTL--%>
<c:if test="${cars==null}">
    <h1>No cars to view</h1>
</c:if>


<c:if test="${cars!=null}">
    <%--    Build table--%>
    <table border="1">
        <tr>
            <th>NAME</th>
            <th>PRICE</th>
        </tr>
        <c:forEach var="car" items="${cars}">
            <tr>
                <td>
                    <c:out value="${car}"/>
                </td>
                <td>
                    <c:out value="${2000 + 2000}"/>
                </td>
            </tr>

        </c:forEach>


    </table>

</c:if>


<table>


</table>


</table>


</body>
</html>
