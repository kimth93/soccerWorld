package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;


@WebServlet("/memberDel")
public class MemberDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService memberService = new MemberService();
		
		memberService.deleteMember(request.getParameter("id"));
		
		//회원정보를 삭제한 후에 응답으로 보여줄 내용이 없음
		//이럴때는 다시 다르 요청을 하게 할 수 있다.
		response.sendRedirect("MemberDelForm.jsp");
	}

}
