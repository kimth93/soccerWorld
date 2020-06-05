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

import service.PredictService;

/**
 * Servlet implementation class PredictServlet
 */
@WebServlet("/PredictServlet")
public class PredictServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PredictService service = new PredictService();
		
//		//�� ����Ʈ�� �� ����
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//getparmeter�� �ޱ�
		//HttpSession session = request.getSession();
		String value = request.getParameter("match");
		ArrayList<String> resultWin = new ArrayList<String>();
		double[][] resultRate = new double[2][2];
		int choice = 0;
		if(value.equals("1")) {
			choice = 1;
		}else if(value.equals("2")) {
			choice = 2;
		}else if(value.equals("3")) {
			choice = 3;
		}else if(value.equals("4")) {
			choice = 4;
		}else if(value.equals("5")) {
			choice = 5;
		}else if(value.equals("6")) {
			choice = 6;
		}else if(value.equals("7")) {
			choice = 7;
		}else if(value.equals("8")) {
			choice = 8;
		}else if(value.equals("9")) {
			choice = 9;
		}else if(value.equals("10")) {
			choice = 10;
		}
		
		resultWin = service.getWin(choice);
		resultRate = service.getRate(choice);
		System.out.println(resultWin);
		
		//�����ִ� �κ��� jsp
		//request.setAttribute("memberList", memberList);
		request.setAttribute("resultWin", resultWin);
		request.setAttribute("resultRate", resultRate);
		
		//�˻���� ������ ����
		RequestDispatcher rd = request.getRequestDispatcher("predictResultForm.jsp");

		rd.forward(request, response);
		
		
	}

}
