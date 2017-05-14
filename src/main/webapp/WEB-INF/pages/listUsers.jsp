
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 19.04.2017
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ page errorPage="../../error.jsp" %>--%>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<% String message = (String) request.getAttribute("title");%><%-- <a href='../../logout.jsp'>Log out</a>--%>
<h1>
    <%=message%>
</h1>
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
<form method="get">
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>login</th>
        <th>enable</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.userService}" var="userService">
    <tr>
        <td><c:out value="${userService.id}"></c:out></td>
        <td><c:out value="${userService.login}"></c:out></td>
        <td><c:out value="${userService.enable}"></c:out></td>
        <td><input type="button" name="blockbutton" value="block" onclick="location.href = '<%= request.getContextPath() %>/blocking/<c:out value="${userService.id}" />'"></td>
        <td><input type="button" name="unblockbutton" value="unblock" onclick="location.href = '<%= request.getContextPath() %>/unblocking/<c:out value="${userService.id}" />'"></td>
    </tr>
    </c:forEach>
    <tbody/>
</table>
</form>
</body>
</html>

