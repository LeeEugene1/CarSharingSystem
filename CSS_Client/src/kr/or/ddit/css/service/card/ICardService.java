package kr.or.ddit.css.service.card;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.css.vo.CardVO;

public interface ICardService extends Remote{
	
	public List<CardVO> getAllCard() throws RemoteException;
	
	public int insertCard(CardVO cardVo) throws RemoteException;
	
	public int deleteCard(String memId) throws RemoteException;
	
	public List<CardVO> getMemCard(String memId) throws RemoteException;
}
