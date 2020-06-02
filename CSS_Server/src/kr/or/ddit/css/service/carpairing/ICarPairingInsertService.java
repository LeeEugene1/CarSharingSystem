package kr.or.ddit.css.service.carpairing;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.css.vo.CarPairingInsertBoardVO;

public interface ICarPairingInsertService extends Remote{
	
	public List<CarPairingInsertBoardVO> getAllBoardList() throws RemoteException;
	
	public int insertBoard(CarPairingInsertBoardVO CPIBVo) throws RemoteException;
	
	public int updateBoard(CarPairingInsertBoardVO CPIBVo) throws RemoteException;
	
	public int deleteBoard(int boardId) throws RemoteException;
	
	public int setCountIncrement(int boardId) throws RemoteException;
	
	public List<CarPairingInsertBoardVO> getSearchBoardList(String memId) throws RemoteException;
}
