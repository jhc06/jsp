<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>가입</title>
</head>
<body>
    <form action="join.do" method="post">
        <!-- c:if는  처음으로 param을 submit한 후에 나온다. handler에서 error가 있으면 출력되는 text-->
        <p>
            아이디:<br/><input type="text" name="id" value="${param.id}">
            <c:if test="${errors.id}">ID를 입력하세요.</c:if>
            <c:if test="${errors.duplicateId}">이미 사용중인 아이디입니다.</c:if>
        </p>
        <p>
            이름:<br/><input type="text" name="name" value="${param.name}">
            <c:if test="${errors.name}">이름을 입력하세요.</c:if>
        </p>
        <p>
            암호:<br/><input type="password" name="password">
            <c:if test="${errors.password}">암호를 입력하세요</c:if>
        </p>
        <input type="submit" value="가입">
    </form>
</body>
</html>
