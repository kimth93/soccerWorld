<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>epl 승부예측</title>
</head>
<body>
	<%  // 인증된 세션이 없는경우, 해당페이지를 볼 수 없게 함.
   	if (session.getAttribute("login") != null) {%>
       	<%@ include file="memberTop.jsp" %>
   	<%}else{%>
   		<%@ include file="top.jsp" %>
   	<%} %>

<div>
	<p>epl 승부예측 서비스</p>
</div>
<div>
	<p>경기일정</p>
	<div>
	<form method ="post" action="PredictServlet">
		<ul>
			<li>
				<p>1. West Ham vs Wolves <input type="radio" name="match" value="1"></p>
			</li>
			<li>
				<p>2. Watford vs Leicester <input type="radio" name="match" value="2"></p>
			</li>
			<li>
				<p>3. Tottenham	vs Man United <input type="radio" name="match" value="3"></p>
			</li>
			<li>
				<p>4. Huddersfield vs Southampton <input type="radio" name="match" value="4"></p>
			</li>
			<li>
				<p>5.Newcastle vs Cardiff <input type="radio" name="match" value="5"></p>
			</li>
			<li>
				<p>6. Man City vs Burnley <input type="radio" name="match" value="6"></p>
			</li>
			<li>
				<p>7. Everton vs Liverpool <input type="radio" name="match" value="7"></p>
			</li>
			<li>
				<p>8. Brighton vs Arsenal <input type="radio" name="match" value="8"></p>
			</li>
			<li>
				<p>9. Bournemouth vs Crystal Palace <input type="radio" name="match" value="9"></p>
			</li>
			<li>
				<p>10. Fulham vs Chelsea <input type="radio" name="match" value="10"></p>
			</li>
		</ul>
				<input type="submit" value="승부예측">
		</form>
	</div>
</div>
</body>
</html>