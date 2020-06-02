package kr.or.ddit.css.service.payment;

import java.rmi.Remote;
import java.rmi.RemoteException;

import kr.or.ddit.css.vo.PaymentVO;

public interface IPaymentService extends Remote{
	
	public int insertPayment(PaymentVO payVo) throws RemoteException;
	
}
