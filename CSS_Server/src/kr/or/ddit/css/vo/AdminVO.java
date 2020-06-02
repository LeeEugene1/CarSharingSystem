package kr.or.ddit.css.vo;

import java.io.Serializable;

public class AdminVO implements Serializable{
	private String admin_id;	//관리자 아이디(PK)
	private String admin_pw;	//관리자 비밀번호
	private String admin_name;	//관리자 이름
	private String admin_tel;	//관리자 전화번호
	private String admin_email;	//관리자 이메일
	
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_pw() {
		return admin_pw;
	}
	public void setAdmin_pw(String admin_pw) {
		this.admin_pw = admin_pw;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_tel() {
		return admin_tel;
	}
	public void setAdmin_tel(String admin_tel) {
		this.admin_tel = admin_tel;
	}
	public String getAdmin_email() {
		return admin_email;
	}
	public void setAdmin_email(String admin_email) {
		this.admin_email = admin_email;
	}
	
	
}
