package kr.or.ddit.css.service.blacklist;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.css.dao.blacklist.BlacklistDaoImpl;
import kr.or.ddit.css.dao.blacklist.IBlacklistDao;
import kr.or.ddit.css.vo.BlackListVO;

public class BlacklistServiceImpl extends UnicastRemoteObject implements IBlacklistService {
	private static BlacklistServiceImpl service;
	
	public static BlacklistServiceImpl getInstance() throws RemoteException {
		if(service == null) service = new BlacklistServiceImpl();
		return service;
	}
	
	private IBlacklistDao dao;
	
	public BlacklistServiceImpl() throws RemoteException{
		dao = BlacklistDaoImpl.getInstance();
	}
	
	@Override
	public List<BlackListVO> getAllBoardList() throws RemoteException {
		return dao.getAllBoardList();
	}

	@Override
	public int insertBoard(BlackListVO bListVo) throws RemoteException {
		return dao.insertBoard(bListVo);
	}

	@Override
	public int setCountIncrement(int boardId) throws RemoteException {
		return dao.setCountIncrement(boardId);
	}

	@Override
	public int updateBoard(BlackListVO bListVo) throws RemoteException {
		return dao.updateBoard(bListVo);
	}

	@Override
	public int deleteBoard(int boardId) throws RemoteException {
		return dao.deleteBoard(boardId);
	}

	@Override
	public List<BlackListVO> getSearchBoardList(String boardTitle) throws RemoteException {
		return dao.getSearchBoardList(boardTitle);
	}

}
