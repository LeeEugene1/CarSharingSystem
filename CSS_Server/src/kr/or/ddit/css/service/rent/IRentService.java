package kr.or.ddit.css.service.rent;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.css.vo.RentVO;

public interface IRentService extends Remote{
	
	public int insertRent(RentVO rentVo) throws RemoteException;
	
	public int selectRentId(String memId) throws RemoteException;
	
	public List<RentVO> selectRentList(String memId) throws RemoteException;
}
