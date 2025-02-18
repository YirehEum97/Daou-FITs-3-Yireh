<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log In Page</title>
</head>
<body>
<form action="/HelloServletProject/login" method="POST">
    <h1>로그인 페이지</h1><br>
    ID : <input name="id" type="text"><br>
    PW : <input name="pw" type="password"><br><br>

    <input type="submit" value="login">
</form>
</body>
</html>
