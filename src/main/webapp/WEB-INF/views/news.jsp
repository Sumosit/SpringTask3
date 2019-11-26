<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/test/add" method="post">
        TITLE : <input type="text" name="title">
        CONTENT : <input type="text" name="content">
        <button>ADD</button>
    </form>
    <table cellpadding="20">
        <tr>
            <td>ID</td>
            <td>TITLE</td>
            <td>CONTENT</td>
            <td>READ</td>
        </tr>
        <c:forEach items="${news}" var="habar">
            <tr>
                <td>${habar.id}</td>
                <td>${habar.title}</td>
                <td>${habar.content}</td>
                <td>
                    <a href="/test/read?id=${habar.id}">READ MORE</a>
                </td>
                <td>
                    <a href="/test/edit/${habar.id}.html">EDIT MORE</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
