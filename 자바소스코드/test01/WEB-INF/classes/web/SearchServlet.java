package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.MangerHistory;
import dto.Transfers;
import service.SearchService;
import service.daumNewsService;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SearchService service = new SearchService();
		
//		//각 리스트에 값 저장
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//getparmeter로 받기
		//HttpSession session = request.getSession();
		String name = request.getParameter("name");
		String opt = request.getParameter("opt");
		
		ArrayList<String> result = new ArrayList<>();
		List<MangerHistory> result_history = new ArrayList<>();
		List<Transfers> result_transfer = new ArrayList<>();
		
		if(opt.equals("1")) { //감독
			result = service.getManager(name);
			String id = result.get(1);	//id 리턴
			result_history = service.getHistory(id);
			request.setAttribute("result_history", result_history);
			
		}else if(opt.equals("0")) {	//선수
			result = service.getPlayer(name);
			String id = result.get(1);	//id리턴
			result_transfer = service.getTransfers(id);
			request.setAttribute("result_transfer", result_transfer);
		}
		
		
		//보여주는 부분은 jsp
		//request.setAttribute("memberList", memberList);
		request.setAttribute("result", result);
		request.setAttribute("opt", opt);
		
		//검색결과 폼으로 전달
		RequestDispatcher rd = request.getRequestDispatcher("SearchResultForm.jsp");

		rd.forward(request, response);
		
		
	}
     

}
