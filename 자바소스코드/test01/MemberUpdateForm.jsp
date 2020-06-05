<%@page import="dto.Member"%>
<%@page import="service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%  // 인증된 세션이 없는경우, 해당페이지를 볼 수 없게 함.
    if (session.getAttribute("login") == null) {
        response.sendRedirect("logout.jsp");
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	MemberService service = new MemberService();
	String email = (String)session.getAttribute("login");
	Member member =service.getMember(email);
	System.out.println(member);
%>



<h1>회원정보수정</h1>
<form action="memberUpdate?id=<%=member.getEmail() %>" method="post">
	닉네임 : <input name="nickname" type="text" placeholder="<%=member.getNickname() %>"/><br>
	이름 : <input name="name" type="text" placeholder="<%=member.getName() %>"/><br>
	응원하는 팀 : <input name="team" type="text" placeholder="<%=member.getTeam() %>"/><br>
	<input type="submit" value="정보수정"/>
	<input type="reset" value="취소"/>
</form>


</body>
</html>