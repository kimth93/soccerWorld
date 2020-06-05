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
//		//각 리스트에 값 저장
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//String nation = request.getParameter("text1");
		//String choice = "epl";	//kleague,kleague2, epl,primera,bundesliga,seria,ligue1	버튼에서 넘겨받기 지금은 확인만
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
		
		//보여주는 부분은 jsp
		//request.setAttribute("memberList", memberList);
		//다음 뉴스로 전달
		RequestDispatcher rd = request.getRequestDispatcher("ranking.jsp");

		rd.forward(request, response);
		
		
	}
	

}
