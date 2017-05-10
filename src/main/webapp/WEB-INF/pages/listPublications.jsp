<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 24.04.2017
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ page errorPage="error.jsp" %>--%>
<html>
<head>
    <title>listPublications</title>
</head>
<body>
<c:url value="/logout" var="logoutUrl" />
<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />
</form>
<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <h2>
        Welcome : ${pageContext.request.userPrincipal.name} | <a
            href="javascript:formSubmit()"> Logout</a>
    </h2>
</c:if>
<h2>
    <a href = '<%= request.getContextPath() %>/welcome'> Welcome</a>
    <a href = '<%= request.getContextPath() %>/allPublications'> All publications</a>
</h2>
<h2>My publications</h2>
<form method="get">
    <table border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>user_id</th>
            <th>name</th>
            <th>genre</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.usersPublications}" var="usersPublications">
        <tr>
            <td><c:out value="${usersPublications.id}"></c:out></td>
            <td><c:out value="${usersPublications.user_id}"></c:out></td>
            <td><c:out value="${usersPublications.name}"></c:out></td>
            <td><c:out value="${usersPublications.genre}"></c:out></td>
            <td><input type="button" value="update" onclick="location.href = '<%= request.getContextPath() %>/publications?id=<c:out value="${usersPublications.id}" />'"></td>
            <td><input type="button" name="delbutton" value="delete" onclick="location.href = '<%= request.getContextPath() %>/delete/<c:out value="${usersPublications.id}" />'"></td>
            <td><input type="button" value="read" onclick="location.href = '<%= request.getContextPath() %>/reading?id=<c:out value="${usersPublications.id}" />'"></td>
        </tr>
        </c:forEach>
    </table>
    <td><input type="button" name="button" value="new publication" onclick="location.href = '<%= request.getContextPath() %>/publications'"></td>
</form>
</body>
</html>
