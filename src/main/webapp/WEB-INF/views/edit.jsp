<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <form action="save" method="post">
        TITLE: <input type="text" value="${news.title}">
        <br>
        CONTENT: <textarea>${news.content}</textarea>
        <button>SAVE</button>
    </form>

</body>
</html>
