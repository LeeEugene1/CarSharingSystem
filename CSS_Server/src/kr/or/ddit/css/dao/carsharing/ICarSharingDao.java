package kr.or.ddit.css.dao.carsharing;

import java.util.List;

import kr.or.ddit.css.vo.CarVO;


public interface ICarSharingDao {
	public List<CarVO> getAllCarList(int carTypeId);
	
	public String getCarName(String carNum);
}
