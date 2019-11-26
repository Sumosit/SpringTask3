<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${status.equals('True')}">
    <div class="alert alert-success" role="alert">
        Success!
    </div>
</c:if>
<c:if test="${status.equals('False')}">
    <div class="alert alert-danger" role="alert">
        Error!
    </div>
</c:if>
<a style="color: white;" href="/task3/login"><button type="button" class="btn btn-secondary">To Login</button></a>
<a style="color: white;" href="/task3/register"><button type="button" class="btn btn-secondary">To Register</button></a>
<a style="color: white;" href="/task3/posts"><button type="button" class="btn btn-secondary">To Posts</button></a>
<a style="color: white;" href="/task3/logout"><button type="button" class="btn btn-secondary">Logout</button></a>
<c:if test="${status.equals('True')}">
    <a style="color: white;" href="/task3/profile"><button type="button" class="btn btn-secondary">To Profile</button></a>
</c:if>
