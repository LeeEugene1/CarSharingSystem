package kr.or.ddit.css.vo;

import java.io.Serializable;

public class CommunityBoardVO implements Serializable{
	private int board_id;			//커뮤니티게시판 아이디(PK)
	private String board_title;		//커뮤니티게시판 제목
	private String board_date;		//커뮤니티게시판 날짜
	private String board_content;	//커뮤니티게시판 내용
	private int board_cnt;
	private String mem_id;			//회원 아이디(FK)
	private String admin_id;		//관리자 아이디(FK)
	private String board_img;
	
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_date() {
		return board_date;
	}
	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	public String getBoard_content() {
		return board_content;
	}
	public int getBoard_cnt() {
		return board_cnt;
	}
	public void setBoard_cnt(int board_cnt) {
		this.board_cnt = board_cnt;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
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
	public String getBoard_img() {
		return board_img;
	}
	public void setBoard_img(String board_img) {
		this.board_img = board_img;
	}
	
	
}
