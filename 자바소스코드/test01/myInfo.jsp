<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  // 인증된 세션이 없는경우, 해당페이지를 볼 수 없게 함.
    if (session.getAttribute("login") == null) {
        response.sendRedirect("logout.jsp");
    }
%>
<%
String id = (String)session.getAttribute("login");


%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
    </head>
    <body>
        <h1><%= session.getAttribute("login") %>님 <small>반갑습니다.</small></h1>
        
        
        <a href="logout.jsp">로그아웃</a>
    </body>
</html>