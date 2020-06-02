package kr.or.ddit.css.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class BuildedSqlMapClient {
	private static SqlMapClient smc;
	
	static{
		Reader rd;
		try {
			// 1-1 문자 인코딩 케릭터셋 설정
			Charset charset = Charset.forName("utf-8");
			Resources.setCharset(charset);

			// 1-2 xml문서 읽어오기
			rd = Resources.getResourceAsReader("sqlMapConfig.xml"); // xml file

			// 1-3 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
			// (SqlMapClient객체 생성)
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);

			rd.close(); // Reader객체 닫기
		} catch (IOException e) {
			throw new RuntimeException("SqlMapClient 객체 생성 실패 : " + e, e);
		}
	}
	
	public static SqlMapClient getSqlMapClient(){
		return smc;
	}
}
