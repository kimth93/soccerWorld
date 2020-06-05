package service;

import java.util.ArrayList;
import java.util.List;

import dao.ManagerHistoryDAO;
import dao.ManagersDAO;
import dao.PlayersDAO;
import dao.TransfersDAO;
import dto.MangerHistory;
import dto.Transfers;


public class SearchService {
	
	//���� 1�� ���� ���
	public ArrayList<String> getManager(String name) {
		ManagersDAO dao = new ManagersDAO();
		return dao.getManager(name);
	}
	
	public List<MangerHistory> getHistory(String id){
		ManagerHistoryDAO dao = new ManagerHistoryDAO();
		return dao.getHistoryList(id);
	}
	
	//���� 1�� ���� ���
	public ArrayList<String> getPlayer(String name){
		PlayersDAO dao = new PlayersDAO();
		return dao.getplayer(name);
	}
	
	//���� �������
	public List<Transfers> getTransfers(String id){
		TransfersDAO dao = new TransfersDAO();
		return dao.getTransferList(id);
	}
}
