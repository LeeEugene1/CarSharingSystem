package kr.or.ddit.css.vo;

import java.io.Serializable;

public class RentVO implements Serializable {
	private int rent_id;			//렌트 아이디(PK)
	private String rent_start;		//렌트 시작시간
	private String rent_end;		//렌트 종료시간
	private int rent_cost; 			//렌트 비용
	private String rent_status;		//렌트 결제상태	
	private String rent_addr;		//렌트 주소
	private String mem_id;			//회원 아이디(FK)
	private String car_num;			//차량 번호(FK)
	private int ins_id;				//보험 아이디(FK)
	
	public int getRent_id() {
		return rent_id;
	}
	public void setRent_id(int rent_id) {
		this.rent_id = rent_id;
	}
	public String getRent_start() {
		return rent_start;
	}
	public void setRent_start(String rent_start) {
		this.rent_start = rent_start;
	}
	public String getRent_end() {
		return rent_end;
	}
	public void setRent_end(String rent_end) {
		this.rent_end = rent_end;
	}
	public int getRent_cost() {
		return rent_cost;
	}
	public void setRent_cost(int rent_cost) {
		this.rent_cost = rent_cost;
	}
	public String getRent_status() {
		return rent_status;
	}
	public void setRent_status(String rent_status) {
		this.rent_status = rent_status;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getCar_num() {
		return car_num;
	}
	public void setCar_num(String car_num) {
		this.car_num = car_num;
	}
	public String getRent_addr() {
		return rent_addr;
	}
	public void setRent_addr(String rent_addr) {
		this.rent_addr = rent_addr;
	}
	public int getIns_id() {
		return ins_id;
	}
	public void setIns_id(int ins_id) {
		this.ins_id = ins_id;
	}
	
	
}
