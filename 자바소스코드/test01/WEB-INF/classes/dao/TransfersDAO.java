package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.DButilMysql;
import dto.Transfers;

public class TransfersDAO {
	public List<Transfers> getTransferList(String id) {
		List<Transfers> transferList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DButilMysql.getConnection();
			String sql = "select season, fee, marketvalue, teamA, teamB from transfers where playerId = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Transfers transfer = new Transfers();
				transfer.setSeason(rs.getString(1));
				transfer.setFee(rs.getString(2));
				transfer.setMarketValue(rs.getString(3));
				transfer.setTeamA(rs.getString(4));
				transfer.setTeamB(rs.getString(5));
				transferList.add(transfer);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButilMysql.close(conn,ps,rs);
		}
		
		return transferList;
	}

}
