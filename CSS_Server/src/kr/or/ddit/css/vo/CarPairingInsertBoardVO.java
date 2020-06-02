package kr.or.ddit.css.vo;

import java.io.Serializable;

public class CarPairingInsertBoardVO implements Serializable{
	private int pairinginsertboard_id;			//카페어링등록게시판 아이디(PK)
	private String pairinginsertboard_title;	//카페어링등록게시판 제목
	private String pairinginsertboard_date;		//카페어링등록게시판 날짜
	private String pairinginsertboard_content;	//카페어링등록게시판 내용
	private String mem_id;						//회원 아이디(FK)
	private int pairinginsertboard_cnt;			//카페어링등록게시판 조회수
	private String pairinginsertboard_carnum;
	private int pairinginsertboard_rentcost;
	private int pairinginsertboard_drivecost;
	private String pairinginsertboard_img1;
	private String pairinginsertboard_img2;
	private String pairinginsertboard_img3;
//	private int pairinginsertboard_index;

	
	public int getPairinginsertboard_id() {
		return pairinginsertboard_id;
	}
	public void setPairinginsertboard_id(int pairinginsertboard_id) {
		this.pairinginsertboard_id = pairinginsertboard_id;
	}
	public String getPairinginsertboard_title() {
		return pairinginsertboard_title;
	}
	public void setPairinginsertboard_title(String pairinginsertboard_title) {
		this.pairinginsertboard_title = pairinginsertboard_title;
	}
	public String getPairinginsertboard_date() {
		return pairinginsertboard_date;
	}
	public void setPairinginsertboard_date(String pairinginsertboard_date) {
		this.pairinginsertboard_date = pairinginsertboard_date;
	}
	public String getPairinginsertboard_content() {
		return pairinginsertboard_content;
	}
	public void setPairinginsertboard_content(String pairinginsertboard_content) {
		this.pairinginsertboard_content = pairinginsertboard_content;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getPairinginsertboard_cnt() {
		return pairinginsertboard_cnt;
	}
	public void setPairinginsertboard_cnt(int pairinginsertboard_cnt) {
		this.pairinginsertboard_cnt = pairinginsertboard_cnt;
	}
	public String getPairinginsertboard_carnum() {
		return pairinginsertboard_carnum;
	}
	public void setPairinginsertboard_carnum(String pairinginsertboard_carnum) {
		this.pairinginsertboard_carnum = pairinginsertboard_carnum;
	}
	public int getPairinginsertboard_rentcost() {
		return pairinginsertboard_rentcost;
	}
	public void setPairinginsertboard_rentcost(int pairinginsertboard_rentcost) {
		this.pairinginsertboard_rentcost = pairinginsertboard_rentcost;
	}
	public int getPairinginsertboard_drivecost() {
		return pairinginsertboard_drivecost;
	}
	public void setPairinginsertboard_drivecost(int pairinginsertboard_drivecost) {
		this.pairinginsertboard_drivecost = pairinginsertboard_drivecost;
	}
	public String getPairinginsertboard_img1() {
		return pairinginsertboard_img1;
	}
	public void setPairinginsertboard_img1(String pairinginsertboard_img1) {
		this.pairinginsertboard_img1 = pairinginsertboard_img1;
	}
	public String getPairinginsertboard_img2() {
		return pairinginsertboard_img2;
	}
	public void setPairinginsertboard_img2(String pairinginsertboard_img2) {
		this.pairinginsertboard_img2 = pairinginsertboard_img2;
	}
	public String getPairinginsertboard_img3() {
		return pairinginsertboard_img3;
	}
	public void setPairinginsertboard_img3(String pairinginsertboard_img3) {
		this.pairinginsertboard_img3 = pairinginsertboard_img3;
	}
//	public int getPairinginsertboard_index() {
//		return pairinginsertboard_index;
//	}
//	public void setPairinginsertboard_index(int pairinginsertboard_index) {
//		this.pairinginsertboard_index = pairinginsertboard_index;
//	}
	
	
}
