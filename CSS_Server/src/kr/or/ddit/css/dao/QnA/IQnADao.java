package kr.or.ddit.css.dao.QnA;

import java.util.List;

import kr.or.ddit.css.vo.QnAVO;

public interface IQnADao {
	public List<QnAVO> getAllQnAList();
	
	public int insertQnA(QnAVO qnaVo);
	
	public int updateQnA(QnAVO qnaVo);
	
	public int deleteQnA(int qnaId);
	
	public int setCountIncrement(int qnaId);
	
	public List<QnAVO> getSearchQnAList(String qnaTitle);
}
