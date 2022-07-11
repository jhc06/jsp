<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!--contextType은 응답결과 인코딩 -->
<%@ page pageEncoding="UTF-8" %> <!--pageEncoding은 JSP 인코딩 -->
<%@ page import ="java.util.Date" %>
<%@ page trimDirectiveWhitespaces="true" %> <!-- 페이지 소스 상에서 빈 줄을 제거해준다 -->
<%@ page session ="true" %> <!-- 세션 객체 생성 및 설정. default값은 true. -->
<%
    session.setMaxInactiveInterval(60*60);  //60분. 기본이 초단위로 설정됨.
%>
<%@ page import="TEMP.ConstructorStudent" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    지금:<%=new Date()  %>
    <br>
    <form action="<%=request.getContextPath() %>/jspforward/view.jsp" method="get"> <!-- action 파라미터는 url상의 URI를 의미한다 -->
      <select name="code" >
        <option value="A">A페이지</option>
        <option value="B">B페이지</option>
      </select>
    <input type = "submit" value = "이동">
    <input type="reset" value="초기화">
    <input type="checkbox" name="체크박스" value="dog">
    </form>
  </body>
</html>
<!-- HTML 주석, 소스 상 노출됨 -->
<%-- JSP 주석, 소스 상 노출되지 않음 --%>



