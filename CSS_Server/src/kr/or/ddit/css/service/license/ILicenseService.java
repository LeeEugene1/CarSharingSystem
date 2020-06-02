package kr.or.ddit.css.service.license;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.css.vo.LicenseVO;

public interface ILicenseService extends Remote{
	
	public List<LicenseVO> getAllLicense() throws RemoteException;
	
	public int insertLicense(LicenseVO licenseVo) throws RemoteException;
	
	public int deleteLicense(String memId) throws RemoteException;
	
	public List<LicenseVO> getMemLicense(String memId) throws RemoteException;
}
