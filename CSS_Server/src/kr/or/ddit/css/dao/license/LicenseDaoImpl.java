package kr.or.ddit.css.dao.license;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.builder.xml.SqlMapClasspathEntityResolver;

import kr.or.ddit.css.util.BuildedSqlMapClient;
import kr.or.ddit.css.vo.LicenseVO;

public class LicenseDaoImpl implements ILicenseDao{
	private static LicenseDaoImpl dao;
	private SqlMapClient smc;
	
	private LicenseDaoImpl() {
		smc=BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static LicenseDaoImpl getInstance() {
		if(dao==null) dao = new LicenseDaoImpl();
		return dao;
	}

	@Override
	public List<LicenseVO> getAllLicense() {
		List<LicenseVO> licenseList = null;
		try {
			// select문의 처리결과가 여러개일 경우
			licenseList = smc.queryForList("license.getALllisence");
		} catch (SQLException e) {
			licenseList = null;
			e.printStackTrace();
		} 
		return licenseList;
	}

	@Override
	public int insertLicense(LicenseVO licenseVo) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("license.insertLicense", licenseVo);
		    if(obj==null){ //insert작업 성공여부
		    	cnt=1;
		    }
			
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteLicense(String memId) {
		int cnt = 0;
		try {
			cnt=smc.delete("license.deleteLicense",memId);
			
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<LicenseVO> getMemLicense(String memId) {
		List<LicenseVO> memLicenseList=null;
		try {
			memLicenseList=smc.queryForList("license.getMemLicense",memId);
		} catch (SQLException e) {
			memLicenseList=null;
			e.printStackTrace();
		}
		return memLicenseList;
	}
	
	
}
