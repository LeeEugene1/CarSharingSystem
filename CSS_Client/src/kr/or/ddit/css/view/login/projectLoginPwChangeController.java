package kr.or.ddit.css.view.login;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.css.service.login.ILoginService;
import kr.or.ddit.css.view.signUp.AES256Util;
import kr.or.ddit.css.vo.MemberVO;

public class projectLoginPwChangeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane pwChangePane;

    @FXML
    private TextField pwArea;

    @FXML
    private TextField pwCheckArea;

    private ILoginService loginService;
    
    @FXML
    void changeClicked(ActionEvent event) {
    	try {
			AES256Util aes = new AES256Util();
			
			String memPw = pwArea.getText();
	    	String encPass = aes.encrypt(memPw);
	    	if(memPw.isEmpty()) {
	    		alert("입력오류","회원 비밀번호를 입력하세요.");
	    		pwArea.requestFocus();
	    		return;
	    	}
	    	
	    	String memPwCheck = pwCheckArea.getText();
	    	if(memPwCheck.isEmpty()) {
	    		alert("입력오류","회원 비밀번호를 입력하세요.");
	    		pwCheckArea.requestFocus();
	    		return;
	    	}else if(!memPw.equals(memPwCheck)) {
	    		alert("입력오류","회원 비밀번호가 동일하지 않습니다.");
	    		pwCheckArea.requestFocus();
	    		return;
	    	}
	    	
	    	projectLoginPwSearchController plpsc = null;
	    	
	    	String mem_id = plpsc.getParam().get("memId");
	    	String mem_name = plpsc.getParam().get("memName");
	    	String mem_email = plpsc.getParam().get("memEmail");
	    	
	    	// 임시 비밀번호로 변경
	    	MemberVO memVo = new MemberVO();
	    	memVo.setMem_id(mem_id);
	    	memVo.setMem_name(mem_name);
	    	memVo.setMem_email(mem_email);
	    	memVo.setMem_pw(encPass);
	    	
	    	int cnt = 0;
	    	
			cnt = loginService.pwUpdateMember(memVo);
			if(cnt>0) {
				infoMsg("확인", "비밀번호 수정이 완료되었습니다.");
				try {
					AnchorPane pane = FXMLLoader.load(getClass().getResource("projectLogin.fxml"));
					pwChangePane.getScene().setRoot(pane);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				alert("입력오류","비밀번호 수정에 실패하였습니다.");
			}
	    	
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
    }

    @FXML
    void initialize() {
    	Registry reg;
		try {
			reg = LocateRegistry.getRegistry("localhost",8899);
			loginService = (ILoginService) reg.lookup("login");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    }
    
    //----------------------------------------------------------alert, infoMsg, confirm 메서드 설정 ---------------------------
    public void alert(String head, String msg) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("경고");
		alert.setHeaderText(head);
		alert.setContentText(msg);
		
		alert.showAndWait();
	}
	
	public void infoMsg(String head, String msg) {
		Alert info = new Alert(AlertType.INFORMATION);
		info.setTitle("정보");
		info.setHeaderText(head);
		info.setContentText(msg);
		
		info.showAndWait();
	}
    
    public ButtonType confirm(String head, String msg) {
    	Alert confirm = new Alert(AlertType.CONFIRMATION);
    	confirm.setTitle("확인");
    	confirm.setHeaderText(head);
    	confirm.setContentText(msg);
    	
    	return confirm.showAndWait().get();
    }
    //--------------------------------------------------------------------------------------------------------------------
}
