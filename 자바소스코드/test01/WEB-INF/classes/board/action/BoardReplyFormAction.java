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
        // ��� �ۼ� �� ���� �������� ���ư��� ���� ������ ��ȣ�� �ʿ��ϴ�.
        String pageNum = request.getParameter("page");
        
        BoardBean board = dao.getDetail(num);
        request.setAttribute("board", board);
        request.setAttribute("page", pageNum);
        
        forward.setRedirect(false); // �ܼ��� ��ȸ�̹Ƿ�
        forward.setNextPath("BoardReplyForm.bo");
        
        return forward;
    }
}
