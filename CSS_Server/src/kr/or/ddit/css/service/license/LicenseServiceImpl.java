package kr.or.ddit.css.service.license;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.css.dao.license.ILicenseDao;
import kr.or.ddit.css.dao.license.LicenseDaoImpl;
import kr.or.ddit.css.vo.LicenseVO;

public class LicenseServiceImpl extends UnicastRemoteObject implements ILicenseService{
	private static LicenseServiceImpl service;
	
	public static LicenseServiceImpl getInstance() throws RemoteException{
		if(service==null) service = new LicenseServiceImpl();
		return service;
	}
	
	private ILicenseDao dao;
	
	public LicenseServiceImpl() throws RemoteException{
		dao=LicenseDaoImpl.getInstance();
	}

	@Override
	public List<LicenseVO> getAllLicense() throws RemoteException {
		return dao.getAllLicense();
	}

	@Override
	public int insertLicense(LicenseVO licenseVo) throws RemoteException {
		return dao.insertLicense(licenseVo);
	}

	@Override
	public int deleteLicense(String memId) throws RemoteException {
		return dao.deleteLicense(memId);
	}

	@Override
	public List<LicenseVO> getMemLicense(String memId) throws RemoteException {
		return dao.getMemLicense(memId);
	}
}
