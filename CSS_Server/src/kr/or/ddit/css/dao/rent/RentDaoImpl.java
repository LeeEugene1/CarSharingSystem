package kr.or.ddit.css.dao.rent;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.css.util.BuildedSqlMapClient;
import kr.or.ddit.css.vo.PaymentVO;
import kr.or.ddit.css.vo.RentVO;

public class RentDaoImpl implements IRentDao{
	private static RentDaoImpl dao;
	private SqlMapClient smc;
	
	private RentDaoImpl() {
		smc=BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static RentDaoImpl getInstance() {
		if(dao==null) dao = new RentDaoImpl();
		return dao;
	}

	@Override
	public int insertRent(RentVO rentVo) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("rent.insertRent", rentVo);
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
	public int selectRentId(String memId) {
		int cnt = 0;
		
		try {
			cnt = (int)smc.queryForObject("rent.selectRentId", memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<RentVO> selectRentList(String memId) {
		List<RentVO> list = null;
		
		try {
			list = (List<RentVO>)smc.queryForList("rent.getAllRentList", memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	
}
