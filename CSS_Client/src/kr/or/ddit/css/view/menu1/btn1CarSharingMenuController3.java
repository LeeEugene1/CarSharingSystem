package kr.or.ddit.css.view.menu1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import kr.or.ddit.css.session.CarDetailSession;
import netscape.javascript.JSObject;

public class btn1CarSharingMenuController3 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane communityMainRoot;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbTitle1;

    @FXML
    private Label lbTitle11;

    @FXML
    private Label lbTitle12;

    @FXML
    private WebView webView2;

    @FXML
    private HBox mapView;

    @FXML
    private WebView webView;

    @FXML
    private HBox mapView1;

    @FXML
    private TextField tfAddr1;

    @FXML
    private TextField tfAddr2;

    @FXML
    private HBox mapView11;

    private JavaConnector javaConnector = new JavaConnector();
    private double pY = 0;
    private double pX = 0;
    // 지도 API
	private WebEngine webEngine1;
	// 우편 API
	private WebEngine webEngine2;
    
	@FXML
	void resetClicked(MouseEvent event) {
//		webEngine2 = webView2.getEngine();
    	
    	webEngine2.load("http://localhost:8081/SignUpTest/member/postcode.html");
	}
    
    public class JavaConnector{
		
	    public void addrFromJS(String addr,String sido) {
	        
	        try {
	        	tfAddr1.setText(addr);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    
	    public void pointFromJS(double pointY, double pointX) {
	    	pY = pointY;
	    	pX = pointX;
	    	
	    	webEngine1.load("https://map.kakao.com/link/map/"+pY+","+pX);
	    	
//	    	System.out.println(addr);
	    	
//	    	webEngine2 = webView2.getEngine();
//        	webEngine2.load("http://localhost:8081/SignUpTest/member/postcode.html");
//	    	
//	    	webEngine1 = webView.getEngine();
//        	webEngine1.load("https://map.kakao.com/link/map/"+pY+","+pX);
//	    	webEngine1.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
//	            if (Worker.State.SUCCEEDED == newValue) {
//	                JSObject window = (JSObject) webEngine1.executeScript("window");
//	                window.setMember("javaConnector", javaConnector);
//	            }
//	        });
	    }
		
	}

    @FXML
    void btnNext(MouseEvent event) throws IOException {
    	if(tfAddr1.getText().equals("")) {
    		alert("입력오류", "기본 주소값을 입력하세요.");
    	}else if(!tfAddr1.getText().equals("")) {
    		if(tfAddr2.getText().equals("")) {
    			alert("입력오류", "상세 주소값을 입력하세요.");
    		}else {
    			CarDetailSession.addr1 = tfAddr1.getText();
    	    	CarDetailSession.addr2 = tfAddr2.getText();
    	    	
    	    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn1CarSharingMenuInsert4.fxml"));
    	    	//Stage primaryStage = 
    	    	communityMainRoot.getChildren().setAll(pane);
    		}
    	}
    	
    }

    @FXML
    void btnPrevious(MouseEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn1CarSharingMenuInsert2.fxml"));
    	//Stage primaryStage = 
    	communityMainRoot.getChildren().setAll(pane);
    }

    @FXML
    void initialize() {
    	// 우편 API
    	webEngine2 = webView2.getEngine();
    	
    	webEngine2.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED == newValue) {
                JSObject window = (JSObject) webEngine2.executeScript("window");
                window.setMember("javaConnector", javaConnector);
            }
        });
    	
    	webEngine2.load("http://localhost:8081/SignUpTest/member/postcode.html");
    	
    	webEngine1 = webView.getEngine();
    	webEngine1.load("https://map.kakao.com/link/map/37.402056,127.108212");
    }
    
    public void alert(String head, String msg) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("경고");
		alert.setHeaderText(head);
		alert.setContentText(msg);
		
		alert.showAndWait();
	}
}


