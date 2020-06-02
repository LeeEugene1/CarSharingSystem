package kr.or.ddit.css.service.payment;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.css.dao.license.ILicenseDao;
import kr.or.ddit.css.dao.license.LicenseDaoImpl;
import kr.or.ddit.css.dao.payment.IPaymentDao;
import kr.or.ddit.css.dao.payment.PaymentDaoImpl;
import kr.or.ddit.css.vo.LicenseVO;
import kr.or.ddit.css.vo.PaymentVO;

public class PaymentServiceImpl extends UnicastRemoteObject implements IPaymentService{
	private static PaymentServiceImpl service;
	
	public static PaymentServiceImpl getInstance() throws RemoteException{
		if(service==null) service = new PaymentServiceImpl();
		return service;
	}
	
	private IPaymentDao dao;
	
	public PaymentServiceImpl() throws RemoteException{
		dao=PaymentDaoImpl.getInstance();
	}

	@Override
	public int insertPayment(PaymentVO payVo) throws RemoteException {
		return dao.insertPayment(payVo);
	}

}
