package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Member;
import service.MemberService;


@WebServlet("/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = new MemberService();
		request.setCharacterEncoding("utf-8");
		Member member = new Member();
		//String email = request.getParameter("id");
		member.setNickname(request.getParameter("nickname"));
		member.setName(request.getParameter("name"));
		member.setTeam(request.getParameter("team"));
		member.setEmail(request.getParameter("id"));
		
		service.updateMember(member);
		
		response.sendRedirect("memberList");	//내정보만 보여주는거로 바꾸기
	}

}
