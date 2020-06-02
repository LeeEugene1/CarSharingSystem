package kr.or.ddit.css.service.carpairing;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.css.vo.CarPairingUseBoardVO;

public interface ICarPairingUseService extends Remote{
public List<CarPairingUseBoardVO> getAllBoardList() throws RemoteException;
	
	public int insertBoard(CarPairingUseBoardVO CPUBVo) throws RemoteException;
	
	public int updateBoard(CarPairingUseBoardVO CPUBVo) throws RemoteException;
	
	public int deleteBoard(int boardId) throws RemoteException;
	
	public int deleteApply(HashMap<String, String> paramMap) throws RemoteException;
	
	public int setCountIncrement(int boardId) throws RemoteException;
	
	public List<CarPairingUseBoardVO> getSearchBoardList(String boardTitle) throws RemoteException;
}
