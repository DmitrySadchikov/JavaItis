
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <style>
        .error {
            color: red;
            font-size: large;
        }
    </style>
</head>
<body>
<h1>
    Registration
</h1>
<form action="registration" method="post">
    Login: <input type="text" name="login"> * &nbsp;
    Password: <input type="password" name="password"> *
    <hr>
    Last name: <input type="text" name="last_name"> * &nbsp;
    First name: <input type="text" name="first_name"> *
    <hr>
    Age: <input type="text" name="age"> &nbsp;
    City: <input type="text" name="city">
    <input type="submit" value="Sign up">
    <span class="error">&nbsp;&nbsp;&nbsp;${requestScope.error}</span>
</form>
</body>
</html>
