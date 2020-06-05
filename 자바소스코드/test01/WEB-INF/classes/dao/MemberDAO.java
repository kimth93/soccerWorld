package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import common.DButil;
import dto.Member;

public class MemberDAO {

	public boolean addMember(Member member) {
		boolean resultFlag = false;
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DButil.getConnection();
			String sql = "insert into member(nickname,name,password,email,team) values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getNickname());
			ps.setString(2, member.getName());
			ps.setString(3, member.getPassword());
			ps.setString(4, member.getEmail());
			ps.setString(5, member.getTeam());
			
			int resultCount = ps.executeUpdate();
		
			if(resultCount == 1)
				resultFlag = true;
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {

			DButil.close(conn, ps);
		}
		return resultFlag;
	}

	public int updateMember(Member member) {
		int resultCount = 0;
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DButil.getConnection();
			String sql = "update member set nickname = ?, name=?, team = ? where email=? ";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, member.getNickname());
			ps.setString(2, member.getName());
			ps.setString(3, member.getTeam());
			ps.setString(4, member.getEmail());
			
			resultCount = ps.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {

			DButil.close(conn, ps);
		}
		
		return resultCount;
	}
	
	//삭제
	public int deleteMember(String email) {
		int resultCount=0;
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DButil.getConnection();
			String sql = "delete from member where email = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,email);
			
			resultCount = ps.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			
			DButil.close(conn, ps);
		}
		return resultCount;
	}
	
	public Member getMember(String email) {
		Member member = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DButil.getConnection();
			String sql = "select *from member where email =? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				member = new Member();
				member.setNickname(rs.getString(1));
				member.setName(rs.getString(2));
				member.setPassword(rs.getString(3));
				member.setEmail(rs.getString(4));
				member.setTeam(rs.getString(5));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.close(conn,ps,rs);
		}
		
		return member;
		
	}
	
	public Member getMemberNick(String email) {
		Member member = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DButil.getConnection();
			String sql = "select nickname from member where email =? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				member = new Member();
				member.setNickname(rs.getString(1));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.close(conn,ps,rs);
		}
		
		return member;
		
	}
	
	//멤버 정보
	public List<Member> getMemberList() {
		List<Member> memberList = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DButil.getConnection();
			String sql = "select nickname,name,password,email,team from member ";
			ps = conn.prepareStatement(sql);
			//ps.setString(1, email);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Member member = new Member();
				member.setNickname(rs.getString(1));
				member.setName(rs.getString(2));
				member.setPassword(rs.getString(3));
				member.setEmail(rs.getString(4));
				member.setTeam(rs.getString(5));
				
				memberList.add(member);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.close(conn,ps,rs);
		}
		
		return memberList;
		
	}
	
	

//	public List<Member> getMemberList() {
//		List<Member> memberList = new ArrayList<>();
//		
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			conn = DButil.getConnection();
//			String sql = "select id,name,password,email from member";
//			ps = conn.prepareStatement(sql);
//
//			rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				Member member = new Member();
//				member.setId(rs.getString(1));
//				member.setName(rs.getString(2));
//				member.setPassword(rs.getString(3));
//				member.setEmail(rs.getString(4));
//				
//				memberList.add(member);
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			DButil.close(conn,ps,rs);
//		}
//		
//		return memberList;
//	}
	
	
}
