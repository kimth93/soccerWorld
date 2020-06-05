package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.DButil;
import common.DButilMysql;
import dto.Member;
import dto.Players;

public class PlayersDAO {
	
	//선수 전체 정보 가져오기
	public Players getPlayerList(String name) {
		Players players = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DButilMysql.getConnection();
			String sql = "select *from players where name = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				players = new Players();
				players.setName(rs.getString(1));
				players.setId(rs.getString(2));;
				players.setBirthdate(rs.getString(3));
				players.setBirthplace(rs.getString(4));
				players.setAge(rs.getString(5));
				players.setHeight(rs.getString(6));
				players.setNationality(rs.getString(7));
				players.setPosition(rs.getString(8));
				players.setFoot(rs.getString(9));
				players.setJoined(rs.getString(10));
				players.setConstractlength(rs.getString(11));
				players.setImg(rs.getString(12));
			}else {
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButilMysql.close(conn,ps,rs);
		}
		
		return players;
		
	}
	
	//선수 검색하기
	public ArrayList<String> getplayer(String name) {
		ArrayList<String> result = new ArrayList<String>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DButilMysql.getConnection();
			String sql = "select * from players where name = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				result.add(rs.getString(1));	//이름
				result.add(rs.getString(2));	//id
				result.add(rs.getString(3));	//태어난날
				result.add(rs.getString(4));	//장소
				result.add(rs.getString(5));	//나이
				result.add(rs.getString(6));	//키
				result.add(rs.getString(7));	//국적
				result.add(rs.getString(8));	//포지션
				result.add(rs.getString(9));	//주발
				result.add(rs.getString(10));	//입단
				result.add(rs.getString(11));	//계약기간
				result.add(rs.getString(12));	//이미지링크
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButilMysql.close(conn,ps,rs);
		}
		
		return result;
		
	}
}
