<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 탈퇴 페이지</title>
</head>
<body>
<h1>회원 탈퇴가 완료되었습니다.</h1>
<%
    // 1: 기존의 세션 데이터를 모두 삭제
    session.invalidate();
	response.sendRedirect("loginForm.jsp");
%>

</body>
</html>