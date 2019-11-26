<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: erlan
  Date: 12.11.2019
  Time: 23:35
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
    <c:set var="comment" value="${comment}"/>
    <c:set var="posts" value="${posts}"/>
    <table class="table table-striped" cellpadding="20" style="font-size: 25px">
        <thead>
        <tr>
            <th scope="col">Author</th>
            <th scope="col">Comment</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
            <tbody>
            <tr>
                <th scope="row">${comment.author}</th>
                <th scope="row">
                    <form action="/task3/editcommentdo?id=${comment.id}&postId=${posts.get(0).id}" method="post">
                        <input name="comment" type="text" placeholder="${comment.comment}">
                        <button style="font-size: 30px; color: black" class="btn btn-firstly">Edit</button>
                    </form>
                </th>
                <th scope="row">
                    <form action="/task3/deletecomment?id=${comment.id}&postId=${posts.get(0).id}" method="post">
                        <button style="font-size: 30px; color: black" class="btn btn-firstly">Delete</button>
                    </form>
                </th>
            </tr>
            </tbody>
    </table>
</body>
</html>
