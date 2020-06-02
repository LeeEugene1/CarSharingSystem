package kr.or.ddit.css.vo;

import java.io.Serializable;

public class BestCarVO implements Serializable{
	private int bestCar_id;				//차량추천 아이디(PK)
	private int bestcar_peopleqty;		//차량추천 인원
	private String bestcar_purpose;		//차량추천 목적
	private String bestcar_distance;	//차량추천 거리
	private int bestcar_cost;			//차량추천 금액
	private String bestcar_option;		//차량추천 옵션
	private String mem_id;				//회원 아이디(FK)
	
	public int getBestCar_id() {
		return bestCar_id;
	}
	public void setBestCar_id(int bestCar_id) {
		this.bestCar_id = bestCar_id;
	}
	public int getBestcar_peopleqty() {
		return bestcar_peopleqty;
	}
	public void setBestcar_peopleqty(int bestcar_peopleqty) {
		this.bestcar_peopleqty = bestcar_peopleqty;
	}
	public String getBestcar_purpose() {
		return bestcar_purpose;
	}
	public void setBestcar_purpose(String bestcar_purpose) {
		this.bestcar_purpose = bestcar_purpose;
	}
	public String getBestcar_distance() {
		return bestcar_distance;
	}
	public void setBestcar_distance(String bestcar_distance) {
		this.bestcar_distance = bestcar_distance;
	}
	public int getBestcar_cost() {
		return bestcar_cost;
	}
	public void setBestcar_cost(int bestcar_cost) {
		this.bestcar_cost = bestcar_cost;
	}
	public String getBestcar_option() {
		return bestcar_option;
	}
	public void setBestcar_option(String bestcar_option) {
		this.bestcar_option = bestcar_option;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
	
}
