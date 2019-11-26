<%--
  Created by IntelliJ IDEA.
  User: erlan
  Date: 08.11.2019
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <form action="/task3/register" method="post">
        <ul>
            <li>Email       <input style="margin-left: 53px" name="email"      type="email"></li>
            <li>Fullname    <input style="margin-left: 27px" name="fullname"   type="text"></li>
            <li>Password    <input style="margin-left: 25px" name="password"   type="password"></li>
            <li>Re-password <input name="repassword" type="password"></li>
        </ul>
        <button type="submit">Submit</button>
    </form>
    <jsp:include page="login-password-template.jsp"/>
</body>
</html>
