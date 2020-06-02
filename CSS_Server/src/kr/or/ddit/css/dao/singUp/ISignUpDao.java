package kr.or.ddit.css.dao.singUp;

import java.rmi.RemoteException;
import java.util.HashMap;

import kr.or.ddit.css.vo.MemberVO;

public interface ISignUpDao {
	
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param memVo DB에 insert할 회원 정보가 저장된 MemberVO객체
	 * @return 작업성공이면 1, 작업 실패면 0을 반환
	 * @author 김찬기
	 */
	public int insertMember(MemberVO memVo);
	
	/**
	 * 회원ID를 매개변수로 받아서 ID중복여부를 확인하는 메서드
	 * @param memId 
	 * @return ID중복이 아니면 false, 중복이면 true을 반환
	 * @author 김찬기
	 */
	public String checkId(String memId);

//	/**
//	 * 회원ID를 매개변수로 받아서 그 회원 정보를 삭제하는 메서드
//	 * @param memId 삭제할 회원ID
//	 * @return 작업성공 : 1, 작업실패 : 0
//	 * @author 박인성
//	 */
//	public int deleteMember(String memId);
	
	/**
	 * 
	 * 회원pw를 매개변수로 받아서 PW중복여부를 확인하는 메서드
	 * @param memPw 
	 * @return PW중복이 아니면 false, 중복이면 true을 반환
	 */
	public String checkPw(HashMap<String, String> map);
	
	/**
	 * 
	 * 회원active를 매개변수로 받아서 id를 비활성화하는 메서드
	 * @param memActive
	 * @return memActive가 활성화면 true, 비활성화면 false를 반환
	 */
	public int checkActive(String memActive);
	
	/**
	 * 
	 * MemberVO에 담겨진 자료를 DB에 update하는 메서드
	 * @param memVo
	 * @return 작업성공이면 1, 작업 실패면 0을 반환
	 */
	public int updateMember(MemberVO memVo);
	
	public int blacklistMemberUpdate(String memId);

}
