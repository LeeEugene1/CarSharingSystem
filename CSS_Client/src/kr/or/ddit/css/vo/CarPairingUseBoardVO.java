package kr.or.ddit.css.vo;

import java.io.Serializable;

public class CarPairingUseBoardVO implements Serializable {
	private int pairinguseboard_id;			//카페어링이용게시판 아이디(PK)
	private String pairinguseboard_title;	//카페어링이용게시판 제목
	private String pairinguseboard_date;	//카페어링이용게시판 날짜
	private String pairinguseboard_content;	//카페어링이용게시판 내용
	private String mem_id;					//회원 아이디(FK)
	private String admin_id;				//관리자 아이디(FK)
	private String pairinguseboard_carnum;
	private int pairinguseboard_rentcost;
	private int pairinguseboard_drivecost;
	private String pairinguseboard_img1;
	private String pairinguseboard_img2;
	private String pairinguseboard_img3;
	private int pairinguseboard_cnt;
	
	public int getPairinguseboard_id() {
		return pairinguseboard_id;
	}
	public void setPairinguseboard_id(int pairinguseboard_id) {
		this.pairinguseboard_id = pairinguseboard_id;
	}
	public String getPairinguseboard_title() {
		return pairinguseboard_title;
	}
	public void setPairinguseboard_title(String pairinguseboard_title) {
		this.pairinguseboard_title = pairinguseboard_title;
	}
	public String getPairinguseboard_date() {
		return pairinguseboard_date;
	}
	public void setPairinguseboard_date(String pairinguseboard_date) {
		this.pairinguseboard_date = pairinguseboard_date;
	}
	public String getPairinguseboard_content() {
		return pairinguseboard_content;
	}
	public void setPairinguseboard_content(String pairinguseboard_content) {
		this.pairinguseboard_content = pairinguseboard_content;
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
//	
	public String getPairinguseboard_carnum() {
		return pairinguseboard_carnum;
	}
	public void setPairinguseboard_carnum(String pairinguseboard_carnum) {
		this.pairinguseboard_carnum = pairinguseboard_carnum;
	}
	public int getPairinguseboard_rentcost() {
		return pairinguseboard_rentcost;
	}
	public void setPairinguseboard_rentcost(int pairinguseboard_rentcost) {
		this.pairinguseboard_rentcost = pairinguseboard_rentcost;
	}
	public int getPairinguseboard_drivecost() {
		return pairinguseboard_drivecost;
	}
	public void setPairinguseboard_drivecost(int pairinguseboard_drivecost) {
		this.pairinguseboard_drivecost = pairinguseboard_drivecost;
	}
	public String getPairinguseboard_img1() {
		return pairinguseboard_img1;
	}
	public void setPairinguseboard_img1(String pairinguseboard_img1) {
		this.pairinguseboard_img1 = pairinguseboard_img1;
	}
	public String getPairinguseboard_img2() {
		return pairinguseboard_img2;
	}
	public void setPairinguseboard_img2(String pairinguseboard_img2) {
		this.pairinguseboard_img2 = pairinguseboard_img2;
	}
	public String getPairinguseboard_img3() {
		return pairinguseboard_img3;
	}
	public void setPairinguseboard_img3(String pairinguseboard_img3) {
		this.pairinguseboard_img3 = pairinguseboard_img3;
	}
	public int getPairinguseboard_cnt() {
		return pairinguseboard_cnt;
	}
	public void setPairinguseboard_cnt(int pairinguseboard_cnt) {
		this.pairinguseboard_cnt = pairinguseboard_cnt;
	}
}
