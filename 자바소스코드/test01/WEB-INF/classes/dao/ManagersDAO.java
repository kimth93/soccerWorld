package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.DButilMysql;
import dto.Managers;

public class ManagersDAO {
		//���� ���� �˻�
		public ArrayList<String> getManager(String name) {
			ArrayList<String> result = new ArrayList<>();
			Managers managers = null;
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				conn = DButilMysql.getConnection();
				String sql = "select * from managers where name= ? ";
				ps = conn.prepareStatement(sql);
				ps.setString(1, name);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					result.add(rs.getString(1));	//�̸�
					result.add(rs.getString(2));	//���̵�
					result.add(rs.getString(3));	//�¾��
					result.add(rs.getString(4));	//���
					result.add(rs.getString(5));	//����
					result.add(rs.getString(6));	//��ġ���̼���
					result.add(rs.getString(7));	//�����̼�
					result.add(rs.getString(8));	//�̹���
				}else {
					return null;
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				DButilMysql.close(conn,ps,rs);
			}
			
			return result;
			
		}
		
		
		
//		public List<String> getManagersList(String name) {
//			ArrayList<String> result = new ArrayList<>();
//			Managers managers = null;
//			Connection conn = null;
//			PreparedStatement ps = null;
//			ResultSet rs = null;
//			
//			try {
//				conn = DButilMysql.getConnection();
//				String sql = "select * from managers where name= ? ";
//				ps = conn.prepareStatement(sql);
//				ps.setString(1, name);
//				rs = ps.executeQuery();
//				
//				if(rs.next()) {
//					managers = new Managers();
//					managers.setName(rs.getString(1));
//					managers.setId(rs.getString(2));
//					managers.setBirthDate(rs.getString(3));
//					managers.setBirthPlace(rs.getString(4));
//					managers.setNationality(rs.getString(5));
//					managers.setCoachingLicense(rs.getString(6));
//					managers.setPreferredFormation(rs.getString(7));
//					managers.setImg(rs.getString(7));
//				}else {
//					return null;
//				}
//			}catch (Exception e) {
//				e.printStackTrace();
//			}finally {
//				DButilMysql.close(conn,ps,rs);
//			}
//			
//			return result;
//			
//		}
	
}
