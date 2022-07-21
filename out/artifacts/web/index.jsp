<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="u" tagdir= "/WEB-INF/tags" %>
<html>
<head>
    <title>회원제 게시판 예제</title>
</head>
<body>

    <u:isLogin>
        CT: ${authUser.name}님, 안녕하세요. <!-- 표현언어상 key값에 해당하는 객체를 호출해서 사용한다.-->
        <a href="logout.do">[로그아웃하기]</a>
        <a href="change.Pwd.do">[암호변경하기]</a>
    </u:isLogin>
    <u:notLogin>
        CT: <a href="join.do">[회원가입하기]</a>
        <a href="login.do">[로그인하기]</a>
    </u:notLogin>
    <br/>
</body>
</html>
