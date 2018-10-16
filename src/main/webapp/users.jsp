<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<%@include file="template/Header.html"%>
<h1>Users</h1>

<h2>Current users:</h2>
<c:forEach var="user" items="${requestScope.users}">
    <div>
        <p>Name: ${user.name}, age: ${user.age}</p>
        <form action="/updateUser" method="get">
            <input type="text" hidden name="id" value="${user.id}" />
            <input type="submit" value="Change" />
        </form>
    </div>
</c:forEach>

<h2>Add new user form</h2>
<form action="/users" method="post">
    <p>Name: <input type="text" name="name" /></p>
    <p>Age: <input type="text" name="age" /></p>
    <p><input type="submit" value="Add" /></p>
</form>
<%@include file="template/Footer.html"%>
</body>
</html>
