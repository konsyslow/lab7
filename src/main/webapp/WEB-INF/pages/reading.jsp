<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 29.04.2017
  Time: 4:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ page errorPage="error.jsp" %>--%>
<html>
<head>
    <title>Reading</title>
</head>
<body>
<% String publication = (String) request.getAttribute("text");%> <%--<a href='../../logout.jsp'>Log out</a>--%>
<% String name = (String) request.getAttribute("name");%>
<h1>
    Reading
</h1>
<h2>
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

</h2>
<h2>
    <a href = '<%= request.getContextPath() %>/welcome'> Welcome</a>
    <a href = '<%= request.getContextPath() %>/allPublications'> All publications</a>
    <a href = '<%= request.getContextPath() %>/listPublications'> My publications</a>
</h2>
<h2>
    <%=name%>
</h2>
<h3>
    <%=publication%>
</h3>

</body>
</html>
