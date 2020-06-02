package kr.or.ddit.css.service.signUp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import kr.or.ddit.css.dao.singUp.ISignUpDao;
import kr.or.ddit.css.dao.singUp.SignUpDaoImpl;
import kr.or.ddit.css.vo.MemberVO;

public class SignUpServiceImpl extends UnicastRemoteObject implements ISignUpService{
	
	private ISignUpDao dao;
	
	private static SignUpServiceImpl service;
	
	
	private SignUpServiceImpl() throws RemoteException {
		dao = SignUpDaoImpl.getInstance();
	}
	
	public static SignUpServiceImpl getInstance() throws RemoteException{
		if(service==null) {
			service = new SignUpServiceImpl();
		}
		return service;
	}

	@Override
	public int insertMember(MemberVO memVo) throws RemoteException {
		return dao.insertMember(memVo);
	}

	@Override
	public String checkId(String memId) throws RemoteException {
		String checkId = dao.checkId(memId);
		return checkId;
	}

//	@Override
//	public int deleteMember(String memId) throws RemoteException{
//		return dao.deleteMember(memId);
//	}

	@Override
	public String checkPw(HashMap<String, String> map) throws RemoteException {
		String checkPw = dao.checkPw(map);
		return checkPw;
	}

	@Override
	public int checkActive(String memActive) throws RemoteException {
		return dao.checkActive(memActive);
	}
	
	@Override
	public int updateMember(MemberVO memVo) throws RemoteException{
		return dao.updateMember(memVo);
	}

	@Override
	public int blacklistMemberUpdate(String memId) throws RemoteException {
		return dao.blacklistMemberUpdate(memId);
	}
}
