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
import service.liveScoreService;
import java.util.Date;
import java.text.SimpleDateFormat;
/**
 * Servlet implementation class LiveScoreServlet
 */
@WebServlet("/LiveScoreServlet")
public class LiveScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		//각 리스트에 값 저장
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//String date = "/?date=20200525";
		
		String date = (String)request.getParameter("date");	//현재 날짜 받기
		
		
		String dateurl = "/?date=" + date;
//		
		
		//보여주는 부분은 jsp
		//request.setAttribute("memberList", memberList);
		liveScoreService live = new liveScoreService();
		ArrayList<String> result = live.getScore(dateurl);
		
		request.setAttribute("result", result);
		request.setAttribute("date", date);	//현재날짜 전송
		
		RequestDispatcher rd = request.getRequestDispatcher("liveScore.jsp");

		rd.forward(request, response);
		
		
	}

}
