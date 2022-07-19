<%@ tag body-content="scriptless" pageEncoding="utf-8" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%
    HttpSession httpSession = request.getSession(false);
    if(httpSession == null && httpSession.getAttribute("authUser") == null){
%>
<jsp:doBody /> <!-- jsp:doBody 태그는 사용 시 몸체에 입력한 값을 출력하는 태그.
<%
    }
%>