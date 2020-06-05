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
     * 명령어로부터 다음 이동할 페이지 경로를 생성한다.
     * @param command 명령어
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
        
        // 메인화면일 경우 MainForm.jsp만 경로로 지정한다.
        if(path.equals("BoardListAction.bo"))
            forward.setNextPath(form);
        else
            forward.setNextPath(path);
        
        return forward;
    }
}