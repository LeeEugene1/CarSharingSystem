package kr.or.ddit.css.service.card;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.css.dao.card.CardDaoImpl;
import kr.or.ddit.css.dao.card.ICardDao;
import kr.or.ddit.css.vo.CardVO;

public class CardServiceImpl  extends UnicastRemoteObject implements ICardService{
	private static CardServiceImpl service;
	
	public static CardServiceImpl getInstance() throws RemoteException{
		if(service==null) service = new CardServiceImpl();
		return service;
	}
	
	private ICardDao dao;
	
	public CardServiceImpl() throws RemoteException{
		dao=CardDaoImpl.getInastance();
	}

	@Override
	public List<CardVO> getAllCard() throws RemoteException {
		return dao.getAllCard();
	}

	@Override
	public int insertCard(CardVO cardVo) throws RemoteException {
		return dao.insertCard(cardVo);
	}

	@Override
	public int deleteCard(String memId) throws RemoteException {
		return dao.deleteCard(memId);
	}

	@Override
	public List<CardVO> getMemCard(String memId) throws RemoteException {
		return dao.getMemCard(memId);
	}
	
}
