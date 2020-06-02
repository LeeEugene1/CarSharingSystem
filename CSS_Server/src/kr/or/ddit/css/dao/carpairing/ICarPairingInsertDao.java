package kr.or.ddit.css.dao.carpairing;

import java.util.List;

import kr.or.ddit.css.vo.CarPairingInsertBoardVO;

public interface ICarPairingInsertDao {
	
	public List<CarPairingInsertBoardVO> getAllBoardList();
	
	public int insertBoard(CarPairingInsertBoardVO CPIBVo);
	
	public int updateBoard(CarPairingInsertBoardVO CPIBVo);
	
	public int deleteBoard(int boardId);
	
	public int setCountIncrement(int boardId);
	
	public List<CarPairingInsertBoardVO> getSearchBoardList(String memId);
}
