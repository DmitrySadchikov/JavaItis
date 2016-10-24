
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<h1>
    Menu
</h1>
<form action="">
    <hr>
    <td><c:out value="${requestScope.user}" /><td>
    <hr>
    <p>
        <input type="button" value="Add car" onclick="window.location='/addcar'">
    </p>
    <p>
        <input type="button" value="Users list" onclick="window.location='/users'">
    </p>
</form>

<form action="logout" method="post">
    <p>
        <input type="submit" value="Logout">
    </p>
</form>
</body>
</html>
