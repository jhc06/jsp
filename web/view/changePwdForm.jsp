<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>암호변경</title>
</head>
<body>
    <form action="changePwd.do" method="post"> <!-- post 방식 호출시 매핑된 handler 호출 -->
        <p>
            현재암호: <br/><input type="password" name="curPwd">
            <c:if test="${errors.curPwd}"> 현재 암호를 입력하세요.</c:if>
            <c:if test="${errors.bacCurPwd}"> 현재 암호가 일치하지 않습니다.</c:if>
        </p>
        <p>
            새 암호: <br/><input type="password" name="newPwd">
            <c:if test="${errors.newPwd}"> 새 암호를 입력하세요.</c:if>
        </p>
        <input type="submit" value="암호 변경">
    </form>
</body>
</html>
