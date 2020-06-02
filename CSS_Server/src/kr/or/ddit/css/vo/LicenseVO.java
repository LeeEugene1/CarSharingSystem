package kr.or.ddit.css.vo;

import java.io.Serializable;

public class LicenseVO implements Serializable{
	private int license_id;			//면허 아이디(PK)
	private String license_type;	//면허 종류
	private String license_num;		//면허 번호
	private String license_start;	//면허 시작일
	private String license_end;		//면허 종료일
	private String mem_id;			//회원 아이디(FK)
	
	public int getLicense_id() {
		return license_id;
	}
	public void setLicense_id(int license_id) {
		this.license_id = license_id;
	}
	public String getLicense_type() {
		return license_type;
	}
	public void setLicense_type(String license_type) {
		this.license_type = license_type;
	}
	public String getLicense_num() {
		return license_num;
	}
	public void setLicense_num(String license_num) {
		this.license_num = license_num;
	}
	public String getLicense_start() {
		return license_start;
	}
	public void setLicense_start(String license_start) {
		this.license_start = license_start;
	}
	public String getLicense_end() {
		return license_end;
	}
	public void setLicense_end(String license_end) {
		this.license_end = license_end;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
	
}
