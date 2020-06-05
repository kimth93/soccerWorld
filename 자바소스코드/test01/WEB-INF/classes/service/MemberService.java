package service;

import java.util.List;

import javax.servlet.RequestDispatcher;

import dao.MemberDAO;
import dto.Member;

//전달받은 데이터를 실질적으로 처리해주는 곳

public class MemberService {
	private static final boolean False = false;
	MemberDAO dao = new MemberDAO();
	
	public boolean login(String email, String password) {	//로그인 email과 비밀번호 확인
		
		boolean loginFlag = false;
		
		Member member = dao.getMember(email);
		//System.out.println(password);
		//System.out.println(member.getPassword());
		if(member != null && member.getPassword().equals(password)) {
			loginFlag = true;
		}
		
		return loginFlag;
		
	}
	
	public boolean memberJoin(Member member) {
		//Member 데이터를 얻어와서 회원가입 하기 위해
		//필요한 로직이 존재한다면 여기에서 수행함
		
		
		//최종 가입 승인(정보를 넘겨줌)
		boolean resultFlag = dao.addMember(member);
		
		
		
		return resultFlag;
		
		
	}
	
//	public List<Member> getMemberList() {
//		
//		return dao.getMemberList();
//		
//	}
	
	public List<Member> getMemberList() {
		return dao.getMemberList();
	}
	
	public void deleteMember(String email) {
		//삭제하기 전에 수행할 로직이 있다면 여기서 수행
		
		dao.deleteMember(email);
		
	}
	
	
	
	public Member getMember(String email) {
		return dao.getMember(email);
	}
	

	
	public void updateMember(Member member) {
		dao.updateMember(member);
	}
}
