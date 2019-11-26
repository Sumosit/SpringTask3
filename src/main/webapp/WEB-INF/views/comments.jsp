<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: erlan
  Date: 14.11.2019
  Time: 23:37
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
    <c:set var="comments" value="${comments}"/>
    <table class="table table-dark" cellpadding="20" style="font-size: 25px">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Author</th>
            <th scope="col">Title</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>${posts.get(0).id}</th>
            <th>${posts.get(0).author}</th>
            <th>${posts.get(0).title}</th>
            <th>${posts.get(0).content}</th>
            <th>${posts.get(0).postDate}</th>
            <th>
                <form style="margin-top: -10px" action="/task3/editcomment?id=${posts.get(0).id}" method="post">
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

    <table class="table table-striped" cellpadding="20" style="font-size: 25px">
        <thead>
        <tr>
            <th scope="col">Author</th>
            <th scope="col">Comment</th>
            <th scope="col">Date</th>
        </tr>
        </thead>
        <c:forEach items="${comments}" var="comments">
            <tbody>
            <tr>
                <th scope="row">${comments.author}</th>
                <th scope="row">${comments.comment}</th>
                <th scope="row">${comments.date}</th>
                <c:choose>
                    <c:when test="${comments.author.equals(user.login)}">
                        <th>
                            <form style="margin-top: -10px" action="/task3/editcomment?id=${comments.id}&postId=${posts.get(0).id}" method="post">
                                <button style="font-size: 30px; color: white" class="btn btn-firstly">Edit</button>
                            </form>
                        </th>
                        <th>
                            <form style="smargin-top: -10px" action="/task3/editcomment?id=${comments.id}&postId=${posts.get(0).id}" method="post">
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
    <c:if test="${user != null}">
        <form action="/task3/docomments?login=${user.login}&postId=${posts.get(0).id}"method="post">
            <input name="comment" type="text">
            <button type="submit">Submit</button>
        </form>
    </c:if>
</body>
</html>
