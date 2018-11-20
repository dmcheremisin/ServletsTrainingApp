<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="locale" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${param.title}</title>
    <script src="scripts/jquery.min.js"></script>
</head>
<body>
<div>
    <a href="index.jsp"><fmt:message key="main.page" /></a>&nbsp;
    <a href="users"><fmt:message key="users.list.page" /></a>&nbsp;
    <a href="json"><fmt:message key="json.page" /></a>&nbsp;
    <a href="jsonUsers"><fmt:message key="users.json.list" /></a>&nbsp;
    <a href="upload"><fmt:message key="upload" /></a>&nbsp;
    <a href="logout"><fmt:message key="logout.page" /></a>&nbsp;
    <a href="./locale?lang=en">EN</a>
    <a href="./locale?lang=ru">RU</a>
</div>
<h1>${param.title}</h1>