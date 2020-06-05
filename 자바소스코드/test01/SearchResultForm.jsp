<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.*"%>
<%@page import="dto.MangerHistory" %>
<%@page import="dto.Transfers" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>검색결과</title>
</head>
<body>
<%
ArrayList<String> result = (ArrayList<String>)request.getAttribute("result");
String opt = (String)request.getAttribute("opt");
List<MangerHistory> result_history =(List<MangerHistory>)request.getAttribute("result_history");
List<Transfers> result_transfer = (List<Transfers>)request.getAttribute("result_transfer");
%>


	<%  // 인증된 세션이 없는경우, 해당페이지를 볼 수 없게 함.
   	if (session.getAttribute("login") != null) {%>
       	<%@ include file="memberTop.jsp" %>
   	<%}else{%>
   		<%@ include file="top.jsp" %>
   	<%} %>
<div>
<%if(opt.equals("1")){//감독 %>
	<div id="info">
		<div id="image">
			<img alt width="70" height="70" src="<%=result.get(7) %>" />
			<span id="name"><%=result.get(0) %></span>
		</div>
		<div id="detail">
			<div id="birthdate">
				<span>생년월일</span>
				<span><%=result.get(2) %></span>
				
			</div>
			<div id="birthplace">
				<span>도시</span>
				<span><%=result.get(3) %></span>

			</div>
			<div id="nation">
				<span>국적</span>
				<span><%=result.get(4) %></span>
				
			</div>
			<div id="coach">
				<span>코치 라이센스</span>
				<span><%=result.get(5) %></span>
				
			</div>
		</div>
	</div>
	<br>
	<div id="history">
		<table>
			<thead>
			<tr>
				<th>구단</th>
				<th>계약</th>
				<th>계약만료</th>
				<th>포지션</th>
				<th>경기수</th>
				<th>승률</th>
			<tr>
			</thead>
			<tbody>
			<%if(result_history != null){
				for(MangerHistory managerhistory : result_history){
				%>
				<tr>
					<td><%=managerhistory.getTeam() %></td>
					<td><%=managerhistory.getAppoint() %></td>
					<td><%=managerhistory.getConstract() %></td>
					<td><%=managerhistory.getPosition() %></td>
					<td><%=managerhistory.getMatches() %></td>
					<td><%=managerhistory.getPointPerMatch() %></td>
				</tr>
			<%}//for end
			}// if end%>
			</tbody>
		</table>
	</div>
	<%}else if(opt.equals("0")){ //선수%>
	<div id="info">
		<div id="image">
			<img alt width="70" height="70" src="<%=result.get(11) %>" />
			<span id="name"><%=result.get(0) %></span>
		</div>
		<div id="detail">
			<div id="birthdate">
				<span>생년월일</span>
				<span><%=result.get(2) %></span>
				
			</div>
			<div id="birthplace">
				<span>도시</span>
				<span><%=result.get(3) %></span>
				
			</div>
			<div id="age">
				<span>나이</span>
				<span><%=result.get(4) %></span>
				
			</div>
			<div id="height">
				<span>키</span>
				<span><%=result.get(5) %></span>
				
			</div>
			<div id="nation">
				<span>국적</span>
				<span><%=result.get(6) %></span>
				
			</div>
			<div id="position">
				<span>포지션</span>
				<span><%=result.get(7) %></span>
				
			</div>
			<div id="age">
				<span>계약기간</span>
				<span><%=result.get(10) %></span>
				
			</div>
		</div>
	</div>
	<br>
	<div id="transfer">
		<table>
			<thead>
			<tr>
				<th>시즌	</th>
				<th>이적료	</th>
				<th>몸값	</th>
				<th>직전팀	</th>
				<th>이적팀</th>
			<tr>
			</thead>
			<tbody>
			<%if(result_transfer != null){
				for(Transfers transfers : result_transfer){
				%>
				<tr>
					<td><%=transfers.getSeason() %></td>
					<td><%=transfers.getFee() %></td>
					<td><%=transfers.getMarketValue() %></td>
					<td><%=transfers.getTeamA() %></td>
					<td><%=transfers.getTeamB() %></td>
				</tr>
			<%}//for end
			}// if end%>
			</tbody>
		</table>
	</div>
	<%} %>
	
</div>
</body>
</html>