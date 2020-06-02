package kr.or.ddit.css.dao.insurance;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.builder.xml.SqlMapClasspathEntityResolver;

import kr.or.ddit.css.util.BuildedSqlMapClient;
import kr.or.ddit.css.vo.CarVO;
import kr.or.ddit.css.vo.InsuranceVO;
import kr.or.ddit.css.vo.LicenseVO;

public class InsuranceDaoImpl implements IInsuranceDao{
	private static InsuranceDaoImpl dao;
	private SqlMapClient smc;
	
	private InsuranceDaoImpl() {
		smc=BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static InsuranceDaoImpl getInstance() {
		if(dao==null) dao = new InsuranceDaoImpl();
		return dao;
	}

	@Override
	public int getInsuranceCost(int insuranceId) {
		int insuranceCost = 0;
		
		try {
			insuranceCost = (int)smc.queryForObject("insurance.getInsuranceCost",insuranceId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insuranceCost;
	}
	
}
