<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021-07-13
  Time: 오후 4:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
<h2>약관</h2>
<p>약관 내용: 동의하시면 짱짱맨으로 거듭날 수 있습니다.</p>
<form action="step2" method="post">
    <label>
        <input type="checkbox" name="agree" value="true"> 약관 동의
    </label>
    <input type="submit" value="다음 단계" />
</form>

</body>
</html>
