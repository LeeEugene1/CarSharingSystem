package kr.or.ddit.css.vo;

import java.io.Serializable;

import javafx.scene.image.ImageView;

public class CarVO implements Serializable{
	private String car_num;		//차량 번호(PK)
	private String car_name;	//차량 이름
	private String car_oiltype;	//차량 연료타입
	private int car_rentcost;	//차량 렌트비용
	private int car_drivecost;	//차량 운행비용
	private String car_option;	//차량 옵션
	private String car_active;	//차량 활성화여부
	private int cartype_id;		//차종 아이디(FK)
	private String car_img;		//차량 이미지	
//	private ImageView car_img; 
	private String car_img1;
	private String car_img2;
	private String car_img3;

	
	public String getCar_num() {
		return car_num;
	}
	public void setCar_num(String car_num) {
		this.car_num = car_num;
	}
	public String getCar_name() {
		return car_name;
	}
	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}
	public String getCar_oiltype() {
		return car_oiltype;
	}
	public void setCar_oiltype(String car_oiltype) {
		this.car_oiltype = car_oiltype;
	}
	public int getCar_rentcost() {
		return car_rentcost;
	}
	public void setCar_rentcost(int car_rentcost) {
		this.car_rentcost = car_rentcost;
	}
	public int getCar_drivecost() {
		return car_drivecost;
	}
	public void setCar_drivecost(int car_drivecost) {
		this.car_drivecost = car_drivecost;
	}
	public String getCar_option() {
		return car_option;
	}
	public void setCar_option(String car_option) {
		this.car_option = car_option;
	}
	public String isCar_active() {
		return car_active;
	}
	public void setCar_active(String car_active) {
		this.car_active = car_active;
	}
	public int getCartype_id() {
		return cartype_id;
	}
	public void setCartype_id(int cartype_id) {
		this.cartype_id = cartype_id;
	}
	public String getCar_img() {
		return car_img;
	}
	public void setCar_img(String car_img) {
		this.car_img = car_img;
	}
	public String getCar_img1() {
		return car_img1;
	}
	public void setCar_img1(String car_img1) {
		this.car_img1 = car_img1;
	}
	public String getCar_img2() {
		return car_img2;
	}
	public void setCar_img2(String car_img2) {
		this.car_img2 = car_img2;
	}
	public String getCar_img3() {
		return car_img3;
	}
	public void setCar_img3(String car_img3) {
		this.car_img3 = car_img3;
	}
	
	
	
	
}
