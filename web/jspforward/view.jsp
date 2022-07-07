<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-07-05
  Time: 오후 2:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String code = request.getParameter("code");
    String viewPageURI = null;

    if(code.equals("A")){
        viewPageURI = "a.jsp";
    } else if (code.equals("B")) {
        viewPageURI = "b.jsp";
    }
%>
<jsp:forward page="<%=viewPageURI%>" /> <!--viewPageURI는 웹 어플리케이션 내에서의 경로이다 -->
