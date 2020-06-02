package kr.or.ddit.css.dao.login;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

import kr.or.ddit.css.vo.AdminVO;
import kr.or.ddit.css.vo.MemberVO;

public interface ILoginDao {
	public List<MemberVO> login(String memId);
	
	public Boolean idCheck(String memId);
	
	public List<AdminVO> adminLogin(String adminId);
	
	public Boolean adminIdCheck(String adminId);
	
	public String idSearch(HashMap<String, String> param);

	public String pwSearch(HashMap<String, String> param);

	public int pwUpdateMember(MemberVO memVo);
}
