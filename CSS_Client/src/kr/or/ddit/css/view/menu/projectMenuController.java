package kr.or.ddit.css.view.menu;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import kr.or.ddit.css.service.login.ILoginService;
import kr.or.ddit.css.service.signUp.ISignUpService;
import kr.or.ddit.css.session.AdminLoginSession;
import kr.or.ddit.css.session.LoginSession;

public class projectMenuController implements Initializable {
    @FXML
    private AnchorPane subFrame;

    @FXML
    private AnchorPane pnlRoot;

    @FXML
    private MediaView movie;
    
    private Label label;
    public Label lblLink;
    public Button btnHospital;			//1단메뉴 이용안내
    public Button btnHelp;				//1단메뉴 요금안내
    public Button btnEvent;				//1단메뉴 이벤트
    private Button btnCarSharing;		//1단메뉴 카셰어링
    private Button btnCarPairing;		//1단메뉴 카페어링
    private Button btnCommunity;		//1단메뉴 커뮤니티
    public Button btnEvent1;			//1단메뉴 고객센터

    public VBox vbSidebarMain;			//1단메뉴바
    
    public VBox vbHospitalSidebar;		//2단메뉴 이용안내
    public VBox vbHelpSidebar;			//2단메뉴 요금안내
    public VBox vbEvent;				//2단메뉴 이벤트
    public VBox vbEvent1;				//2단메뉴 고객센터
    private VBox vbCarSharing;			//2단메뉴 카셰어링
    private VBox vbCarPairing;			//2단메뉴 카페어링
    private VBox vbCommunity;			//2단메뉴 커뮤니티
    
    private TranslateTransition openNav;
    private TranslateTransition closeNav;
    private TranslateTransition closeFastNav;
   
    private TranslateTransition openNav2;
    private TranslateTransition closeNav2;
    private TranslateTransition closeFastNav2;
    
    private TranslateTransition openNav3;
    private TranslateTransition closeNav3;
    private TranslateTransition closeFastNav3;
    
    private TranslateTransition openNav4;
    private TranslateTransition closeNav4;
    private TranslateTransition closeFastNav4;
    
    private TranslateTransition openNavRight1;
    private TranslateTransition closeNavRight1;
    private TranslateTransition closeFastNavRight1;
    @FXML
    void MessageButtonAction(ActionEvent event) {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../chat/ClientMain.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.initStyle(StageStyle.TRANSPARENT.DECORATED);
			stage.setTitle("Message Window");
			stage.setScene(new Scene(root1));
			stage.setResizable(false); 
			stage.show();

		} catch (Exception e) {
			System.out.println("cant load new window");
		}
    }
    
    @FXML
    void btn2CarInsurenceClicked(MouseEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu2/btn2CarInsurence.fxml"));
    	subFrame.getChildren().setAll(pane);
    	
    }

    @FXML
    void btn2CarPriceClicked(MouseEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu2/btn2CarPrice.fxml"));
    	subFrame.getChildren().setAll(pane);
    	
    }

    @FXML
    void btn2UseInfoCarPairingClicked(MouseEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu2/btn2CarPairingMenu.fxml"));
    	subFrame.getChildren().setAll(pane);
    	btnHospitalHover();
    }

    @FXML
    void btn2UseInfoCarRecommandationClicked(MouseEvent event) {
    	btnHospitalHover();
    }

    @FXML
    void btn2UseInfoCarSharingClicked(MouseEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu2/btn2CarSharingMenu.fxml"));
    	subFrame.getChildren().setAll(pane);
    	btnHospitalHover();
    }

    @FXML
    void btn2UseInfoGradeInfoClicked(MouseEvent event) throws IOException {
    	//2단메뉴 등급정보 오픈소스 
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu2/btn2GradeInfo.fxml"));
    	subFrame.getChildren().setAll(pane);
    	
    	btnHospitalHover();
    }
    
    @FXML
    void btn2CouponClicked(MouseEvent event) throws IOException {	// 쿠폰창으로 이동 메서드
    	//2단메뉴 쿠폰 오픈소스 
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu2/btn2Coupon.fxml"));
    	subFrame.getChildren().setAll(pane);
    }
    
    @FXML
    void btn2EventClicked(MouseEvent event) throws IOException {	// 이벤트창으로 이동 메서드
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu2/btn2Event.fxml"));
    	subFrame.getChildren().setAll(pane);
    }
    
