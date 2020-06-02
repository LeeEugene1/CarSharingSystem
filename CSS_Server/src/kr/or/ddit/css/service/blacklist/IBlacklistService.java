package kr.or.ddit.css.service.blacklist;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.css.vo.BlackListVO;

public interface IBlacklistService extends Remote {
	public List<BlackListVO> getAllBoardList() throws RemoteException;
	public int insertBoard(BlackListVO bListVo) throws RemoteException;
	public int setCountIncrement(int boardId) throws RemoteException;
	public int updateBoard(BlackListVO bListVo) throws RemoteException;
	public int deleteBoard(int boardId) throws RemoteException;
	public List<BlackListVO> getSearchBoardList(String boardTitle) throws RemoteException;
}
