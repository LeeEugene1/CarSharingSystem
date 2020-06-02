package kr.or.ddit.css.service.QnA;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.css.vo.QnAVO;

public interface IQnAService extends Remote{
	public List<QnAVO> getAllQnAList() throws RemoteException;
	
	public int insertQnA(QnAVO qnaVo) throws RemoteException;
	
	public int updateQnA(QnAVO qnaVo) throws RemoteException;
	
	public int deleteQnA(int qnaId) throws RemoteException;
	
	public int setCountIncrement(int qnaId) throws RemoteException;
	
	public List<QnAVO> getSearchQnAList(String qnaTitle) throws RemoteException;
}
