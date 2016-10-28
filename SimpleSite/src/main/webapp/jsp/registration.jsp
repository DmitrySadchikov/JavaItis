
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

    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>

    <script type="text/javascript">
        function validateForm() {
            var a=document.forms["Form"]["login"].value;
            var b=document.forms["Form"]["password"].value;
            var c=document.forms["Form"]["last_name"].value;
            var d=document.forms["Form"]["first_name"].value;
            if (a==null || a=="",b==null || b=="",c==null || c=="",d==null || d=="")
            {
                alert("Please Fill All Required Field");
                return false;
            }
        }

    </script>

    <script type="text/javascript">

    </script>
</head>
<body>
<h1>
    Registration
</h1>
<form name="Form" onsubmit="return validateForm()" action="registration" method="post">
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
