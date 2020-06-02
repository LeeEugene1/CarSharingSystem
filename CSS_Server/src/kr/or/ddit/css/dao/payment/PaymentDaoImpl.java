package kr.or.ddit.css.dao.payment;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.css.util.BuildedSqlMapClient;
import kr.or.ddit.css.vo.PaymentVO;

public class PaymentDaoImpl implements IPaymentDao{
	private static PaymentDaoImpl dao;
	private SqlMapClient smc;
	
	private PaymentDaoImpl() {
		smc=BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static PaymentDaoImpl getInstance() {
		if(dao==null) dao = new PaymentDaoImpl();
		return dao;
	}

	@Override
	public int insertPayment(PaymentVO payVo) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("payment.insertPayment", payVo);
		    if(obj==null){ //insert작업 성공여부
		    	cnt=1;
		    }
			
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}
	
}
