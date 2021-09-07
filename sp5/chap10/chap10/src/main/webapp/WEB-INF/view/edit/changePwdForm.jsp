<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021-09-07
  Time: 오후 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="change.pwd.title" /></title>
</head>
<body>
<form:form>
    <p>
        <label><spring:message code="currentPassword" />:<br>
            <form:input path="currentPassword" />
            <form:errors path="currentPassword"/>
        </label>
    </p>
    <p>
        <label><spring:message code="newPassword" />:<br>
            <form:password path="newPassword" />
            <form:errors path="newPassword"/>
        </label>
    </p>
    <input type="submit" value="<spring:message code="change.btn" />">
</form:form>
</body>
</html>