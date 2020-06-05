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
	
	//감독 1명 정보 얻기
	public ArrayList<String> getManager(String name) {
		ManagersDAO dao = new ManagersDAO();
		return dao.getManager(name);
	}
	
	public List<MangerHistory> getHistory(String id){
		ManagerHistoryDAO dao = new ManagerHistoryDAO();
		return dao.getHistoryList(id);
	}
	
	//선수 1명 정보 얻기
	public ArrayList<String> getPlayer(String name){
		PlayersDAO dao = new PlayersDAO();
		return dao.getplayer(name);
	}
	
	//선수 이적기록
	public List<Transfers> getTransfers(String id){
		TransfersDAO dao = new TransfersDAO();
		return dao.getTransferList(id);
	}
}
