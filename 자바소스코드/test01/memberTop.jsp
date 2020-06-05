<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- Google font -->
		<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

		<!-- Bootstrap -->
		<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>

		<!-- Slick -->
		<link type="text/css" rel="stylesheet" href="css/slick.css"/>
		<link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>

		<!-- nouislider -->
		<link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>

		<!-- Font Awesome Icon -->
		<link rel="stylesheet" href="css/font-awesome.min.css">

		<!-- Custom stlylesheet -->
		<link type="text/css" rel="stylesheet" href="css/style.css"/>

</head>
<body>
<%	Date nowTime = new Date();	//현재 날짜 받기
SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");//형식 변경
String date = sf.format(nowTime);	//url에 붙여주기
%>
<!-- HEADER -->
		<header>
			<!-- TOP HEADER -->
			<div id="top-header">
				<div class="container">
					
					<ul class="header-links pull-right">

						<li><a href="memberList"><i class="fa fa-user-o"></i> My infomation</a></li>
					</ul>
				</div>
			</div>
			<!-- /TOP HEADER -->

			<!-- MAIN HEADER -->
				<!-- container -->
				<div class="container">
					<!-- row -->
					<div class="row">
						<!-- LOGO -->
						<div class="col-md-3">
						</div>
						<!-- /LOGO -->

						<!-- SEARCH BAR -->
						<div class="col-md-6">
							<div class="header-search">
								<form action="SearchServlet" method="get">
									<select class="input-select" name="opt">
										<option value="0">player</option>
										<option value="1">manager</option>
									</select>
									<input class="input" type="text" name="name" placeholder="Search here">
									<button class="search-btn" type="submit">Search</button>
								</form>
							</div>
						</div>
						<!-- /SEARCH BAR -->
						
						<!-- ACCOUNT -->
						<div class="col-md-3 clearfix">
							<div class="header-ctn">
							<div>
								<a href="logout.jsp"><i class="fa fa-user-o"></i>
								<span>logout</span></a>
							</div>
							
							</div>
						</div>
						<!-- /ACCOUNT -->
					</div>
					<!-- row -->
				</div>
				<!-- container -->
		</header>
		<!-- NAVIGATION -->
		<nav id="navigation">
			<!-- container -->
			<div class="container">
				<!-- responsive-nav -->
				<div id="responsive-nav">
					<!-- NAV -->
					
					<ul class="main-nav nav navbar-nav">
						<li class="active"><a href="test.jsp">Home</a></li>
						<li><a href="BoardListAction.bo?page=1">게시판</a></li>
						<li><a href="DaumNews?nation=soccer/">뉴스</a></li>
						<li><a href="Ranking">리그 순위</a></li>
						<li><a href="LiveScoreServlet?date=<%= date%>">라이브스코어</a></li>
						<li><a href="predictForm.jsp">승부예측</a></li>
					</ul>
					<!-- /NAV -->
				</div>
				<!-- /responsive-nav -->
			</div>
			<!-- /container -->
		</nav>
		<!-- /NAVIGATION -->

</body>
</html>