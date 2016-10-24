<%--
  Created by IntelliJ IDEA.
  User: dmitry
  Date: 22.10.16
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add car</title>
</head>
<body>
<h1>
    Add car
</h1>

<form action="addcar" method="post">
    <hr>
    Make: <input type="text" name="make">
    Power: <input type="text" name="power">
    Color: <input type="text" name="color">
    <input type="submit" value="Add car">
    <hr>
</form>

</body>
</html>
