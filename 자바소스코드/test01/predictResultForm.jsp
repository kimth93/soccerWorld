<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
double[][] resultRate = (double[][])request.getAttribute("resultRate");
ArrayList<String> resultWin = (ArrayList<String>) request.getAttribute("resultWin");

%>
 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawChart);
      var test = 1;
      var test2 = 2;
      var test3 = 4;
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['예측봇', '홈팀','무승부','원정팀'],
          ['봇A', <%=resultRate[0][2] %>,<%=resultRate[0][1] %>,<%=resultRate[0][0] %>],
          ['봇B', <%=resultRate[1][2] %>,<%=resultRate[1][1] %>,<%=resultRate[1][0] %>],
          ['봇C', <%=resultRate[2][2] %>,<%=resultRate[2][1] %>,<%=resultRate[2][0] %>]
        ]);

        var options = {
          chart: {
            title: '승부예측 데이터',
            subtitle: '',
          }
        };

        var chart = new google.charts.Bar(document.getElementById('columnchart_material'));

        chart.draw(data, google.charts.Bar.convertOptions(options));
      }
    </script>


</head>
<body>
	<%  // 인증된 세션이 없는경우, 해당페이지를 볼 수 없게 함.
   	if (session.getAttribute("login") != null) {%>
       	<%@ include file="memberTop.jsp" %>
   	<%}else{%>
   		<%@ include file="top.jsp" %>
   	<%} %>

<div>
	<p>승부예측 결과!!</p>
</div>
	<div id="columnchart_material" style="width: 600px; height: 400px;"></div>
	<br>
	<div>
		<p>봇 A : <%=resultWin.get(0) %>의 결과를 예측했습니다.</p>
		<p>봇 B : <%=resultWin.get(1) %>의 결과를 예측했습니다.</p>
		<p>봇 C : <%=resultWin.get(2) %>의 결과를 예측했습니다.</p>
	</div>
</body>
</html>