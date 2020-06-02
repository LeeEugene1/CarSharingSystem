package kr.or.ddit.css.dao.carsharing;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.css.util.BuildedSqlMapClient;
import kr.or.ddit.css.vo.CarVO;

public class CarSharingDaoImpl implements ICarSharingDao{
	
	private static CarSharingDaoImpl dao;
	private SqlMapClient smc;
	
	private CarSharingDaoImpl() {
		smc=BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static CarSharingDaoImpl getInstance() {
		if(dao==null) dao = new CarSharingDaoImpl();
		return dao;
	}
	
	@Override
	public List<CarVO> getAllCarList(int carTypeId) {
		List<CarVO> carList = null;
		
		try {
			carList = smc.queryForList("carSharing.getAllCarList",carTypeId);
		} catch (SQLException e) {
			carList = null;
			e.printStackTrace();
		}
		return carList;
	}

	@Override
	public String getCarName(String carNum) {
		String getCarName = null;
		try {
			getCarName = (String) smc.queryForObject("carSharing.getCarName", carNum);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getCarName;
	}

	
}
