<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021-08-23
  Time: 오후 4:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
<h2>회원 정보 입력</h2>
<form:form action="step3" modelAttribute="registerRequest">
    <p>
        <label>이메일:<br>
<%--            프로퍼티를 path로 표현, value로 사용 --%>
            <form:input path="email"/>
        </label>
    </p>
    <p>
        <label>이름:<br>
            <form:input path="name"/>
        </label>
    </p>
    <p>
        <label>비밀번호:<br>
            <form:password path="password"/>
        </label>
    </p>
    <p>
        <label>비밀번호 확인:<br>
            <form:password path="confirmPassword"/>
        </label>
    </p>
    <input type="submit" value="가입 완료">
</form:form>
</body>
</html>