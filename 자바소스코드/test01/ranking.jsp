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


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>리그순위</title>

<style>
table.type11 { border-collapse: separate; border-spacing: 1px; text-align: center; line-height: 1.5; margin: 20px 10px;}
table.type11 th { width: 155px; padding: 10px; font-weight: bold; vertical-align: top; color: #fff; background: #ce4869 ;}
table.type11 td { width: 155px; padding: 10px; vertical-align: top; border-bottom: 1px solid #ccc; background: #eee;}


.league_choice_tab{position:relative;display:table;width:960px;table-layout:fixed}
.league_choice_tab li{display:table-cell;text-align:center}

</style>

</head>
<body>
	<%  // 인증된 세션이 없는경우, 해당페이지를 볼 수 없게 함.
    if (session.getAttribute("login") != null) {%>
       	<%@ include file="memberTop.jsp" %>
   	<%}else{%>
   		<%@ include file="top.jsp" %>
   	<%} %>


<%
ArrayList<String> epl_list = new ArrayList<String>();
ArrayList<String> k_list = new ArrayList<String>();
	if(request.getAttribute("epl_list") != null){
		epl_list =(ArrayList<String>)request.getAttribute("epl_list");
	}else{
		k_list =(ArrayList<String>)request.getAttribute("k_list");
	}
	
%>

<div id="container">
	<div id="content">
		<div class="record_tab">
    		<ul class="league_choice_tab">
    		<li>
        		<a href="Ranking?choice=kleague">
                	<span class="menu">
                    	<img src="https://ssl.pstatic.net/static/sports/2019/pc/common/ranking/k_league_on.png" height="23" alt="k리그1">
                    	<span class="title">K리그 1</span>
                	</span>
                </a>
        	</li>
        <li>
        		<a href="Ranking?choice=kleague2">
                	<span class="menu">
                    	<img src="https://ssl.pstatic.net/static/sports/2019/pc/common/ranking/k_league_on.png" height="23" alt="k리그2">
                    	<span class="title">K리그 2</span>
                	</span>
                </a>
        </li>
        
        		<li>
        		<a href="Ranking?choice=epl">
                	<span class="menu">
                    	<img src="https://imgsports.pstatic.net/images/2016/pc/common/league/epl_on.png" height="23" alt="프리미어리그">
                    	<span class="title">프리미어리그</span>
                	</span>
                </a>
        </li>
        <li>
        	<a href="Ranking?choice=primera">
                <span class="menu">
                    <img src="https://imgsports.pstatic.net/images/2016/pc/common/league/primera_on.png" height="23" alt="라리가">
                    <span class="title">라리가</span>
            
                </span>
            </a>
        </li>
        <li>
        	<a href="Ranking?choice=bundesliga">
                <span class="menu">
                    <img src="https://imgsports.pstatic.net/images/2016/pc/common/league/bundesliga_on.png" height="23" alt="분데스리가">
                    <span class="title">분데스리가</span>
                </span>
            </a>
        </li>
        <li>
        	<a href="Ranking?choice=seria">
                <span class="menu">
                    <img src="https://imgsports.pstatic.net/images/2016/pc/common/league/seria_on.png" height="23" alt="세리에 A">
                    <span class="title">세리에 A</span>
                </span>        
           </a>
             </li>
        <li>
        	<a href="Ranking?choice=ligue1">
                <span class="menu">
                    <img src="https://imgsports.pstatic.net/images/2016/pc/common/league/ligue1_on.png" height="23" alt="리그 1">
                    <span class="title">리그 1</span>
                </span>
            </a>
        </li>
    </ul>
</div>
<div id="rank">
<%if(request.getAttribute("epl_list") != null){ %>
	<table class ="type11" summary="팀 순위">
		<thead>
		<tr>
			<th>순위</th>
			<th>팀</th>
			<th>경기수</th>
			<th>승점</th>
			<th>승</th>
			<th>무</th>
			<th>패</th>
			<th>득점</th>
			<th>실점</th>
			<th>득실차</th>
		</tr>
		</thead>
		<tbody>
		<%
			for(int i=0; i<epl_list.size()/10; i++){
			%><tr>
				<%for(int j=0; j<10; j++){
		%>
		
			<td><%=epl_list.get((i*10)+j) %></td>
		
			<%} %>
			</tr>
		<%} %>
		
		</tbody>
	</table>
	<%}else{ %>
		<table class="type11" summary="팀 순위">
		<thead>
		<tr>
			<th>순위</th>
			<th>팀</th>
			<th>경기수</th>
			<th>승점</th>
			<th>승</th>
			<th>무</th>
			<th>패</th>
			<th>득점</th>
			<th>실점</th>
			<th>득실차</th>
			<th>도움</th>
			<th>파울</th>
		</tr>
		</thead>
		<tbody>
		<%
			for(int i=0; i<k_list.size()/12; i++){
			%><tr>
				<%for(int j=0; j<12; j++){
		%>
		
			<td><%=k_list.get((i*12)+j) %></td>
		
			<%} %>
			</tr>
		<%} %>
	<%} %>
		
		</tbody>
	</table>
</div>
	
	
</div>
</div>




</body>
</html>