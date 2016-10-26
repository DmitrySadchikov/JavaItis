
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add car</title>
    <style>
        .error {
            color: red;
            font-size: large;
        }
    </style>
</head>
<body>
<h1>
    Add car
</h1>

<form action="addcar" method="post">
    <hr>
    Make: <input type="text" name="make"> * &nbsp;
    Number: <input type="text" name="number"> * &nbsp;
    Color: <input type="text" name="color">
    <input type="submit" value="Add car">
    <span class="error">&nbsp;&nbsp;&nbsp;${requestScope.error}</span>
    <hr>
</form>

</body>
</html>
