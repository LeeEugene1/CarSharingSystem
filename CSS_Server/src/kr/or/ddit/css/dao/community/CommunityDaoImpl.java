package kr.or.ddit.css.dao.community;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.css.util.BuildedSqlMapClient;
import kr.or.ddit.css.vo.CommunityBoardVO;

public class CommunityDaoImpl implements ICommunityDao{
	
	private static CommunityDaoImpl dao;
	private SqlMapClient smc;
	
	private CommunityDaoImpl() {
		smc=BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static CommunityDaoImpl getInstance() {
		if(dao==null) dao = new CommunityDaoImpl();
		return dao;
	}
	
	
	@Override
	public List<CommunityBoardVO> getAllBoardList() {
		List<CommunityBoardVO> boardList = null;
		
		try {
			// select문의 처리결과가 여러개일 경우
			boardList = smc.queryForList("communityboard.getAllBoardList");
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} 
		return boardList;
	}
	
	@Override
	public int insertBoard(CommunityBoardVO CBoardVo) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("communityboard.insertBoard", CBoardVo);
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
	public int updateBoard(CommunityBoardVO CBoardVo) {
		int cnt = 0;
		try {
			cnt=smc.update("communityboard.updateBoard",CBoardVo);
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
			cnt=smc.delete("communityboard.boardDelete",boardId);
			
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public int setCountIncrement(int boardId) {
		int cnt = 0;
		try {
			cnt=smc.update("communityboard.setCountIncrement",boardId);
			
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public List<CommunityBoardVO> getSearchBoardList(String boardTitle) {
		if(boardTitle==null) boardTitle="";
		List<CommunityBoardVO> boardList = null;
		
		try {
			boardList=smc.queryForList("communityboard.getSearchBoardList",boardTitle);
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		}
		return boardList;
	}
}
