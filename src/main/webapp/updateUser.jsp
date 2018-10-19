<jsp:include page="template/Header.jsp">
    <jsp:param name="title" value="Update users" />
</jsp:include>

<p>Name: ${user.name}, age: ${user.age}</p>

<h2>Change user details to:</h2>
<form action="/updateUser" method="post">
    <p>Name: <input type="text" name="name" value="${user.name}" /></p>
    <p>Age: <input type="text" name="age" value="${user.age}" /></p>
    <input type="text" name="id" value="${user.id}" hidden />
    <p><input type="submit" value="Change" /></p>
</form>

<%@include file="template/Footer.jsp"%>