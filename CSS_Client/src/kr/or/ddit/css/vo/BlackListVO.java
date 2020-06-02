package kr.or.ddit.css.vo;

import java.io.Serializable;

public class BlackListVO implements Serializable {
	private int blacklist_id;			//블랙리스트 아이디(PK)
	private String blacklist_title; 	//블랙리스트 제목
	private String blacklist_date; 		//블랙리스트 날짜
	private String blacklist_content;	//블랙리스트 내용
	private String mem_id;				//회원 아이디(FK)
	private String admin_id;			//관리자 아이디(FK)
	private int blacklist_cnt;			//블랙리스트 조회수
	private String blacklist_img;	//블랙리스트 이미지
	private String blacklist_claimid;	//블랙리스트 신고받은 아이디
	
	public int getBlacklist_id() {
		return blacklist_id;
	}
	public void setBlacklist_id(int blacklist_id) {
		this.blacklist_id = blacklist_id;
	}
	public String getBlacklist_title() {
		return blacklist_title;
	}
	public void setBlacklist_title(String blacklist_title) {
		this.blacklist_title = blacklist_title;
	}
	public String getBlacklist_date() {
		return blacklist_date;
	}
	public void setBlacklist_date(String blacklist_date) {
		this.blacklist_date = blacklist_date;
	}
	public String getBlacklist_content() {
		return blacklist_content;
	}
	public void setBlacklist_content(String blacklist_content) {
		this.blacklist_content = blacklist_content;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public int getBlacklist_cnt() {
		return blacklist_cnt;
	}
	public void setBlacklist_cnt(int blacklist_cnt) {
		this.blacklist_cnt = blacklist_cnt;
	}
	public String getBlacklist_img() {
		return blacklist_img;
	}
	public void setBlacklist_img(String blacklist_img) {
		this.blacklist_img = blacklist_img;
	}
	public String getBlacklist_claimid() {
		return blacklist_claimid;
	}
	public void setBlacklist_claimid(String blacklist_claimid) {
		this.blacklist_claimid = blacklist_claimid;
	}
	
	
}
