package board.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import common.action.Action;
import common.action.ActionForward;
 

public class BoardFormChangeAction implements Action{
    //private String form = "MainForm.jsp?contentPage=board/";
    //private String form = "BoardMainForm.jsp?contentPage=board/";
    private String form = "BoardListForm.jsp";
    private String path;
    
    /**
     * ��ɾ�κ��� ���� �̵��� ������ ��θ� �����Ѵ�.
     * @param command ��ɾ�
     */
    public void setCommand(String command){
        int idx = command.indexOf(".");
        path = command.substring(0, idx)+".jsp";
    }
 
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        forward.setRedirect(false);
//        forward.setRedirect(true);
        
        // ����ȭ���� ��� MainForm.jsp�� ��η� �����Ѵ�.
        if(path.equals("BoardListAction.bo"))
            forward.setNextPath(form);
        else
            forward.setNextPath(path);
        
        return forward;
    }
}