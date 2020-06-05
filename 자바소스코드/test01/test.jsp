<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=2.0, user-scale=no">
		<title>soccerWorld Test</title>
 <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

<script type="text/javascript" language="javascript">
var date = new Date(); 
var year = date.getFullYear(); 
var month = new String(date.getMonth()+1); 
var day = new String(date.getDate());
//한자리수일 경우 0을 채워준다. 
if(month.length == 1){ 
  month = "0" + month; 
} 
if(day.length == 1){ 
  day = "0" + day; 
}

var nowdate = year+month+day;

$(document).ready(function(){
	 $.ajax({
	        type : "GET",
	        url : "LiveScoreServlet?date="+nowdate,
	        dataType : "html",
	        error : function(){
	            alert('통신실패!!');
	        },
	        success : function(data){
	            $("#livescore").html(data) ;
	        }
	         
	    });
	 
	 $.ajax({
	        type : "GET",
	        url : "DaumNews?nation=soccer/",
	        dataType : "html",
	        error : function(){
	            alert('통신실패!!');
	        },
	        success : function(data){
	            $("#news").html(data) ;
	        }
	         
	    });
    
	 $.ajax({
	        type : "GET",
	        url : "DaumNews?nation=worldsoccer/",
	        dataType : "html",
	        error : function(){
	            alert('통신실패!!');
	        },
	        success : function(data){
	            $("#Wnews").html(data) ;
	        }
	         
	    });
    
   
});

</script>

    </head>
	<body>
<%  // 인증된 세션이 없는경우, 해당페이지를 볼 수 없게 함.
    	if (session.getAttribute("login") != null) {%>
        	<%@ include file="memberTop.jsp" %>
    	<%}else{%>
    		<%@ include file="top.jsp" %>
    	<%} %>
	<div class="container">
		<div class="row">
			<div>
				<div class='left-box'>
					<div id="livescore"></div>
				</div>
			
				<div class = 'right-box'>
					<div id="news"></div>
			
					<div id="Wnews"></div>
				</div>
			</div>
		</div>
	</div>

		<!-- FOOTER -->
		<%@ include file="bottom.jsp"%>

		<!-- jQuery Plugins -->
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/slick.min.js"></script>
		<script src="js/nouislider.min.js"></script>
		<script src="js/jquery.zoom.min.js"></script>
		<script src="js/main.js"></script>
		

	</body>
</html>