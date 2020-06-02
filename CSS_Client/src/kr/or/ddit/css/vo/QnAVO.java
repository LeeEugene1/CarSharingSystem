package kr.or.ddit.css.vo;

import java.io.Serializable;

public class QnAVO implements Serializable{
	private int qna_id;			//자주묻는 질문 아이디(PK)
	private String qna_title;	//자주묻는 질문 제목
	private String qna_content;	//자주묻는 질문 내용
	private String admin_id;	//관리자 아이디(FK)
	private String qna_date;
	private int qna_cnt;
	
	public int getQna_id() {
		return qna_id;
	}
	public void setQna_id(int qna_id) {
		this.qna_id = qna_id;
	}
	public String getQna_title() {
		return qna_title;
	}
	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getQna_date() {
		return qna_date;
	}
	public void setQna_date(String qna_date) {
		this.qna_date = qna_date;
	}
	public int getQna_cnt() {
		return qna_cnt;
	}
	public void setQna_cnt(int qna_cnt) {
		this.qna_cnt = qna_cnt;
	}
	
	
}
