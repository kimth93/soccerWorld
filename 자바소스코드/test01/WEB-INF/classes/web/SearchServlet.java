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
		
//		//�� ����Ʈ�� �� ����
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//getparmeter�� �ޱ�
		//HttpSession session = request.getSession();
		String name = request.getParameter("name");
		String opt = request.getParameter("opt");
		
		ArrayList<String> result = new ArrayList<>();
		List<MangerHistory> result_history = new ArrayList<>();
		List<Transfers> result_transfer = new ArrayList<>();
		
		if(opt.equals("1")) { //����
			result = service.getManager(name);
			String id = result.get(1);	//id ����
			result_history = service.getHistory(id);
			request.setAttribute("result_history", result_history);
			
		}else if(opt.equals("0")) {	//����
			result = service.getPlayer(name);
			String id = result.get(1);	//id����
			result_transfer = service.getTransfers(id);
			request.setAttribute("result_transfer", result_transfer);
		}
		
		
		//�����ִ� �κ��� jsp
		//request.setAttribute("memberList", memberList);
		request.setAttribute("result", result);
		request.setAttribute("opt", opt);
		
		//�˻���� ������ ����
		RequestDispatcher rd = request.getRequestDispatcher("SearchResultForm.jsp");

		rd.forward(request, response);
		
		
	}
     

}
