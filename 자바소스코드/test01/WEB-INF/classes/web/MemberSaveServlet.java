package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Member;
import service.MemberService;

//회원가입 요청시 파라미터를 입력받아 
@WebServlet("/memberSave")
public class MemberSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = new MemberService();
		request.setCharacterEncoding("utf-8");
		
		Member member = new Member();
		
		member.setNickname(request.getParameter("nickname"));
		member.setName(request.getParameter("name"));
		member.setPassword(request.getParameter("password"));
		member.setEmail(request.getParameter("email"));
		member.setTeam(request.getParameter("team"));
		
		boolean flag = service.memberJoin(member);
		
		if(flag) {
			response.sendRedirect("loginForm.jsp");
		}else {
			response.sendRedirect("joincheck.jsp");
		}

		
		
//		request.setAttribute("resultFlag", resultFlag); 	//�븵�뿉�뒗 �씠由� �뮘�뿉�뒗 �떎�젣 媛�
//		request.setAttribute("memberInfo", member);
//		RequestDispatcher rd=request.getRequestDispatcher("memberSave.jsp");
//		rd.forward(request, response); 	//memberSave.jsp濡� �꽆寃⑥쨲

		
		
		
	}

}