//    @FXML
//    void btnHelpHover(MouseEvent event) {
//    	
//    }

//    @FXML
//    void btnHospitalHover(MouseEvent event) {
//
//    }

    @FXML
    void btnIcons8_Click(ActionEvent event) {

    }

//    @FXML
//    void handleButtonAction(ActionEvent event) {
//
//    }

    @FXML
    void hideAllSliderMenus(MouseEvent event) {  //모든메뉴들을 클릭했을때들어가는 클릭이벤트(마이페이지제외)	
    	hospitalSideBarHide();
        helpSidebarHide();
        btn3SideBarHide();
        btn4SideBarHide();
    }
    
    @FXML
    void btn1LogoClicked(MouseEvent event) throws IOException {
    	//메인페이지 돌아가는 소스
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("projectMenu.fxml"));
    	//Stage primaryStage = 
    	pnlRoot.getScene().setRoot(pane);
    }
    
    @FXML
    void btn1CarPairingClicked(MouseEvent event) throws IOException {
    	//1단메뉴 카페어링 오픈소스
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu1/btn1CarPairingMenu.fxml"));
    	subFrame.getChildren().setAll(pane);
    }

    @FXML
    void btn1CarSharingClicked(MouseEvent event) throws IOException {
    	//1단메뉴 카셰어링 오픈소스 
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu1/btn1CarSharingMenu.fxml"));
    	subFrame.getChildren().setAll(pane);
    }
    
    @FXML
    void btn1CommunityClicked(MouseEvent event) throws IOException {
    	//1단메뉴 커뮤니티 오픈소스
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu1/btn1CommunityMenu.fxml"));
    	subFrame.getChildren().setAll(pane);
    }
    
    @FXML
    void loadLogin(ActionEvent event) throws IOException {	// 로그아웃 메서드
    	AdminLoginSession.adminSession = null;
    	LoginSession.session = null;
    	
    	//로그인페이지 열리게하는 소스
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../login/projectLogin.fxml"));
    	//Stage primaryStage = 
    	pnlRoot.getScene().setRoot(pane);
    }
    
    @FXML
    void btn2CarSharingClicked(MouseEvent event) throws IOException {
    	//2단메뉴 카셰어링 오픈소스
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu2/btn2CarSharingMenu.fxml"));
    	subFrame.getChildren().setAll(pane);
    	//subFrame.getScene().setRoot(pane);
   
    }
    
    @FXML
    void btn2CustomerServiceNoticeClicked(MouseEvent event) throws IOException {
//    	//2단메뉴 고객센터 오픈소스 - 공지사항
    	
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu2/btn2CustomerServiceNoticeMenu.fxml"));
    	subFrame.getChildren().setAll(pane);
    	
    }
    
    @FXML
    void btn2CustomerServiceQnAClicked(MouseEvent event) throws IOException {
//    	//2단메뉴 고객센터 오픈소스 - QnA
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu2/btn2CustomerServiceQnAMenu.fxml"));
    	subFrame.getChildren().setAll(pane);
    	
    }
    
    @FXML
    void btn2CustomerServiceBlackListClicked(MouseEvent event) throws IOException {
//    	//2단메뉴 고객센터 오픈소스 - 신고
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu2/btn2CustomerServiceBlackListMenu.fxml"));
    	subFrame.getChildren().setAll(pane);
    	
    }
    
    @FXML
    void btn3GradeConfirmationClicked(MouseEvent event) throws IOException {
//    	//3단메뉴 등급조회 오픈소스
    	if(AdminLoginSession.adminSession==null) {
	    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu3/btn3GradeConfirmation.fxml"));
	    	subFrame.getChildren().setAll(pane);
	    	btnRightHover();
    	}else {
    		alert("오류","회원의 등급조회 창입니다.");
    		return;
    	}
    }
    

    @FXML
    void btn3DriveLicenseCertificationClicked(MouseEvent event) throws IOException {
    	if(AdminLoginSession.adminSession==null) {
	    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../license/license.fxml"));
	    	subFrame.getChildren().setAll(pane);
	    	btnRightHover();
    	}else {
    		alert("오류","회원의 운전면허 조회 창입니다.");
    		return;
    	}
    }
    
    @FXML
    void btn3CardClicked(MouseEvent event) throws IOException {
    	if(LoginSession.session!=null) {
	    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../card/cardView.fxml"));
	    	subFrame.getChildren().setAll(pane);
	    	btnRightHover();
    	}
    }

    @FXML
    void btn3PersonalInfoClicked(MouseEvent event) throws IOException {
//    	3단메뉴 개인정보 오픈소스
    	if(AdminLoginSession.adminSession==null) {
	    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu3/btn3PersonalInfo.fxml"));
	    	subFrame.getChildren().setAll(pane);
	    	btnRightHover();
    	}else {
    		alert("오류","회원의 정보 조회/수정 창입니다.");
    		return;
    	}
    }

    @FXML
    void btn3PersonaluseClicked(MouseEvent event) {
    	btnRightHover();
    }

    @FXML
    void btn3SignOutClicked(MouseEvent event) {	// 회원탈퇴 메서드
    	if(AdminLoginSession.adminSession==null) {
	    	try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("projectSignOut.fxml"));
				Parent root2 = (Parent) fxmlLoader.load();
				
				projectSignOutController ctl = fxmlLoader.getController();
				
				ctl.setPrimaryStage((Stage)pnlRoot.getScene().getWindow());
				
				Stage stage = new Stage();
				stage.initStyle(StageStyle.TRANSPARENT.DECORATED);
				stage.setTitle("SignOut Window");
				stage.setScene(new Scene(root2));
				stage.show();
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("cant load new window");
			}
	    	
	    	btnRightHover();
    	}else {
    		return;
    	}
    }
    
    @FXML
    void btn3UseListClicked(ActionEvent event) throws IOException {
    	
//    	3단메뉴 개인정보 오픈소스
    	if(AdminLoginSession.adminSession==null) {
	    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu3/btn3useInfo.fxml"));
	    	subFrame.getChildren().setAll(pane);
	    	btnRightHover();
    	}else {
    		alert("오류","회원의 이용내역 조회 창입니다.");
    		return;
    	}
    }

