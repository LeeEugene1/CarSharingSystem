package kr.or.ddit.css.vo;

import java.io.Serializable;

public class CouponBookVO implements Serializable {
	private int couponmaster_id;		//쿠폰 아이디(PK)
	private String couponmaster_name;	//쿠폰 이름
	private String couponmaster_start;	//쿠폰 시작일
	private String couponmaster_end;	//쿠폰 종료일
	private String option_header;	 	//쿠폰 사용조건
	private String option_detail;	 	//쿠폰 설명
	private String coupon_image;	 	//쿠폰 이미지
	private String dc_option;	 		//할인 방식
	private int dc_cost;				//할인 금액
	private int coupon_count;			//쿠폰 수량
	
	public int getCouponmaster_id() {
		return couponmaster_id;
	}
	public void setCouponmaster_id(int couponmaster_id) {
		this.couponmaster_id = couponmaster_id;
	}
	public String getCouponmaster_name() {
		return couponmaster_name;
	}
	public void setCouponmaster_name(String couponmaster_name) {
		this.couponmaster_name = couponmaster_name;
	}
	public String getCouponmaster_start() {
		return couponmaster_start;
	}
	public void setCouponmaster_start(String couponmaster_start) {
		this.couponmaster_start = couponmaster_start;
	}
	public String getCouponmaster_end() {
		return couponmaster_end;
	}
	public void setCouponmaster_end(String couponmaster_end) {
		this.couponmaster_end = couponmaster_end;
	}
	public String getOption_header() {
		return option_header;
	}
	public void setOption_header(String option_header) {
		this.option_header = option_header;
	}
	public String getOption_detail() {
		return option_detail;
	}
	public void setOption_detail(String option_detail) {
		this.option_detail = option_detail;
	}
	public String getCoupon_image() {
		return coupon_image;
	}
	public void setCoupon_image(String coupon_image) {
		this.coupon_image = coupon_image;
	}
	public String getDc_option() {
		return dc_option;
	}
	public void setDc_option(String dc_option) {
		this.dc_option = dc_option;
	}
	public int getDc_cost() {
		return dc_cost;
	}
	public void setDc_cost(int dc_cost) {
		this.dc_cost = dc_cost;
	}
	public int getCoupon_count() {
		return coupon_count;
	}
	public void setCoupon_count(int coupon_count) {
		this.coupon_count = coupon_count;
	}
	
}
