package kr.or.ddit.css.dao.card;

import java.util.List;

import kr.or.ddit.css.vo.CardVO;


public interface ICardDao {
	
	public List<CardVO> getAllCard();
	
	public int insertCard(CardVO cardVo);
	
	public int deleteCard(String memId);
	
	public List<CardVO> getMemCard(String memId);
}
