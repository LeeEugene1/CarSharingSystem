package kr.or.ddit.css.dao.notice;

import java.util.List;

import kr.or.ddit.css.vo.NoticeVO;


public interface INoticeDao {
	
	public List<NoticeVO> getAllNBoardList();
	
	public int insertNBoard(NoticeVO noticeVo);
	
	public int updateNBorad(NoticeVO noticeVo);
	
	public int deleteNBoard(int boardId);
	
	public int setCountIncrement(int boardId);
	
	public List<NoticeVO> getSearchNBoardList(String boardTitle);
}
