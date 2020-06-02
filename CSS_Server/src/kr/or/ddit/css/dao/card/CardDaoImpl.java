package kr.or.ddit.css.dao.card;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.css.util.BuildedSqlMapClient;
import kr.or.ddit.css.vo.CardVO;

public class CardDaoImpl implements ICardDao{
	private static CardDaoImpl dao;
	private SqlMapClient smc;
	
	private CardDaoImpl() {
		smc=BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static CardDaoImpl getInastance() {
		if(dao==null)dao = new CardDaoImpl();
		return dao;
	}

	@Override
	public List<CardVO> getAllCard() {
		List<CardVO> cardList=null;
		try {
			cardList = smc.queryForList("card.getAllCard");
		} catch (SQLException e) {
			cardList=null;
			e.printStackTrace();
		}
		return cardList;
	}

	@Override
	public int insertCard(CardVO cardVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("card.insertCard",cardVo);
			if(obj==null) {
				cnt=1;
			}
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteCard(String memId) {
		int cnt=0;
		try {
			cnt=smc.delete("card.deleteCard",memId);
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<CardVO> getMemCard(String memId) {
		List<CardVO> memCardList=null;
		try {
			memCardList = smc.queryForList("card.getMemCard",memId);
		} catch (SQLException e) {
			memCardList = null;
			e.printStackTrace();
		}
		return memCardList;
	}
	
}
