
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
    <script type="text/javascript">
        function validateForm() {
            var a=document.forms["Form"]["login"].value;
            var b=document.forms["Form"]["password"].value;
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
    Login
</h1>
<form name="Form" onsubmit="return validateForm()" action="login" method="post">
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