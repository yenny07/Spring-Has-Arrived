<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021-08-23
  Time: 오후 5:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>메인이다아아아ㅏ아ㅏㅏ아ㅏ드디어어어ㅓ</title>
</head>
<body>
<%--authInfo 정보가 없는 경우 : 로그인 X--%>
<c:if test="${empty authInfo}">
    <p>환영합니다.</p>
    <p>
        <a href="<c:url value="/register/step1" />">[회원 가입하기]</a>
        <a href="<c:url value="/login" />">[로그인]</a>
    </p>
</c:if>

<%--authInfo 정보가 존재하는 경우 : 기존에 로그인 O--%>
<c:if test="${! empty authInfo}">
    <p>${authInfo.name}님, 환영합니다.</p>
    <p>
        <a href="<c:url value="/edit/changePassword" />">[비밀번호 변경]</a>
        <a href="<c:url value="/logout" />">[로그아웃]</a>
    </p>
</c:if>
</body>
</html>
