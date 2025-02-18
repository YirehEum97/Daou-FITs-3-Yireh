<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1>도서 검색 프로그램</h1>
<form action="/HelloServletProject/booksearch" method="POST">
    도서명 키워드 : <input name="title" type="text"><br>

    <input name="price" value="10000" type="radio" checked="checked"> ~10,000<br>
    <input name="price" value="20000" type="radio"> ~20,000<br>
    <input name="price" value="30000" type="radio"> ~30,000<br>
    <input name="price" value="40000" type="radio"> ~40,000~<br><br>

    <input type="submit" value="도서 검색">
</form>
</body>
</html>