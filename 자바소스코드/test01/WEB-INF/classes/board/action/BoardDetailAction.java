package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.action.ActionForward;
import dao.BoardDAO;
import dto.BoardBean;

public class BoardDetailAction implements Action{
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        // �Ķ���ͷ� �Ѿ�� �۹�ȣ�� �����´�.
        String num = request.getParameter("num");
        int boardNum = Integer.parseInt(num);
        
        String pageNum = request.getParameter("pageNum");
        
        BoardDAO dao = BoardDAO.getInstance();
        BoardBean board = dao.getDetail(boardNum);
        boolean result = dao.updateCount(boardNum);
        
        request.setAttribute("board", board);
        request.setAttribute("pageNum", pageNum);
        
        if(result){
            forward.setRedirect(false); // �ܼ��� ��ȸ�̹Ƿ�
            forward.setNextPath("BoardDetailForm.bo");
        }
        
        return forward;
    }
}