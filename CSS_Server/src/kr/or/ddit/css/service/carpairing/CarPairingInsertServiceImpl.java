package kr.or.ddit.css.service.carpairing;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.css.dao.carpairing.CarpairingInsertDaoImpl;
import kr.or.ddit.css.dao.carpairing.ICarPairingInsertDao;
import kr.or.ddit.css.vo.CarPairingInsertBoardVO;

public class CarPairingInsertServiceImpl extends UnicastRemoteObject implements ICarPairingInsertService{
	private static CarPairingInsertServiceImpl service;
	
	public static CarPairingInsertServiceImpl getInstance() throws RemoteException{
		if(service==null) service = new CarPairingInsertServiceImpl();
		return service;
	}
	
	private ICarPairingInsertDao dao;
	
	public CarPairingInsertServiceImpl() throws RemoteException{
		dao=CarpairingInsertDaoImpl.getinstance();
	}

	@Override
	public List<CarPairingInsertBoardVO> getAllBoardList() throws RemoteException {
		return dao.getAllBoardList();
	}

	@Override
	public int insertBoard(CarPairingInsertBoardVO CPIBVo) throws RemoteException {
		return dao.insertBoard(CPIBVo);
	}

	@Override
	public int updateBoard(CarPairingInsertBoardVO CPIBVo) throws RemoteException {
		return dao.updateBoard(CPIBVo);
	}

	@Override
	public int deleteBoard(int boardId) throws RemoteException {
		return dao.deleteBoard(boardId);
	}

	@Override
	public int setCountIncrement(int boardId) throws RemoteException {
		return dao.setCountIncrement(boardId);
	}

	@Override
	public List<CarPairingInsertBoardVO> getSearchBoardList(String memId) throws RemoteException {
		return dao.getSearchBoardList(memId);
	}
}
