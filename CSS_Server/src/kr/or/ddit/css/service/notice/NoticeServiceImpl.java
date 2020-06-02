package kr.or.ddit.css.service.notice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.css.dao.notice.INoticeDao;
import kr.or.ddit.css.dao.notice.NoticeDaoImpl;
import kr.or.ddit.css.vo.NoticeVO;

public class NoticeServiceImpl extends UnicastRemoteObject implements INoticeService{
	private static NoticeServiceImpl service;
	
	public static NoticeServiceImpl getInstance() throws RemoteException{
		if(service==null) service = new NoticeServiceImpl();
		return service;
	}
	
	private INoticeDao dao;
	
	public NoticeServiceImpl() throws RemoteException{
		dao = NoticeDaoImpl.getInstance();
	}

	@Override
	public List<NoticeVO> getAllNBoardList() throws RemoteException {
		return dao.getAllNBoardList();
	}

	@Override
	public int insertNBoard(NoticeVO noticeVo) throws RemoteException {
		return dao.insertNBoard(noticeVo);
	}

	@Override
	public int updateNBorad(NoticeVO noticeVo) throws RemoteException {
		return dao.updateNBorad(noticeVo);
	}

	@Override
	public int deleteNBoard(int boardId) throws RemoteException {
		return dao.deleteNBoard(boardId);
	}

	@Override
	public int setCountIncrement(int boardId) throws RemoteException {
		return dao.setCountIncrement(boardId);
	}

	@Override
	public List<NoticeVO> getSearchNBoardList(String boardTitle) throws RemoteException {
		return dao.getSearchNBoardList(boardTitle);
	}
	
}
