<%@ page import="java.lang.reflect.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 이하 각각 JSTL 코어, XML, 국제화, 데이터베이스, 함수 라이브러리 -->
<!-- prefix 값은 태그 사용시 쓰임 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<!-- default value of scope is page. -->
<!-- available value of scope(page, request, session, application) -->
<!-- EL 변수 생성 -->
<c:set var="var2" value="${m.lastName} ${m.firstName}" scope="page"/> <!-- EL변수 --> <!-- 값으로 null 입력시 삭제됨 -->
<!-- 프로퍼티 값 설정을 하려면 객체 내 메소드가 setter를 제공하여야 한다 -->
<c:set target="object" property="${m.lastName} ${m.firstName}" value="hello" scope="page" /> <!-- 프로퍼티 생성 -->
<!-- 제거 -->
<c:remove var="var2" scope="page" />


<!-- 흐름제어태그 -->
<c:if test="${true}"> hello </c:if>
<c:if test="<%= true %>" var="hello" scope="page"> hello </c:if> <!-- 테스트 조건의 결과를 var 속성에 저장 -->

<!-- 국제화 태그. Accept-Language 헤더에 지정 언어를 사용하게 한다. -->
<fmt:setLocale value="ko-kr" scope="request" />
<!-- 요청 파라미터의 인코딩을 지정한다. -->
<fmt:requestEncoding value="utf-8" />
<!-- 위와 동일하다 -->
<%
   request.setCharacterEncoding("utf-8");
%>


