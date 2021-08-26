<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021-08-26
  Time: 오후 4:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>응답 내용</title>
</head>
<body>
    <p>응답 내용:</p>
    <ul>
        <c:forEach var="response"
                   items="${answerData.responses}" varStatus="status">
            <li>${status.index + 1}번 문항: ${response}</li>
        </c:forEach>
    </ul>
    <p>응답자 위치: ${answerData.respondent.location}</p>
    <p>응답자 나이: ${answerData.respondent.age}</p>
</body>
</html>
