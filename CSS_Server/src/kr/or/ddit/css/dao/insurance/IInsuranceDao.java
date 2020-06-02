package kr.or.ddit.css.dao.insurance;

import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.css.vo.InsuranceVO;
import kr.or.ddit.css.vo.LicenseVO;

public interface IInsuranceDao {
	public int getInsuranceCost(int insuranceId);
}
