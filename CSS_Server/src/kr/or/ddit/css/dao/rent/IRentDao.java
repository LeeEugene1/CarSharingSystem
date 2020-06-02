package kr.or.ddit.css.dao.rent;

import java.util.List;

import kr.or.ddit.css.vo.RentVO;

public interface IRentDao {
	
	public int insertRent(RentVO rentVo);
	
	public int selectRentId(String memId);
	
	public List<RentVO> selectRentList(String memId);
}
