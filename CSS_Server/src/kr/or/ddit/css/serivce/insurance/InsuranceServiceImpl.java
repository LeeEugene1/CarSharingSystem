package kr.or.ddit.css.serivce.insurance;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.css.dao.blacklist.BlacklistDaoImpl;
import kr.or.ddit.css.dao.blacklist.IBlacklistDao;
import kr.or.ddit.css.dao.insurance.IInsuranceDao;
import kr.or.ddit.css.dao.insurance.InsuranceDaoImpl;
import kr.or.ddit.css.vo.BlackListVO;
import kr.or.ddit.css.vo.InsuranceVO;

public class InsuranceServiceImpl extends UnicastRemoteObject implements IInsuranceService {
	private static InsuranceServiceImpl service;
	
	public static InsuranceServiceImpl getInstance() throws RemoteException {
		if(service == null) service = new InsuranceServiceImpl();
		return service;
	}
	
	private IInsuranceDao dao;
	
	public InsuranceServiceImpl() throws RemoteException{
		dao = InsuranceDaoImpl.getInstance();
	}

	@Override
	public int getInsuranceCost(int insuranceId) throws RemoteException {
		return dao.getInsuranceCost(insuranceId);
	}

}
