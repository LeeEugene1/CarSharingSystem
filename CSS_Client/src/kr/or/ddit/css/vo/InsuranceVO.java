package kr.or.ddit.css.vo;

import java.io.Serializable;

public class InsuranceVO implements Serializable {
	private int ins_id;			//보험 아이디(PK)
	private String ins_type;	//보험 타입
	private int ins_cost;		//보험 비용
	private String admin_id;	//관리자 아이디(FK)	
	
	public int getIns_id() {
		return ins_id;
	}
	public void setIns_id(int ins_id) {
		this.ins_id = ins_id;
	}
	public String getIns_type() {
		return ins_type;
	}
	public void setIns_type(String ins_type) {
		this.ins_type = ins_type;
	}
	public int getIns_cost() {
		return ins_cost;
	}
	public void setIns_cost(int ins_cost) {
		this.ins_cost = ins_cost;
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	
	
}
