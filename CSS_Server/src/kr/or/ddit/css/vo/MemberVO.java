package kr.or.ddit.css.vo;

import java.io.Serializable;

// ctrl+shift+y

public class MemberVO implements Serializable{
	private String mem_id;				//회원 아이디(PK)
	private String mem_name;			//회원 이름
	private String mem_pw;				//회원 비밀번호
	private String mem_tel;				//회원 전화번호
	private String mem_addr;			//회원 주소
	private String mem_gender;			//회원 성별
	private String mem_birth;			//회원 생년월일
	private String mem_email;			//회원 이메일
	private String mem_class;			//회원 등급
	private String mem_blacklist;	 	//회원 블랙리스트
	private int blacklist_point;	 	//블랙리스트 벌점
	private String mem_active;			//회원 활성화 여부
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_pw() {
		return mem_pw;
	}
	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}
	public String getMem_tel() {
		return mem_tel;
	}
	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}
	public String getMem_addr() {
		return mem_addr;
	}
	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}
	public String getMem_gender() {
		return mem_gender;
	}
	public void setMem_gender(String mem_gender) {
		this.mem_gender = mem_gender;
	}
	public String getMem_birth() {
		return mem_birth;
	}
	public void setMem_birth(String mem_birth) {
		this.mem_birth = mem_birth;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_class() {
		return mem_class;
	}
	public void setMem_class(String mem_class) {
		this.mem_class = mem_class;
	}
	public String getMem_blacklist() {
		return mem_blacklist;
	}
	public void setMem_blacklist(String mem_blacklist) {
		this.mem_blacklist = mem_blacklist;
	}
	public int getBlacklist_point() {
		return blacklist_point;
	}
	public void setBlacklist_point(int blacklist_point) {
		this.blacklist_point = blacklist_point;
	}
	public String getMem_active() {
		return mem_active;
	}
	public void setMem_active(String mem_active) {
		this.mem_active = mem_active;
	}
	
}
