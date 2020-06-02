package kr.or.ddit.css.serivce.insurance;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.css.vo.BlackListVO;
import kr.or.ddit.css.vo.CarVO;
import kr.or.ddit.css.vo.InsuranceVO;

public interface IInsuranceService extends Remote {
	public int getInsuranceCost(int insuranceId) throws RemoteException;
}
