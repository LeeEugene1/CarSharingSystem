package kr.or.ddit.css.dao.notice;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.css.util.BuildedSqlMapClient;
import kr.or.ddit.css.vo.NoticeVO;

public class NoticeDaoImpl implements INoticeDao{
	
	private static NoticeDaoImpl dao;
	private SqlMapClient smc;
	
	private NoticeDaoImpl() {
		smc=BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static NoticeDaoImpl getInstance() {
		if(dao==null) dao = new NoticeDaoImpl();
		return dao;
	}
	
	@Override
	public List<NoticeVO> getAllNBoardList() {
		List<NoticeVO> boardList = null;
		
		try {
			boardList = smc.queryForList("notice.getAllNBoardList");
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		}
		return boardList;
	}

	@Override
	public int insertNBoard(NoticeVO noticeVo) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("notice.insertNBoard", noticeVo);
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
	public int updateNBorad(NoticeVO noticeVo) {
		int cnt = 0;
		try {
			cnt=smc.update("notice.updateNBoard",noticeVo);
			
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteNBoard(int boardId) {
		int cnt = 0;
		try {
			cnt=smc.delete("notice.boardNDelete",boardId);
			
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
			cnt=smc.update("notice.setCountIncrement",boardId);
			
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<NoticeVO> getSearchNBoardList(String boardTitle) {
		if(boardTitle==null) boardTitle="";
		List<NoticeVO> boardList = null;
		
		try {
			boardList=smc.queryForList("notice.getSearchNBoardList",boardTitle);
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		}
		return boardList;
	}
}
