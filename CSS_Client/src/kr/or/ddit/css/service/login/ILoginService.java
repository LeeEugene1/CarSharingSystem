package kr.or.ddit.css.service.login;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

import kr.or.ddit.css.vo.AdminVO;
import kr.or.ddit.css.vo.MemberVO;

public interface ILoginService extends Remote{
	/**
	 * 
	 * @param memId
	 * @return
	 * @throws RemoteException
	 */
	public List<MemberVO> login(String memId) throws RemoteException;
	/**
	 * 
	 * @param str
	 * @return
	 * @throws RemoteException
	 */
	public Boolean idCheck(String memId) throws RemoteException;
	/**
	 * 
	 * @param adminId
	 * @return
	 * @throws RemoteException
	 */
	public List<AdminVO> adminLogin(String adminId) throws RemoteException;
	/**
	 * 
	 * @param str
	 * @return
	 * @throws RemoteException
	 */
	public Boolean adminIdCheck(String adminId) throws RemoteException;
	
	/**
	 * 
	 * @param str
	 * @return
	 * @throws RemoteException
	 */
	public String idSearch(HashMap<String, String> param) throws RemoteException;
	
	/**
	 * 
	 * @param str
	 * @return
	 * @throws RemoteException
	 */
	public String pwSearch(HashMap<String, String> param) throws RemoteException;
	
	/**
	 * 
	 * MemberVO에 담겨진 자료를 DB에 update하는 메서드
	 * @param memVo
	 * @return 작업성공이면 1, 작업 실패면 0을 반환
	 */
	public int pwUpdateMember(MemberVO memVo) throws RemoteException;
}
