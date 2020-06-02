package kr.or.ddit.css.dao.QnA;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.css.util.BuildedSqlMapClient;
import kr.or.ddit.css.vo.NoticeVO;
import kr.or.ddit.css.vo.QnAVO;

public class QnADaoImpl implements IQnADao{
	
	private static QnADaoImpl dao;
	private SqlMapClient smc;
	
	private QnADaoImpl() {
		smc=BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static QnADaoImpl getInstance() {
		if(dao==null) dao = new QnADaoImpl();
		return dao;
	}

	@Override
	public List<QnAVO> getAllQnAList() {
		List<QnAVO> boardList = null;
		
		try {
			boardList = smc.queryForList("qna.getAllQnAList");
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		}
		return boardList;
	}

	@Override
	public int insertQnA(QnAVO qnaVo) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("qna.insertQnA", qnaVo);
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
	public int updateQnA(QnAVO qnaVo) {
		int cnt = 0;
		try {
			cnt=smc.update("qna.updateQnA",qnaVo);
			
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteQnA(int qnaId) {
		int cnt = 0;
		try {
			cnt=smc.delete("qna.QnADelete",qnaId);
			
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int setCountIncrement(int qnaId) {
		int cnt = 0;
		try {
			cnt=smc.update("qna.setCountIncrement",qnaId);
			
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<QnAVO> getSearchQnAList(String qnaTitle) {
		if(qnaTitle==null) qnaTitle="";
		List<QnAVO> boardList = null;
		
		try {
			boardList=smc.queryForList("qna.getSearchQnAList",qnaTitle);
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		}
		return boardList;
	}
	
	
	
	
}
