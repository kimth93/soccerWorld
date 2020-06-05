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
import service.daumNewsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Servlet implementation class DaumNewsServlet
 */
@WebServlet("/DaumNews")
public class DaumNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//국내축구인지 해외축구인지 구분
		String nation = request.getParameter("nation");
		daumNewsService service = new daumNewsService();
//		//각 리스트에 값 저장
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//String nation = request.getParameter("text1");
		
		ArrayList<String> imgList = service.news_img(nation);
		
		ArrayList<String> titleList = service.news_title(nation);
		
		ArrayList<String> linkList = service.news_link(nation);
		
//		ArrayList<String> result = new ArrayList<>();
//		result.addAll(linkList);
//		result.addAll(imgList);
//		result.addAll(titleList);
//		
//		System.out.println(result);
//		
		
		//보여주는 부분은 jsp
		//request.setAttribute("memberList", memberList);
		
		request.setAttribute("imgList", imgList);
		request.setAttribute("titleList", titleList);
		request.setAttribute("linkList", linkList);
		//request.setAttribute("result", result);
		
		
		//다음 뉴스로 전달
		RequestDispatcher rd = request.getRequestDispatcher("daumNews.jsp");

		rd.forward(request, response);
		
		
	}

}