//    @FXML
//    void btn3alarmSettingClicked(MouseEvent event) {
//    	btnRightHover();
//    }

    
    /////////////////우측메뉴바
    
    @FXML
    private VBox vbSidebarMain2;

    @FXML
    private Button btnRight1;

    @FXML
    private Button btnRight2;

    @FXML
    private Button btnRight3;
    
    @FXML
    private VBox vbRight1;
    
//    @FXML
//    void btnRightHover(MouseEvent event) {
//
//    }
    ////////////////
    
    @FXML
    void initialize() {
       
    }

    private ILoginService loginService;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Registry reg;
		try {
			reg = LocateRegistry.getRegistry("localhost",8899);
			loginService = (ILoginService) reg.lookup("login");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		String VUrl = "file:/D:/A_TeachingMaterial/4.MiddleProject/workspace/CSS_Client/src/img/McLaren.mp4";
		Media media = new Media(VUrl);
		MediaPlayer mediaplayer = new MediaPlayer(media);
		  DoubleProperty width =movie.fitWidthProperty();
	      DoubleProperty height =movie.fitHeightProperty();
	      width.bind(Bindings.selectDouble(movie.sceneProperty(), "width"));
	      height.bind(Bindings.selectDouble(movie.sceneProperty(), "height"));
//		movie.setFitWidth(1900);
//		movie.setFitHeight(1000);
		movie.setMediaPlayer(mediaplayer);
		mediaplayer.play();
		
		
		//영상가운데정렬
		
	        openNav = new TranslateTransition(Duration.millis(100), vbHospitalSidebar);
	        openNav.setToX(vbHospitalSidebar.getTranslateX()-vbHospitalSidebar.getWidth());
	        closeNav = new TranslateTransition(Duration.millis(100), vbHospitalSidebar);
	        closeFastNav = new TranslateTransition(Duration.millis(.1), vbHospitalSidebar);

	        openNav2 = new TranslateTransition(Duration.millis(100), vbHelpSidebar);
	        openNav2.setToX(vbHelpSidebar.getTranslateX()-vbHelpSidebar.getWidth());
	        closeNav2 = new TranslateTransition(Duration.millis(100), vbHelpSidebar);
	        closeFastNav2 = new TranslateTransition(Duration.millis(.1), vbHelpSidebar);

	        openNav3 = new TranslateTransition(Duration.millis(100), vbEvent);
	        openNav3.setToX(vbEvent.getTranslateX()-vbEvent.getWidth());
	        closeNav3 = new TranslateTransition(Duration.millis(100), vbEvent);
	        closeFastNav3 = new TranslateTransition(Duration.millis(.1), vbEvent);
	        
	        openNav4 = new TranslateTransition(Duration.millis(100), vbEvent1);
	        openNav4.setToX(vbEvent1.getTranslateX()-vbEvent1.getWidth());
	        closeNav4 = new TranslateTransition(Duration.millis(100), vbEvent1);
	        closeFastNav4 = new TranslateTransition(Duration.millis(.1), vbEvent1);
	        
	        openNavRight1 = new TranslateTransition(Duration.millis(100), vbRight1);
	        openNavRight1.setToX(vbRight1.getTranslateX()-vbRight1.getWidth());
	        closeNavRight1 = new TranslateTransition(Duration.millis(100), vbRight1);
	        closeFastNavRight1 = new TranslateTransition(Duration.millis(.1), vbRight1);
	        
	        btnHospital.setOnAction((ActionEvent evt) -> {
	            btnHospitalHover();
	        });        
	        
	        btnHelp.setOnAction((ActionEvent evt) -> {
	           btnHelpHover();
	        });
	        
	        btnEvent.setOnAction((ActionEvent evt) -> {
		           btn3Hover();
		        });
	        
	        btnEvent1.setOnAction((ActionEvent evt) -> {
		           btn4Hover();
		        });
	        
	        btnRight1.setOnAction((ActionEvent evt) -> {
	        		btnRightHover();
		        });
	               
	        Platform.runLater(new Runnable() {
	            @Override
	            public void run() {
	                closeFastNav.setToX(-(vbHospitalSidebar.getWidth()));
	                closeFastNav.play();
	                closeFastNav2.setToX(-(vbHelpSidebar.getWidth()));
	                closeFastNav2.play();   
	                closeFastNav3.setToX(-(vbHelpSidebar.getWidth()));
	                closeFastNav3.play();  
	                closeFastNav4.setToX(-(vbHelpSidebar.getWidth()));
	                closeFastNav4.play(); 
	                
	                closeFastNavRight1.setToY(-(vbRight1.getWidth()));
	                closeFastNavRight1.play();  
	            }
	        });  
	    }   
	    
	    public void hideAllSliderMenus(){
	        hospitalSideBarHide();
	        helpSidebarHide();
	        btn3SideBarHide();
	        btn4SideBarHide();
	        //btnCarPairingHide();
	        //btnCarSharingHide();
	        //btnCommunityHide();
	        
	        btnRight1Hide();
	    }
	    //////////////////////////////////////////////
    
	    public void btn4SideBarHide() {
	        btnEvent1.getStyleClass().remove("sidebar-button-active");
	        btnEvent1.getStyleClass().add("sidebar-button");
	        closeNav4.setToX(-(vbEvent1.getWidth()));
	        closeNav4.play();
	    }    
	    
	    public void btn4Hover() {
	        helpSidebarHide();
	        hospitalSideBarHide();
	        btn3SideBarHide();
	            
	        if ((vbEvent1.getTranslateX()) == -(vbEvent1.getWidth()) ) {
	        	btnEvent1.getStyleClass().remove("sidebar-button");
	        	btnEvent1.getStyleClass().add("sidebar-button-active");
	            openNav4.play(); 
	        } else {
	        	btn4SideBarHide();
	         }
	    } 
	    
	    public void btn3SideBarHide() {
	        btnEvent.getStyleClass().remove("sidebar-button-active");
	        btnEvent.getStyleClass().add("sidebar-button");
	        closeNav3.setToX(-(vbEvent.getWidth()));
	        closeNav3.play();
	    }    
	    
	    public void btn3Hover() {
	        helpSidebarHide();
	        hospitalSideBarHide();
	        btn4SideBarHide();
	            
	        if ((vbEvent.getTranslateX()) == -(vbEvent.getWidth()) ) {
	        	btnEvent.getStyleClass().remove("sidebar-button");
	        	btnEvent.getStyleClass().add("sidebar-button-active");
	            openNav3.play(); 
	        } else {
	        	btn3SideBarHide();
	          }
	    }    
	    
	    ///////////////////////////////////////////////////
	    public void hospitalSideBarHide() {
	        btnHospital.getStyleClass().remove("sidebar-button-active");
	        btnHospital.getStyleClass().add("sidebar-button");
	        closeNav.setToX(-(vbHospitalSidebar.getWidth()));
	        closeNav.play();
	    }    
	    
	    public void btnHospitalHover() {
	        helpSidebarHide();
	        btn3SideBarHide();
	        btn4SideBarHide();
	            
	        if ((vbHospitalSidebar.getTranslateX()) == -(vbHospitalSidebar.getWidth()) ) {
	            btnHospital.getStyleClass().remove("sidebar-button");
	            btnHospital.getStyleClass().add("sidebar-button-active");
	            openNav.play(); 
	        } else {
	            hospitalSideBarHide();
	          }
	    }    

	    public void helpSidebarHide() {
	        btnHelp.getStyleClass().remove("sidebar-button-active");
	        btnHelp.getStyleClass().add("sidebar-button");
	        closeNav2.setToX(-(vbHelpSidebar.getWidth()));
	        closeNav2.play();
	    }
	    
	    public void btnHelpHover() {        
	        hospitalSideBarHide();
	        btn3SideBarHide();
	        btn4SideBarHide();

	       if ((vbHelpSidebar.getTranslateX()) == -(vbHelpSidebar.getWidth()) ) {
	            btnHelp.getStyleClass().remove("sidebar-button");
	            btnHelp.getStyleClass().add("sidebar-button-active");
	            openNav2.play(); 
	        } else {
	            helpSidebarHide();
	          }
	    }
	    
	    public void btnRight1Hide() {
	    	btnRight1.getStyleClass().remove("sidebar-button-active");
	    	btnRight1.getStyleClass().add("sidebar-button");
		    closeNavRight1.setToX(-(vbRight1.getWidth()));
		    closeNavRight1.play();
	    }
	    
	    public void btnRightHover() {
	            
	        if ((vbRight1.getTranslateX()) == -(vbRight1.getWidth()) ) {
	        	btnRight1.getStyleClass().remove("sidebar-button");
	        	btnRight1.getStyleClass().add("sidebar-button-active");
	            openNavRight1.play(); 
	        } else {
	        	btnRight1Hide();
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
