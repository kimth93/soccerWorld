<%@page import="dto.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%  // 인증된 세션이 없는경우, 해당페이지를 볼 수 없게 함.
    if (session.getAttribute("login") == null) {
        response.sendRedirect("logout.jsp");
    }
%>
<% //List<Member> memberList =(List<Member>)request.getAttribute("memberList"); 
	Member member = (Member)request.getAttribute("member");
	//System.out.println(request.getAttribute("memberList"));
%>

<%  // 인증된 세션이 없는경우, 해당페이지를 볼 수 없게 함.
    if (session.getAttribute("login") != null) {%>
       	<%@ include file="memberTop.jsp" %>
   	<%}else{%>
   		<%@ include file="top.jsp" %>
   	<%} %>
<br>
<center>
<h1>회원정보</h1>
<table>
	<tr>
		<th>닉네임</th>
		<th>이름</th>
		<th>이메일</th>
		<th>응원하는 팀</th>
		<th>수정</th>
		<th>삭제</th>
	</tr>
	<tr><%if(member != null){
		//for(Member member : memberList){
	%>
		<tr>
			<td><%=member.getNickname() %></td>
			<td><%=member.getName() %></td>
			<td><%=member.getEmail() %></td>
			<td><%=member.getTeam() %></td>
			<td><a href="MemberUpdateForm.jsp?id=<%=member.getEmail() %>">수정</a></td>
			<td><a href="memberDel?id=<%=member.getEmail() %>">회원탈퇴</a></td>
		</tr>
	<% //}//for end
	}//if end%>
</table>
</center>
</body>
</html>