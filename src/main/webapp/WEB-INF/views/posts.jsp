<%--
  Created by IntelliJ IDEA.
  User: erlan
  Date: 12.11.2019
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        @font-face {
            font-family: "PathwayGothicOne-Regular";
            src: url("../font/PathwayGothicOne-Regular.ttf")format("truetype");
            font-style: normal;
            font-weight: normal;
        }
        body {
                font-family: PathwayGothicOne-Regular, sans-serif;
        }
    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <jsp:include page="nav.jsp"/>
    <c:set var="user" value="${user}"/>
    <form style="margin-top: -10px" action="/task3/addpost" method="post">
        <table class="table table-dark" cellpadding="20" style="font-size: 25px">
            <thead>
            <tr>
                <th scope="col">Title</th>
                <th scope="col">Content</th>
            </tr>
            </thead>
                <tbody>
                <tr>
                    <th><input name="title" type="text"></th>
                    <th><input name="content" type="text"></th>
                </tr>
                </tbody>
        </table>
        <button style="font-size: 30px; background-color: #343a40; color: white" class="btn btn-firstly">ADD POST</button>
    </form>
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
        <c:forEach items="${posts}" var="posts">
            <tbody>
            <tr>
                <th>${posts.id}</th>
                <th>${posts.author}</th>
                <th>${posts.title}</th>
                <th>${posts.content}</th>
                <th>${posts.postDate}</th>
                <th>
                    <form style="smargin-top: -10px" action="/task3/comments?id=${posts.id}" method="post">
                        <button style="font-size: 30px; color: white" class="btn btn-firstly">Comments</button>
                    </form>
                </th>
                <c:choose>
                    <c:when test="${posts.author.equals(user.login)}">
                        <th>
                            <form style="margin-top: -10px" action="/task3/editpost?id=${posts.id}" method="post">
                                <button style="font-size: 30px; color: white" class="btn btn-firstly">Edit</button>
                            </form>
                        </th>
                        <th>
                            <form style="smargin-top: -10px" action="/task3/deletepost?id=${posts.id}" method="post">
                                <button style="font-size: 30px; color: white" class="btn btn-firstly">Delete</button>
                            </form>
                        </th>
                    </c:when>
                    <c:otherwise>
                        <th><form></form></th>
                        <th><form></form></th>
                    </c:otherwise>
                </c:choose>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</body>
</html>
