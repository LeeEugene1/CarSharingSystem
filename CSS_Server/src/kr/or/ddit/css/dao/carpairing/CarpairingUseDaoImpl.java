package kr.or.ddit.css.dao.carpairing;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.css.util.BuildedSqlMapClient;
import kr.or.ddit.css.vo.CarPairingInsertBoardVO;
import kr.or.ddit.css.vo.CarPairingUseBoardVO;

public class CarpairingUseDaoImpl implements ICarPairingUseDao{
	
	private static CarpairingUseDaoImpl dao;
	private SqlMapClient smc;
	
	private CarpairingUseDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static CarpairingUseDaoImpl getinstance() {
		if(dao==null) dao = new CarpairingUseDaoImpl();
		return dao;
	}

	@Override
	public List<CarPairingUseBoardVO> getAllBoardList() {
		List<CarPairingUseBoardVO> boardList = null;
		try {
			boardList = smc.queryForList("carpairingUseboard.getAllBoardList");
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		}
		
		return boardList;
	}

	@Override
	public int insertBoard(CarPairingUseBoardVO CPUBVo) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("carpairingUseboard.insertBoard", CPUBVo);
		    if(obj==null){ //insert작업 성공여부
		    	cnt=1;
		    }	
		}catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateBoard(CarPairingUseBoardVO CPUBVo) {
		int cnt = 0;
		try {
			cnt=smc.update("carpairingUseboard.updateBoard",CPUBVo);
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardId) {
		int cnt = 0;
		try {
			cnt=smc.delete("carpairingUseboard.boardDelete",boardId);
			
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<CarPairingUseBoardVO> getSearchBoardList(String boardTitle) {
		if(boardTitle==null) boardTitle="";
		List<CarPairingUseBoardVO> boardList = null;
		
		try {
			boardList=smc.queryForList("carpairingUseboard.getSearchBoardList",boardTitle);
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		}
		return boardList;
	
	}

	@Override
	public int setCountIncrement(int boardId) {
		int cnt = 0;
		try {
			cnt=smc.update("carpairingUseboard.setCountIncrement",boardId);
			
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteApply(HashMap<String, String> paramMap) {
		int cnt = 0;
		try {
			cnt=smc.delete("carpairingUseboard.boardDeleteApply",paramMap);
			System.out.println("cnt"+cnt);
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}
}
