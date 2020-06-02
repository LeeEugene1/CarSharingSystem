package kr.or.ddit.css.view.signUp;

import java.util.regex.Pattern;

public class RegEx {
	
	public static boolean checkMem_bir(String mem_bir) {
		String patternMBir = "[1-2]{1}[0-9]{3}-[0-1]{1}[1-9]{1}-[0-3]{1}[0-9]{1}";
		return Pattern.matches(patternMBir, mem_bir);
	}   
	   
	public static boolean checkMem_id(String mem_id) {
		String patternMId = "^[A-za-z0-9]{4,15}$";	//영문대소문자,숫자 4-15글자
		return Pattern.matches(patternMId, mem_id);
	}
	
	public static boolean checkMem_email(String mem_email) {
		String patternMEmail = "^[A-Za-z0-9+_.-]+@(.+)$";
		return Pattern.matches(patternMEmail, mem_email);
	}

	public static boolean checkMem_pw(String mem_pw) {
		String patternMPw = "\\S{4,8}$";			
		return Pattern.matches(patternMPw, mem_pw);
	}
	
	public static boolean checkCard_pw(String card_pw) {
		String patternCPw = "\\S{4}$";			
		return Pattern.matches(patternCPw, card_pw);
	}

	public static boolean checkMem_name(String mem_name){
		String patternMName = "[가-힣]{2,4}";
		return Pattern.matches(patternMName, mem_name);
	}
	
	public static boolean checkMem_num1(String mem_number){
		String patternMNum = "0[0-1]{2}";
		return Pattern.matches(patternMNum, mem_number);
	}
	public static boolean checkMem_num2(String mem_number2){
		String patternMNum2 = "[0-9]{3,4}";
		return Pattern.matches(patternMNum2, mem_number2);
	}
	public static boolean checkMem_num3(String mem_number3){
		String patternMNum3 = "[0-9]{3,4}";
		return Pattern.matches(patternMNum3, mem_number3);
	}
   
}
