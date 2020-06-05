<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import ="java.util.*" %>
<%@ page import="org.jsoup.Jsoup" %>
<%@ page import="org.jsoup.nodes.Document" %>
<%@ page import="org.jsoup.nodes.Element" %>
<%@ page import="org.jsoup.select.Elements" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인화면 뉴스 띄워주기</title>
</head>
<body>

<% 
ArrayList<String> titleList =(ArrayList<String>)request.getAttribute("titleList");
ArrayList<String> linkList =(ArrayList<String>)request.getAttribute("linkList");
ArrayList<String> imgList =(ArrayList<String>)request.getAttribute("imgList");
//ArrayList<String> result = (ArrayList<String>)request.getAttribute("result");
%>



<div class="news">
	<div class="news_tab">
    		
	<div class="newsin">
		<div class="korea"> 
			<a href="<%=linkList.get(0) %>" class="link">
				<div class="newsimg">
					<img alt width="350" height="200" src="<%=imgList.get(0) %>" class="mainimage" >
				</div>
				<p class="title"><%=titleList.get(0) %></p>
			</a>
			<br>
			<a href="<%=linkList.get(1) %>" class="link">
				<div class="newsimg">
					<img alt width="80" height="50" src="<%=imgList.get(1) %>" class="mainimage" >
				</div>
				<div>
					<p class="title"><%=titleList.get(1) %></p>
				</div>
			</a>
			<br>
			<a href="<%=linkList.get(2) %>" class="link">
				<div class="newsimg">
					<img alt width="80" height="50" src="<%=imgList.get(2) %>" class="mainimage" >
				</div>
				<div>
					<p class="title"><%=titleList.get(2) %></p>
				</div>
			</a>
			<br>
			<a href="<%=linkList.get(3) %>" class="link">
				<div class="newsimg">
					<img alt width="80" height="50" src="<%=imgList.get(3) %>" class="mainimage" >
				</div>
				<div>
					<p class="title"><%=titleList.get(3) %></p>
				</div>
			</a>
			<br>
			<a href="<%=linkList.get(4) %>" class="link">
				<div class="newsimg">
					<img alt width="80" height="50" src="<%=imgList.get(4) %>" class="mainimage" >
				</div>
				<div>
					<p class="title"><%=titleList.get(4) %></p>
				</div>
			</a>
		</div>
	</div>
</div>

</body>
</html>