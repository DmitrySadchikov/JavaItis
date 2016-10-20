
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Список пользователей</title>
</head>
<body>
    <h1>
    Список пользователей:
    </h1>
    <p>
        <c:forEach items="${requestScope.CarUsers}" var="currentUser">
            <tr>
                <td><c:out value="${currentUser}" /><td>
                <br>
            </tr>
        </c:forEach>
    </p>
</body>
</html>
