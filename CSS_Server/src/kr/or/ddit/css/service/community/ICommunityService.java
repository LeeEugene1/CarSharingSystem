package kr.or.ddit.css.service.community;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.css.vo.CommunityBoardVO;

public interface ICommunityService extends Remote{
	
	public List<CommunityBoardVO> getAllBoardList() throws RemoteException;
	
	public int insertBoard(CommunityBoardVO CBoardVo) throws RemoteException;
	
	public int updateBoard(CommunityBoardVO CBoardVo) throws RemoteException;
	
	public int deleteBoard(int boardId) throws RemoteException;
	
	public int setCountIncrement(int boardId) throws RemoteException;
	
	public List<CommunityBoardVO> getSearchBoardList(String boardTitle) throws RemoteException;
}
