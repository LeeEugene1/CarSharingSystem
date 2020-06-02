package kr.or.ddit.css.dao.singUp;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.css.util.BuildedSqlMapClient;
import kr.or.ddit.css.vo.MemberVO;

public class SignUpDaoImpl implements ISignUpDao{
	
	private static SignUpDaoImpl dao; //자기 참조값 저장할 변수
	private SqlMapClient smc; // iBatis처리용 객체(SqlMapClient) 변수선언
	
	private SignUpDaoImpl() {
		smc=BuildedSqlMapClient.getSqlMapClient();
	}
	
	// 객체를 생성해서 반환하는 메서드
	public static SignUpDaoImpl getInstance(){
		if(dao==null) dao = new SignUpDaoImpl();
		return dao;
	}
	
	@Override
	public int insertMember(MemberVO memVo) {
		int cnt =0;
		try {
			Object obj = smc.insert("member.memInsert",memVo);
			if(obj==null) {
				cnt=1;
			}
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public String checkId(String memId) {
		String checkId = null;
		try {
			checkId = (String) smc.queryForObject("member.checkId",memId);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return checkId;
	}

//	@Override
//	public int deleteMember(String memId) {
//		int cnt = 0;
//		try {
//			cnt = smc.delete("member.memDelete", memId);
//		} catch (SQLException e) {
//			cnt = 0;
//			e.printStackTrace();
//		}
//		return cnt;
//	}

	@Override
	public int checkActive(String memActive) {
		int cnt = 0;
		try {
			cnt = smc.update("member.memUpdate", memActive);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public String checkPw(HashMap<String, String> map){
		String checkPw = null;
		try {
			checkPw = (String) smc.queryForObject("member.checkPw",map);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return checkPw;
	}
	
	@Override
	public int updateMember(MemberVO memVo) {
		int cnt = 0;
		try {
			cnt = smc.update("member.memInfoUpdate", memVo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int blacklistMemberUpdate(String memId) {
		int cnt = 0;
		try {
			cnt = smc.update("member.blacklistMemberUpdate", memId);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

}
