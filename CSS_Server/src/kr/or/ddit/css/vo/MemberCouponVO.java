package kr.or.ddit.css.vo;

import java.io.Serializable;

public class MemberCouponVO implements Serializable{
	private int coupon_num;			//쿠폰 고유번호(PK)
	private boolean coupon_use;		//쿠폰 사용여부
	private String mem_id;			//회원 아이디(FK)
	private int couponmaster_id;	//쿠폰북 아이디(FK)
	
	public int getCoupon_num() {
		return coupon_num;
	}
	public void setCoupon_num(int coupon_num) {
		this.coupon_num = coupon_num;
	}
	public boolean isCoupon_use() {
		return coupon_use;
	}
	public void setCoupon_use(boolean coupon_use) {
		this.coupon_use = coupon_use;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getCouponmaster_id() {
		return couponmaster_id;
	}
	public void setCouponmaster_id(int couponmaster_id) {
		this.couponmaster_id = couponmaster_id;
	}
	
}
