<%--
  Created by IntelliJ IDEA.
  User: Yr
  Date: 25. 2. 18.
  Time: 오후 3:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log In Failed</title>
</head>
<body>
    <h1>로그인 실패</h1><br>
    비밀번호가 맞지 않습니다.<br>
    다시 로그인 해주세요.
    <form action="${pageContext.request.contextPath}/" method="GET">
        <input type="submit" value="다시 로그인">
    </form>
</body>
</html>
