<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${param.title}</title>
    <script src="scripts/jquery.min.js"></script>
</head>
<body>
<a href="index.jsp">Main page</a>&nbsp;
<a href="users">Users list</a>&nbsp;
<a href="json">Json</a>
<a href="jsonUsers">Users as Json</a>
<a href="logout">Logut</a>
<h1>${param.title}</h1>