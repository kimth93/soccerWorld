package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.DButilMysql;
import dto.MangerHistory;

public class ManagerHistoryDAO {
	
	public List<MangerHistory> getHistoryList(String id) {
		List<MangerHistory> historyList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DButilMysql.getConnection();
			String sql = "select team, appoint, constract, position, matches, pointpermatch from managerhistory where managerId = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				MangerHistory manager = new MangerHistory();
				manager.setTeam(rs.getString(1));
				manager.setAppoint(rs.getString(2));
				manager.setConstract(rs.getString(3));
				manager.setPosition(rs.getString(4));
				manager.setMatches(rs.getString(5));
				manager.setPointPerMatch(rs.getString(6));
				historyList.add(manager);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButilMysql.close(conn,ps,rs);
		}
		
		return historyList;
	}

}
