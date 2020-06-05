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
    
    // �������� �����´�.
    public int getSeq(){
        int result = 1;
        
        try {
        	conn = DButil.getConnection();
            
            // ������ ���� �����´�. (DUAL : ������ ���� ������������ �ӽ� ���̺�)
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT BOARD_NUM.NEXTVAL FROM DUAL");
            
            ps = conn.prepareStatement(sql.toString());
            // ���� ����
            rs = ps.executeQuery();
            
            if(rs.next())    result = rs.getInt(1);
 
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return result;    
    } // end getSeq
    
    // �� ����
    public boolean boardInsert(BoardBean board){
        boolean result = false;
        
        try {
        	conn = DButil.getConnection();
        	// �ڵ� Ŀ���� false�� �Ѵ�.
            conn.setAutoCommit(false);

            
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO MEMBER_BOARD");
            sql.append("(BOARD_NUM, BOARD_ID, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE");
            sql.append(", BOARD_RE_REF, BOARD_COUNT, BOARD_DATE, BOARD_PARENT)");
            sql.append(" VALUES(?,?,?,?,?,?,?,sysdate,?)");
 
            // ������ ���� �۹�ȣ�� �׷��ȣ�� ���
            int num = board.getBoard_num();            // �۹�ȣ(������ ��)
            int ref = board.getBoard_re_ref();         // �׷��ȣ
            int parent = board.getBoard_parent();     // �θ�۹�ȣ
         // �θ���� ��� �׷��ȣ�� �۹�ȣ ����
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
    
    // �۸�� ��������
    public ArrayList<BoardBean> getBoardList(HashMap<String, Object> listOpt){
        ArrayList<BoardBean> list = new ArrayList<BoardBean>();
        
        String opt = (String)listOpt.get("opt"); // �˻��ɼ�(����, ����, �۾��� ��..)
        String condition = (String)listOpt.get("condition"); // �˻�����
        int start = (Integer)listOpt.get("start"); // ���� ��������ȣ
        
        try {
        	conn = DButil.getConnection();
            StringBuffer sql = new StringBuffer();
            
            // �۸�� ��ü�� ������ ��
            if(opt == null){
                // BOARD_RE_REF(�׷��ȣ)�� �������� ���� �� ������ �׷��ȣ�� ����
                // BOARD_RE_SEQ(�亯�� ����)�� ������������ ���� �� �Ŀ�
                // 10���� ���� �� ȭ�鿡 �����ִ�(start��° ���� start+9����) ����
                // desc : ��������, asc : �������� ( ���� ���� )
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
                
                // StringBuffer�� ����.
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("0")) { // �������� �˻�
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
            else if(opt.equals("1")){ // �������� �˻�
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
            else if(opt.equals("2")) { // ����+�������� �˻�
            
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
            else if(opt.equals("3")) { // �۾��̷� �˻�
            
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
    
 // ���� ������ �������� �޼���
    public int getBoardListCount(HashMap<String, Object> listOpt){
        int result = 0;
        String opt = (String)listOpt.get("opt"); // �˻��ɼ�(����, ����, �۾��� ��..)
        String condition = (String)listOpt.get("condition"); // �˻�����
        
        try {
        	conn = DButil.getConnection();
            StringBuffer sql = new StringBuffer();
            
            if(opt == null) {    // ��ü���� ����
            
                sql.append("select count(*) from MEMBER_BOARD");
                ps = conn.prepareStatement(sql.toString());
                
                // StringBuffer�� ����.
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("0")) { // �������� �˻��� ���� ����
            
                sql.append("select count(*) from MEMBER_BOARD where BOARD_SUBJECT like ?");
                ps = conn.prepareStatement(sql.toString());
                ps.setString(1, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("1")) { // �������� �˻��� ���� ����
            
                sql.append("select count(*) from MEMBER_BOARD where BOARD_CONTENT like ?");
                ps = conn.prepareStatement(sql.toString());
                ps.setString(1, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("2")) { // ����+�������� �˻��� ���� ����
            
                sql.append("select count(*) from MEMBER_BOARD ");
                sql.append("where BOARD_SUBJECT like ? or BOARD_CONTENT like ?");
                ps = conn.prepareStatement(sql.toString());
                ps.setString(1, '%'+condition+'%');
                ps.setString(2, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("3")) { // �۾��̷� �˻��� ���� ����
            
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
    
    
 // �󼼺���
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
    
 // ��ȸ�� ����
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
                conn.commit(); // �Ϸ�� Ŀ��
            }    
        } catch (Exception e) {
            try {
                conn.rollback(); // ������ �ѹ�
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return result;
    } // end updateCount
    
    // ������ ���ϸ��� �����´�.
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
    
 // �Խñ� ����
    public boolean deleteBoard(int boardNum) {
        boolean result = false;
 
        try {
            conn = DButil.getConnection();
            conn.setAutoCommit(false); // �ڵ� Ŀ���� false�� �Ѵ�.
 
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
                conn.commit(); // �Ϸ�� Ŀ��
            }    
            
        } catch (Exception e) {
            try {
                conn.rollback(); // ������ �ѹ�
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            throw new RuntimeException(e.getMessage());
        }
 
        close();
        return result;
    } // end deleteBoard
    
    //��ۼ���

    public boolean updateReSeq(BoardBean board){
        boolean result = false;
        
        int ref = board.getBoard_re_ref();     // ������ ��ȣ(�׷��ȣ)
        int seq = board.getBoard_re_seq();     // �亯���� ����
        
        try {    
            StringBuffer sql = new StringBuffer();
            
            conn = DButil.getConnection();
            conn.setAutoCommit(false); // �ڵ� Ŀ���� false�� �Ѵ�.
            
            // ref(�׷��ȣ)�� seq(��ۼ���)�� Ȯ���Ͽ� ���� �ۿ� �ٸ� �亯 ���� ������, 
            // �亯 �� �� �亯 �ۺ��� ������ �ִ� ���� seq���� ���� ���� seq���� 1�� ������Ų��.
            sql.append("update MEMBER_BOARD set BOARD_RE_SEQ = BOARD_RE_SEQ+1 ");
            sql.append("where BOARD_RE_REF = ? and BOARD_RE_SEQ > ?");
            
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, ref);
            ps.setInt(2, seq);
            
            int flag = ps.executeUpdate();
            if(flag > 0){
                result = true;
                conn.commit(); // �Ϸ�� Ŀ��
            }
            
        } catch (Exception e) {
            try {
                conn.rollback(); // ������ �ѹ�
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return result;
    }
    
 // �� ����
    public boolean updateBoard(BoardBean border){
        boolean result = false;
        
        try{
            conn = DButil.getConnection();
            conn.setAutoCommit(false); // �ڵ� Ŀ���� false�� �Ѵ�.
            
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
                conn.commit(); // �Ϸ�� Ŀ��
            }
            
        } catch (Exception e) {
            try {
                conn.rollback(); // ������ �ѹ�
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            throw new RuntimeException(e.getMessage());
        }
    
        close();
        return result;
    } // end updateBoard
    
    // DB �ڿ�����
    private void close(){
        try {
            if ( ps != null ){ ps.close(); ps=null; }
            if ( conn != null ){ conn.close(); conn=null;    }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    } // end close()


}
