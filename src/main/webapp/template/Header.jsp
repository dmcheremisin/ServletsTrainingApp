<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<c:set var="lang" value="${not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value="ru" />
<fmt:setBundle basename="locale" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${param.title}</title>
    <script src="scripts/jquery.min.js"></script>
</head>
<body>
<a href="index.jsp"><fmt:message key="main.page" /></a>&nbsp;
<a href="users"><fmt:message key="users.list.page" /></a>&nbsp;
<a href="json"><fmt:message key="json.page" /></a>
<a href="jsonUsers"><fmt:message key="users.json.list" /></a>
<a href="logout"><fmt:message key="logout.page" /></a>
<ul class="dropdown-menu">
    <li><a href="./language?lang=en">English</a></li>
    <li><a href="./language?lang=ru">Русский</a></li>
</ul>
<h1>${param.title}</h1>