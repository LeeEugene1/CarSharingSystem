package kr.or.ddit.css.dao.license;

import java.util.List;

import kr.or.ddit.css.vo.LicenseVO;

public interface ILicenseDao {
	
	public List<LicenseVO> getAllLicense();
	
	public int insertLicense(LicenseVO licenseVo);
	
	public int deleteLicense(String memId);
	
	public List<LicenseVO> getMemLicense(String memId);
}
