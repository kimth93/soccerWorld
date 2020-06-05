package web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.daumNewsService;
import service.naverRankService;

/**
 * Servlet implementation class RankingServlet
 */
@WebServlet("/Ranking")
public class RankingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		naverRankService service = new naverRankService();
//		//�� ����Ʈ�� �� ����
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//String nation = request.getParameter("text1");
		//String choice = "epl";	//kleague,kleague2, epl,primera,bundesliga,seria,ligue1	��ư���� �Ѱܹޱ� ������ Ȯ�θ�
		String choice = request.getParameter("choice");
		if(choice == null) {
			choice = "kleague";
		}
		
		if(choice.equals("kleague")) {
			ArrayList<String> k_list = service.kleague(choice);
			request.setAttribute("k_list", k_list);
		}else if(choice.equals("kleague2")) {
			ArrayList<String> k_list = service.kleague(choice);
			request.setAttribute("k_list", k_list);
		}else {
			ArrayList<String> epl_list = service.wLeague(choice);
			request.setAttribute("epl_list", epl_list);
		}
		
		//�����ִ� �κ��� jsp
		//request.setAttribute("memberList", memberList);
		//���� ������ ����
		RequestDispatcher rd = request.getRequestDispatcher("ranking.jsp");

		rd.forward(request, response);
		
		
	}
	

}
