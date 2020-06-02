package kr.or.ddit.css.service.notice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.css.vo.NoticeVO;

public interface INoticeService extends Remote{
	
	public List<NoticeVO> getAllNBoardList() throws RemoteException ;
	
	public int insertNBoard(NoticeVO noticeVo) throws RemoteException;
	
	public int updateNBorad(NoticeVO noticeVo) throws RemoteException;
	
	public int deleteNBoard(int boardId) throws RemoteException;
	
	public int setCountIncrement(int boardId) throws RemoteException;
	
	public List<NoticeVO> getSearchNBoardList(String boardTitle) throws RemoteException;
}
