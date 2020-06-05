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
		
		//ȸ�������� ������ �Ŀ� �������� ������ ������ ����
		//�̷����� �ٽ� �ٸ� ��û�� �ϰ� �� �� �ִ�.
		response.sendRedirect("MemberDelForm.jsp");
	}

}
