package kr.or.ddit.css.dao.carpairing;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.css.util.BuildedSqlMapClient;
import kr.or.ddit.css.vo.CarPairingInsertBoardVO;

public class CarpairingInsertDaoImpl implements ICarPairingInsertDao{
	
	private static CarpairingInsertDaoImpl dao;
	private SqlMapClient smc;
	
	private CarpairingInsertDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static CarpairingInsertDaoImpl getinstance() {
		if(dao==null) dao = new CarpairingInsertDaoImpl();
		return dao;
	}

	@Override
	public List<CarPairingInsertBoardVO> getAllBoardList() {
		List<CarPairingInsertBoardVO> boardList = null;
		try {
			boardList = smc.queryForList("carpairingInsertboard.getAllBoardList");
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		}
		
		return boardList;
	}

	@Override
	public int insertBoard(CarPairingInsertBoardVO CPIBVo) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("carpairingInsertboard.insertBoard", CPIBVo);
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
	public int updateBoard(CarPairingInsertBoardVO CPIBVo) {
		int cnt = 0;
		try {
			cnt=smc.update("carpairingInsertboard.updateBoard",CPIBVo);
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
			cnt=smc.delete("carpairingInsertboard.boardDelete",boardId);
			
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<CarPairingInsertBoardVO> getSearchBoardList(String memId) {
		if(memId==null) memId="";
		List<CarPairingInsertBoardVO> boardList = null;
		
		try {
			boardList=smc.queryForList("carpairingInsertboard.getSearchBoardList",memId);
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
			cnt=smc.update("carpairingInsertboard.setCountIncrement",boardId);
			
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}
}
