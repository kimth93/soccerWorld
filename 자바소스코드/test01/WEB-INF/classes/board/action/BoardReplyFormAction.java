package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.action.ActionForward;
import dao.BoardDAO;
import dto.BoardBean;

public class BoardReplyFormAction implements Action{
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        BoardDAO dao = BoardDAO.getInstance();
        int num = Integer.parseInt(request.getParameter("num"));
        // 답글 작성 후 원래 페이지로 돌아가기 위해 페이지 번호가 필요하다.
        String pageNum = request.getParameter("page");
        
        BoardBean board = dao.getDetail(num);
        request.setAttribute("board", board);
        request.setAttribute("page", pageNum);
        
        forward.setRedirect(false); // 단순한 조회이므로
        forward.setNextPath("BoardReplyForm.bo");
        
        return forward;
    }
}
