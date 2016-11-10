
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

    <!--<script src="http://code.jquery.com/jquery-1.9.1.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {

            $('#Form').submit(function (e) {
               e.preventDefault();
            });

            $('#submit').click(function (e) {
                $.ajax({
                    type: 'post',
                    url: 'registration',
                    data : $('#Form').serialize(),
                    success:function () {
                        window.location = '/profile'
                    },
                    error:function (data) {
                        document.getElementsByClassName("error").innerHTML = "AAA";
                        //$('#submit').disabled = true;
                    }
                });
            })
        })

    </script><-->

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
</head>
<body>
<h1>
    Registration
</h1>
<form id="Form" onsubmit="return validateForm()" action="registration" method="post">
    Login: <input id="login" type="text" name="login"> * &nbsp;
    Password: <input id="password" type="password" name="password"> *
    <hr>
    Last name: <input id="last_name" type="text" name="last_name"> * &nbsp;
    First name: <input id="first_name" type="text" name="first_name"> *
    <hr>
    Age: <input id="age" type="text" name="age"> &nbsp;
    City: <input id="city" type="text" name="city">
    <input id="submit" type="submit" value="Sign up">
    <span id="error" class="error">&nbsp;&nbsp;&nbsp;${requestScope.error}</span>
</form>
</body>
</html>
