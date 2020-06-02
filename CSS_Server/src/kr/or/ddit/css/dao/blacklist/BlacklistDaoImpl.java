package kr.or.ddit.css.dao.blacklist;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.css.util.BuildedSqlMapClient;
import kr.or.ddit.css.vo.BlackListVO;

public class BlacklistDaoImpl implements IBlacklistDao {
	
	private static BlacklistDaoImpl dao;
	private SqlMapClient smc;
	
	public BlacklistDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static BlacklistDaoImpl getInstance() {
		if(dao==null) dao = new BlacklistDaoImpl();
		return dao;
	}

	@Override
	public List<BlackListVO> getAllBoardList() {
		List<BlackListVO> bList = null;
		
		try {
			//select문의 처리결과가 여러개일 경우
			bList = smc.queryForList("blacklist.getAllBoardList");
		} catch (SQLException e) {
			bList = null;
			e.printStackTrace();
		}
		return bList;
	}

	@Override
	public int insertBoard(BlackListVO bListVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("blacklist.insertBoard", bListVo);
			cnt = 1;
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int setCountIncrement(int boardId) {
		int cnt = 0;
		try {
			cnt = smc.update("blacklist.setCountIncrement",boardId);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateBoard(BlackListVO bListVo) {
		int cnt = 0;
		try {
			cnt = smc.update("blacklist.updateBoard", bListVo);
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
			cnt = smc.delete("blacklist.boardDelete", boardId);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<BlackListVO> getSearchBoardList(String boardTitle) {
		if(boardTitle == null) boardTitle="";
		List<BlackListVO> boardList = null;
		
		try {
			boardList = smc.queryForList("blacklist.getSearchBoardList", boardTitle);
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		}
		return boardList;
	}


}
