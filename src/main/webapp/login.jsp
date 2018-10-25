<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template/Header.jsp">
    <jsp:param name="title" value="Login page" />
</jsp:include>

<form method="post">
    <p>Name: <input type="text" name="login"/></p>
    <p>Age: <input type="password" name="password"/></p>
    <p><input type="submit" value="Login"/></p>
</form>

<%@include file="template/Footer.jsp"%>
