package kr.or.ddit.css.dao.login;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.css.vo.AdminVO;
import kr.or.ddit.css.vo.MemberVO;

public class LoginDaoImpl implements ILoginDao{
	
	private SqlMapClient smc;
	
	private static LoginDaoImpl dao;
	
	private LoginDaoImpl() {
		try {
			Reader rd =  Resources.getResourceAsReader("sqlMapConfig.xml");

			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static LoginDaoImpl getInstance() {
		if (dao == null) {
			dao = new LoginDaoImpl();
		}

		return dao;
	}
	
	
	@Override
	public List<MemberVO> login(String memId) {
		ArrayList<MemberVO> list = new ArrayList<>();

		try {
			list = (ArrayList<MemberVO>) smc.queryForList("login.loginSelect",memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	@Override
	public Boolean idCheck(String memId) {
		Boolean idCheck = false;
		try {
			int count = (int) smc.queryForObject("login.loginIdCheck", memId);

			if (count > 0) {
				idCheck = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idCheck;
	}
	
	@Override
	public List<AdminVO> adminLogin(String adminId) {
		ArrayList<AdminVO> loginList = new ArrayList<>();

		try {
			loginList = (ArrayList<AdminVO>) smc.queryForList("login.adminLoginSelect",adminId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return loginList;
	}
	
	@Override
	public Boolean adminIdCheck(String adminId) {
		Boolean adminIdCheck = false;
		try {
			int count = (int) smc.queryForObject("login.loginAdminIdCheck", adminId);

			if (count > 0) {
				adminIdCheck = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adminIdCheck;
	}
	
	@Override
	public String idSearch(HashMap<String, String> param) {
		String memId = "";
		try {
			memId = (String) smc.queryForObject("login.idSearch", param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memId;
	}
	
	@Override
	public String pwSearch(HashMap<String, String> param) {
		String pwCheck = "";
		try {
			pwCheck = (String) smc.queryForObject("login.pwSearch", param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pwCheck;
	}
	
	@Override
	public int pwUpdateMember(MemberVO memVo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("login.pwUpdate", memVo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}
	
}
