<%--
  Created by IntelliJ IDEA.
  User: erlan
  Date: 12.11.2019
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <form action="/task3/login" method="post">
        <ul>
            <li>Login    <input style="margin-left: 26px;" name="login"    type="text"></li>
            <li>Password <input name="password" type="password"></li>
        </ul>
        <button type="submit">Submit</button>
    </form>
    <jsp:include page="login-password-template.jsp"/>
</body>
</html>
