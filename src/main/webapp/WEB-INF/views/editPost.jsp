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
    <c:set var="posts" value="${posts}"/>
    <form action="/task3/editpostdo?id=${posts.get(0).id}" method="post">
        <table class="table table-dark" cellpadding="20" style="font-size: 25px">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Author</th>
                <th scope="col">Title</th>
                <th scope="col">Content</th>
                <th scope="col">Post Date</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>${posts.get(0).id}</th>
                <th>${posts.get(0).author}</th>
                <th><input name="title" type="text" placeholder="${posts.get(0).title}"></th>
                <th><input name="content" type="text" placeholder="${posts.get(0).content}"></th>
                <th>${posts.get(0).postDate}</th>
                <th>
                    <form style="margin-top: -10px" action="/task3/editpost?id=${posts.get(0).id}" method="post">
                        <button style="font-size: 30px; color: white" class="btn btn-firstly">Edit</button>
                    </form>
                </th>
                <th>
                    <form style="smargin-top: -10px" action="/task3/deletepost?id=${posts.get(0).id}" method="post">
                        <button style="font-size: 30px; color: white" class="btn btn-firstly">DELETE</button>
                    </form>
                </th>
            </tr>
            </tbody>
        </table>
    </form>
</body>
</html>
