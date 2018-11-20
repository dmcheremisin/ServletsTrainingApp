<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template/Header.jsp">
    <jsp:param name="title" value="File Upload" />
</jsp:include>


<form method="post" action="upload" enctype="multipart/form-data">
    Select file to upload: <input type="file" name="file" size="60" /><br />
    <br />
    <input type="submit" value="Upload" />
</form>

<%@include file="template/Footer.jsp"%>