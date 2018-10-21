<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template/Header.jsp">
    <jsp:param name="title" value="Users" />
</jsp:include>
<script src="scripts/usersAsJson.js"></script>
<div class="result"></div>

<h2>Add new user form</h2>
<form>
    <p>Name: <input type="text" name="name" class="userName" /></p>
    <p>Age: <input type="text" name="age" class="userAge" /></p>
    <p><button class="addUser">Add</button></p>
</form>

<%@include file="template/Footer.jsp"%>
