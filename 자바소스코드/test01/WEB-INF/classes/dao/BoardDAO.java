package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import common.DButil;
import dto.BoardBean;

public class BoardDAO {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
    
    private static BoardDAO instance;
    
    private BoardDAO(){}
    
    public static BoardDAO getInstance(){
        if(instance==null)
            instance=new BoardDAO();
        return instance;
    }
    
    // 시퀀스를 가져온다.
    public int getSeq(){
        int result = 1;
        
        try {
        	conn = DButil.getConnection();
            
            // 시퀀스 값을 가져온다. (DUAL : 시퀀스 값을 가져오기위한 임시 테이블)
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT BOARD_NUM.NEXTVAL FROM DUAL");
            
            ps = conn.prepareStatement(sql.toString());
            // 쿼리 실행
            rs = ps.executeQuery();
            
            if(rs.next())    result = rs.getInt(1);
 
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return result;    
    } // end getSeq
    
    // 글 삽입
    public boolean boardInsert(BoardBean board){
        boolean result = false;
        
        try {
        	conn = DButil.getConnection();
        	// 자동 커밋을 false로 한다.
            conn.setAutoCommit(false);

            
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO MEMBER_BOARD");
            sql.append("(BOARD_NUM, BOARD_ID, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE");
            sql.append(", BOARD_RE_REF, BOARD_COUNT, BOARD_DATE, BOARD_PARENT)");
            sql.append(" VALUES(?,?,?,?,?,?,?,sysdate,?)");
 
            // 시퀀스 값을 글번호와 그룹번호로 사용
            int num = board.getBoard_num();            // 글번호(시퀀스 값)
            int ref = board.getBoard_re_ref();         // 그룹번호
            int parent = board.getBoard_parent();     // 부모글번호
         // 부모글일 경우 그룹번호와 글번호 동일
            if(parent == 0) ref = num;
            
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, num);
            ps.setString(2, board.getBoard_id());
            ps.setString(3, board.getBoard_subject());
            ps.setString(4, board.getBoard_content());
            ps.setString(5, board.getBoard_file());
            ps.setInt(6, ref);
            ps.setInt(7, board.getBoard_count());
            ps.setInt(8, parent);

            
            int flag = ps.executeUpdate();
            if(flag > 0){
               result = true;
               conn.commit(); 
            }

        }catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } 
            throw new RuntimeException(e.getMessage());
        }
        close();
        return result;    
    } // end boardInsert();
    
    // 글목록 가져오기
    public ArrayList<BoardBean> getBoardList(HashMap<String, Object> listOpt){
        ArrayList<BoardBean> list = new ArrayList<BoardBean>();
        
        String opt = (String)listOpt.get("opt"); // 검색옵션(제목, 내용, 글쓴이 등..)
        String condition = (String)listOpt.get("condition"); // 검색내용
        int start = (Integer)listOpt.get("start"); // 현재 페이지번호
        
        try {
        	conn = DButil.getConnection();
            StringBuffer sql = new StringBuffer();
            
            // 글목록 전체를 보여줄 때
            if(opt == null){
                // BOARD_RE_REF(그룹번호)의 내림차순 정렬 후 동일한 그룹번호일 때는
                // BOARD_RE_SEQ(답변글 순서)의 오름차순으로 정렬 한 후에
                // 10개의 글을 한 화면에 보여주는(start번째 부터 start+9까지) 쿼리
                // desc : 내림차순, asc : 오름차순 ( 생략 가능 )
                sql.append("select * from ");
                sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
                sql.append(", BOARD_CONTENT, BOARD_FILE, BOARD_COUNT, BOARD_RE_REF");
                sql.append(", BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_DATE ");
                sql.append("FROM");
                sql.append(" (select * from MEMBER_BOARD order by BOARD_RE_REF desc, BOARD_RE_SEQ asc)) ");
                sql.append("where rnum>=? and rnum<=?");
                
                ps = conn.prepareStatement(sql.toString());
                ps.setInt(1, start);
                ps.setInt(2, start+9);
                
                // StringBuffer를 비운다.
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("0")) { // 제목으로 검색
                sql.append("select * from ");
                sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
                sql.append(", BOARD_CONTENT, BOARD_FILE, BOARD_DATE, BOARD_COUNT");
                sql.append(", BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ ");
                sql.append("FROM ");
                sql.append("(select * from MEMBER_BOARD where BOARD_SUBJECT like ? ");
                sql.append("order BY BOARD_RE_REF desc, BOARD_RE_SEQ asc)) ");
                sql.append("where rnum>=? and rnum<=?");
                
                ps = conn.prepareStatement(sql.toString());
                ps.setString(1, "%"+condition+"%");
                ps.setInt(2, start);
                ps.setInt(3, start+9);
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("1")){ // 내용으로 검색
                sql.append("select * from ");
                sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
                sql.append(", BOARD_CONTENT, BOARD_FILE, BOARD_DATE, BOARD_COUNT");
                sql.append(", BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ ");
                sql.append("FROM ");
                sql.append("(select * from MEMBER_BOARD where BOARD_CONTENT like ? ");
                sql.append("order BY BOARD_RE_REF desc, BOARD_RE_SEQ asc)) ");
                sql.append("where rnum>=? and rnum<=?");
                
                ps = conn.prepareStatement(sql.toString());
                ps.setString(1, "%"+condition+"%");
                ps.setInt(2, start);
                ps.setInt(3, start+9);
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("2")) { // 제목+내용으로 검색
            
                sql.append("select * from ");
                sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
                sql.append(", BOARD_CONTENT, BOARD_FILE, BOARD_DATE, BOARD_COUNT");
                sql.append(", BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ ");
                sql.append("FROM ");
                sql.append("(select * from MEMBER_BOARD where BOARD_SUBJECT like ? OR BOARD_CONTENT like ? ");
                sql.append("order BY BOARD_RE_REF desc, BOARD_RE_SEQ asc)) ");
                sql.append("where rnum>=? and rnum<=?");
                
                ps = conn.prepareStatement(sql.toString());
                ps.setString(1, "%"+condition+"%");
                ps.setString(2, "%"+condition+"%");
                ps.setInt(3, start);
                ps.setInt(4, start+9);
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("3")) { // 글쓴이로 검색
            
                sql.append("select * from ");
                sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
                sql.append(", BOARD_CONTENT, BOARD_FILE, BOARD_DATE, BOARD_COUNT");
                sql.append(", BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ ");
                sql.append("FROM ");
                sql.append("(select * from MEMBER_BOARD where BOARD_ID like ? ");
                sql.append("order BY BOARD_RE_REF desc, BOARD_RE_SEQ asc)) ");
                sql.append("where rnum>=? and rnum<=?");
                
                ps = conn.prepareStatement(sql.toString());
                ps.setString(1, "%"+condition+"%");
                ps.setInt(2, start);
                ps.setInt(3, start+9);
                
                sql.delete(0, sql.toString().length());
            }
            
            rs = ps.executeQuery();
            while(rs.next()){
                BoardBean board = new BoardBean();
                board.setBoard_num(rs.getInt("BOARD_NUM"));
                board.setBoard_id(rs.getString("BOARD_ID"));
                board.setBoard_subject(rs.getString("BOARD_SUBJECT"));
                board.setBoard_content(rs.getString("BOARD_CONTENT"));
                board.setBoard_file(rs.getString("BOARD_FILE"));
                board.setBoard_count(rs.getInt("BOARD_COUNT"));
                board.setBoard_re_ref(rs.getInt("BOARD_RE_REF"));
                board.setBoard_re_lev(rs.getInt("BOARD_RE_LEV"));
                board.setBoard_re_seq(rs.getInt("BOARD_RE_SEQ"));
                board.setBoard_date(rs.getDate("BOARD_DATE"));
                list.add(board);
            }
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return list;
    } // end getBoardList
    
 // 글의 개수를 가져오는 메서드
    public int getBoardListCount(HashMap<String, Object> listOpt){
        int result = 0;
        String opt = (String)listOpt.get("opt"); // 검색옵션(제목, 내용, 글쓴이 등..)
        String condition = (String)listOpt.get("condition"); // 검색내용
        
        try {
        	conn = DButil.getConnection();
            StringBuffer sql = new StringBuffer();
            
            if(opt == null) {    // 전체글의 개수
            
                sql.append("select count(*) from MEMBER_BOARD");
                ps = conn.prepareStatement(sql.toString());
                
                // StringBuffer를 비운다.
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("0")) { // 제목으로 검색한 글의 개수
            
                sql.append("select count(*) from MEMBER_BOARD where BOARD_SUBJECT like ?");
                ps = conn.prepareStatement(sql.toString());
                ps.setString(1, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("1")) { // 내용으로 검색한 글의 개수
            
                sql.append("select count(*) from MEMBER_BOARD where BOARD_CONTENT like ?");
                ps = conn.prepareStatement(sql.toString());
                ps.setString(1, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("2")) { // 제목+내용으로 검색한 글의 개수
            
                sql.append("select count(*) from MEMBER_BOARD ");
                sql.append("where BOARD_SUBJECT like ? or BOARD_CONTENT like ?");
                ps = conn.prepareStatement(sql.toString());
                ps.setString(1, '%'+condition+'%');
                ps.setString(2, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("3")) { // 글쓴이로 검색한 글의 개수
            
                sql.append("select count(*) from MEMBER_BOARD where BOARD_ID like ?");
                ps = conn.prepareStatement(sql.toString());
                ps.setString(1, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            
            rs = ps.executeQuery();
            if(rs.next())    result = rs.getInt(1);
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return result;
    } // end getBoardListCount
    
    
 // 상세보기
    public BoardBean getDetail(int boardNum){    
        BoardBean board = null;
        
        try {
            conn = DButil.getConnection();
            
            StringBuffer sql = new StringBuffer();
            sql.append("select * from MEMBER_BOARD where BOARD_NUM = ?");
            
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, boardNum);
            
            rs = ps.executeQuery();
            if(rs.next()){
                board = new BoardBean();
                board.setBoard_num(boardNum);
                board.setBoard_id(rs.getString("BOARD_ID"));
                board.setBoard_subject(rs.getString("BOARD_SUBJECT"));
                board.setBoard_content(rs.getString("BOARD_CONTENT"));
                board.setBoard_file(rs.getString("BOARD_FILE"));
                board.setBoard_count(rs.getInt("BOARD_COUNT"));
                board.setBoard_re_ref(rs.getInt("BOARD_RE_REF"));
                board.setBoard_re_lev(rs.getInt("BOARD_RE_LEV"));
                board.setBoard_re_seq(rs.getInt("BOARD_RE_SEQ"));
                board.setBoard_date(rs.getDate("BOARD_DATE"));
            }
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return board;
    } // end getDetail()
    
 // 조회수 증가
    public boolean updateCount(int boardNum){
        boolean result = false;
        
        try {
            conn = DButil.getConnection();
            
            StringBuffer sql = new StringBuffer();
            sql.append("update MEMBER_BOARD set BOARD_COUNT = BOARD_COUNT+1 ");
            sql.append("where BOARD_NUM = ?");
            
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, boardNum);
            
            int flag = ps.executeUpdate();
            if(flag > 0){
                result = true;
                conn.commit(); // 완료시 커밋
            }    
        } catch (Exception e) {
            try {
                conn.rollback(); // 오류시 롤백
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return result;
    } // end updateCount
    
    // 삭제할 파일명을 가져온다.
    public String getFileName(int boardNum){
        String fileName = null;
        
        try {
            conn = DButil.getConnection();
            
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT BOARD_FILE from MEMBER_BOARD where BOARD_NUM=?");
            
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, boardNum);
            
            rs = ps.executeQuery();
            if(rs.next()) fileName = rs.getString("BOARD_FILE");
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return fileName;
    } // end getFileName
    
 // 게시글 삭제
    public boolean deleteBoard(int boardNum) {
        boolean result = false;
 
        try {
            conn = DButil.getConnection();
            conn.setAutoCommit(false); // 자동 커밋을 false로 한다.
 
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM MEMBER_BOARD");
            sql.append(" WHERE BOARD_NUM IN");
            sql.append(" (SELECT BOARD_NUM");
            sql.append(" FROM MEMBER_BOARD");
            sql.append(" START WITH BOARD_NUM = ?");
            sql.append(" CONNECT BY PRIOR BOARD_NUM = BOARD_PARENT) ");
            
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, boardNum);
            
            int flag = ps.executeUpdate();
            if(flag > 0){
                result = true;
                conn.commit(); // 완료시 커밋
            }    
            
        } catch (Exception e) {
            try {
                conn.rollback(); // 오류시 롤백
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            throw new RuntimeException(e.getMessage());
        }
 
        close();
        return result;
    } // end deleteBoard
    
    //답글수정

    public boolean updateReSeq(BoardBean board){
        boolean result = false;
        
        int ref = board.getBoard_re_ref();     // 원본글 번호(그룹번호)
        int seq = board.getBoard_re_seq();     // 답변글의 순서
        
        try {    
            StringBuffer sql = new StringBuffer();
            
            conn = DButil.getConnection();
            conn.setAutoCommit(false); // 자동 커밋을 false로 한다.
            
            // ref(그룹번호)와 seq(답글순서)을 확인하여 원본 글에 다른 답변 글이 있으면, 
            // 답변 글 중 답변 글보다 상위에 있는 글의 seq보다 높은 글의 seq값을 1씩 증가시킨다.
            sql.append("update MEMBER_BOARD set BOARD_RE_SEQ = BOARD_RE_SEQ+1 ");
            sql.append("where BOARD_RE_REF = ? and BOARD_RE_SEQ > ?");
            
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, ref);
            ps.setInt(2, seq);
            
            int flag = ps.executeUpdate();
            if(flag > 0){
                result = true;
                conn.commit(); // 완료시 커밋
            }
            
        } catch (Exception e) {
            try {
                conn.rollback(); // 오류시 롤백
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return result;
    }
    
 // 글 수정
    public boolean updateBoard(BoardBean border){
        boolean result = false;
        
        try{
            conn = DButil.getConnection();
            conn.setAutoCommit(false); // 자동 커밋을 false로 한다.
            
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE MEMBER_BOARD SET");
            sql.append(" BOARD_SUBJECT=?");
            sql.append(" ,BOARD_CONTENT=?");
            sql.append(" ,BOARD_FILE=?");
            sql.append(" ,BOARD_DATE=SYSDATE ");
            sql.append("WHERE BOARD_NUM=?");
 
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, border.getBoard_subject());
            ps.setString(2, border.getBoard_content());
            ps.setString(3, border.getBoard_file());
            ps.setInt(4, border.getBoard_num());
            
            int flag = ps.executeUpdate();
            if(flag > 0){
                result = true;
                conn.commit(); // 완료시 커밋
            }
            
        } catch (Exception e) {
            try {
                conn.rollback(); // 오류시 롤백
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            throw new RuntimeException(e.getMessage());
        }
    
        close();
        return result;
    } // end updateBoard
    
    // DB 자원해제
    private void close(){
        try {
            if ( ps != null ){ ps.close(); ps=null; }
            if ( conn != null ){ conn.close(); conn=null;    }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    } // end close()


}
