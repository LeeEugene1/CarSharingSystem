package kr.or.ddit.css.service.rent;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.css.dao.rent.IRentDao;
import kr.or.ddit.css.dao.rent.RentDaoImpl;
import kr.or.ddit.css.vo.RentVO;

public class RentServiceImpl extends UnicastRemoteObject implements IRentService{
	private static RentServiceImpl service;
	
	public static RentServiceImpl getInstance() throws RemoteException{
		if(service==null) service = new RentServiceImpl();
		return service;
	}
	
	private IRentDao dao;
	
	public RentServiceImpl() throws RemoteException{
		dao=RentDaoImpl.getInstance();
	}

	@Override
	public int insertRent(RentVO rentVo) throws RemoteException {
		return dao.insertRent(rentVo);
	}

	@Override
	public int selectRentId(String memId) throws RemoteException {
		return dao.selectRentId(memId);
	}

	@Override
	public List<RentVO> selectRentList(String memId) throws RemoteException {
		return dao.selectRentList(memId);
	}
	
}
