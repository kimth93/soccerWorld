<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import ="java.util.*" %>
<%@ page import="org.jsoup.Jsoup" %>
<%@ page import="org.jsoup.nodes.Document" %>
<%@ page import="org.jsoup.nodes.Element" %>
<%@ page import="org.jsoup.select.Elements" %>
<%@ page import="org.openqa.selenium.By" %>
<%@ page import="java.io.IOException" %>
<%@ page import="org.openqa.selenium.WebDriver" %>
<%@ page import="org.openqa.selenium.WebElement" %>
<%@ page import="org.openqa.selenium.chrome.ChromeDriver" %>
<%@ page import="org.openqa.selenium.chrome.ChromeOptions" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>



<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>라이브스코어보드</title>
</head>
<body>
<%
	ArrayList<String> result_list = (ArrayList<String>)request.getAttribute("result");
	String date = (String)request.getAttribute("date");
%>


<div id="datebar">
<div id="today"><%=date %></div>
</div>
<div id="scoremain" style ="width:140%; height:600px; overflow:scroll;">
	<div id="score">
		<%for(int i=0; i<result_list.size(); i++){ %>
		<%if(!result_list.get(i).equals("리그명")){ %>
			<div><%=result_list.get(i) %> </div>
		<%}else{ %>
			<br>
		<%} %>
		<% }%>
			
	</div>
</div>


</body>
</html>