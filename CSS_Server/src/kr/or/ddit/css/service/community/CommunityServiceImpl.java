package kr.or.ddit.css.service.community;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.css.dao.community.CommunityDaoImpl;
import kr.or.ddit.css.dao.community.ICommunityDao;
import kr.or.ddit.css.vo.CommunityBoardVO;

public class CommunityServiceImpl extends UnicastRemoteObject implements ICommunityService{
	private static CommunityServiceImpl service;
	
	public static CommunityServiceImpl getInstance() throws RemoteException{
		if(service==null) service = new CommunityServiceImpl();
		return service;
	}
	
	private ICommunityDao dao;
	
	public CommunityServiceImpl() throws RemoteException{
		dao=CommunityDaoImpl.getInstance();
	}

	@Override
	public List<CommunityBoardVO> getAllBoardList() throws RemoteException {
		return dao.getAllBoardList();
	}

	@Override
	public int insertBoard(CommunityBoardVO CBoardVo) throws RemoteException {
		return dao.insertBoard(CBoardVo);
	}

	@Override
	public int updateBoard(CommunityBoardVO CBoardVo) throws RemoteException {
		return dao.updateBoard(CBoardVo);
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
	public List<CommunityBoardVO> getSearchBoardList(String boardTitle) throws RemoteException {
		return dao.getSearchBoardList(boardTitle);
	}
	
	
}
