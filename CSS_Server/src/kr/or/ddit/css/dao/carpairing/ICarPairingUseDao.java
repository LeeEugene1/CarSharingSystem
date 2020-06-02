package kr.or.ddit.css.dao.carpairing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.css.vo.CarPairingUseBoardVO;

public interface ICarPairingUseDao {
	
	public List<CarPairingUseBoardVO> getAllBoardList();
	
	public int insertBoard(CarPairingUseBoardVO CPUBVo);
	
	public int updateBoard(CarPairingUseBoardVO CPUBVo);
	
	public int deleteBoard(int boardId);
	
	public int deleteApply(HashMap<String, String> paramMap);
	
	public int setCountIncrement(int boardId);
	
	public List<CarPairingUseBoardVO> getSearchBoardList(String boardTitle);
}
