<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: erlan
  Date: 13.11.2019
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
    <jsp:include page="nav.jsp"/>
    <c:set var="user" value="${user}"/>
    <form action="/task3/changepassword" method="post">
        <ul>
            <li>Old - Password <input style="margin-left: 20px" name="oldpassword" type="password"></li>
            <li>New - Password <input style="margin-left: 13px" name="newpassword" type="password"></li>
            <li>Re-New Password <input name="renewpassword" type="password"></li>
            <li style="padding-top: 10px"><button type="submit">Change password</button></li>
        </ul>
    </form>
    <form action="/task3/changelogin" method="post">
        <ul>
            <li><input name="login" type="text" placeholder="${user.login}"></li>
            <li style="padding-top: 10px"><button type="submit">Change Login</button></li>
        </ul>
    </form>
</body>
</html>
