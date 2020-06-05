<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
body {
  margin: 0;
  padding: 0;
  background-color: #17a2b8;
  height: 100vh;
}
#login .container #login-row #login-column #login-box {
  margin-top: 120px;
  max-width: 600px;
  height: 320px;
  border: 1px solid #9C9C9C;
  background-color: #EAEAEA;
}
#login .container #login-row #login-column #login-box #login-form {
  padding: 20px;
}
#login .container #login-row #login-column #login-box #login-form #register-link {
  margin-top: -85px;
}
</style>

</head>
<body>
<%@ include file="top.jsp" %>
<%
		String msg =(String)session.getAttribute("msg");
		session.removeAttribute("msg");
	if(msg != null) {
		
		out.print(msg);
	}
%>
<div id="login">
        <div class="container">
        <br>
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                        <form id="login-form" class="form" action="login" method="post">
                            <h3 class="text-center text-info">Login</h3>
                            <div class="form-group">
                                <label for="username" class="text-info">이메일:</label><br>
                                <input input type ="email" name = "email" id="username" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">Password:</label><br>
                                <input type = "password" name ="password" id="password" class="form-control">
                            </div>
                            <div class="form-group">
                                <br><br>
                                <input type="submit" name="submit" class="btn btn-info btn-md" value="로그인">
                            </div>
                            <div id="register-link" class="text-right">
                                <a href="memberJoinForm.jsp" class="text-info">Register here</a>
                            </div>
                        </form>
                    </div>
                </div>
        </div>
    </div>


</body>
</html>