package kr.or.ddit.css.service.carpairing;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.css.dao.carpairing.CarpairingUseDaoImpl;
import kr.or.ddit.css.dao.carpairing.ICarPairingUseDao;
import kr.or.ddit.css.vo.CarPairingUseBoardVO;

public class CarPairingUseServiceImpl extends UnicastRemoteObject implements ICarPairingUseService{
	private static CarPairingUseServiceImpl service;
	
	public static CarPairingUseServiceImpl getInstance() throws RemoteException{
		if(service==null) service = new CarPairingUseServiceImpl();
		return service;
	}
	
	private ICarPairingUseDao dao;
	
	public CarPairingUseServiceImpl() throws RemoteException{
		dao=CarpairingUseDaoImpl.getinstance();
	}

	@Override
	public List<CarPairingUseBoardVO> getAllBoardList() throws RemoteException {
		return dao.getAllBoardList();
	}

	@Override
	public int insertBoard(CarPairingUseBoardVO CPUBVo) throws RemoteException {
		return dao.insertBoard(CPUBVo);
	}

	@Override
	public int updateBoard(CarPairingUseBoardVO CPUBVo) throws RemoteException {
		return dao.updateBoard(CPUBVo);
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
	public List<CarPairingUseBoardVO> getSearchBoardList(String boardTitle) throws RemoteException {
		return dao.getSearchBoardList(boardTitle);
	}

	@Override
	public int deleteApply(HashMap<String, String> paramMap) throws RemoteException {
		return dao.deleteApply(paramMap);
	}
}
