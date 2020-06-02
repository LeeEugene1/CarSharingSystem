package kr.or.ddit.css.vo;

import java.io.Serializable;

public class CarTypeVO implements Serializable {
	private int cartype_id;			//차종 아이디(PK)
	private String cartype_name;	//차종 이름
	
	public int getCartype_id() {
		return cartype_id;
	}
	public void setCartype_id(int cartype_id) {
		this.cartype_id = cartype_id;
	}
	public String getCartype_name() {
		return cartype_name;
	}
	public void setCartype_name(String cartype_name) {
		this.cartype_name = cartype_name;
	}
	
}
