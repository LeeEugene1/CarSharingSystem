package kr.or.ddit.css.dao.blacklist;

import java.util.List;

import kr.or.ddit.css.vo.BlackListVO;

public interface IBlacklistDao {
	/**
	 * BlacklistBoard테이블의 전체 레코드를 가져와 List에 담아서 반환하는 메서드
	 * @return BlackListVO객체를 담고있는 List객체
	 */
	public List<BlackListVO> getAllBoardList();
	
	/**
	 * BlackListVO객체에 담겨진 자료를 DB에 insert하는 메서드
	 * @param blBoardVO DB에 insert할 자료가 저장된 BlackListVO객체
	 * @return 작업성공 1, 실패 0 반환
	 */
	public int insertBoard(BlackListVO bListVo);
	
	/**
	 * BlackListVO 자료를 이용하여 update하는 메서드
	 * @param bListVo update할 게시글 정보가 들어있는 BlackListVO객체
	 * @return 작업성공 1, 실패 0 반환
	 */
	public int updateBoard(BlackListVO bListVo);
	
	/**
	 * 게시글 번호를 매개변수로 받아 해당 게시글 정보를 delete하는 메서드
	 * @param boardId 삭제할 게시글 번호
	 * @return 성공하면 1 실패시 0
	 */
	public int deleteBoard(int boardId);
	
	/**
	 * 게시글 번호를 매개변수로 받아 해당 게시글 조회수를 증가시키는 메서드
	 * @param boardId 조회수를 증가할 게시글 번호
	 * @return 성공 1 실패 0
	 */
	public int setCountIncrement(int boardId);
	
	/**
	 * 검색할 문자열을 매개변수로 받아서 게시글의 제목에서 검색하는 메서드
	 * @param boardTitle 검색할 게시글 제목
	 * @return 검색된 결과를 담은 List객체
	 */
	public List<BlackListVO> getSearchBoardList(String boardTitle);
	

}
