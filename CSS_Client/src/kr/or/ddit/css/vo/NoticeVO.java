package kr.or.ddit.css.vo;

import java.io.Serializable;

public class NoticeVO implements Serializable {
	private int notice_id;			//공지사항 아이디(PK)
	private String notice_title;	//공지사항 제목
	private String notice_date;		//공지사항 날짜
	private String notice_content;	//공지사항 내용
	private String admin_id;		//관리자 아이디(FK)
	private int notice_cnt;			//공지사항 조회수
	
	public int getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(String notice_date) {
		this.notice_date = notice_date;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public int getNotice_cnt() {
		return notice_cnt;
	}
	public void setNotice_cnt(int notice_cnt) {
		this.notice_cnt = notice_cnt;
	}
	
	
}
