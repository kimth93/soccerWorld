package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		MemberService service = new MemberService();
		boolean loginFlag = service.login(email, password);
		HttpSession session = request.getSession();
		if(loginFlag) {
			//아이디 패스워드가 동일한 경우
			//상태정보유지
			//쿠키를 이용한 상태정보 유지
//			Cookie cookie = new Cookie("login", id);
//			cookie.setPath("/");
//			cookie.setMaxAge(-1);
//			
//			
//			response.addCookie(cookie);
			
			//세션을 이용한 상태정보 유지
			
			session.setAttribute("login", email);
			
			
			//로그인이 성공했을때 가야할 페이지로 리다이렉트
			response.sendRedirect("test.jsp");
			
			
		}else {
			//입력한 아이디 패스워드가 다를 경우
			session.setAttribute("msg","login정보가 다릅니다. 다시 로그인하세요");
			response.sendRedirect("loginForm.jsp");
		}
	}

}
