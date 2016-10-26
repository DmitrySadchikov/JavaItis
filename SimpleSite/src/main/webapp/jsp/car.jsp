
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
    Make: <input type="text" name="make"> * &nbsp;
    Number: <input type="text" name="number"> * &nbsp;
    Color: <input type="text" name="color">
    <input type="submit" value="Add car">
    <span class="error">&nbsp;&nbsp;&nbsp;${requestScope.error}</span>
    <hr>
</form>

</body>
</html>
