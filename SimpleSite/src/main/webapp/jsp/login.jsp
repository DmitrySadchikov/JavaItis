<%--
  Created by IntelliJ IDEA.
  User: dmitry
  Date: 22.10.16
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        .error {
            color: red;
            font-size: large;
        }
    </style>
</head>
<body>
<h1>
    Login
</h1>
<form action="login" method="post">
    <p style="text-align: center">
    <hr>
        Login: <input type="text" name="login">
        Password: <input type="password" name="password">
    <input type="submit" value="Login">
    <span class="error">&nbsp;&nbsp;&nbsp;${requestScope.error}</span>
    <hr>
    </p>
</form>
</body>
</html>
