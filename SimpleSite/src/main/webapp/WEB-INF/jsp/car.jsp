
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add car</title>
    <style>
        .error {
            color: red;
            font-size: large;
        }
    </style>
    <script type="text/javascript">
        function validateForm() {
            var a=document.forms["Form"]["make"].value;
            var b=document.forms["Form"]["number"].value;
            if (a==null || a=="",b==null || b=="")
            {
                alert("Please Fill All Required Field");
                return false;
            }
        }
    </script>
</head>
<body>
<h1>
    Add car
</h1>

<form name="Form" onsubmit="return validateForm()" action="addcar" method="post">
    <hr>
    <td><c:out value="${requestScope.user}" /><td>
    <hr>
    Make: <input type="text" name="make"> * &nbsp;
    Number: <input type="text" name="number"> * &nbsp;
    Color: <input type="text" name="color">
    <input type="submit" value="Add car">
    <span class="error">&nbsp;&nbsp;&nbsp;${requestScope.error}</span>
    <hr>
</form>

<form action="profile" method="get">
    <p>
        <input type="submit" value="Profile">
    </p>
</form>

</body>
</html>
