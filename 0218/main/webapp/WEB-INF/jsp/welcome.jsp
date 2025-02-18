<%--
  Created by IntelliJ IDEA.
  User: Yr
  Date: 25. 2. 18.
  Time: 오후 2:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <h1><%= request.getAttribute("name") %>님 환영합니다!</h1><br>
    <form action='/HelloServletProject/booksearch' method='get'>
        <input type='submit' value='도서 검색'>
    </form>
</body>
</html>
