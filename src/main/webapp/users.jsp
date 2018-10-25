<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template/Header.jsp">
    <jsp:param name="title" value="Users" />
</jsp:include>

<c:forEach var="user" items="${requestScope.users}">
    <div>
        <p>Name: ${user.name}, age: ${user.age}</p>
        <div>
            <form action="/updateUser" method="get">
                <input type="text" hidden name="id" value="${user.id}"/>
                <input type="submit" value="Change"/>
            </form>&nbsp
            <c:if test="${requestScope.role eq 'admin'}">
                <form action="/deleteUser" method="get">
                    <input type="text" hidden name="id" value="${user.id}"/>
                    <input type="submit" value="Delete"/>
                </form>
            </c:if>
        </div>
    </div>
</c:forEach>

<c:if test="${requestScope.role eq 'admin' || requestScope.role eq 'member'}">
    <h2>Add new user form</h2>
    <form action="/users" method="post">
        <p>Name: <input type="text" name="name"/></p>
        <p>Age: <input type="text" name="age"/></p>
        <p><input type="submit" value="Add"/></p>
    </form>
</c:if>

<%@include file="template/Footer.jsp"%>
