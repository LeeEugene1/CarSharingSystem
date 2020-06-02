package kr.or.ddit.css.view.login;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.css.service.login.ILoginService;
import kr.or.ddit.css.session.AdminLoginSession;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.view.signUp.projectSignUpController;
import kr.or.ddit.css.view.signUp.projectSignUpMain;
import kr.or.ddit.css.vo.AdminVO;
import kr.or.ddit.css.vo.MemberVO;
import javafx.scene.control.TextField;

public class projectLoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private TextField idArea;

    @FXML
    private PasswordField  pwArea;
    
    @FXML
    private MediaView movie;
    
    @FXML
    void SignIdSearch(MouseEvent event) throws IOException {	// 아이디 찾기 메서드
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("projectLoginIdSearch.fxml"));
    	rootPane.getScene().setRoot(pane);
    }

    @FXML
    void SignPwSearch(MouseEvent event) throws IOException {	// 비밀번호 찾기 메서드
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("projectLoginPwSearch.fxml"));
    	rootPane.getScene().setRoot(pane);
    }
    
    @FXML
    void loadMain(ActionEvent event) throws Exception {	// 로그인
    	// 아이디
    	Boolean idCheck = false;	// 회원 아이디를 담을 그릇
    	Boolean adminIdCheck = false;	// 관리자 아이디를 담을 그릇
    	
    	try {
    		idCheck = loginService.idCheck(idArea.getText());
    		adminIdCheck = loginService.adminIdCheck(idArea.getText());
			// DB에 id가 없을경우 -> false
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	
    	if (idCheck == false && adminIdCheck == false) {
    		alert("입력오류","아이디가 존재하지 않습니다.");
			return;
		}
    	
    	// 비밀번호
    	String Pw = ""; // 암호화된 pw
    	Pw = pwArea.getText();
    	
    	// 활성화 여부
    	String active = "Y";
    	
    	AES256Util aes256 = new AES256Util();
    	
    	String encryptedPw = ""; // 암호화된 pw
		encryptedPw = aes256.encrypt(Pw);

//		String decryptedPw = ""; // 복호화시킨 pw
//		decryptedPw = aes256.decrypt(encryptedPw);
    	
    	try {
    		if(idCheck == true) {
    			list = loginService.login(idArea.getText());
    		}else if(adminIdCheck == true) {
    			adminList = loginService.adminLogin(idArea.getText());
    		}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
    	
    	if(idCheck == true) {
    		String black = list.get(0).getMem_blacklist();
    		if(black.equals("Y")) {
    			alert("입력오류", "블랙리스트 회원은 로그인이 불가능합니다.");
    		}else {
    			if(encryptedPw.equals(list.get(0).getMem_pw()) && active.equals(list.get(0).getMem_active())) {
        			vo.setMem_id(idArea.getText());
            		LoginSession.session = list.get(0);
            		AdminLoginSession.adminSession = null;
            		
            		System.out.println("list -> getMem_active:"+list.get(0).getMem_active());
            		System.out.println("session -> getMem_active:"+LoginSession.session.getMem_active());
            		
        			//메뉴화면 열리게하는 소스
                	AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu/projectMenu.fxml"));
                	rootPane.getScene().setRoot(pane);
                	
                	System.out.println("가져온 회원 id : "+LoginSession.session.getMem_id());
            	}else if(encryptedPw.equals("")){
            		alert("입력오류","비밀번호가 존재하지 않습니다.");
        			return;
            	}else if(list.get(0).getMem_active().equals("N")){
            		alert("입력오류","탈퇴한 회원의 아이디입니다.");
            	}else {
            		alert("입력오류","비밀번호가 옳지 않습니다.");
        			return;
            	}
    		}
    	}else if(adminIdCheck == true) {
    		if(encryptedPw.equals(adminList.get(0).getAdmin_pw())) {
        		adminVo.setAdmin_id(idArea.getText());
        		AdminLoginSession.adminSession = adminList.get(0);
        		LoginSession.session = null;
        		
        		//메뉴화면 열리게하는 소스
            	AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu/projectMenu.fxml"));
            	rootPane.getScene().setRoot(pane);
            	
            	System.out.println("가져온 관리자 id : "+AdminLoginSession.adminSession.getAdmin_id());
            	
            	//관리자로 로그인할때 채팅창 오픈
            	try {
        			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../chat/ClientMain.fxml"));
        			Parent root1 = (Parent) fxmlLoader.load();
        			Stage stage = new Stage();
        			stage.initStyle(StageStyle.TRANSPARENT.DECORATED);
        			stage.setTitle("Message Window");
        			stage.setScene(new Scene(root1));
        			stage.setResizable(false); 
        			stage.show();
        			stage.setIconified(true);

        		} catch (Exception e) {
        			System.out.println("cant load new window");
        		}
            	
            	
        	}else if(encryptedPw.equals("")){
        		alert("입력오류","비밀번호가 존재하지 않습니다.");
    			return;
        	}else {
        		alert("입력오류","비밀번호가 옳지 않습니다.");
    			return;
        	}
    	}

    	
    }
    
    @FXML
    void loadSignUp(MouseEvent event) throws IOException {	// 회원가입 메서드
    	
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../signUp/projectSignUp.fxml"));
    	rootPane.getScene().setRoot(pane);
    	
    }
    
    private ILoginService loginService;
    // 회원 로그인
    private List<MemberVO> list = new ArrayList<>();
    private MemberVO vo = new MemberVO();
    // 관리자 로그인
    private List<AdminVO> adminList = new ArrayList<>();
    private AdminVO adminVo = new AdminVO();
    
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
		String VUrl = "file:/D:/A_TeachingMaterial/4.MiddleProject/workspace/CSS_Client/src/img/loginBG.mp4";
		
		Media media = new Media(VUrl);
		MediaPlayer mediaplayer = new MediaPlayer(media);
		DoubleProperty width = movie.fitWidthProperty();
		DoubleProperty height = movie.fitHeightProperty();
		width.bind(Bindings.selectDouble(movie.sceneProperty(), "width"));
		height.bind(Bindings.selectDouble(movie.sceneProperty(), "height"));
		movie.setMediaPlayer(mediaplayer);
		mediaplayer.play();
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
