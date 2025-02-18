<%--
  Created by IntelliJ IDEA.
  User: Yr
  Date: 25. 2. 19.
  Time: 오전 1:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글 작성</title>
</head>
<body>
<h2>게시글 작성</h2>
<form action="${pageContext.request.contextPath}/submitBoard" method="post">
    <label>제목:</label><br/>
    <input type="text" name="title" required /><br/><br/>
    <label>내용:</label><br/>
    <textarea name="content" rows="10" cols="50" required></textarea><br/><br/>
    <input type="submit" value="등록" />
    <input type="button" value="취소" onclick="location.href='${pageContext.request.contextPath}/bulletin'" />
</form>
</body>
</html>

