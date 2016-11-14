
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users list</title>
</head>
<body>
    <h1>
    Users list:
    </h1>
    <table>
        <c:forEach items="${requestScope.CarUsers}" var="currentUser">
            <tr>
                <td><c:out value="${currentUser}" /><td>
                <br>
            </tr>
        </c:forEach>
    </table>
    <form action="profile" method="get">
        <p>
            <input type="submit" value="Profile">
        </p>
    </form>
</body>
</html>
