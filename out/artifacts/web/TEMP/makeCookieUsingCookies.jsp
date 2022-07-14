
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="util.Cookies" %>
<%
    response.addCookie(Cookies.createCookie("name","최범균"));
    response.addCookie(Cookies.createCookie("id", "madvirus","/we", -1));
%>
<html>
<head>
    <title>Cookies 사용 예</title>
</head>
<body>
Cookies를 사용하여 생성
</body>
</html>
