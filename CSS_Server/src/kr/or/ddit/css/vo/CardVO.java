package kr.or.ddit.css.vo;

import java.io.Serializable;

public class CardVO implements Serializable{
	private String card_num;	//카드 번호(PK)
	private String card_pw;		//카드 비밀번호
	private String card_month;	//만료 월
	private String card_year;	//만료 년
	private String mem_id;		//회원 아이디(FK)
	
	public String getCard_num() {
		return card_num;
	}
	public void setCard_num(String card_num) {
		this.card_num = card_num;
	}
	public String getCard_pw() {
		return card_pw;
	}
	public void setCard_pw(String card_pw) {
		this.card_pw = card_pw;
	}
	public String getCard_month() {
		return card_month;
	}
	public void setCard_month(String card_month) {
		this.card_month = card_month;
	}
	public String getCard_year() {
		return card_year;
	}
	public void setCard_year(String card_year) {
		this.card_year = card_year;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
}
