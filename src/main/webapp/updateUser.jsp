<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<%@include file="template/Header.html"%>

<h1>User Info</h1>
<p>Name: ${user.name}, age: ${user.age}</p>

<h2>Change user details to:</h2>
<form action="/updateUser" method="post">
    <p>Name: <input type="text" name="name" value="${user.name}" /></p>
    <p>Age: <input type="text" name="age" value="${user.age}" /></p>
    <input type="text" name="id" value="${user.id}" hidden />
    <p><input type="submit" value="Change" /></p>
</form>

<%@include file="template/Footer.html"%>
</body>
</html>
