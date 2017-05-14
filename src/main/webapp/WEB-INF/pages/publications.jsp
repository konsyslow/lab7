<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 24.04.2017
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--<%@ page errorPage="error.jsp" %>--%>
<html>
<head>
    <title>Publications</title>
</head>
<style type="text/css">
    span.error {
        color: red;
    }
</style>
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
            Welcome : ${pageContext.request.userPrincipal.name} |  <a
                href="javascript:formSubmit()"> Logout</a>
        </h2>
    </c:if>
</h2>
<body>
<%--<form method="post">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
&lt;%&ndash;    <input type="text" name="id" value="<%= request.getAttribute("pubid")%>" >id</input>&ndash;%&gt;
    <input type="text" name="name" value="<%= request.getAttribute("name")%>">name</input>
    <input type="text" name="genre" value="<%= request.getAttribute("genre") %>">genre</input>
    <input type="text" name="text" value="<%= request.getAttribute("text") %>">text</input>
    <input type="submit" value="Save" />
</form>--%>
<form:form method="post" commandName="publications">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <table>
        <tr>
            <td>name:</td>
            <td><form:input path="name" /></td>
            <td><span class="error"><form:errors path="name" /></span></td>
        </tr>

        <tr>
            <td>genre:</td>
            <td><form:input path="genre" /></td>
            <td><span class="error"><form:errors path="genre" /></span></td>
        </tr>

        <tr>
            <td>text:</td>
            <td><form:input path="text" /></td>
            <td><span class="error"><form:errors path="text" /></span></td>
        </tr>

        <tr>
            <td colspan="3"><input type="submit" value="Save" /></td>
        </tr>
    </table>
</form:form>
</body>
</html>
