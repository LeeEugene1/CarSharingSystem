package kr.or.ddit.css.vo;

import java.io.Serializable;

public class PaymentVO implements Serializable{
	private int payment_id;			//결제내역 아이디(PK)
	private String payment_date;	//결제 날짜
	private int payment_cost;		//결제 비용
	private int rent_id;			//렌트 아이디(FK)
	private String mem_id;			//회원 아이디(FK)
	private String card_num;		//카드 번호(FK)
	
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	public String getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}
	public int getPayment_cost() {
		return payment_cost;
	}
	public void setPayment_cost(int payment_cost) {
		this.payment_cost = payment_cost;
	}
	public int getRent_id() {
		return rent_id;
	}
	public void setRent_id(int rent_id) {
		this.rent_id = rent_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getCard_num() {
		return card_num;
	}
	public void setCard_num(String card_num) {
		this.card_num = card_num;
	}
	
	
}
