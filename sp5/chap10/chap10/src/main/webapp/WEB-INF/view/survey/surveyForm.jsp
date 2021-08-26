<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021-08-26
  Time: 오후 4:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>설문조사</title>
</head>
<body>
<h2>설문조사</h2>
<form method="post">
    <c:forEach var="q" items="${questions}" varStatus="status">
        <p>
            ${status.index + 1}.${q.title}<br/>
            <c:if test="${q.choice}">
                <c:forEach var="option" items="${q.options}">
                    <label><input type="radio" name="responses[${status.index}]"
                                  value="${option}">${option}</label>
                </c:forEach>
            </c:if>
            <c:if test="${!q.choice}">
                <input type="text" name="responses[${status.index}]">
            </c:if>
        </p>
    </c:forEach>
<%--    <p>--%>
<%--        1. 당신의 역할은?<br/>--%>
<%--        <label><input type="radio" name="responses[0]" value="서버">서버 개발자</label>--%>
<%--        <label><input type="radio" name="responses[0]" value="프론트">프론트 개발자</label>--%>
<%--        <label><input type="radio" name="responses[0]" value="풀스택">풀스택 개발자</label>--%>
<%--    </p>--%>
<%--    <p>--%>
<%--        2. 가장 많이 사용하는 개발도구는?<br/>--%>
<%--        <label><input type="radio" name="responses[1]" value="Eclipse">Eclipse</label>--%>
<%--        <label><input type="radio" name="responses[1]" value="Intellij">Intellij</label>--%>
<%--        <label><input type="radio" name="responses[1]" value="Sublime">Sublime</label>--%>
<%--    </p>--%>
<%--    <p>--%>
<%--        3. 하고 싶은 말<br/>--%>
<%--        <label><input type="text" name="responses[2]"></label>--%>
<%--    </p>--%>
    <p>
        <label>응답자 위치:<br>
            <input type="text" name="respondent.location">
        </label>
    </p>
    <p>
        <label>응답자 나이:<br>
            <input type="text" name="respondent.age">
        </label>
    </p>
    <input type="submit" value="전송">
</form>
</body>
</html>
