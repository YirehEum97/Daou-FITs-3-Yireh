<%--
  Created by IntelliJ IDEA.
  User: Yr
  Date: 25. 2. 18.
  Time: 오전 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인 페이지</title>
</head>
<body>
<h1>도서 검색 시스템</h1>
<form action="${pageContext.request.contextPath}/login" method="POST">
    <h1>로그인 페이지</h1><br>
    ID : <input name="id" type="text"><br>
    PW : <input name="pw" type="password"><br><br>

    <input type="submit" value="login">
</form>
</body>
</html>
