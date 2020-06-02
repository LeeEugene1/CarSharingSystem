package kr.or.ddit.css.service.QnA;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.css.dao.QnA.IQnADao;
import kr.or.ddit.css.dao.QnA.QnADaoImpl;
import kr.or.ddit.css.vo.QnAVO;

public class QnAServiceImpl  extends UnicastRemoteObject implements IQnAService{
	private static QnAServiceImpl service;
	
	public static QnAServiceImpl getInstance() throws RemoteException{
		if(service==null) service = new QnAServiceImpl();
		return service;
	}
	
	private IQnADao dao;
	
	public QnAServiceImpl() throws RemoteException{
		dao=QnADaoImpl.getInstance();
	}

	@Override
	public List<QnAVO> getAllQnAList() throws RemoteException {
		return dao.getAllQnAList();
	}

	@Override
	public int insertQnA(QnAVO qnaVo) throws RemoteException {
		return dao.insertQnA(qnaVo);
	}

	@Override
	public int updateQnA(QnAVO qnaVo) throws RemoteException {
		return dao.updateQnA(qnaVo);
	}

	@Override
	public int deleteQnA(int qnaId) throws RemoteException {
		return dao.deleteQnA(qnaId);
	}

	@Override
	public int setCountIncrement(int qnaId) throws RemoteException {
		return dao.setCountIncrement(qnaId);
	}

	@Override
	public List<QnAVO> getSearchQnAList(String qnaTitle) throws RemoteException {
		return dao.getSearchQnAList(qnaTitle);
	}
}
