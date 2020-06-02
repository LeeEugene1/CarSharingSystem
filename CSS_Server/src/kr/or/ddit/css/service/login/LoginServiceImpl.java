package kr.or.ddit.css.service.login;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

import kr.or.ddit.css.dao.login.ILoginDao;
import kr.or.ddit.css.dao.login.LoginDaoImpl;
import kr.or.ddit.css.vo.AdminVO;
import kr.or.ddit.css.vo.MemberVO;

public class LoginServiceImpl extends UnicastRemoteObject implements ILoginService{
	
	private ILoginDao dao;
	
	private static LoginServiceImpl service;
	
	private LoginServiceImpl() throws RemoteException{
		dao = LoginDaoImpl.getInstance();
	}
	
	public static LoginServiceImpl getInstance() throws RemoteException{
		if(service == null) {
			service = new LoginServiceImpl();
		}
		return service;
	}

	@Override
	public List<MemberVO> login(String memId) throws RemoteException {
		return dao.login(memId);
	}

	@Override
	public Boolean idCheck(String memId) throws RemoteException {
		return dao.idCheck(memId);
	}

	@Override
	public List<AdminVO> adminLogin(String adminId) throws RemoteException {
		return dao.adminLogin(adminId);
	}

	@Override
	public Boolean adminIdCheck(String adminId) throws RemoteException {
		return dao.adminIdCheck(adminId);
	}

	@Override
	public String idSearch(HashMap<String, String> param) throws RemoteException {
		return dao.idSearch(param);
	}

	@Override
	public String pwSearch(HashMap<String, String> param) throws RemoteException {
		return dao.pwSearch(param);
	}

	@Override
	public int pwUpdateMember(MemberVO memVo) throws RemoteException {
		return dao.pwUpdateMember(memVo);
	}
	
}
