<%--
  Created by IntelliJ IDEA.
  User: Yr
  Date: 25. 2. 18.
  Time: 오후 4:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="VO.BoardPostVO" %>
<html>
<head>
    <title>게시판</title>
    <style>
        .top-bar { text-align: right; margin-bottom: 10px; }
    </style>
</head>
<body>
<h2>게시판</h2>
<div class="top-bar">
    <a href="<%= request.getContextPath() %>/writeBoard">게시글 작성</a>
</div>
<table border="1" width="100%">
    <tr>
        <th>순번</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성날짜</th>
    </tr>
    <%
        List<BoardPostVO> posts = (List<BoardPostVO>) request.getAttribute("posts");
        if (posts != null) {
            for (BoardPostVO post : posts) {
    %>
    <tr>
        <td><%= post.getId() %></td>
        <td>
            <a href="<%= request.getContextPath() + "/viewBoard?id=" + post.getId() %>">
                <%= post.getTitle() %>
            </a>
        </td>
        <td><%= post.getAuthor() %></td>
        <td><%= post.getRegDate() %></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
